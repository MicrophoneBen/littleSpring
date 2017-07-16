package com.spring.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bean.mapper.UserMapper;

/**
 * @Description:   
 *
 */
@Service
public class HelloServiceImpl implements HelloService{
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	UserMapper uMapper;
	
	@Override
	public void sayHelloBaby() {
		logger.info("hello world!");
		logger.error("hello world!");
		logger.warn("hello world!");
	}

	@Override
	public void useUserMapper() {
//		uMapper.selectByPrimaryKey(1);
	}
	
}
