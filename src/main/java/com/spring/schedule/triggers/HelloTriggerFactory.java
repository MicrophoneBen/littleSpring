package com.spring.schedule.triggers;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailAwareTrigger;


/**
 * @Description:   
 *
 * @author         zhuojl
 * @Date           2017��4��10��
 */
public class HelloTriggerFactory extends CronTriggerFactoryBean{
	/*
	 * spring 4.0.9֮����Ҫ�ˡ�Ӧ�á� 
	@Override
    public void afterPropertiesSet() throws java.text.ParseException {
        super.afterPropertiesSet();
//        getJobDataMap().remove(JobDetailAwareTrigger.JOB_DETAIL_KEY);
    }*/
}