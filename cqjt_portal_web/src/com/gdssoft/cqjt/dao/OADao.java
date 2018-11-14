package com.gdssoft.cqjt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.pojo.ListItem;

/**
 * 待办事项
 * @author  gyf 20150108
 * @return
 */
@Service("OADao")
public class OADao {
	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	public ListItem getUserSchemacode(final String userName){
		//String userId = "";
		ListItem userSchema = (ListItem) jdbcTemplate.execute(new CallableStatementCreator(){
			public CallableStatement createCallableStatement(
					Connection con) throws SQLException {
				String storedProc = "{call OA_COMMON.common_pg.get_user_schemacode(?,?,?)}";// 调用的sql
				CallableStatement cs = con.prepareCall(storedProc);
				System.out.println("userName:"+userName);
				System.out.println("java.sql.Types.INTEGER:"+java.sql.Types.INTEGER);
				System.out.println("java.sql.Types.VARCHAR:"+java.sql.Types.VARCHAR);
				// 设置输入参数的值
				cs.setString(1, userName);//setString(0, userName);// 
				
				// 注册输出参数的类型
				cs.registerOutParameter(2, java.sql.Types.INTEGER);
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
				return cs;
			}
		},  new CallableStatementCallback(){
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				ListItem user = new ListItem();
				cs.execute();
				Long userId  = cs.getLong(2);
				String schemaCode = cs.getString(3);
				user.setUserId(userId);
				user.setSchemaCode(schemaCode);
				cs.close();
				return user;
			}
		});
		logger.debug(userSchema.getUserId());
		return userSchema;
	}
	
	
	/**
	 * 获取待办事项
	 */
	@SuppressWarnings("unchecked")
	public List<ListItem> getNewFlowTaskList(final Long userId,final String schemaName,final int pageSize) {
		List<ListItem> resultList = (List<ListItem>) jdbcTemplate
				.execute(new CallableStatementCreator() {
					public CallableStatement createCallableStatement(
							Connection con) throws SQLException {
						
						String storedProc = "{call " + schemaName + ".getToDoList_New(?,?,?,?,?,?,?,?)}";// 调用的sql

						CallableStatement cs = con.prepareCall(storedProc);
						cs.setString(1, "0");// 设置输入参数的值
						cs.setString(2, userId.toString());
						cs.setString(3, "");
						cs.setString(4, "");
						cs.setString(5, "");
						cs.setInt(6, 0 + 1);
						int endNo = 0 + pageSize;
						cs.setInt(7, endNo);
						logger.info("endNo:" + endNo);
						// 注册输出参数的类型
						cs.registerOutParameter(8,oracle.jdbc.OracleTypes.CURSOR);
						return cs;

					}
				}, new CallableStatementCallback() {
					public Object doInCallableStatement(CallableStatement cs)
							throws SQLException, DataAccessException {
						List<ListItem> taskReportList = new ArrayList<ListItem>();
						cs.execute();
						ResultSet rs = (ResultSet) cs.getObject(8);
						int i = 0;
						while (rs.next()) {// 转换每行的返回值到Map中
							ListItem taskReport = new ListItem();
							if(i == 0)
								taskReport.setTotlecount(Integer.parseInt(rs.getString(1)));				
							taskReport.setTitle(rs.getString(5));
							taskReport.setDate(rs.getDate(12));							
							taskReportList.add(taskReport);
							i++;
						}
						rs.close();
						return taskReportList;
					}
				});
		return resultList;
	}

}
