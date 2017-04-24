package com.spring.config;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.jobs.ee.mail.SendMailJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.spring.schedule.jobs.HelloJob;
import com.spring.schedule.triggers.HelloTriggerFactory;

/**
 * 单独测试 quartz
 * @author zhuojl
 * @Date 2017年4月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class, MybatisConfig.class, SecheuleConfig.class})
public class ScheuleConfigTest {
	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	HelloTriggerFactory h;
	
	@Test
	public void testQuartz(){
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testSimpleTrigger() {
		try {

			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job2",
					"group1").build();
			
			// Trigger the job to run now, and then repeat every 40 seconds
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group1")
					.startNow().withSchedule(
							SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
									.repeatForever()).build();

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
//	@Test
	public void testCronTrigger() {
		try {

			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1",
					"group1").build();
			
			System.out.println(new Date());
			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow().withSchedule(
							CronScheduleBuilder.dailyAtHourAndMinute(14, 28)).build();
			scheduler.scheduleJob(job, trigger2);
			scheduler.start();
			try {
				TimeUnit.SECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}

//	@Test  //quartz-jobs自帶，实际业务中，自己修改job类。
	public void testSendEmail() throws SchedulerException {
		// Grab the Scheduler instance from the Factory
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// define the job and tie it to our HelloJob class
		JobDetail job = newJob(SendMailJob.class).withIdentity("job1", "group1")
				.build();
		JobDataMap dataMap=job.getJobDataMap();
		
		dataMap.put(SendMailJob.PROP_SMTP_HOST, "smtp.qq.com");//端口465或587
		dataMap.put(SendMailJob.PROP_RECIPIENT, "xxxxx@qq.com");
		dataMap.put(SendMailJob.PROP_SENDER, "xxxxx@qq.com");
		dataMap.put(SendMailJob.PROP_SUBJECT, "主题yeah");
		dataMap.put(SendMailJob.PROP_MESSAGE, "study every day，and you will receive your success!总要有两个中文看看乱码");
		dataMap.put("mail.smtp.port", "587");//邮箱服务器端口
		dataMap.put("reply_to", "");
		dataMap.put("cc_recipient", "");
		dataMap.put("content_type", "");
		dataMap.put("username", "xxxxx@qq.com");
		dataMap.put("password", "frzyluykkxkgbgjc");//邮箱授权码
		dataMap.put("mail.smtp.auth", "true");//必备
//		dataMap.put("mail.smtp.ssl.enable", "true");
//		dataMap.put("mail.debug", "true");

		// Trigger the job to run now, and then repeat every 40 seconds
		Trigger trigger = newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(
						simpleSchedule().withIntervalInSeconds(2)
								.repeatForever()).build();

		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(job, trigger);

		scheduler.start();
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduler.shutdown(true);

	}

}
