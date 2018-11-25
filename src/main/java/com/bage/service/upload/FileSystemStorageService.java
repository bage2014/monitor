package com.bage.service.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bage.constraints.UploadConstrants;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService() {
        this.rootLocation = Paths.get(UploadConstrants.UPLOAD_UPLOAD_DIR);
    }

    @Override
    public void store(MultipartFile file) {
        try {
        	
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            String path =  this.getClass().getResource("/").getPath() + this.rootLocation;
            path = path.replaceFirst("/", "");
            File tir = new File(path);
            if(!tir.exists()){
            	tir.mkdirs();
            }
            path = path + "/" + file.getOriginalFilename();
            System.out.println("path:" + path);
            Files.copy(file.getInputStream(),Paths.get(path) );
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	System.out.println(e.getCause());
            System.out.println("Failed to store file " + file.getOriginalFilename());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            System.out.println("Failed to read stored files");
        }
		return null;

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            
        } catch (MalformedURLException e) {
            System.out.println("Failed to read stored files");
        }
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            System.out.println("Could not initialize storage");
        }
    }
}
