package com.bage.controller.web.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bage.service.upload.StorageService;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	/*@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		
		model.addAttribute("files",
				storageService.loadAll()
				.map(path -> MvcUriComponentsBuilder
						.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
						.build().toString())
				.collect(Collectors.toList()));
		
		return "uploadForm";
	}*/
	
	@GetMapping("/list")
	public String list(Model model) throws IOException {
		
		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/save")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		System.out.println("********************save*******************");
		
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "uploadForm";
	}

	/*@ExceptionHandler(Exception.class)
	public ResponseEntity handleStorageFileNotFound(Exception exc) {
		System.out.println("********** Exception *****************");
		return ResponseEntity.notFound().build();
	}
*/
}
