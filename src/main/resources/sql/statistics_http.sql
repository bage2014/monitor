
DROP TABLE statistics_year_http
DROP TABLE statistics_month_http
DROP TABLE statistics_day_http
DROP TABLE statistics_hour_http
DROP TABLE statistics_minute_http


-------------------------------- Http请求统计信息表  -------------------------------------------

-- statistics_year_http -- 2017-05-02
CREATE TABLE statistics_year_http (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits INT DEFAULT 0,
  durationsSum INT DEFAULT 0,
  durationsSquareSum INT DEFAULT 0,  
  maximum INT DEFAULT 0,
  cpuTimeSum INT DEFAULT 0,
  systemErrors INT DEFAULT 0,
  responseSizesSum INT DEFAULT 0,
  childHits INT DEFAULT 0,
  childDurationsSum INT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_year_http_queryTime ON statistics_year_http (queryTime); 

-- statistics_month_http -- 2017-05-02
CREATE TABLE statistics_month_http (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits INT DEFAULT 0,
  durationsSum INT DEFAULT 0,
  durationsSquareSum INT DEFAULT 0,  
  maximum INT DEFAULT 0,
  cpuTimeSum INT DEFAULT 0,
  systemErrors INT DEFAULT 0,
  responseSizesSum INT DEFAULT 0,
  childHits INT DEFAULT 0,
  childDurationsSum INT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_month_http_queryTime ON statistics_month_http (queryTime); 

-- statistics_day_http -- 2017-05-02
CREATE TABLE statistics_day_http (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits INT DEFAULT 0,
  durationsSum INT DEFAULT 0,
  durationsSquareSum INT DEFAULT 0,  
  maximum INT DEFAULT 0,
  cpuTimeSum INT DEFAULT 0,
  systemErrors INT DEFAULT 0,
  responseSizesSum INT DEFAULT 0,
  childHits INT DEFAULT 0,
  childDurationsSum INT DEFAULT 0,
  
  UNIQUE KEY id (id)
  
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_day_http_queryTime ON statistics_day_http (queryTime); 

-- statistics_hour_http -- 2017-05-02
CREATE TABLE statistics_hour_http (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits INT DEFAULT 0,
  durationsSum INT DEFAULT 0,
  durationsSquareSum INT DEFAULT 0,  
  maximum INT DEFAULT 0,
  cpuTimeSum INT DEFAULT 0,
  systemErrors INT DEFAULT 0,
  responseSizesSum INT DEFAULT 0,
  childHits INT DEFAULT 0,
  childDurationsSum INT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_hour_http_queryTime ON statistics_hour_http (queryTime); 


-- statistics_minute_http -- 2017-05-02
CREATE TABLE statistics_minute_http (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
 
  NAME VARCHAR(255) DEFAULT "",
  iid VARCHAR(255) DEFAULT "",
  hits INT DEFAULT 0,
  durationsSum INT DEFAULT 0,
  durationsSquareSum INT DEFAULT 0,  
  maximum INT DEFAULT 0,
  cpuTimeSum INT DEFAULT 0,
  systemErrors INT DEFAULT 0,
  responseSizesSum INT DEFAULT 0,
  childHits INT DEFAULT 0,
  childDurationsSum INT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_minute_http_queryTime ON statistics_minute_http (queryTime); 


-------------------------------- Http请求统计信息表  -------------------------------------------
