package com.bage.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bage.service.module.UserService;
import com.bage.utils.MD5Utils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/monitoring/**","/index/init").permitAll().anyRequest().authenticated()
		.and().formLogin().loginPage("/user/login").permitAll().defaultSuccessUrl("/index/index")
		.and().logout().logoutUrl("/user/logout").permitAll()
		//.and().rememberMe()
		.and().csrf().disable()
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/third/**", "/css/**", "/img/**", "/js/**", "/theme/**","/webservice/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("bage").password("bage").roles("USER");

		PasswordEncoder md5PasswordEncoder = new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Utils.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(MD5Utils.encode((String) rawPassword));
			}
		};

		auth.userDetailsService(userDetailsService).passwordEncoder(md5PasswordEncoder);

	}
}
