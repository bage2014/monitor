
DROP TABLE statistics_year_sql
DROP TABLE statistics_month_sql
DROP TABLE statistics_day_sql
DROP TABLE statistics_hour_sql
DROP TABLE statistics_minute_sql


-------------------------------- sql请求统计信息表  -------------------------------------------

-- statistics_year_sql -- 2017-05-02
CREATE TABLE statistics_year_sql (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits FLOAT DEFAULT 0,
  durationsSum FLOAT DEFAULT 0,
  durationsSquareSum FLOAT DEFAULT 0,  
  maximum FLOAT DEFAULT 0,
  cpuTimeSum FLOAT DEFAULT 0,
  systemErrors FLOAT DEFAULT 0,
  responseSizesSum FLOAT DEFAULT 0,
  childHits FLOAT DEFAULT 0,
  childDurationsSum FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_year_sql_queryTime ON statistics_year_sql (queryTime); 

-- statistics_month_sql -- 2017-05-02
CREATE TABLE statistics_month_sql (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits FLOAT DEFAULT 0,
  durationsSum FLOAT DEFAULT 0,
  durationsSquareSum FLOAT DEFAULT 0,  
  maximum FLOAT DEFAULT 0,
  cpuTimeSum FLOAT DEFAULT 0,
  systemErrors FLOAT DEFAULT 0,
  responseSizesSum FLOAT DEFAULT 0,
  childHits FLOAT DEFAULT 0,
  childDurationsSum FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_month_sql_queryTime ON statistics_month_sql (queryTime); 


-- statistics_day_sql -- 2017-05-02
CREATE TABLE statistics_day_sql (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits FLOAT DEFAULT 0,
  durationsSum FLOAT DEFAULT 0,
  durationsSquareSum FLOAT DEFAULT 0,  
  maximum FLOAT DEFAULT 0,
  cpuTimeSum FLOAT DEFAULT 0,
  systemErrors FLOAT DEFAULT 0,
  responseSizesSum FLOAT DEFAULT 0,
  childHits FLOAT DEFAULT 0,
  childDurationsSum FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
  
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_day_sql_queryTime ON statistics_day_sql (queryTime); 


-- statistics_hour_sql -- 2017-05-02
CREATE TABLE statistics_hour_sql (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits FLOAT DEFAULT 0,
  durationsSum FLOAT DEFAULT 0,
  durationsSquareSum FLOAT DEFAULT 0,  
  maximum FLOAT DEFAULT 0,
  cpuTimeSum FLOAT DEFAULT 0,
  systemErrors FLOAT DEFAULT 0,
  responseSizesSum FLOAT DEFAULT 0,
  childHits FLOAT DEFAULT 0,
  childDurationsSum FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_hour_sql_queryTime ON statistics_hour_sql (queryTime); 


-- statistics_minute_sql -- 2017-05-02
CREATE TABLE statistics_minute_sql (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits FLOAT DEFAULT 0,
  durationsSum FLOAT DEFAULT 0,
  durationsSquareSum FLOAT DEFAULT 0,  
  maximum FLOAT DEFAULT 0,
  cpuTimeSum FLOAT DEFAULT 0,
  systemErrors FLOAT DEFAULT 0,
  responseSizesSum FLOAT DEFAULT 0,
  childHits FLOAT DEFAULT 0,
  childDurationsSum FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_minute_sql_queryTime ON statistics_minute_sql (queryTime); 


-------------------------------- sql请求统计信息表  -------------------------------------------
