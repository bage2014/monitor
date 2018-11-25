
--------------------  准备工作  -----------------------------
-- 创建数据库
create database luculent;

-- 将数据库权限给graduation用户
GRANT ALL ON luculent.* TO 'graduation'@'localhost';

----------------------- 测试  --------------------------------------

-- 创建用户表
CREATE TABLE tbuser (
  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  account VARCHAR(255) DEFAULT '',
  username VARCHAR(255) DEFAULT '',
  PASSWORD VARCHAR(255) DEFAULT '',
  icon VARCHAR(1024) DEFAULT '',
  roleId BIGINT(16) DEFAULT 0,
  role VARCHAR(255) DEFAULT '',
  queryTime VARCHAR(255) DEFAULT '',
  deleteStatus CHAR DEFAULT '0',
  des VARCHAR(1024) DEFAULT '',
  
  UNIQUE KEY id (id),
  UNIQUE KEY account (account)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

----------------------- 建表 --------------------------------------

-- 创建appinfo表  -- 2017-04-06
CREATE TABLE appinfo (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  delete_status char DEFAULT 0,
  
  appurl VARCHAR(255) DEFAULT "",
  appip varchar(16) default "localhost",
  appport varchar(8) default "8080",
  appname varchar(64) default "",
  appdesc varchar(1024) default "",  
  
  city varchar(1024) default "",  
  lan float default 0,  
  lat float default 0,  
  
  UNIQUE KEY id (id),
  UNIQUE KEY appurl (appurl)
  
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 创建appinfo表  -- 2017-04-06
CREATE TABLE appheathyinfo (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  appid BIGINT(16) NOT NULL DEFAULT 0,
  queryTime VARCHAR(32) NOT NULL DEFAULT "",
  
  ahttp float DEFAULT 100,
  amemory float DEFAULT 100,
  asession float DEFAULT 100,
  ajdbc float DEFAULT 100,
  asql float DEFAULT 100,
  adisk float DEFAULT 100,
  
  UNIQUE KEY id (id)
  
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- 创建memoryInformations表 -- 2017-04-06
CREATE TABLE memoryInformations (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  querytime TIMESTAMP NOT NULL DEFAULT NOW(),
  appid BIGINT(16) UNSIGNED NOT NULL ,
  delete_status char DEFAULT 0,
  
  usedMemory int DEFAULT 0,
  maxMemory int DEFAULT 0,
  usedPermGen int DEFAULT 0,
  maxPermGen int DEFAULT 0,
  usedNonHeapMemory int DEFAULT 0,
  usedBufferedMemory int DEFAULT 0,
  loadedClassesCount int DEFAULT 0,
  garbageCollectionTimeMillis int DEFAULT 0,
  usedPhysicalMemorySize int DEFAULT 0,
  usedSwapSpaceSize int DEFAULT 0,
  memoryDetails VARCHAR(1024) DEFAULT "",
  
  UNIQUE KEY id (id),
  CONSTRAINT fk_memory_ref_appinfo FOREIGN KEY (appid) REFERENCES appinfo(id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- 创建cpuInformations表 -- 2017-04-06
CREATE TABLE cpuInformations (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  querytime TIMESTAMP NOT NULL DEFAULT NOW(),
  appid BIGINT(16) UNSIGNED NOT NULL ,
  delete_status CHAR DEFAULT 0,
  
  os VARCHAR(255) DEFAULT "",
  HOST VARCHAR(255) DEFAULT "",
  javaVersion VARCHAR(255) DEFAULT "",
  jvmVersion VARCHAR(255) DEFAULT "",
  jvmArguments VARCHAR(1024) DEFAULT "",  
  freeDiskSpaceInTemp INT DEFAULT 0,
  contextPath VARCHAR(64) DEFAULT "",
  serverInfo VARCHAR(255) DEFAULT "",
  pid VARCHAR(64) DEFAULT "",
  startDate VARCHAR(64) DEFAULT "",
  
  maxConnectionCount INT DEFAULT 0,
  peakThreadCount INT DEFAULT 0,
  processCpuTimeMillis INT DEFAULT 0,
  sessionAgeSum INT DEFAULT 0,
  sessionCount INT DEFAULT 0,
  systemCpuLoad VARCHAR(64) DEFAULT "",
  threadCount INT DEFAULT 0,
  totalStartedThreadCount INT DEFAULT 0,
  transactionCount INT DEFAULT 0,
  usedConnectionCount INT DEFAULT 0,
  
  UNIQUE KEY id (id),
  CONSTRAINT fk_cpu_ref_appinfo FOREIGN KEY (appid) REFERENCES appinfo(id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 创建sqlInformations表 -- 2017-04-10
CREATE TABLE sqlInformations (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  querytime TIMESTAMP NOT NULL DEFAULT NOW(),
  appid BIGINT(16) UNSIGNED NOT NULL ,
  delete_status CHAR DEFAULT 0,
  
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
  
  UNIQUE KEY id (id),
  CONSTRAINT fk_sql_ref_appinfo FOREIGN KEY (appid) REFERENCES appinfo(id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 创建sessionInformations表 -- 2017-04-25
CREATE TABLE sessionInformations (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  querytime TIMESTAMP NOT NULL DEFAULT NOW(),
  appid BIGINT(16) UNSIGNED NOT NULL ,
  delete_status CHAR DEFAULT 0,
  
  iid VARCHAR(255) DEFAULT "",
  lastAccess VARCHAR(255) DEFAULT "",
  age VARCHAR(255) DEFAULT 0,
  expirationDate VARCHAR(255) DEFAULT "", 
  attributeCount INT DEFAULT 0,
  SERIALIZABLE VARCHAR(8) DEFAULT "",
  country INT DEFAULT 0, 
  remoteAddr VARCHAR(255) DEFAULT "",
  remoteUser VARCHAR(255) DEFAULT "",
  userAgent VARCHAR(255) DEFAULT "",
  serializedSize INT DEFAULT 0,
  
  UNIQUE KEY id (id),
  CONSTRAINT fk_session_ref_appinfo FOREIGN KEY (appid) REFERENCES appinfo(id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;





-- 创建httpInformations表 -- 2017-04-26
CREATE TABLE httpInformations (

  id BIGINT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  querytime TIMESTAMP NOT NULL DEFAULT NOW(),
  appid BIGINT(16) UNSIGNED NOT NULL ,
  delete_status CHAR DEFAULT 0,
  
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
  
  UNIQUE KEY id (id),
  CONSTRAINT fk_http_ref_appinfo FOREIGN KEY (appid) REFERENCES appinfo(id)
   
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
