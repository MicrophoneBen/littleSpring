package com.spring.util;

import java.util.List;
import java.util.Map;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;
/**
 * 创建一个利用StoredProcedure访问存储过程的工具类。
 * @author zhuojl
 * @date 2017年4月6日
 *
 */
@Component
public class BeforeDB {
	BasicDataSource datasource;
	
	public BeforeDB( ) {
		
	}
	//构造注入
	public BeforeDB(BasicDataSource datasource) {
		this.datasource = datasource;
//		this.datasource = TestStoredProcedure.setDataSourceByFoot();
	}
	/**
	 * 访问存储过程，返回map
	 * @param sql			proc 的名称
	 * @param sqlparams		用于StoredProcedure.declareParameter，顺序与paramsIn一致
	 * @param paramsIn		proc输入参数
	 * @return
	 */
	public Map<String,Object> access(String sql,List<SqlParameter> sqlparams,Map<String, Object> paramsIn){
		TestStoredProcedure ts = new TestStoredProcedure(datasource);
		ts.setSql(sql);
		for(SqlParameter sqlparam:sqlparams){
			ts.declareParameter(sqlparam);
		}
        Map<String,Object> rst = ts.execute(paramsIn);
        System.out.println("sql:"+sql+"\r\nrst:"+rst);
		return rst;
	}
	
	
}
