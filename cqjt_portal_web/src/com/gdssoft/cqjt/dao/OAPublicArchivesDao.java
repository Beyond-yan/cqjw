package com.gdssoft.cqjt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gdssoft.core.dao.JdbcBaseDao;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.pojo.ListItem;

@Repository("oaPublicArchivesDao")
public class OAPublicArchivesDao extends JdbcBaseDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("rawtypes")
	public List<ListItem> getUnReceiveArchives(final String userNo,
			final int count) {
		@SuppressWarnings("unchecked")
		List<ListItem> resultList = (List<ListItem>) jdbcTemplate.execute(
				new CallableStatementCreator() {
					@Override
					public CallableStatement createCallableStatement(
							Connection con) throws SQLException {

						String storedProc = "{call OA_COMMON.INTERFACE_PG.archives_sign(?,?,?,?)}";// 调用的sql`
						CallableStatement cs = con.prepareCall(storedProc);
						cs.setString(1, userNo);
						cs.setInt(2, count);
						cs.registerOutParameter(3, java.sql.Types.INTEGER);
						cs.registerOutParameter(4,
								oracle.jdbc.OracleTypes.CURSOR);
						return cs;
					}

				}, new CallableStatementCallback() {
					public Object doInCallableStatement(CallableStatement cs)
							throws SQLException, DataAccessException {
						List<ListItem> List = new ArrayList<ListItem>();
						cs.execute();
						ResultSet rs = (ResultSet) cs.getObject(4);
						int totalCounts = Integer.parseInt(cs.getObject(3)
								.toString());
						while (rs.next()) {// 转换每行的返回值到Map中
							ListItem list2 = new ListItem();
							list2.setTotlecount(totalCounts);
							list2.setDate(rs.getDate("create_date"));
							list2.setLink(rs.getString("send_dep"));
							list2.setTitle(rs.getString("subject"));
							/* send_dep,subject,create_date，receive_type */
							List.add(list2);
						}
						rs.close();
						return List;
					}
				});
		return resultList;
	}
}
