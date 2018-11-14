package com.gdssoft.cqjt.controller.portal.sso;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.JsonUtil;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.pojo.UserRelationVo;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.UserRoleService;
import com.gdssoft.cqjt.util.PortalUtils;


@Controller
@RequestMapping("/sso/api.xhtml")
public class SSOLogin extends BaseController  {

	@Value("${lcxzsp.business.url}")
	private String lcxzspBusinessUrl;//浪潮获取代办件地址
	
	@Value("${lcxzsp.sybmol}")
	private String lcxzspSybmol;//浪潮请求标志
	
	@Value("${lcxzsp.target}")
	private String lcxzspTarget;//目标标志
	
	@Autowired
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userService;
	
	@Autowired
	@Resource(name = "userRoleServiceImpl")
	private UserRoleService userRoleService;
	
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;
	
	@RequestMapping(params = "method=login")
	public String login(String key,String loginId,HttpServletResponse response,HttpServletRequest request,Model model) throws IOException {
		UserDetail user  = new UserDetail();
		try {
			System.out.println("loginId:"+loginId);
			String ygjLoginId = PortalUtils.decrypt(loginId);
			UserRelationVo userVo = userService.queryUserRelation(null, ygjLoginId);
			String oaLoginId = userVo.getRelaOaLoginId();
			if(StringUtils.isNotBlank(oaLoginId)){
				user = userService.getUserByCode(oaLoginId);
				if(user != null){
					System.out.println("pwd:"+user.getPassword());
					System.out.println("username:"+user.getUsername());
					System.out.println("userName:"+user.getUserName());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("user",user);
		return  "portal/ssoLogin/login";
	}
	
	@RequestMapping(params = "method=addUser")
	@ResponseBody
	public String addUser(String key,String loginId,String userName,String userEmail, String userMobile,String userPassword,String userCardNo,Integer userSex,HttpServletResponse response,HttpServletRequest request) throws IOException {
		UserDetail user  = new UserDetail();
		user.setUserNo(loginId);
		user.setUserName(PortalUtils.decrypt(userName)); // userName 姓名
		user.setUserMobile(userMobile);
		user.setUserEmail(userEmail);
		if(StringUtils.isBlank(userPassword)){
			userPassword =PortalUtils.encryptSha256("123456");
		} else {
			userPassword = PortalUtils.decrypt(userPassword);
			System.out.println("password:"+userPassword);
			userPassword = PortalUtils.encryptSha256(userPassword);
		}
		user.setPassword(userPassword);
		user.setUserSex(userSex);
		user.setCreateDate(DateUtil.getCurrentDateStr());
		try{
			userService.addSchemaAppUser(user);
			userService.addOacommonUser(user);
			return  "{'code':'0','msg':'添加成功'}";
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("method=addUser",e);
			return  "{'code':'1','msg':'添加失败'}";
		}
		
	}
	@RequestMapping(params = "method=editUser")
	@ResponseBody
	public String editUser(String key,String loginId,String userName,String userEmail, String userMobile,String userPassword,String userCardNo,Integer userSex,HttpServletResponse response,HttpServletRequest request) throws IOException {
		UserDetail user  = new UserDetail();
		user.setUserNo(loginId);
		user.setUserName(userName); // userName 姓名
		user.setUserMobile(userMobile);
		user.setUserEmail(userEmail);
		user.setPassword(userPassword);
		user.setUserSex(userSex);
		try {
			if(StringUtils.isNotBlank(userPassword)){
				userService.editOacommonUser(user);
			}
			userService.editSchemaAppUser(user);
			return  "{'code':'0','msg':'修改成功'}";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("method=editUser", e);
			return  "{'code':'1','msg':'修改失败'}";
		}
		
	}
	@RequestMapping(params = "method=delUser")
	@ResponseBody
	public String delUser(String key,String loginId,HttpServletResponse response,HttpServletRequest request) throws IOException {
		try {
			userService.delOacommonUser(loginId);
			userService.delSchemaAppUser(loginId);
			return  "{'code':'0','msg':'删除成功'}";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("method=editUser", e);
			return  "{'code':'1','msg':'删除失败'}";
		}
	}
	
	@RequestMapping(params = "method=getUserBusinessInfo")
	@ResponseBody
	public String getUserBusinessInfo(String key,String ygjLoginId,String oaLoginId,HttpServletResponse response,HttpServletRequest request) throws IOException {
		String result = "{}";
		try {
			UserRelationVo userVo = userService.queryUserRelation(oaLoginId, ygjLoginId);
			String lcLoginId = userVo.getRelaLcLoginId();
			
			String url = lcxzspBusinessUrl+"?username="+PortalUtils.encrypt(lcLoginId) + "&sybmol="+PortalUtils.encrypt(lcxzspSybmol) + "&token="+ PortalUtils.encrypt(lcLoginId+lcxzspSybmol);
			System.out.println("url"+url);
			result = PortalUtils.httpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("method=getUserBusinessInfo", e);
			return  "{'code':'1','msg':'删除失败'}";
		}
		return  result;
	}
	
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;//URLEncoder.encode(s, "utf-8");
            //br = new BufferedReader(new InputStreamReader(new FileInputStream(   
        //"e:/utf16.txt"), "UTF-16"));   
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    @RequestMapping(params = "method=queryNewsList")
    @ResponseBody
	public String queryNewsList(String key,String loginId,HttpServletResponse response,HttpServletRequest request,Model model) throws IOException {
    	String result = "";
    	int code = 0;
    	String msg = "成功！";
    	String data = "[]";
		try {
			String newsType = request.getParameter("newsType");
			int queryCount = StringUtils.isNotBlank(request.getParameter("queryCount")) ? Integer.parseInt(request.getParameter("queryCount")) : 7;
			List<TextNews> trafficNews = new ArrayList<TextNews>();
			if("1".equals(newsType)){
				trafficNews = textNewsService.getPhotoNewsList(queryCount);
			} else {
				trafficNews = textNewsService.getSiteNewsList(null, null, new String[] { "3" }, new String[] { "交通要闻" }, 0,queryCount);
			}
			if(trafficNews != null && trafficNews.size() > 0){
				data = JsonUtil.toJson(trafficNews);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			code = 1;
			msg = "失败！";
		}
		result = "{'code':"+code+",'msg':'"+msg+"','data':"+data+"}";
		return  result;
	}
    
    @RequestMapping(params = "method=queryNewsDetail")
    @ResponseBody
    public String queryNewsDetail(String key,String loginId,HttpServletResponse response,HttpServletRequest request,Model model) throws IOException {
    	String result = "";
    	int code = 0;
    	String msg = "成功！";
    	String data = "{}";
    	try {
    		String newsId =  request.getParameter("newsId");
    		TextNews textNews =  textNewsService.getTextNewsDetail(newsId);
    		if(textNews != null ){
    			data = JsonUtil.toJson(textNews);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		code = 1;
    		msg = "失败！";
    	}
    	result = "{'code':"+code+",'msg':'"+msg+"','data':"+data+"}";
    	return  result;
    }
}
	
