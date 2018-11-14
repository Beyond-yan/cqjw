package com.gdssoft.cqjt.service;

import java.util.List;
import com.gdssoft.cqjt.pojo.Mail;

/**
 * 邮件服务接口
 * @author F3201252
 *
 */
public interface MailService {
	/**
	 * 执行服务器指令
	 * @param cmd
	 * @param args
	 * @return
	 */
	public String userLogin(String userEmail);
	
	/**
	 * 获取未读邮件数
	 * @param userEmail
	 * @return
	 */
	public int getNewMailCnt(String userEmail);
	
	/**
	 * 获取邮件列表
	 * @param email
	 * @param mailbox
	 * @return
	 */
	public List<Mail> getNewMailList(String userEmail);
	
	public String getEmailUrl(String userEmail,String mid);

}
