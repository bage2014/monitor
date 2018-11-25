
DROP TABLE statistics_year_session
DROP TABLE statistics_month_session
DROP TABLE statistics_day_session
DROP TABLE statistics_hour_session
DROP TABLE statistics_minute_session

-- 直接获取 ？？？？
-------------------------------- session请求统计信息表  -------------------------------------------

-- statistics_year_session -- 2017-05-02
CREATE TABLE statistics_year_session (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  userCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_year_session_queryTime ON statistics_year_session (queryTime); 

-- statistics_month_session -- 2017-05-02
CREATE TABLE statistics_month_session (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  userCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_month_session_queryTime ON statistics_month_session (queryTime); 

-- statistics_day_session -- 2017-05-02
CREATE TABLE statistics_day_session (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  userCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_day_session_queryTime ON statistics_day_session (queryTime); 

-- statistics_hour_session -- 2017-05-02
CREATE TABLE statistics_hour_session (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  userCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_hour_session_queryTime ON statistics_hour_session (queryTime); 

-- statistics_minute_session -- 2017-05-02
CREATE TABLE statistics_minute_session (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  userCount FLOAT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE INDEX index_statistics_minute_session_queryTime ON statistics_minute_session (queryTime); 
