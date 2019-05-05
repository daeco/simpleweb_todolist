package com.leanne.edu.springboot.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Configuraion {
    static final String h2WebConsoleUrl = "/console/*";

    @Bean
    ServletRegistrationBean h2ServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings(h2WebConsoleUrl);
        return registration;
    }
}
