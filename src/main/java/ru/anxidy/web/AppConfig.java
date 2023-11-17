package ru.anxidy.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@ComponentScan(basePackages = "ru.anxidy")
public class AppConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean getFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("TestPersistenceUnit");
        return bean;
    }
}