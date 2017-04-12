package com.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.spring.schedule.triggers.TestTrigger;

/**
 * @Description:
 * 
 * @author zhuojl
 * @Date 2017年3月31日
 */
@ComponentScan("com.spring.service")
@Configuration
public class SpringConfig {
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/account");
		dataSource.setUsername("root");
		dataSource.setPassword("888888");
		dataSource.setMaxActive(50);
		dataSource.setValidationQuery("SELECT 1 ");// oracle需要更改。
		dataSource.setTestOnBorrow(true);
		return dataSource;
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(BasicDataSource dataSource){
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setDataSource(dataSource);
		Resource r = new ClassPathResource("quartz-config.xml");
		factory.setConfigLocation(r);
		factory.setStartupDelay(30);
		factory.setApplicationContextSchedulerContextKey("applicationContextKey");
		factory.setOverwriteExistingJobs(true);
		factory.setAutoStartup(true);
		Trigger test = new TestTrigger(); 
		factory.setTriggers(test);
		factory.setJobDetails(jobDetails);
		
		return factory;
	}
}
