package ru.anxidy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.anxidy.web.AppConfig;
import ru.anxidy.web.StartupListener;
import ru.anxidy.web.WebConfig;

@Configuration
@ComponentScan(basePackages = "ru.anxidy", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                WebConfig.class,
                AppConfig.class,
                StartupListener.class
        })
})
public class TestConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean getFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("TestPersistenceUnit");
        return factoryBean;
    }
}
