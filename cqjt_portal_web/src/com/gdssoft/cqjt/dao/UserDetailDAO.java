package com.gdssoft.cqjt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.pojo.UserRelationVo;
/**
 * @author F3228761
 * @date : Jul 27, 2013 10:34:35 AM
 */
@Repository("userDetailDAO")
public class UserDetailDAO {
	
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	

	public UserDetail queryUserByCode(String userNo) {
		
		return (UserDetail)sqlMapClientTemplate.queryForObject("Users.queryUserByCode",
				userNo);
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> login(UserDetail user) {
		return (List<UserDetail>) sqlMapClientTemplate.queryForList("Users.Login", user);
	}

	public void AddUser(UserDetail user) {
		sqlMapClientTemplate.insert("Users.AddUser", user);
	}

	public int UpdateUser(UserDetail user) {
		return sqlMapClientTemplate.update("Users.UpdateUser", user);
	}
	//H2902992  20140604
	public int UpdateUserInfo(UserDetail user) {
		return sqlMapClientTemplate.update("Users.UpdateUserInfo", user);
	}
	
	// modified the return type void with integer by Cube @130806
	public void DeleteUser(String userId) {
		this.sqlMapClientTemplate.update("Users.UpdateUser", userId);
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> SelectUser(Map<String, String> map) {
		return (List<UserDetail>) sqlMapClientTemplate.queryForList("Users.SelectUser", map);
	}

	/**
	 * 新增查询用户信息lsit方法
	 * @author H2602965
	 * @param userNo
	 * @param roleName
	 * @param deptName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserDetail> getSelectUserList(String userNo, String roleName, String deptID,int pageIndex, int pageSize) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userNo", userNo);
		map.put("roleName", roleName);
		map.put("deptID", deptID);
		
		return (List<UserDetail>) sqlMapClientTemplate
				.queryForList("Users.SelectUser", map, pageIndex, pageSize);
	}

	public int getUserCount(Map<String, String> map) {
		return (Integer) this.sqlMapClientTemplate.queryForObject(
				"Users.getUserCount", map);
	}

	public void updatePassword(UserDetail user) {
		sqlMapClientTemplate.update("Users.updatePassword", user);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserDetail> getApprovers(String userNo) {
		return (List<UserDetail>) sqlMapClientTemplate.queryForList("Users.getApprovers", userNo);
	}
	//通过userName获取审核人08.08
	@SuppressWarnings("unchecked")
	public List<UserDetail> getApp(String userName) {
		return (List<UserDetail>) sqlMapClientTemplate.queryForList("Users.getApp", userName);
	}
	
	public UserDetail getUserEditInfo(String userId) {
		return (UserDetail) this.sqlMapClientTemplate.queryForObject("Users.EditUserInfo", userId);
	}
	
	//新增用户名唯一性验证 H2602965 2014.06.04
	@SuppressWarnings("unchecked")
	public List<UserDetail> queryUserUnique(String userNo) {
		return (List<UserDetail>) sqlMapClientTemplate.queryForList("Users.queryUserUniqueByCode", userNo);
	}
	/**
	 * 添加getUserAllList 查询OA_COMMON数据库中SYS_USER_ALL中的数据.
	 * @author H2602965 
	 * @param userNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserDetail> getUserAllList(String userNo, String userName, int pageIndex, int pageSize) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userNo", userNo);
		map.put("userName", userName);
		
		return (List<UserDetail>) sqlMapClientTemplate
				.queryForList("Users.queryUserAllByOA", map, pageIndex, pageSize);
	  
	}
	/**
	 * 修改密码
	 * @author gyf  20140826
	 * @param id
	 * @param password
	 */
	public void updatePassword(String userNo, String password){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("password", password);
		
		sqlMapClientTemplate.update("Users.updatePassword", map);
	}
	
	public void addOacommonUser(UserDetail user) {
		sqlMapClientTemplate.insert("Users.addOacommonUser", user);
	}
	public void addSchemaAppUser(UserDetail user) {
		sqlMapClientTemplate.insert("Users.addSchemaAppUser", user);
	}

	public int editOacommonUser(UserDetail user) {
		return sqlMapClientTemplate.update("Users.editOacommonUser", user);
	}
	public int editSchemaAppUser(UserDetail user) {
		return sqlMapClientTemplate.update("Users.editSchemaAppUser", user);
	}
	
	public void delOacommonUser(String loginId) {
		this.sqlMapClientTemplate.update("Users.delOacommonUser", loginId);
	}
	public void delSchemaAppUser(String loginId) {
		this.sqlMapClientTemplate.update("Users.delSchemaAppUser", loginId);
	}
	
	//新增用户名唯一性验证 H2602965 2014.06.04
	@SuppressWarnings("unchecked")
	public List<UserRelationVo> queryUserRelation(String relaOaLoginId, String relaYgjLoginId) {
		UserRelationVo rela = new UserRelationVo();
		rela.setRelaOaLoginId(relaOaLoginId);
		rela.setRelaYgjLoginId(relaYgjLoginId);
		return (List<UserRelationVo>) sqlMapClientTemplate.queryForList("Users.queryUserRelation", rela);
	}
}
