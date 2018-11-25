
DROP TABLE statistics_year_action
DROP TABLE statistics_month_action
DROP TABLE statistics_day_action
DROP TABLE statistics_hour_action
DROP TABLE statistics_minute_action


-------------------------------- action请求统计信息表  -------------------------------------------

-- statistics_year_action -- 2017-05-24
CREATE TABLE statistics_year_action (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  pgmNo VARCHAR(255) DEFAULT "",
  pgmId VARCHAR(255) DEFAULT "",
  pgmNam VARCHAR(255) DEFAULT "",
  modTyp VARCHAR(255) DEFAULT "",
  pathDsc VARCHAR(255) DEFAULT "",
  accessCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_year_action_queryTime ON statistics_year_action (queryTime); 

-- statistics_month_action -- 2017-05-24
CREATE TABLE statistics_month_action (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  pgmNo VARCHAR(255) DEFAULT "",
  pgmId VARCHAR(255) DEFAULT "",
  pgmNam VARCHAR(255) DEFAULT "",
  modTyp VARCHAR(255) DEFAULT "",
  pathDsc VARCHAR(255) DEFAULT "",
  accessCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_month_action_queryTime ON statistics_month_action (queryTime); 


-- statistics_day_action -- 2017-05-24
CREATE TABLE statistics_day_action (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  pgmNo VARCHAR(255) DEFAULT "",
  pgmId VARCHAR(255) DEFAULT "",
  pgmNam VARCHAR(255) DEFAULT "",
  modTyp VARCHAR(255) DEFAULT "",
  pathDsc VARCHAR(255) DEFAULT "",
  accessCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
  
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_day_action_queryTime ON statistics_day_action (queryTime); 


-- statistics_hour_action -- 2017-05-24
CREATE TABLE statistics_hour_action (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  pgmNo VARCHAR(255) DEFAULT "",
  pgmId VARCHAR(255) DEFAULT "",
  pgmNam VARCHAR(255) DEFAULT "",
  modTyp VARCHAR(255) DEFAULT "",
  pathDsc VARCHAR(255) DEFAULT "",
  accessCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_hour_action_queryTime ON statistics_hour_action (queryTime); 


-- statistics_minute_action -- 2017-05-24
CREATE TABLE statistics_minute_action (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  pgmNo VARCHAR(255) DEFAULT "",
  pgmId VARCHAR(255) DEFAULT "",
  pgmNam VARCHAR(255) DEFAULT "",
  modTyp VARCHAR(255) DEFAULT "",
  pathDsc VARCHAR(255) DEFAULT "",
  accessCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_minute_action_queryTime ON statistics_minute_action (queryTime); 


-------------------------------- action请求统计信息表  -------------------------------------------
