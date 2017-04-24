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
 * @Date 2017��4��13��
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
	
	 
	@Bean//xml���Å���quartz-scheduler.xml
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
						SimpleScheduleBuilder.repeatSecondlyForTotalCount(2).withIntervalInSeconds(20)//�˴�С�ģ��ڲ�����Ҫ���������ʱ�乻�ã���Ȼ��û���ü�����
					).build();
		
		factory.setJobDetails(job);//���Բ�����
		factory.setTriggers(test,helloTriggerFactory.getObject());//ͨ��װ���trigger����Ҫ�ٴ�setJobDetails
		return factory;
	}
}
