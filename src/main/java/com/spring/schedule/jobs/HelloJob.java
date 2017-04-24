package com.spring.schedule.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhuojl
 * @Date 2017年4月13日
 */

public class HelloJob implements Job {
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("just listen the voice from your heart!");
		// Every job has its own job detail
		JobDetail jobDetail = context.getJobDetail();
		// 获取jobdetail的datamap
		logger.info("getWrappedMap	"+context.getMergedJobDataMap().getWrappedMap());
		// The name of this class configured for the job
		logger.info("Job Class: 	" + jobDetail.getJobClass());
		// Log the time the job started
		logger.info("fired 		at  " + context.getFireTime());
		logger.info("Next fire time " + context.getNextFireTime());
	}

}
