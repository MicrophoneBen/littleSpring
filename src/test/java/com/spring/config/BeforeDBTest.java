package com.spring.config;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.util.BeforeDB;


/**
 * 测试BeforeDB访问存储过程
 * @author zhuojl
 * @Date 2017年4月6日
 */
//@RunWith(SpriNGJUNIT4CLASSRUNNER.CLASS)   
//@CONTEXTCONFIguration(classes={SpringConfig.class,MybatisConfig.class}) 
public class BeforeDBTest {
	@Autowired
	BeforeDB db;
	
//	@Test
	public void test1() {
		String sql = "CursorProc";
		List<SqlParameter> sqlparams = new ArrayList<SqlParameter>();
		sqlparams.add(new SqlParameter("id", Types.VARCHAR));
//		sqlparams.add(new SqlOutParameter("list",Types.REF_CURSOR));

		Map<String, Object> paramsIn = new HashMap<String, Object>();
		paramsIn.put("id", 3);
		db.access(sql, sqlparams, paramsIn);
	}
	
//	@Test
	public void test2() {
		String sql = "dowhile";
		List<SqlParameter> sqlparams = new ArrayList<SqlParameter>();
		sqlparams.add(new SqlParameter("str", Types.VARCHAR));
//		sqlparams.add(new SqlOutParameter("list",Types.REF_CURSOR));

		Map<String, Object> paramsIn = new HashMap<String, Object>();
		paramsIn.put("str", "sss,ss");
		db.access(sql, sqlparams, paramsIn);
	}
	

}
