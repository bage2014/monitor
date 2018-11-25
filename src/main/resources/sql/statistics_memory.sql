
DROP TABLE statistics_year_memory
DROP TABLE statistics_month_memory
DROP TABLE statistics_day_memory
DROP TABLE statistics_hour_memory
DROP TABLE statistics_minute_memory


-------------------------------- 内存统计信息表  -------------------------------------------

-- statistics_year_memory -- 2017-04-28
CREATE TABLE statistics_year_memory (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedMemory FLOAT DEFAULT 0,
  maxMemory FLOAT DEFAULT 0,
  usedPermGen FLOAT DEFAULT 0,
  maxPermGen FLOAT DEFAULT 0,
  usedNonHeapMemory FLOAT DEFAULT 0,
  
  usedBufferedMemory FLOAT DEFAULT 0,
  loadedClassesCount FLOAT DEFAULT 0,
  garbageCollectionTimeMillis FLOAT DEFAULT 0,
  usedPhysicalMemorySize FLOAT DEFAULT 0,
  usedSwapSpaceSize FLOAT DEFAULT 0,
 
  committedVirtualMemory FLOAT DEFAULT 0,
  freePhysicalMemory FLOAT DEFAULT 0,
  totalPhysicalMemory FLOAT DEFAULT 0,
  freeSwapSpace FLOAT DEFAULT 0,
  totalSwapSpace FLOAT DEFAULT 0,

  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_year_memory_queryTime ON statistics_year_memory (queryTime); 

-- statistics_month_memory -- 2017-04-28
CREATE TABLE statistics_month_memory (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedMemory FLOAT DEFAULT 0,
  maxMemory FLOAT DEFAULT 0,
  usedPermGen FLOAT DEFAULT 0,
  maxPermGen FLOAT DEFAULT 0,
  usedNonHeapMemory FLOAT DEFAULT 0,
  
  usedBufferedMemory FLOAT DEFAULT 0,
  loadedClassesCount FLOAT DEFAULT 0,
  garbageCollectionTimeMillis FLOAT DEFAULT 0,
  usedPhysicalMemorySize FLOAT DEFAULT 0,
  usedSwapSpaceSize FLOAT DEFAULT 0,
  
  committedVirtualMemory FLOAT DEFAULT 0,
  freePhysicalMemory FLOAT DEFAULT 0,
  totalPhysicalMemory FLOAT DEFAULT 0,
  freeSwapSpace FLOAT DEFAULT 0,
  totalSwapSpace FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_month_memory_queryTime ON statistics_month_memory (queryTime); 

-- statistics_day_memory -- 2017-04-28
CREATE TABLE statistics_day_memory (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedMemory FLOAT DEFAULT 0,
  maxMemory FLOAT DEFAULT 0,
  usedPermGen FLOAT DEFAULT 0,
  maxPermGen FLOAT DEFAULT 0,
  usedNonHeapMemory FLOAT DEFAULT 0,
  
  usedBufferedMemory FLOAT DEFAULT 0,
  loadedClassesCount FLOAT DEFAULT 0,
  garbageCollectionTimeMillis FLOAT DEFAULT 0,
  usedPhysicalMemorySize FLOAT DEFAULT 0,
  usedSwapSpaceSize FLOAT DEFAULT 0,
  
  committedVirtualMemory FLOAT DEFAULT 0,
  freePhysicalMemory FLOAT DEFAULT 0,
  totalPhysicalMemory FLOAT DEFAULT 0,
  freeSwapSpace FLOAT DEFAULT 0,
  totalSwapSpace FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_day_memory_queryTime ON statistics_day_memory (queryTime); 

-- statistics_hour_memory -- 2017-04-28
CREATE TABLE statistics_hour_memory (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedMemory FLOAT DEFAULT 0,
  maxMemory FLOAT DEFAULT 0,
  usedPermGen FLOAT DEFAULT 0,
  maxPermGen FLOAT DEFAULT 0,
  usedNonHeapMemory FLOAT DEFAULT 0,
  
  usedBufferedMemory FLOAT DEFAULT 0,
  loadedClassesCount FLOAT DEFAULT 0,
  garbageCollectionTimeMillis FLOAT DEFAULT 0,
  usedPhysicalMemorySize FLOAT DEFAULT 0,
  usedSwapSpaceSize FLOAT DEFAULT 0,
  
  committedVirtualMemory FLOAT DEFAULT 0,
  freePhysicalMemory FLOAT DEFAULT 0,
  totalPhysicalMemory FLOAT DEFAULT 0,
  freeSwapSpace FLOAT DEFAULT 0,
  totalSwapSpace FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_hour_memory_queryTime ON statistics_hour_memory (queryTime); 


-- statistics_minute_memory -- 2017-04-28
CREATE TABLE statistics_minute_memory (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedMemory FLOAT DEFAULT 0,
  maxMemory FLOAT DEFAULT 0,
  usedPermGen FLOAT DEFAULT 0,
  maxPermGen FLOAT DEFAULT 0,
  usedNonHeapMemory FLOAT DEFAULT 0,
  
  usedBufferedMemory FLOAT DEFAULT 0,
  loadedClassesCount FLOAT DEFAULT 0,
  garbageCollectionTimeMillis FLOAT DEFAULT 0,
  usedPhysicalMemorySize FLOAT DEFAULT 0,
  usedSwapSpaceSize FLOAT DEFAULT 0,
  
  committedVirtualMemory FLOAT DEFAULT 0,
  freePhysicalMemory FLOAT DEFAULT 0,
  totalPhysicalMemory FLOAT DEFAULT 0,
  freeSwapSpace FLOAT DEFAULT 0,
  totalSwapSpace FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_minute_memory_queryTime ON statistics_minute_memory (queryTime); 
-------------------------------- 内存统计信息表  -------------------------------------------
