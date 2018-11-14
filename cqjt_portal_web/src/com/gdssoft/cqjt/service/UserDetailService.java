package com.gdssoft.cqjt.service;
 
import java.util.List;
import java.util.Map;

import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.pojo.UserRelationVo;

/**
 * @Descrition
 * 
 * @author
 * 
 * 
 */
public interface UserDetailService {

	List<UserDetail> login(String username, String password);

	public UserDetail getUserByCode(String code);

	public void AddUser(UserDetail u);

	public boolean UpdateUser(UserDetail u);
	//H2902992  20140604
	public boolean UpdateUserInfo(UserDetail u);
	
	public void DeleteUser(String userId);

	public List<UserDetail> SelectUser(Map<String, String> map, int curpage,
			int pagesize);

	public int getUserCount(Map<String, String> map);

	String GenPwd(String username, String password); // Add by David on date
														// 2013/08/03

	void updatePassword(UserDetail user);
	
	public List<UserDetail> getApprovers(String userNo);
	//通过username 获取审核人
	public List<UserDetail> getApp(String userName);
	//Add by wl 2014/05/28
	List<UserDetail> getSelectUserList(String userNo, String roleName,String deptID,int pageIndex, int pageSize);
	
	public UserDetail getUserEditInfo(String userId);
	//新增用户名唯一性验证 H2602965 2014.06.04
	List<UserDetail> queryUserUnique(String userNo);
	//新增查询 查询OA_COMMON数据库中SYS_USER_ALL中的数据 方法 H2602965
	List<UserDetail> getUserAllList(String userNo,String userName, int pageIndex, int pageSize);
	
	/**
	 * 修改密码
	 * @author gyf  20140826
	 * @param id
	 * @param password
	 */
	public void updatePassword(String userNo, String password);
	
	public void addOacommonUser(UserDetail u);
	public void addSchemaAppUser(UserDetail user);
	public void editOacommonUser(UserDetail u);
	public void editSchemaAppUser(UserDetail u);
	public void delOacommonUser(String loginId);
	public void delSchemaAppUser(String loginId);
	
	public UserRelationVo queryUserRelation(String oaLoginId, String ygjLoginId);
}
