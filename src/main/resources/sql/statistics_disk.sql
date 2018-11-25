
DROP TABLE statistics_year_disk
DROP TABLE statistics_month_disk
DROP TABLE statistics_day_disk
DROP TABLE statistics_hour_disk
DROP TABLE statistics_minute_disk

-------------------------------- disk统计信息表  -------------------------------------------

-- statistics_year_disk -- 2017-05-04
CREATE TABLE statistics_year_disk (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  freeDiskSpaceInTemp FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- statistics_month_disk -- 2017-05-04
CREATE TABLE statistics_month_disk (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  freeDiskSpaceInTemp FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- statistics_day_disk -- 2017-05-04
CREATE TABLE statistics_day_disk (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  freeDiskSpaceInTemp FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
  
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- statistics_hour_disk -- 2017-05-04
CREATE TABLE statistics_hour_disk (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  freeDiskSpaceInTemp FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- statistics_minute_disk -- 2017-05-04
CREATE TABLE statistics_minute_disk (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  freeDiskSpaceInTemp FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-------------------------------- disk统计信息表  -------------------------------------------
