package com.gdssoft.cqjt.service.impl;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tebie.applib.api.APIContext;
import tebie.applib.api.IClient;
import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.cqjt.pojo.Mail;
import com.gdssoft.cqjt.service.MailService;

/**
 * 邮件服务
 * 
 * @author F3201252
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

	@Value("${mail.server.ip}")
	private String mailServerIP;

	@Value("${mail.server.port}")
	private int mailServerPort;

	@Value("${mail.integration.enable}")
	private boolean mailIntegrationEnable;

	protected transient final Log logger = LogFactory.getLog(getClass());

	@Override
	public String userLogin(String userEmail) {
		if (!mailIntegrationEnable)
			return "";

		APIContext ret = null;
		IClient cli = null;

		String result = "";
		try {
			Socket socket = new Socket(mailServerIP, mailServerPort);
			cli = APIContext.getClient(socket);
			ret = cli.userLoginEx(userEmail, "face=XJS");

			if (ret.getRetCode() == 0) {
				result = ret.getResult();
				//截取
				result=result.substring(0, result.indexOf("webname")-1);
			} else {
				result = String.valueOf(ret.getRetCode());
				//截取
				result=result.substring(0, result.indexOf("webname")-1);
				logger.warn("Error: " + ret.getRetCode() + ","
						+ ret.getErrorInfo());
			}

		} catch (Exception e) {
			logger.error("登入邮件系统时发生异常！", e);
		} finally {
			try {
				if (null != cli)
					cli.close();
			} catch (Exception e) {
				logger.error("未能正常关闭邮件系统的Socket连接！", e);
			}
		}

		return result;
	}

	@Override
	public int getNewMailCnt(String userEmail) {
		if (!mailIntegrationEnable)
			return 0;

		int result = 0;
		APIContext ret = null;
		IClient cli = null;

		try {
			Socket socket = new Socket(mailServerIP, mailServerPort);
			cli = APIContext.getClient(socket);
			ret = cli.getAttrs(userEmail, "mbox_newmsgcnt");

			if (ret.getRetCode() == 0) {
				result = Integer.valueOf(ret.getResult().replace(
						"mbox_newmsgcnt=", ""));
			} else {
				logger.warn("Error: " + ret.getRetCode() + ","
						+ ret.getErrorInfo());
			}

		} catch (Exception e) {
			logger.error("获取未读邮件数时发生异常！", e);
		} finally {
			try {
				if (null != cli)
					cli.close();
			} catch (Exception e) {
				logger.error("未能正常关闭邮件系统的Socket连接！", e);
			}
		}

		return result;
	}

	@Override
	public List<Mail> getNewMailList(String userEmail) {
		List<Mail> list = new ArrayList<Mail>();

		if (!mailIntegrationEnable)
			return list;

		IClient cli = null;
		APIContext ret = null;
		String result = "";
		Document dom;

		try {
			Socket socket = new Socket(mailServerIP, mailServerPort);
			cli = APIContext.getClient(socket);
			ret = cli.getNewMailInfos(userEmail, "limit=7&format=xml");

			if (ret.getRetCode() == 0) {
				result = ret.getResult();
				dom = DocumentHelper.parseText(result);

				@SuppressWarnings("unchecked")
				List<Node> nl = dom.selectNodes("//root/mail");

				for (Node n : nl) {
					Mail mail = new Mail();
					mail.setMid(n.valueOf("mid"));
					mail.setMsid(n.valueOf("msid"));
					mail.setFid(n.valueOf("fid"));
					mail.setFlag(n.valueOf("flag"));
					mail.setFrom(n.valueOf("from").replace("<", "&lt;")
							.replace(">", "&gt;"));
					mail.setTo(n.valueOf("to").replace("<", "&lt;")
							.replace(">", "&gt;"));
					mail.setSubject(n.valueOf("subject"));
					mail.setSize(n.valueOf("size"));
					mail.setDate(DateUtil.dateParse(n.valueOf("date")));
					list.add(mail);
				}

			} else {
				logger.warn("Error: " + ret.getRetCode() + ","
						+ ret.getErrorInfo());
			}

		} catch (DocumentException e) {
			logger.error("邮件列表接口数据格式转换失败：" + result, e);
		} catch (UnknownHostException e) {
			logger.error("未知的连接地址：" + mailServerIP + ":" + mailServerPort, e);
		} catch (ConnectException e) {
			logger.error("连接邮件服务失败：" + mailServerIP + ":" + mailServerPort, e);
		} catch (Exception e) {
			logger.error("获取未读邮件数时发生异常！", e);
		} finally {
			try {
				if (null != cli)
					cli.close();
			} catch (Exception e) {
				logger.error("未能正常关闭邮件系统的Socket连接！", e);
			}
		}

		return list;
	}

	/**
	 * 根据mid获取具体的某一封邮件并打开
	 * @author H2602965
	 * @param userEmail
	 * @param mid
	 * @return
	 */
	public String getEmailUrl(String userEmail, String mid) {
		String result = userLogin(userEmail);
		String readMailUrl = "mbox/viewmail.jsp?" + result + "&mid=" + mid
				+ "&nav_type=system&fid=1";
		try {
			readMailUrl = java.net.URLEncoder.encode(readMailUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer buff = new StringBuffer("{\"Status\":0");
		buff.append(",\"Data\":[");
		buff.append("{");
		buff.append("\"pp\":\"" +result+ "\"");
		buff.append(",\"qq\":\"" + readMailUrl + "\"");
		buff.append("}]}");
		String JsonString=buff.toString();
		return JsonString;
	}

}
