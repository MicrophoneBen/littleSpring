package com.spring.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class TestStoredProcedure extends StoredProcedure{
    public TestStoredProcedure(DataSource dataSource) {
    	super(dataSource,"");//此处暂时设置为空，后面可以再次设置
    }
    
    /**
     * 	测试访问简单的存储过程
	TIPs:
	sql:
		create or replace procedure test_param
			(p_id1 in VARCHAR2 default '0',p_id2 in VARCHAR2,v_name1 out varchar2,v_name2 out varchar2) 
		as
		 -- v_name1 varchar2(2000);--重复定义了
		 -- v_name2 varchar2(2000);--重复定义了
		begin
		  select t.name into v_name1 from TESTTABLE t where t.id = p_id1;
		  select t.name into v_name2 from TESTTABLE t where t.id = p_id2;
		  DBMS_OUTPUT.put_line('name1：' || v_name1||'    name2:'||v_name2);
		end; 
	plsql测试：
		declare v_name1 varchar2(200);
		begin
		test_param('1','3',v_name1,v_name1);
		end;
     */
    
    public Map<String,Object> toExecute1() {
    	String sql = "test_param";
    	setSql(sql);
    	/**declare顺序需要和存储过程参数顺序一致，名字无所谓，*/
    	declareParameter(new SqlParameter("p_id3", Types.VARCHAR));//不过输入参数(p_id3)在paramsIn.put时需要对应
    	declareParameter(new SqlParameter("p_id4", Types.VARCHAR));
    	declareParameter(new SqlOutParameter("name1", Types.VARCHAR));//name1作为返回map的键
    	declareParameter(new SqlOutParameter("name2", Types.VARCHAR));
        Map<String, Object> paramsIn = new HashMap<String, Object>();
        paramsIn.put("p_id3", 1);
        paramsIn.put("p_id4", 3);
        Map<String,Object> rst = super.execute(paramsIn);
        System.out.println("sql:"+sql+"\r\nrst:"+rst);
        return rst ;
    }
    /**
     * 
     * 	稍微复杂点的，不过感觉更多的就是数据库编程了，又没去深入了。
	TIPs:
	sql:
		create or replace procedure test_more
			(p_id1 in VARCHAR2 default '0',p_id2 in number,v_name1 out varchar2,v_name2 out varchar2)
		as
		begin
			
  			select t.name into v_name1 from TESTTABLE t where t.id = p_id1;
  			--在利用select...into...语法时，必须先确保数据库中有该条记录，否则会报出"no data found"异常。
   			--可以在该语法之前，先利用select count(*) from 查看数据库中是否存在该记录，如果存在，再利用select...into...
  			update TESTTABLE set name = 'zhangsan2' where id = 5;
  			select t.name into v_name2 from TESTTABLE t where t.id = p_id2;
  			
    		if (v_name2='wangwu') then
       			select t.name into v_name2 from TESTTABLE t where t.id = p_id2+1;
       			Dbms_output.Put_line('打印信息1:'||v_name2);
    		elsif (v_name2='222') then
       			Dbms_output.Put_line('打印信息2:'||v_name2);
			Else
       			Raise NO_DATA_FOUND;
    		End if;
    		
    		Exception
       			When 
       				others 
    			then
			       v_name2:='3333';
			       Dbms_output.Put_line('打印信息3:'||v_name2);    
			       Rollback;

  			DBMS_OUTPUT.put_line('name1：' || v_name1||'    name2:'||v_name2);
		end;

	plsql测试：
		declare v_name1 varchar2(200);
		begin
		test_more('1','3',v_name1,v_name1);
		end;
     */
    public Map<String,Object> toExecute2() {
    	String sql = "test_more";
    	setSql(sql);
    	/**declare顺序需要和存储过程参数顺序一致，名字无所谓，*/
    	declareParameter(new SqlParameter("p_id3", Types.VARCHAR));//不过输入参数(p_id3)在paramsIn.put时需要对应
    	declareParameter(new SqlParameter("p_id4", Types.VARCHAR));
    	declareParameter(new SqlOutParameter("name1", Types.VARCHAR));//name1作为返回map的键
    	declareParameter(new SqlOutParameter("name2", Types.VARCHAR));
        Map<String, Object> paramsIn = new HashMap<String, Object>();
        paramsIn.put("p_id3", 1);
        paramsIn.put("p_id4", 3);
        Map<String,Object> rst = super.execute(paramsIn);
        System.out.println("sql:"+sql+"\r\nrst:"+rst);
        return rst ;
    }
    
    /**
     * 	测试获取游标
    TIPs  包头和包体需要单独执行
    sql:
	    create or replace package MYPKG as
			type mycursor is ref cursor;
			procedure test(tid in number,mylist out mycursor);
		end MYPKG;
		--分割线--
	    create or replace package body MYPKG as
	    	procedure test(tid in number,mylist out mycursor) as
	        begin
	        	open mylist for select * from testtable where id <= tid;
	        end test;
	    end MYPKG;
	    
	plsql测试：
     * */
//    public Map<String,Object> toExecute3() {
//    	String sql = "MYPKG.test";
//    	setSql(sql);
//    	declareParameter(new SqlParameter("id", Types.VARCHAR));
//    	declareParameter(new SqlOutParameter("list", OracleTypes.CURSOR,new RowMapper<Map<String,Object>>() {
//			@Override
//			public Map<String, Object> mapRow(ResultSet rs, int i)
//					throws SQLException {
//				  	Map<String,Object> map = new HashMap<String,Object>();  
//	                map.put("id", rs.getString("ID"));
//	                map.put("name", rs.getString("NAME"));  
//	                return map;  
//			}
//		}));
//        Map<String, Object> paramsIn = new HashMap<String, Object>();
//        paramsIn.put("id", 3);
//        Map<String,Object> rst = super.execute(paramsIn);
//        System.out.println("sql:"+sql+"\r\nrst:"+rst);
//        return rst ;
//    }
//    
    
    
    /**
     * jdbc	访问存储过程
     */
   /* public void testJdbcAccessProc(){
    	String[] strs =  getDBdata();
    	String driver 	= strs[0],
		    	url		= strs[1],
		    	acct 	= strs[2],
		    	pwd 	= strs[3];
    	
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String sql = "{call MYPKG.test(?,?)}";
    	try {
			conn = DriverManager.getConnection(url, acct, pwd);
			proc = conn.prepareCall(sql);
			proc.setInt(1, 3);
			proc.registerOutParameter(2, OracleTypes.CURSOR);
			proc.execute();
			rs = (ResultSet) proc.getObject(2);//如果不是游标，则直接就可以获取对应的数据！
			while(rs.next()){
				System.out.println("id:"+rs.getString("id")+"\tname:"+rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(proc!=null){
					proc.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }*/
    /**
     * jdbcTemplate访问proc
     * @param jdbcTemplate
     */
	/*public void testJdbcTemplateAccessProc(JdbcTemplate jdbcTemplate) {   
        List<Map<String,Object>> resultList = (List<Map<String,Object>>) jdbcTemplate.execute(
        	new CallableStatementCreator() {

				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {   
	                String storedProc = "{call MYPKG.test(?,?)}";// 调用的sql   
	                CallableStatement cs = con.prepareCall(storedProc);   
	                cs.setString(1, "3");// 设置输入参数的值   
	                cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型   
	                return cs;   
	             }   
        		  
          },new CallableStatementCallback<List<Map<String,Object>>>(){

				@Override
				public List<Map<String,Object>>  doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					List<Map<String,Object>> list_rst = new ArrayList<Map<String,Object>>();   
			           cs.execute();   
			           ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值   
			           while (rs.next()) {
			        	  Map<String,Object> rowMap = new HashMap<String,Object>();   
			              rowMap.put("id", rs.getString("id"));   
			              rowMap.put("name", rs.getString("name"));   
			              System.out.println(rowMap);
			              list_rst.add(rowMap);   
			           }   
			           rs.close();   
			           return list_rst;
				}
        	  
          }); 
        
	}*/
	
	/**手动设置datasource*/
	public static BasicDataSource setDataSourceByFoot(){
		String[] strs =  getDBdata();
    	String driver 	= strs[0],
		    	url		= strs[1],
		    	acct 	= strs[2],
		    	pwd 	= strs[3];
    	BasicDataSource ds = new BasicDataSource();      
    	ds.setDriverClassName(driver);      
    	ds.setUrl(url);      
    	ds.setUsername(acct);      
    	ds.setPassword(pwd);  
    	return ds;
	}
	/**获取链接数据库字符串*/
    public static String[] getDBdata(){
    	Properties p =new Properties();
    	try {
			p.load(new FileReader("F://Github//zhuojl.github.io//src//main//resources//db.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String driver 	= p.getProperty("oracle.driver"),
		    	url		= p.getProperty("oracle.url"),
		    	acct 	= p.getProperty("oracle.username"),
		    	pwd 	= p.getProperty("oracle.password");
    	
    	return new String[]{driver,url,acct,pwd};
    }
}







