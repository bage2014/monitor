
DROP TABLE statistics_year_jdbc
DROP TABLE statistics_month_jdbc
DROP TABLE statistics_day_jdbc
DROP TABLE statistics_hour_jdbc
DROP TABLE statistics_minute_jdbc

-------------------------------- jdbc请求统计信息表  -------------------------------------------

-- statistics_year_jdbc -- 2017-05-02
CREATE TABLE statistics_year_jdbc (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedConnectionCount FLOAT DEFAULT 0,
  maxConnectionCount FLOAT DEFAULT 0,
  activeConnectionCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_year_jdbc_queryTime ON statistics_year_jdbc (queryTime); 

-- statistics_month_jdbc -- 2017-05-02
CREATE TABLE statistics_month_jdbc (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedConnectionCount FLOAT DEFAULT 0,
  maxConnectionCount FLOAT DEFAULT 0,
  activeConnectionCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_month_jdbc_queryTime ON statistics_month_jdbc (queryTime); 

-- statistics_day_jdbc -- 2017-05-02
CREATE TABLE statistics_day_jdbc (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedConnectionCount FLOAT DEFAULT 0,
  maxConnectionCount FLOAT DEFAULT 0,
  activeConnectionCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_day_jdbc_queryTime ON statistics_day_jdbc (queryTime); 

-- statistics_hour_jdbc -- 2017-05-02
CREATE TABLE statistics_hour_jdbc (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedConnectionCount FLOAT DEFAULT 0,
  maxConnectionCount FLOAT DEFAULT 0,
  activeConnectionCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_hour_jdbc_queryTime ON statistics_hour_jdbc (queryTime); 

-- statistics_minute_jdbc -- 2017-05-02
CREATE TABLE statistics_minute_jdbc (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  usedConnectionCount FLOAT DEFAULT 0,
  maxConnectionCount FLOAT DEFAULT 0,
  activeConnectionCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_minute_jdbc_queryTime ON statistics_minute_jdbc (queryTime); 
