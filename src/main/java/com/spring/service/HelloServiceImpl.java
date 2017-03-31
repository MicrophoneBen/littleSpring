package com.spring.service;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.bean.mapper.UserMapper;

/**
 * @Description:   
 *
 * @author         zhuojl
 * @Date           2017Äê3ÔÂ31ÈÕ
 */
@Service
public class HelloServiceImpl implements HelloService{
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	UserMapper uMapper;
	
	@Override
	public void sayHelloBaby() {
		logger.info("hello world!");
	}

	@Override
	public void useUserMapper() {
		uMapper.selectByPrimaryKey(1);
	}
	
}
