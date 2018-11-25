# monitor
Spring Boot + MyBatis + AngularJS 的综合项目示例，一体化监控平台


## 整体结构 ##
![](https://github.com/bage2014/monitor/blob/master/src/main/resources/static/img/%E6%95%B4%E4%BD%93%E7%BB%93%E6%9E%84.png)

## 功能划分 ##
![](https://github.com/bage2014/monitor/blob/master/src/main/resources/static/img/%E5%8A%9F%E8%83%BD%E5%88%92%E5%88%86.png)

## 项目目录结构 ##
![](https://github.com/bage2014/monitor/blob/master/src/main/resources/static/img/%E9%A1%B9%E7%9B%AE%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.png)
#### 说明 ####
##### \src\main\java\com\bage 目录下 #####
- application 应用启动类，启动应用直接右键Run as -> Java Application 即可；访问路径：http://localhost:8080/monitor
- config 存放配置相关类
- constraints 存放常量类
- controller 存放接口API，请求请求入口
- dao 存放数据库操作类，原始JDBC操作数据库demo
- domain 存放实体对象类
- hbase 存放操作HBASE相关类，目前没有用到
- mapper 存放基于mybatis的数据库操作类
- schedule 存放定时任务
- service 业务类
- test 测试类(应该放在src/test/java包下，但是目前没有)
- 工具类
- webservice 存放webservice，demo程序，目前没有用到

##### \src\main\resources 目录下 #####
- mapper 存放数据库查询操作语句SQL的XML文件
- sql 初始化SQL、建表语句等
- static 静态资源文件，图片、样式等
- templates 页面HTML
- application.properties 项目spring boot 配置文件
- messages_zh.properties 中文国际化配置文件，同理，messages_en.properties为英文国际化配置文件

