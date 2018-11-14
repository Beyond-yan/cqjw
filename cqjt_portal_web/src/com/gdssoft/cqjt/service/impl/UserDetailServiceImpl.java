package com.gdssoft.cqjt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.UserDetailDAO;
import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.pojo.UserRelationVo;
import com.gdssoft.cqjt.service.UserDetailService;

//import com.gdssoft.core.util.SystemContext;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailService {

	@Resource(name = "userDetailDAO")
	private UserDetailDAO userDao;
	
	protected transient final Log logger = LogFactory.getLog(getClass());

	public UserDetail getUserByCode(String code) {
		return userDao.queryUserByCode(code);
	}

	public List<UserDetail> login(String username, String password) {
		String encodingPassword = new ShaPasswordEncoder().encodePassword(password,
				username);
		UserDetail user = new UserDetail();
		user.setUserNo(username);
		user.setPassword(encodingPassword);
		return userDao.login(user);
	}

	@Override
	public void AddUser(UserDetail u) {
		userDao.AddUser(u);
	}

	@Override
	public boolean UpdateUser(UserDetail u) {

		int count = userDao.UpdateUser(u);
		if (1 == count)
			return true;
		else
			return false;
	}

	//H2902992  20140604
	@Override
	public boolean UpdateUserInfo(UserDetail u) {

		int count = userDao.UpdateUserInfo(u);
		if (1 == count)
			return true;
		else
			return false;
	}
	
	@Override
	public void DeleteUser(String userId) {

		userDao.DeleteUser(userId);
		
	}

	@Override
	public List<UserDetail> SelectUser(Map<String, String> map, int curpage,
			int pagesize) {
		String rStart = Integer.toString((curpage - 1) * pagesize + 1);
		String rEnd = Integer.toString(curpage * pagesize);
		map.put("rStart", rStart);
		map.put("rEnd", rEnd);
		return userDao.SelectUser(map);
	}

	@Override
	public int getUserCount(Map<String, String> map) {
		return userDao.getUserCount(map);
	}

	/*
	 * public void updateUser(UserDetail userDetail) {
	 * if(userDetail.getPassword().length() != 32) { String
	 * password=userDetail.getPassword(); String
	 * username=userDetail.getUsername(); String encodedPassword = new
	 * Md5PasswordEncoder().encodePassword(password, username);
	 * userDetail.setPassword(encodedPassword);
	 * SystemContext.getUserDetail().setPassword(encodedPassword); }
	 * //userDao.modifyUser(userDetail); }
	 */

	// added by Cube @130724
	public String GenPwd(String username, String password) {
		String encodedPassword = new Md5PasswordEncoder().encodePassword(
				password, username);
		return encodedPassword;
	}

	@Override
	public void updatePassword(UserDetail user) {
		userDao.updatePassword(user);
	}
	
	@Override
	public List<UserDetail> getApprovers(String userNo) {
		return userDao.getApprovers(userNo);
	}
	/**
	 * @author H2603282 2014.08.08
	 */
	@Override
	public List<UserDetail> getApp(String userName) {
		return userDao.getApp(userName);
	}
	/**
	 * @author H2602965  2014.05.28
	 */
	@Override
	public List<UserDetail> getSelectUserList(String userNo, String roleName,String deptID, int pageIndex, int pageSize) {
		return userDao.getSelectUserList(userNo, roleName, deptID,pageIndex,pageSize);
	}
	
	public UserDetail getUserEditInfo(String userId){
		return (UserDetail) userDao.getUserEditInfo(userId);
	}
	/**
	 * 新增用户名唯一性验证 H2602965 2014.06.04
	 */
	@Override
	public List<UserDetail> queryUserUnique(String userNo) {
		return userDao.queryUserUnique(userNo);
	}
	/**
	 * 查询OA_COMMON数据库中SYS_USER_ALL中的数据.
	 * @author H2602965 2014.06.09
	 */
	@Override
	public List<UserDetail> getUserAllList(String userNo,String userName, int pageIndex, int pageSize) {
		return userDao.getUserAllList(userNo,userName, pageIndex, pageSize);
	}

	@Override
	public void updatePassword(String userNo, String password) {
		userDao.updatePassword(userNo, password);
	}

	@Override
	public void addOacommonUser(UserDetail user) {
		userDao.addOacommonUser(user);
	}
	@Override
	public void addSchemaAppUser(UserDetail user) {
		userDao.addSchemaAppUser(user);
	}

	@Override
	public void editOacommonUser(UserDetail user) {
		userDao.editOacommonUser(user);
	}
	@Override
	public void editSchemaAppUser(UserDetail user) {
		userDao.editSchemaAppUser(user);
	}

	@Override
	public void delOacommonUser(String loginId) {
		userDao.delOacommonUser(loginId);
	}
	@Override
	public void delSchemaAppUser(String loginId) {
		userDao.delSchemaAppUser(loginId);
	}
	
	public UserRelationVo queryUserRelation(String oaLoginId, String ygjLoginId){
		UserRelationVo userVo = new UserRelationVo();
		List<UserRelationVo>  list = userDao.queryUserRelation(oaLoginId, ygjLoginId);
		if(list != null && list.size() > 0){
			userVo = list.get(0);
		}
		return userVo;
	}
}

