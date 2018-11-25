package com.bage.controller.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import com.bage.config.javamelody.monitor.MonitorConfig;
import com.bage.constraints.Citys;
import com.bage.constraints.LanguageConstriants;
import com.bage.constraints.ResCodeConstraints;
import com.bage.constraints.SessionConstraints;
import com.bage.constraints.ThemeConstraints;
import com.bage.domain.module.User;
import com.bage.domain.monitor.AppInfo;
import com.bage.domain.response.EmptyResponseParam;
import com.bage.domain.response.ResponseParam;
import com.bage.domain.statistics.AppHeathyInfoStatistics;
import com.bage.service.module.UserService;
import com.bage.service.monitor.AppInfoService;
import com.bage.service.statistics.AppHeathyInfoStatisticsService;
import com.bage.utils.JsonUtils;
import com.bage.utils.RandomUtils;
import com.bage.utils.TimeUtils;

import net.bull.javamelody.MonitoredWithSpring;

@Controller
@RequestMapping("/index")
@MonitoredWithSpring
@EnableAutoConfiguration
public class IndexController {

	@Autowired
	LocaleResolver localeResolver;
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private AppHeathyInfoStatisticsService appHeathyInfoStatisticsMapper;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {

		// 设置初始化配置

		// 设置监控平台的 ip地址、端口、应用程序名称
		request.getSession().setAttribute(SessionConstraints.SESSION_ATTR_APPLICATION_IP, MonitorConfig.ApplicationIP);
		request.getSession().setAttribute(SessionConstraints.SESSION_ATTR_APPLICATION_PORT,
				MonitorConfig.ApplicationPort);
		request.getSession().setAttribute(SessionConstraints.SESSION_ATTR_APPLICATION_NAME,
				MonitorConfig.ApplicationName);

		HttpSession session = request.getSession();

		// 设置默认国际化语言
		Object language = session.getAttribute(SessionConstraints.SESSION_ATTR_LANGUAGE);
		if (language == null || "null".equals(language)) {
			localeResolver.setLocale(request, response, Locale.CHINESE);
		}

		// 设置默认主题样式
		Object theme = session.getAttribute(SessionConstraints.SESSION_ATTR_THEME);
		if (theme == null || "null".equals(theme.toString()) || "".equals(theme.toString().trim())) {
			session.setAttribute(SessionConstraints.SESSION_ATTR_THEME, ThemeConstraints.DEFAULT);
		}

		// TODO

		return "index";
	}

	@RequestMapping("/languageSetting")
	public @ResponseBody String languageSetting(
			@RequestParam(value = "language", required = false, defaultValue = "chinese") String language,
			HttpServletRequest request, HttpServletResponse response) {

		// 设置国际化语言
		HttpSession session = request.getSession();
		Locale locale = null;
		boolean isSet = true;

		if (LanguageConstriants.CHINESE.equals(language)) {
			locale = Locale.CHINESE;
			localeResolver.setLocale(request, response, locale);
			session.setAttribute(SessionConstraints.SESSION_ATTR_LANGUAGE, language);
		} else if (LanguageConstriants.ENGLISH.equals(language)) {
			locale = Locale.ENGLISH;
			localeResolver.setLocale(request, response, locale);
			session.setAttribute(SessionConstraints.SESSION_ATTR_LANGUAGE, language);
		} else {
			locale = Locale.CHINESE;
			isSet = false;
		}

		String code = isSet ? ResCodeConstraints.SUCCESS : ResCodeConstraints.NOT_FOUND;
		ResponseParam rp = new EmptyResponseParam(code);

		return JsonUtils.toJson(rp);
	}

	@RequestMapping("/themeSetting")
	public @ResponseBody String themeSetting(
			@RequestParam(value = "theme", required = false, defaultValue = "green") String theme,
			HttpServletRequest request, HttpServletResponse response) {

		// 设置主题风格语言
		HttpSession session = request.getSession();
		boolean isSet = true;
		if (ThemeConstraints.GREEN.equals(theme)) {
			session.setAttribute(SessionConstraints.SESSION_ATTR_THEME, theme);
		} else if (ThemeConstraints.PURPLE.equals(theme)) {
			session.setAttribute(SessionConstraints.SESSION_ATTR_THEME, theme);
		} else {
			isSet = false;
			session.setAttribute(SessionConstraints.SESSION_ATTR_THEME, ThemeConstraints.DEFAULT);
		}

		String code = isSet ? ResCodeConstraints.SUCCESS : ResCodeConstraints.NOT_FOUND;
		ResponseParam rp = new EmptyResponseParam(code);

		return JsonUtils.toJson(rp);
	}

	@RequestMapping("/exception")
	public String testexception(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("exception");
		throw new Exception();
	}

	@RequestMapping("/nullPointerException")
	public String nullgg(HttpServletRequest request, HttpServletResponse response) {
		throw new NullPointerException();
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "exceptionHandler");
		return "exception";
	}

	@ExceptionHandler(NullPointerException.class)
	public String nullPointerExceptionHandler(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "nullPointerExceptionHandler");
		return "exception";
	}

	@RequestMapping("/summarize")
	public String summarize(HttpServletRequest request, HttpServletResponse response) {
		return "summarize";

	}

	@RequestMapping("/memory")
	public String memory(HttpServletRequest request, HttpServletResponse response) {
		return "memory";

	}

	@RequestMapping("/http")
	public String http(HttpServletRequest request, HttpServletResponse response) {
		return "http";

	}

	@RequestMapping("/session")
	public String session(HttpServletRequest request, HttpServletResponse response) {
		return "session";

	}

	@RequestMapping("/jdbc")
	public String jdbc(HttpServletRequest request, HttpServletResponse response) {
		return "jdbc";

	}

	@RequestMapping("/sql")
	public String sql(HttpServletRequest request, HttpServletResponse response) {
		return "sql";

	}
	
	@RequestMapping("/action")
	public String action(HttpServletRequest request, HttpServletResponse response) {
		return "action";
		
	}

	@RequestMapping("/panel")
	public String panel(HttpServletRequest request, HttpServletResponse response) {
		return "panel";

	}

	@RequestMapping("/apps")
	public String apps(HttpServletRequest request, HttpServletResponse response) {
		return "apps";

	}

	@RequestMapping("/init")
	public @ResponseBody String init(HttpServletRequest request, HttpServletResponse response) {

		// 初始化应用信息

//		String city[] = new String[] { "上海", "北京", "南京", "深圳", "广州", "杭州", "天津", "成都", "西安", "武汉" };
//		String name[] = new String[] { "shLiEMS65", "bjLiEMS65", "njLIEMS70", "szLIEMS50", "gzLIEMS50", "hzLIEMS50",
//				"tjLIEMS50", "cdLIEMS50", "xaLIEMS50", "whLIEMS50" };
//		String houzhui = "项目";
//		String des[] = new String[] { city[0] + houzhui, city[1] + houzhui, city[2] + houzhui, city[3] + houzhui,
//				city[4] + houzhui, city[5] + houzhui, city[6] + houzhui, city[7] + houzhui, city[8] + houzhui,
//				city[9] + houzhui };
//
//		for (int i = 0; i < name.length; i++) {
//			String appip = "192.168.10." + 1 + i;
//			String appport = "8080";
//			String appname = name[i];
//			String appdesc = des[i];
//			String citydd = city[i];
//			double lan = Citys.citis.get(citydd).getLongitude();
//			double lat = Citys.citis.get(citydd).getLatitude();
//			AppInfo info = new AppInfo(0, 0, appip + ":" + appport, appip, appport, appname, appdesc, citydd, lan, lat);
//			int res = appInfoService.insert(info);
//		}

		// 初始化用户
//		User user = new User(0, "13601509223", "陆瑞华", "1362810117", "", "0", "", "", "0", "");
//		userService.insert(user);
		
		for (int i = 0; i < 10; i++) {
			AppHeathyInfoStatistics appHeathyInfoStatistics = new AppHeathyInfoStatistics(0, i+1 , TimeUtils.getCurrentTime(), RandomUtils.nextFloat(100), RandomUtils.nextFloat(100), RandomUtils.nextFloat(100), RandomUtils.nextFloat(100), RandomUtils.nextFloat(100), RandomUtils.nextFloat(100));
			appHeathyInfoStatisticsMapper.insert(appHeathyInfoStatistics );
		}
		
		
		return "finish:";


	}

}
