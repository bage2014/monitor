
DROP TABLE statistics_year_cpu
DROP TABLE statistics_month_cpu
DROP TABLE statistics_day_cpu
DROP TABLE statistics_hour_cpu
DROP TABLE statistics_minute_cpu

-- 直接获取 ？？？？
-------------------------------- cpu请求统计信息表  -------------------------------------------

-- statistics_year_cpu -- 2017-05-02
CREATE TABLE statistics_year_cpu (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  freeDiskSpaceInTemp INT DEFAULT 0,
 
  maxConnectionCount INT DEFAULT 0,
  peakThreadCount INT DEFAULT 0,
  processCpuTimeMillis INT DEFAULT 0,
  sessionAgeSum INT DEFAULT 0,
  sessionCount INT DEFAULT 0,
  threadCount INT DEFAULT 0,
  totalStartedThreadCount INT DEFAULT 0,
  transactionCount INT DEFAULT 0,
  usedConnectionCount INT DEFAULT 0,
  
  UNIQUE KEY id (id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
