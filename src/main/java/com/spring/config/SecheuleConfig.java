package com.spring.config;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.SimpleTimeZone;

import org.apache.commons.dbcp.BasicDataSource;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.spring.schedule.jobs.HelloJob;
import com.spring.schedule.triggers.HelloTriggerFactory;

/**
 * @author zhuojl
 * @Date 2017年4月13日
 */
 @Configuration
public class SecheuleConfig {
	
	@Bean
	public HelloTriggerFactory helloTrigger(){
		HelloTriggerFactory h = new HelloTriggerFactory();
		JobDetail job = newJob(HelloJob.class).storeDurably(true).withIdentity("job2", "group1").build();
		h.setJobDetail(job);
		h.setCronExpression("0/20 * * * * ?");
		return h;
	}
	
	 
	@Bean//xml配置參考quartz-scheduler.xml
	public SchedulerFactoryBean schedulerFactoryBean(BasicDataSource dataSource,HelloTriggerFactory helloTriggerFactory) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setDataSource(dataSource);
		Resource r = new ClassPathResource("quartz.properties");
		factory.setConfigLocation(r);
		factory.setStartupDelay(30);
		factory.setApplicationContextSchedulerContextKey("applicationContextKey");
		factory.setOverwriteExistingJobs(true);
		factory.setAutoStartup(true);
		
		JobDetail job = newJob(HelloJob.class).storeDurably(true).withIdentity("job1", "group1").build();
		System.out.println(new Date());
		Trigger test = newTrigger()
				.withIdentity("trigger1", "group1")
				.forJob(job)
				.startNow()
				.withSchedule(
//						CronScheduleBuilder.dailyAtHourAndMinute(21, 35)
						SimpleScheduleBuilder.repeatSecondlyForTotalCount(2).withIntervalInSeconds(20)//此处小心，在测试中要让容器存货时间够久，不然还没来得及触发
					).build();
		
		factory.setJobDetails(job);//可以不传递
		factory.setTriggers(test,helloTriggerFactory.getObject());//通过装配的trigger不需要再次setJobDetails
		return factory;
	}
}
