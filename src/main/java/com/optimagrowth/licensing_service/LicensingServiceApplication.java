package com.optimagrowth.licensing_service;

import com.optimagrowth.licensing_service.filter.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.context.config.annotation.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;
import org.springframework.web.client.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.i18n.*;

import java.util.*;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
public class LicensingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		return messageSource;
	}
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();
		if (interceptors==null){
			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		}
		else{
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}

		return template;
	}

}
