
overview：
	
	application name：
		MonitoringPlatform
		
	background：
		监控平台要求利用Hadoop生态环境搭建大数据分析平台并进行部分数据的分析，监控平台搭建、
		配置及数据分析涉及知识较多，毕业设计题目有一定研究与开发难度。                                                
	 	在监控平台开发过程中要求按照公司的统一的开发环境以及开发模式进行。
	 	整体架构采用B/S架构，用J2EE技术体系搭建Web界面，用Hadoop生态环境搭建大数据分析平台。主要研究如下问题 
	 	1） 在J2EE技术下，如何对服务器的内存、CPU、磁盘IO的读写指标的获取 
	 	2）如何对J2EE企业级应用获取SQL执行、内存消耗、吞吐量等核心指标的获取 
	 	3）如何利用Hadoop 生态技术搭建大数据分析平台。
	
	relative library or technology：
		Javamelody + 
		hadoop + hbase + 
		spring boot + 
		materialize + jQuery
	
	
	
note：	
	
	external API of JavaMelody：
		http://localhost:8080/applicationName/monitoring?resource=help/api.html

	
MySQL：
	基本脚本：
	CREATE DATABASE graduation
	CREATE USER 'graduation'@'localhost' IDENTIFIED BY 'graduation';
	GRANT ALL ON graduation.* TO 'graduation'@'localhost'; 
	
	
常用注解：
	@SpringBootApplication:
		包含@Configuration、@EnableAutoConfiguration、@ComponentScan通常用在主类上。
	@Repository:
		用于标注数据访问组件，即DAO组件。
	@Service:
		用于标注业务层组件。 
	@RestController:
		用于标注控制层组件(如struts中的action)，包含@Controller和@ResponseBody。
	@ResponseBody：
		表示该方法的返回结果直接写入HTTP response body中，一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上
	@responsebody
		返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。比如异步获取json数据，加上@responsebody后，会直接返回json数据。
	@Component：
		泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
	@ComponentScan：
		组件扫描。个人理解相当于<context:component-scan>，如果扫描到有@Component@Controller@Service等这些注解的类，则把这些类注册为bean。
	@Configuration：
		指出该类是 Bean 配置的信息源，相当于XML中的<beans></beans>，一般加在主类上。
	@Bean:
		相当于XML中的<bean></bean>,放在方法的上面，而不是类，意思是产生一个bean,并交给spring管理。
	@EnableAutoConfiguration：
		让 Spring Boot 根据应用所声明的依赖来对 Spring 框架进行自动配置，一般加在主类上。
	@AutoWired:
		byType方式。把配置好的Bean拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。当加上（required=false）时，就算找不到bean也不报错。
	@Qualifier：
		当有多个同一类型的Bean时，可以用@Qualifier("name")来指定。与@Autowired配合使用
	@Resource(name="name",type="type")：
		没有括号内内容的话，默认byName。与@Autowired干类似的事。

	
	
	///////////////////////////////////////////////////////////////// 
	
	页面设计：
		login页面：
			添加阴影，加上透明背景
			去掉框框			
			
		对话框：
			剧中布局
			也要垂直居中
			OK、加上背景
			
		主页：
			图标小一点
			两边留点空隙
			
	
	
/////////////////////
var app = angular.module('IndexApp', []);	
app.controller('IndexController', function($scope, $http) {
	$scope.listTbodyThreads = [];
	$scope.listSessions = [];
});

function initThreadsTbody(){	
//	// Access whole scope
//	angular.element(myDomElement).scope();
//
//	// Access and change variable in scope
//	angular.element(myDomElement).scope().myVar = 5;
//	angular.element(myDomElement).scope().myArray.push(newItem);
//
//	// Update page to reflect changed variables
//	angular.element(myDomElement).scope().$apply();
}
	
	
	
自動省略:
class="truncate"

	
	
	
	
	
	
	
	
	
	