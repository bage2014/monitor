package com.bage.config.Internationalization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 
 * 国际化配置
 * @author bage
 *
 */
@Configuration
public class InternationalizationConfig {

	@Bean(name="localeResolver")
    public LocaleResolver localeResolverBean() {
        return new SessionLocaleResolver();
    }
    @Bean(name="messageSource")
    public ResourceBundleMessageSource resourceBundleMessageSource(){
        ResourceBundleMessageSource source=new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }
}
