package com.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.spring.util.BeforeDB;

/**
 * @Description:
 * 
 * @author zhuojl
 * @Date 2017年3月31日
 */
@ComponentScan("com.spring.service")
@Configuration
//@ImportResource("classpath:forRpc-service.xml") //导入xml配置项
public class SpringConfig {
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.11.121:3306/gxs_test");
		dataSource.setUsername("gxs_test");
		dataSource.setPassword("gxs_test");
		dataSource.setMaxActive(50);
		dataSource.setValidationQuery("SELECT 1 ");// oracle需要更改。
		dataSource.setTestOnBorrow(true);
		return dataSource;
	}
	
	@Bean
	public BeforeDB beforeDB(BasicDataSource datasource){
		return new BeforeDB(datasource);
	} 
	
//	<bean id="beforeDB" class="com.ilovestudy.forum.core.system.BeforeDB">
//	<constructor-arg index="0" ref="dataSource">
//	</constructor-arg>
//</bean>
}
