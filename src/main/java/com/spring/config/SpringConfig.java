package com.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
	
}
