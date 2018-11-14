package com.gdssoft.cqjt.controller.console;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.PageBean;
import com.gdssoft.cqjt.pojo.*;
import com.gdssoft.cqjt.service.RedBlackQueryRecordService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import com.centit.gk.msasoa.BlackRedBackRequest;
import com.centit.gk.msasoa.BlackRedSOAP;
import com.centit.gk.msasoa.BlackRedSOAPImplService;
import com.centit.gk.msasoa.BlackRedbackRedblackBack;
import com.centit.gk.msasoa.BlackRedbackRedblackDetail;
import com.centit.gk.msasoa.BlackRedbackRedblackDetailContent;
import com.centit.gk.msasoa.BlackRedbackRedblackType;
import com.centit.gk.msasoa.BlackRedbackRedblackType.RedblackDetailList;
import com.centit.gk.msasoa.BlackRedbackRedblackType.UnionPairList;
import com.centit.gk.msasoa.BlackRedbackUnionPair;
import com.centit.gk.msasoa.BlackRedbackUnionPairContent;
import com.gdssoft.core.tools.SystemContext;


/**
 * 红黑名单服务接口
 * @author GuoY
 * 2017-08-28
 */
@Controller
@RequestMapping("/console/redBalckQuery.xhtml")
public class RedBalckQueryController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Resource(name = "redBlackQueryRecordService")
	private RedBlackQueryRecordService redBlackQueryRecordService;

	private URL wsdlURL = BlackRedSOAPImplService.WSDL_LOCATION;
	private BlackRedSOAPImplService ss = null;// new BlackRedSOAPImplService(wsdlURL, SERVICE_NAME);
	private BlackRedSOAP port = null;//ss.getBlackRedSOAPImplPort();
	private static final QName SERVICE_NAME = new QName("http://msasoa.gk.centit.com/", "BlackRedSOAPImplService");
	private String sysID = "500008-CQJTDZZWBGPT";
//	private String sysID = "500001-test";
	/**
	 * 页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "action=blackRedQueryList")
	public String blackRedQueryList(HttpServletRequest request) {
		
		return "console/sysMgt/redbalckquery/list";
	}
	
	/**
	 * 红黑名单查询
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(params = "action=enterpriseBlacklist")
	public RedBlackTypeData EnterpriseBlacklist (HttpServletRequest request) {
		
		System.out.println("===========================红黑名单类型查询");
		
//		String name = request.getParameter("name");
//		String code = request.getParameter("code");
//		String type = request.getParameter("type");
		String loginname = SystemContext.getUserNO();
		String name = SystemContext.getUserName();
		
		RedBlackTypeData redBlackTypeData = new RedBlackTypeData();
		try{
//			String result = port.getRedBlackType(sysID, loginname, name, "", "all");
			String result = "<?xml version='1.0' encoding='GBK'?><rs><result>success</result><resultcode>200</resultcode><message>查有红黑名单类型！</message><list><redblackTypeList> <redblackType unitedId='1' unitedType='black' unitedName='失信被执行人' orderId='1' /><redblackType unitedId='2' unitedType='black' unitedName='药品经营严重失信者' orderId='2' /><redblackType unitedId='4' unitedType='black' unitedName='安全生产领域失信者' orderId='4' /><redblackType unitedId='6' unitedType='black' unitedName='重大税收违法案件当事人' orderId='6' /><redblackType unitedId='7' unitedType='red' unitedName='AEO认证企业' orderId='1' /><redblackType unitedId='8' unitedType='red' unitedName='A级纳税人' orderId='2' /><redblackType unitedId='9' unitedType='red' unitedName='重庆市市长质量奖' orderId='3' /><redblackType unitedId='10' unitedType='red' unitedName='重庆名牌产品' orderId='4' /><redblackType unitedId='11' unitedType='black' unitedName='统计领域严重失信当事人' orderId='7' /><redblackType unitedId='12' unitedType='red' unitedName='中国标准创新贡献奖' orderId='5' /><redblackType unitedId='13' unitedType='black' unitedName='失信企业（工商）' orderId='5' /><redblackType unitedId='14' unitedType='black' unitedName='海关失信企业' orderId='8' /><redblackType unitedId='15' unitedType='black' unitedName='安全生产领域联合惩戒对象' orderId='9' /><redblackType unitedId='16' unitedType='black' unitedName='房地产领域相关失信责任主体' orderId='10' /><redblackType unitedId='17' unitedType='black' unitedName='政府采购严重违法失信行为记录名单' orderId='11' /><redblackType unitedId='18' unitedType='black' unitedName='文化市场主体黑名单' orderId='12' /><redblackType unitedId='19' unitedType='black' unitedName='土地市场领域诚信黑名单' orderId='13' /></redblackTypeList> </list><version>1</version><updateurl>centit.com</updateurl></rs>";

			return readType(result);//解析
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redBlackTypeData;
	}
	
	/**
	 * 红黑名单详情页面
	 * @param request
	 */
	@RequestMapping(params = "action=enterpriseRedlistpage")
	public String enterpriseRedlistpage(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		String name = request.getParameter("name");
		try {
			name = java.net.URLDecoder.decode(name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String rednum = request.getParameter("rednum");
		String blacknum = request.getParameter("blacknum");
		String redDes = request.getParameter("redDes");
		try {
			redDes = java.net.URLDecoder.decode(redDes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String blackDes = request.getParameter("blackDes");
		try {
			blackDes = java.net.URLDecoder.decode(blackDes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String redDes = request.getParameter("redDes");
//		String blackDes = request.getParameter("blackDes");
		String recordId = request.getParameter("recordId");
		model.addAttribute("name", name);
		model.addAttribute("code", code);
		model.addAttribute("rednum", rednum);
		model.addAttribute("blacknum", blacknum);
		model.addAttribute("redDes", redDes);
		model.addAttribute("blackDes", blackDes);

		// 向数据库插入一次查询记录
		RedBlackQueryRecord record = null;
		if (StringUtils.isNotEmpty(recordId)) {
			record = redBlackQueryRecordService.getById(recordId);
		}
		if (record == null) {
			// recordId无效，生成记录查询
			record = new RedBlackQueryRecord();
			record.setId(UUID.randomUUID().toString().replace("-", ""));
			record.setRedNum(rednum);
			record.setBlackNum(blacknum);
			record.setRedDes(redDes);
			record.setBlackDes(blackDes);
			record.setQueryName(name);
			record.setUserId(SystemContext.getUserId());
			record.setCreateTime(new Date());
			record.setStatus(RedBlackQueryRecord.NOT_COMMIT);
			redBlackQueryRecordService.insert(record);
		}
		model.addAttribute("recordId", record.getId());
		model.addAttribute("record", record);

		return "portal/traffic/redBlackData";
	}

	@ResponseBody
	@RequestMapping(params = "action=getRedBlackQueryRecordList")
	public List<RedBlackQueryRecord> getRedBlackQueryRecordList(HttpServletRequest request) {
		List<RedBlackQueryRecord> list = redBlackQueryRecordService.getByUserId(SystemContext.getUserId());
		Iterator<RedBlackQueryRecord> it = list.iterator();
		while(it.hasNext()){
			RedBlackQueryRecord record = it.next();
			if(RedBlackQueryRecord.COMMITED.equals(record.getStatus())){
				// 移除已经完成的
				it.remove();
				continue;
			}
			record.setCreateTimeStr(DateUtil.dateFormat(record.getCreateTime()));
		}
		return list;
	}
	
	/**
	 * 红黑名单详情
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(params = "action=enterpriseRedlist")
	public RedBlackPageData enterpriseRedlist(HttpServletRequest request) {
 		System.out.println("===========================红黑名单详情");
		String cname = request.getParameter("name");//公司名或人名
		String sname = SystemContext.getUserName();//登录系统用户姓名
		try {
			cname = java.net.URLDecoder.decode(cname,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		String rednum = request.getParameter("rednum");
		String blacknum = request.getParameter("blacknum");
		String recordId = request.getParameter("recordId");
		String loginname = SystemContext.getUserNO();
		RedBlackPageData redBalckPageData = new RedBlackPageData();

		RedBlackQueryRecord record = null;
		if (StringUtils.isNotEmpty(recordId)) {
			record = redBlackQueryRecordService.getById(recordId);
			if (record != null && StringUtils.isNotEmpty(record.getResult())) {
				// 该记录查询过，不用再次查询，直接解析后返回
				try {
					redBalckPageData = readPage(record.getResult());
				} catch (Exception e) {
					e.printStackTrace();
				}
				redBalckPageData.setRecord(record);
				return redBalckPageData;
			}
		}
		// 调用南大先腾接口查询红黑名单
		try{
//			String result = port.getRedBlackPage(sysID, loginname.trim(), sname.trim(), "", cname.trim(), rednum, blacknum );
			String result = "<?xml version='1.0' encoding='GBK'?><rs><result>success</result><resultcode>200</resultcode><message>查有红黑名单！</message><list><redblackTypeList requestId='5be36dbf-0091-43ec-93d4-4d0738fe3ecc' ><redblackType unitedId='1' unitedType='black' unitedName='失信被执行人' ><unionPairList> <unionPair itemName='关于印发对失信被执行人实施联合惩戒的合作备忘录的通知' originDept='最高法' ><unionPairContent content='限制失信被执行人发行企业债券。（第一条）' /><unionPairContent content='限制失信被执行人设立融资性担保公司；限制失信被执行人任职融资性担保公司或金融机构的董事、监事、高级管理人员。（第三条）' /><unionPairContent content='协助限制失信被执行人申请补贴性资金和社会保障资金支持。（第十条）' /><unionPairContent content='在实施投资、税收、进出口等优惠性政策时，查询相关机构及其法定代表人、实际控制人、董事、监事、高级管理人员是否为失信被执行人，对其享受该政策时审慎性参考。（第十一条）' /><unionPairContent content='将失信被执行人和以失信被执行人为法定代表人、实际控制人、董事、监事、高级管理人员的单位，作为重点监管对象，加大日常监管力度，提高随机抽查的比例和频次，并可依据相关法律法规对其采取行政监管措施。（第十二条）' /><unionPairContent content='将失信被执行人信息通过“信用中国”网站、企业信用信息公示系统向社会公布。（第十五条）' /><unionPairContent content='限制失信被执行人使用国有林地项目；限制其申报重点林业建设项目；限制失信被执行人申报国有草原占地项目；限制其申报重点草原保护建设项目。（第二十五条）' /></unionPair> <unionPair itemName='关于在招标投标活动中对失信被执行人' originDept='最高法' ><unionPairContent content='依法必须进行招标的工程建设项目，招标人应当在资格预审公告、招标公告、投标邀请书及资格预审文件、招标文件中明确规定对失信被执行人的处理方法和评标标准，在评标阶段，招标人或者招标代理机构、评标专家委员会应当查询投标人是否为失信被执行人，对属于失信被执行人的投标活动依法予以限制。' /><unionPairContent content='招标人委托招标代理机构开展招标事宜的，应当查询其失信被执行人信息，鼓励优先选择无失信记录的招标代理机构。（第二条）' /><unionPairContent content='依法建立的评标专家库管理单位在对评标专家聘用审核及日常管理时，应当查询有关失信被执行人信息，不得聘用失信被执行人为评标专家。对评标专家在聘用期间成为失信被执行人的，应及时清退。（第三条）' /><unionPairContent content='招标人、招标代理机构在聘用招标从业人员前，应当明确规定对失信被执行人的处理办法，查询相关人员的失信被执行人信息，对属于失信被执行人的招标从业人员应按照规定进行处理。（第四条）' /></unionPair> </unionPairList> <redblackDetailList> <redblackDetail id='075ef9fe-b4e9-4de0-b307-b658fb34f6c5' name='重庆希尔康医药有限公司' code='915001087093211864' ><redblackDetailContent title='法院名称' content='重庆市九龙坡区人民法院' /><redblackDetailContent title='执行依据文号' content='(2017)渝0107民初11786号' /><redblackDetailContent title='民族名称' content='' /><redblackDetailContent title='数据更新时间' content='2018-06-20 01:27:32' /><redblackDetailContent title='政治面目名称' content='' /><redblackDetailContent title='被执行人类型' content='法人' /><redblackDetailContent title='注册地' content='' /><redblackDetailContent title='法定代表人' content='' /><redblackDetailContent title='姓名' content='重庆希尔康医药有限公司' /><redblackDetailContent title='具体情况' content='其他有履行能力而拒不履行生效法律文书确定义务的' /><redblackDetailContent title='执行依据文号单位' content='' /><redblackDetailContent title='照片地址' content='' /><redblackDetailContent title='数据来源' content='市高法院' /><redblackDetailContent title='id' content='075ef9fe-b4e9-4de0-b307-b658fb34f6c5' /><redblackDetailContent title='案号全称' content='(2017)渝0107执4344号' /><redblackDetailContent title='立案日期 ' content='2017-06-21 00:00:00' /><redblackDetailContent title='统一社会信用代码' content='915001087093211864' /><redblackDetailContent title='法院代码' content='重庆市九龙坡区人民法院' /><redblackDetailContent title='履行情况' content='全部未履行' /></redblackDetail> </redblackDetailList> </redblackType></redblackTypeList></list><version>1</version><updateurl>centit.com</updateurl></rs>";
			redBalckPageData = readPage(result);

			// 更新查询记录
			if (record != null) {
				record.setResult(result);
				record.setRequestId(redBalckPageData.getVersion());
				String detailIds = "";
				if (redBalckPageData.getRbTypeList() != null) {
					for (RedBlackType redBlackType : redBalckPageData.getRbTypeList()) {
						if (redBlackType.getRedblackDetailList() != null) {
							for (RedblackDetail redblackDetail : redBlackType.getRedblackDetailList()) {
								detailIds += redblackDetail.getId() + ",";
							}
						}
					}
				}
				if (detailIds.length() > 0) {
					detailIds = detailIds.substring(0, detailIds.length() - 1);
				}
				record.setDetailIds(detailIds);
				record.setUpdateTime(new Date());
				redBlackQueryRecordService.update(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		redBalckPageData.setRecord(record);
		return redBalckPageData;
	}
	
	/**
	 * 个人黑名单查询
	 * @param request
重庆市方丰商贸有限公司    915002315734283034
重庆希尔康医药有限公司    915001087093211864
重庆市大足区完美东方装饰设计有限公司 9150022558018866XC
重庆思优普贸易有限公司 91500107709479493H
重庆永航钢铁集团有限公司  915001157500680206


王叔兵 512222196512081536
袁浩刚 512222197108309257
	 */
	
	/**
	 * 去空
	 * @param value
	 * @return
	 */
	private String replaceNull (String value) {
		if ("".equals(value) || null == value 
						|| "null".equals(value)) {
			value = "无数据";
		}
		return value;
	}
	
	/***
	 * 第一步解析红黑名单类型数据
	 * @param xmlStr
	 * @return
	 */
	public  RedBlackTypeData readType(String xmlStr){
		List<RedBlackType> redBalckTypeLists = new ArrayList<>();
		Document doc;
		try{
			RedBlackTypeData rbData = new RedBlackTypeData();
			doc = DocumentHelper.parseText(xmlStr); // 将字符串转为XML
			Element root = doc.getRootElement();//rs
			rbData.setResult(root.element("result").getText());
			rbData.setMessage(root.element("message").getText());
			rbData.setResultcode(root.element("resultcode").getText());
			rbData.setVersion(root.element("version").getText());
			rbData.setUpdateurl(root.element("updateurl").getText());
			if("success".equals(rbData.getResult())){
				List list = root.selectNodes("list/redblackTypeList");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					Element docdata = (Element) it.next();
					List projectsdata = docdata.selectNodes("redblackType");
					Iterator itdata = projectsdata.iterator();
					while (itdata.hasNext()) {
						RedBlackType rb = new RedBlackType();
						Element elmdata = (Element) itdata.next();
						rb.setUnitedId(elmdata.attributeValue("unitedId"));
						rb.setUnitedName(elmdata.attributeValue("unitedType"));
						rb.setUnitedType(elmdata.attributeValue("unitedName"));
						redBalckTypeLists.add(rb);
						rb=null;
					}
				}
				rbData.setLists(redBalckTypeLists);
			}else{
				rbData.setLists(redBalckTypeLists);
			}
			return rbData;
		}catch(Exception e){
			return new RedBlackTypeData();
		}
	}
	
	public static RedBlackPageData readPage(String xmlStr){
		RedBlackPageData rbpageDate = new RedBlackPageData();
		List<RedBlackType> rbTypeList = new ArrayList<RedBlackType>();

		StringBuffer sbunionPair = new StringBuffer();
		StringBuffer sbunionPairDetail = new StringBuffer();
		StringBuffer sbredblackDL = new StringBuffer();
		
		Document doc;
		try{
			doc = DocumentHelper.parseText(xmlStr); // 将字符串转为XML
			Element root = doc.getRootElement();//获取到根节点rs
			if ("success".equals(root.element("result").getText())
					&& "200".equals(root.element("resultcode").getText())) {
				rbpageDate.setResult(root.element("result").getText());
				rbpageDate.setResultcode(root.element("resultcode").getText());
				rbpageDate.setMessage(root.element("message").getText());
				rbpageDate.setUpdateurl(root.element("updateurl").getText());
				rbpageDate.setVersion(root.element("version").getText());
				List list = root.selectNodes("list/redblackTypeList");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					Element docdata = (Element) it.next();
//					blackRedBackRequest.setRedblackRequestId(docdata.attributeValue("requestId"));
//					blackRedBackRequest.setRequestId(docdata.attributeValue("requestId"));
					rbpageDate.setVersion(docdata.attributeValue("requestId"));
					List projectsdata = docdata.selectNodes("redblackType");
					Iterator itdata = projectsdata.iterator();
					//获取红黑名单类别
					while (itdata.hasNext()) {
						RedBlackType rb = new RedBlackType();
						Element elmdata = (Element) itdata.next();
						rb.setUnitedId(elmdata.attributeValue("unitedId"));
						rb.setUnitedName(elmdata.attributeValue("unitedName"));
						rb.setUnitedType(elmdata.attributeValue("unitedType"));
						rb.setRequestId(docdata.attributeValue("requestId"));
						rbTypeList.add(rb);

						// 放入奖惩措施列表
						List<UnionPair> unionPairList = new ArrayList<UnionPair>();
						List unionPairListNodes = elmdata.selectNodes("unionPairList");
						Iterator it4 = unionPairListNodes.iterator();
						while (it4.hasNext()) {
							Element elmdata4 = (Element) it4.next();
							List unionPairNodes = elmdata4.selectNodes("unionPair");
							Iterator it41 = unionPairNodes.iterator();
							while (it41.hasNext()) {
								Element elmdata41 = (Element) it41.next();
								UnionPair unionPair = new UnionPair();
								unionPair.setItemName(elmdata41.attributeValue("itemName"));
								unionPair.setOriginDept(elmdata41.attributeValue("originDept"));
								unionPair.setContents(new ArrayList<String>());

								List unionPairContentNotes = elmdata41.selectNodes("unionPairContent");
								Iterator it40 = unionPairContentNotes.iterator();
								while (it40.hasNext()) {
									Element elmdata40 = (Element) it40.next();
									unionPair.getContents().add(elmdata40.attributeValue("content"));
								}
								unionPairList.add(unionPair);
							}
						}
						rb.setUnionPairList(unionPairList);

						// 放入红黑名单详情列表
						List projectsdatalredbDetall = elmdata.selectNodes("redblackDetailList");
						List<RedblackDetail> redblackDetailListThis = new ArrayList<RedblackDetail>();
						Iterator it5 = projectsdatalredbDetall.iterator();
						while (it5.hasNext()) {
							Element elmdata5 = (Element) it5.next();
							List projectsdatalredblackdetal = elmdata5.selectNodes("redblackDetail");
							Iterator it6 = projectsdatalredblackdetal.iterator();
							while (it6.hasNext()) {
								Element elmdata7 = (Element) it6.next();
								RedblackDetail redblackDetail = new RedblackDetail();
								redblackDetail.setId(elmdata7.attributeValue("id"));
								redblackDetail.setName(elmdata7.attributeValue("name"));
								redblackDetail.setCode(elmdata7.attributeValue("code"));
								List projectsdatalredblackdetailcon = elmdata7.selectNodes("redblackDetailContent");
								Iterator it7 = projectsdatalredblackdetailcon.iterator();
								List<RedblackDetailContent> redblackDetailContentList1 = new ArrayList<RedblackDetailContent>();
								while (it7.hasNext()) {
									Element elmdata8 = (Element) it7.next();
									RedblackDetailContent redblackDetailContent = new RedblackDetailContent();
									redblackDetailContent.setContent(elmdata8.attributeValue("content"));
									redblackDetailContent.setTitle(elmdata8.attributeValue("title"));
									redblackDetailContentList1.add(redblackDetailContent);
								}
								redblackDetail.setRedblackDetailContentList(redblackDetailContentList1);
								redblackDetailListThis.add(redblackDetail);
							}
						}
						rb.setRedblackDetailList(redblackDetailListThis);
					}
					
				}
				
				rbpageDate.setRbTypeList(rbTypeList);

			}else{
				System.out.println("无红黑名单信息");
			}
			return rbpageDate;
		}catch(Exception e){
			rbpageDate = null;
			return new RedBlackPageData(); 
		}
	}
	

	
	/***
	 * 信息反馈
	 */
	@ResponseBody
	@RequestMapping(params = "action=commitRedBlackBack")
	public String commitRedBlackBack(HttpServletRequest request) {
		JSONObject commitDetailInfoJson = new JSONObject(); // 反馈信息保存
		String recordId = request.getParameter("recordId");
		try {
			recordId = java.net.URLDecoder.decode(recordId,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String requestId = request.getParameter("requestId");
		try {
			requestId = java.net.URLDecoder.decode(requestId,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String detailId = request.getParameter("detailId");
		try {
			detailId = java.net.URLDecoder.decode(detailId,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cname = request.getParameter("name");
		try {
			cname = java.net.URLDecoder.decode(cname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String code = request.getParameter("code");
		// String num = request.getParameter("num");
		// String type = request.getParameter("type");
		String unitedName = request.getParameter("unitedName");
		try {
			unitedName = java.net.URLDecoder.decode(unitedName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String unitedId = request.getParameter("unitedId");//unitedType
		try {
			unitedId = java.net.URLDecoder.decode(unitedId,"UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String unitedType = request.getParameter("unitedType");//unitedType
		try {
			unitedType = java.net.URLDecoder.decode(unitedType,"UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String content = request.getParameter("content");//反馈结果
		try {
			content = java.net.URLDecoder.decode(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String remark = request.getParameter("remark");//反馈备注
		try {
			remark = java.net.URLDecoder.decode(remark,"UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String action = request.getParameter("action1");//奖惩措施
		try {
			action = java.net.URLDecoder.decode(action,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String handleType = request.getParameter("handleType");//处理类型(1.实施奖惩 2.其他奖惩)
		try {
			handleType = java.net.URLDecoder.decode(handleType,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String title3 = request.getParameter("title3");
		try {
			title3 = java.net.URLDecoder.decode(title3,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String content3 = request.getParameter("content3");
		try {
			content3 = java.net.URLDecoder.decode(content3,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String itemNames = request.getParameter("itemNames");
		try {
			itemNames = java.net.URLDecoder.decode(itemNames,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String originDepts = request.getParameter("originDepts");
		try {
			originDepts = java.net.URLDecoder.decode(originDepts,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String unionPairContents = request.getParameter("unionPairContents");
		try {
			unionPairContents = java.net.URLDecoder.decode(unionPairContents,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String loginname = SystemContext.getUserNO();
		String name = SystemContext.getUserName();
        
        BlackRedBackRequest redblackBackList = new BlackRedBackRequest();
        redblackBackList.setRedblackRequestId(requestId);
        redblackBackList.setRequestId(getUUID());//通过uuid
        List<BlackRedbackRedblackBack> redblackBack = new ArrayList<BlackRedbackRedblackBack>();
        BlackRedbackRedblackBack blackRedbackRedblackBack = new BlackRedbackRedblackBack();
        blackRedbackRedblackBack.setName(cname);
        blackRedbackRedblackBack.setCode(code);
        List<BlackRedbackRedblackType> redblackType = new ArrayList<BlackRedbackRedblackType>();
        BlackRedbackRedblackType blackRedbackRedblackType = new BlackRedbackRedblackType();
        /***
         *     
         *     protected String originDept;
    protected String unitedId;
    protected String unitedType;
    protected String unitedName;
    protected String feedbackResult;
    protected String remark;
    protected String action;
    protected String handleType;
    protected String clTime;
         */
        blackRedbackRedblackType.setOriginDept("重庆交委");//部门名字
        blackRedbackRedblackType.setUnitedId(unitedId);//红黑名单bianhao
        blackRedbackRedblackType.setUnitedType(unitedType);//红黑名单类型
        blackRedbackRedblackType.setUnitedName(unitedName);
        blackRedbackRedblackType.setFeedbackResult(content);//反馈内容
        blackRedbackRedblackType.setRemark(remark);//备注
        blackRedbackRedblackType.setAction(action);//奖惩措施
        blackRedbackRedblackType.setHandleType(handleType);//处理类型(1.实施奖惩 2.其他奖惩)
        blackRedbackRedblackType.setClTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        // 保存反馈信息
		commitDetailInfoJson.put("content", content);
		commitDetailInfoJson.put("remark", remark);
		commitDetailInfoJson.put("action", action);
		commitDetailInfoJson.put("handleType", handleType);
		JSONArray unionPairJsonArr = new JSONArray();
		commitDetailInfoJson.put("unionPairs", unionPairJsonArr);

        // 奖惩措施依据
        UnionPairList unionPairList = new UnionPairList();
        List<BlackRedbackUnionPair> unionPairs = new ArrayList<BlackRedbackUnionPair>();

		if (StringUtils.isNotEmpty(itemNames)) {
			Map<String, List<BlackRedbackUnionPair>> unionPairsMap = new HashMap<String, List<BlackRedbackUnionPair>>();
			String[] itemNameArr = itemNames.split(",");
			String[] originDeptArr = originDepts.split(",");
			String[] unionPairContentArr = unionPairContents.split(",");
			for (int i = 0; i < itemNameArr.length; i++) {
				// 重新放入部门名字
				blackRedbackRedblackType.setOriginDept(originDeptArr[i]);
				if (unionPairsMap.get(originDeptArr[i]) == null) {
					unionPairsMap.put(originDeptArr[i], new ArrayList<BlackRedbackUnionPair>());
				}
				List<BlackRedbackUnionPair> unionPairsLi = unionPairsMap.get(originDeptArr[i]);

				BlackRedbackUnionPair blackRedbackUnionPair = new BlackRedbackUnionPair();
				List<BlackRedbackUnionPairContent> unionPairContent = new ArrayList<BlackRedbackUnionPairContent>();
				BlackRedbackUnionPairContent unionContent1 = new BlackRedbackUnionPairContent();
				unionContent1.setTitle("备忘录名称");
				unionContent1.setContent(itemNameArr[i]);
				unionPairContent.add(unionContent1);
				BlackRedbackUnionPairContent unionContent2 = new BlackRedbackUnionPairContent();
				unionContent2.setTitle("措施");
				unionContent2.setContent(unionPairContentArr[i]);
				unionPairContent.add(unionContent2);
				blackRedbackUnionPair.setUnionPairContent(unionPairContent);
				unionPairsLi.add(blackRedbackUnionPair);

				// 同步放入反馈信息
				JSONObject unionPairJson = new JSONObject();
				unionPairJson.put("originDept", originDeptArr[i]);
				unionPairJson.put("itemName", itemNameArr[i]);
				unionPairJson.put("unionContent", unionPairContentArr[i]);
				unionPairJsonArr.add(unionPairJson);
			}

			for (String key : unionPairsMap.keySet()) {
				unionPairs.addAll(unionPairsMap.get(key));
			}
		}
        unionPairList.setUnionPair(unionPairs);
        blackRedbackRedblackType.setUnionPairList(unionPairList);
        
        /***
         * 红黑名单详情
         */
        RedblackDetailList redblackDetailList = new RedblackDetailList();
        List<BlackRedbackRedblackDetail> redblackDetail = new ArrayList<BlackRedbackRedblackDetail>();
        BlackRedbackRedblackDetail blackRedbackRedblackDetail = new BlackRedbackRedblackDetail();
        List<BlackRedbackRedblackDetailContent> redblackDetailContent = new ArrayList<BlackRedbackRedblackDetailContent>();

        String[] titles = title3.split(",");
        String[] contents = content3.split(",");
        for(int i=0;i<contents.length;i++){
			BlackRedbackRedblackDetailContent blackRedbackRedblackDetailContent = new BlackRedbackRedblackDetailContent();
			blackRedbackRedblackDetailContent.setTitle(titles[i]);
			blackRedbackRedblackDetailContent.setContent(contents[i]);
			redblackDetailContent.add(blackRedbackRedblackDetailContent);
        }
        blackRedbackRedblackDetail.setRedblackDetailContent(redblackDetailContent);
        redblackDetail.add(blackRedbackRedblackDetail);
        redblackDetailList.setRedblackDetail(redblackDetail);
        blackRedbackRedblackType.setRedblackDetailList(redblackDetailList);
        
        
        redblackType.add(blackRedbackRedblackType);
        blackRedbackRedblackBack.setRedblackType(redblackType);
        redblackBack.add(blackRedbackRedblackBack);
        
        redblackBackList.setRedblackBack(redblackBack);

        logger.info("============进入信息反馈入参：");
//		logger.info("============sysID=" + sysID);
		logger.info("============loginname=" + loginname);
		logger.info("============name=" + name);
		logger.info("============redblackBackList:");
		logger.info(JSONObject.toJSON(redblackBackList));
		System.out.println("============信息反馈入参：" + JSONObject.toJSON(redblackBackList));

        String sss = port.commitRedBlackBack(sysID, loginname, name, "", redblackBackList);
        String codes = sss.substring(sss.indexOf("<resultcode>")+12, sss.indexOf("<resultcode>")+15);

        logger.info("============信息反馈结果：" + sss);

        System.out.println(sss);
        if(codes.equals("200")){
        	System.out.println("反馈成功");

        	// 反馈成功，更新反馈记录结果
			updateQueryRecordStatus(recordId, detailId, commitDetailInfoJson);

        	return "反馈成功";
        }else{
        	System.out.println("反馈失败");
        	return "反馈失败";
        }
//		updateQueryRecordStatus(recordId, detailId, commitDetailInfoJson);
//        return "反馈失败";
	}

	/**
	 * 更新反馈
	 *
	 * @param recordId
	 * @param detailId
	 * @param commitDetailInfoJson
	 */
	public void updateQueryRecordStatus(String recordId, String detailId, JSONObject commitDetailInfoJson) {
		if (StringUtils.isEmpty(recordId) || StringUtils.isEmpty(detailId)) {
			return;
		}
		RedBlackQueryRecord record = redBlackQueryRecordService.getById(recordId);
		if (record == null || StringUtils.isEmpty(record.getDetailIds())) {
			return;
		}
		String[] detailIdArr = record.getDetailIds().split(",");
		for (String dId : detailIdArr) {
			if (StringUtils.equals(dId, detailId)) {
				// 判断是否重复提交
				if (StringUtils.indexOf(record.getCommitDetailIds(), detailId) > -1) {
					// 已经提交过，跳出循环
					break;
				}
				// 红黑名单详情id一致，可以更新，写入详情id
				if (StringUtils.isEmpty(record.getCommitDetailIds())) {
					record.setCommitDetailIds(dId);
				} else {
					record.setCommitDetailIds(record.getCommitDetailIds() + "," + dId);
				}
				// 判断是否提交完
				String[] commitIdArr = record.getCommitDetailIds().split(",");
				if (detailIdArr.length == commitIdArr.length) {
					// 完成
					record.setStatus(RedBlackQueryRecord.COMMITED);
				} else {
					// 还没有提交完
					record.setStatus(RedBlackQueryRecord.IN_COMMIT);
				}
				// 插入反馈信息
				JSONObject commitInfoJson =
						record.getCommitInfoJson() != null ? record.getCommitInfoJson() : new JSONObject();
				commitInfoJson.put(detailId, commitDetailInfoJson);
				record.setCommitInfo(JSONObject.toJSONString(commitInfoJson));
				record.setCommitInfoJson(commitInfoJson);
				// 更新数据
				record.setUpdateTime(new Date());
				redBlackQueryRecordService.update(record);
				break;
			}
		}
	}
	
	public  String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
  	}

	/**
	 * 红黑名单查询记录页面
	 *
	 * @return
	 */
	@RequestMapping(params = "action=getRedBackQueryRecordPage")
	public String getRedBackQueryRecordPage(HttpServletRequest request, Model model) {
		String recordId = request.getParameter("recordId");
		model.addAttribute("recordId", recordId);
		return "portal/traffic/redBackQueryRecord";
	}

	/**
	 * 红黑名单查询记录分页查询
	 *
	 * @param request
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "action=getRedBackQueryRecordPageData")
	public Map getRedBackQueryRecordPageData(HttpServletRequest request,
													@RequestParam(defaultValue = "1") String page,
													@RequestParam(defaultValue = "30") String limit) {
		String status = request.getParameter("status");
		String beginCreateTime = request.getParameter("beginCreateTime");
		String endCreateTime = request.getParameter("endCreateTime");
		int pageNo = NumberUtils.toInt(page, 1);
		int pageSize = NumberUtils.toInt(limit, 30);
		RedBlackQueryRecord queryRecord = new RedBlackQueryRecord();
		queryRecord.setUserId(SystemContext.getUserId());
		queryRecord.setStatus(status);
		queryRecord.setBeginCreateTime(DateUtil.parseDate(beginCreateTime));
		queryRecord.setEndCreateTime(DateUtil.parseDate(endCreateTime));
		PageBean pageBean = redBlackQueryRecordService.queryPage(queryRecord, pageNo, pageSize);
		Map map = new HashMap();
		map.put("code", 0);
		map.put("msg", "查询成功");
		map.put("count", pageBean.getTotalCount());
		map.put("data", pageBean.getResult());
		return map;
	}
	
	//详情解析
	public static void main(String[] args) {
//		String xmlStr = "<?xml version='1.0' encoding='GBK'?><rs><result>success</result><resultcode>200</resultcode><message>查有红黑名单类型！</message><list><redblackTypeList> <redblackType unitedId='1' unitedType='black' unitedName='失信被执行人' orderId='1' /><redblackType unitedId='2' unitedType='black' unitedName='药品经营严重失信者' orderId='2' /><redblackType unitedId='4' unitedType='black' unitedName='安全生产领域失信者' orderId='4' /><redblackType unitedId='6' unitedType='black' unitedName='重大税收违法案件当事人' orderId='6' /><redblackType unitedId='7' unitedType='red' unitedName='AEO认证企业' orderId='1' /><redblackType unitedId='8' unitedType='red' unitedName='A级纳税人' orderId='2' /><redblackType unitedId='9' unitedType='red' unitedName='重庆市市长质量奖' orderId='3' /><redblackType unitedId='10' unitedType='red' unitedName='重庆名牌产品' orderId='4' /><redblackType unitedId='11' unitedType='black' unitedName='统计领域严重失信当事人' orderId='7' /><redblackType unitedId='12' unitedType='red' unitedName='中国标准创新贡献奖' orderId='5' /><redblackType unitedId='13' unitedType='black' unitedName='失信企业（工商）' orderId='5' /><redblackType unitedId='14' unitedType='black' unitedName='海关失信企业' orderId='8' /><redblackType unitedId='15' unitedType='black' unitedName='安全生产领域联合惩戒对象' orderId='9' /><redblackType unitedId='16' unitedType='black' unitedName='房地产领域相关失信责任主体' orderId='10' /><redblackType unitedId='17' unitedType='black' unitedName='政府采购严重违法失信行为记录名单' orderId='11' /><redblackType unitedId='18' unitedType='black' unitedName='文化市场主体黑名单' orderId='12' /><redblackType unitedId='19' unitedType='black' unitedName='土地市场领域诚信黑名单' orderId='13' /></redblackTypeList> </list><version>1</version><updateurl>centit.com</updateurl></rs>";
//		String xmlStr = "<?xml version='1.0' encoding='GBK'?><rs><result>success</result><resultcode>200</resultcode><message>查有红黑名单！</message><list><redblackTypeList requestId='ae770025-888f-4919-b996-89d75716f316' ><redblackType unitedId='1' unitedType='black' unitedName='失信被执行人' ><unionPairList><unionPair itemName='关于印发对失信被执行人实施联合惩戒的合作备忘录的通知' originDept='最高法' ><unionPairContent content='限制失信被执行人发行企业债券。（第一条）' /><unionPairContent content='限制失信被执行人设立融资性担保公司；限制失信被执行人任职融资性担保公司或金融机构的董事、监事、高级管理人员。（第三条）' /><unionPairContent content='协助限制失信被执行人申请补贴性资金和社会保障资金支持。（第十条）' /><unionPairContent content='在实施投资、税收、进出口等优惠性政策时，查询相关机构及其法定代表人、实际控制人、董事、监事、高级管理人员是否为失信被执行人，对其享受该政策时审慎性参考。（第十一条）' /><unionPairContent content='将失信被执行人和以失信被执行人为法定代表人、实际控制人、董事、监事、高级管理人员的单位，作为重点监管对象，加大日常监管力度，提高随机抽查的比例和频次，并可依据相关法律法规对其采取行政监管措施。（第十二条）' /><unionPairContent content='将失信被执行人信息通过“信用中国”网站、企业信用信息公示系统向社会公布。（第十五条）' /><unionPairContent content='限制失信被执行人使用国有林地项目；限制其申报重点林业建设项目；限制失信被执行人申报国有草原占地项目；限制其申报重点草原保护建设项目。（第二十五条）' /></unionPair><unionPair itemName='关于在招标投标活动中对失信被执行人' originDept='最高法' ><unionPairContent content='依法必须进行招标的工程建设项目，招标人应当在资格预审公告、招标公告、投标邀请书及资格预审文件、招标文件中明确规定对失信被执行人的处理方法和评标标准，在评标阶段，招标人或者招标代理机构、评标专家委员会应当查询投标人是否为失信被执行人，对属于失信被执行人的投标活动依法予以限制。' /><unionPairContent content='招标人委托招标代理机构开展招标事宜的，应当查询其失信被执行人信息，鼓励优先选择无失信记录的招标代理机构。（第二条）' /><unionPairContent content='依法建立的评标专家库管理单位在对评标专家聘用审核及日常管理时，应当查询有关失信被执行人信息，不得聘用失信被执行人为评标专家。对评标专家在聘用期间成为失信被执行人的，应及时清退。（第三条）' /><unionPairContent content='招标人、招标代理机构在聘用招标从业人员前，应当明确规定对失信被执行人的处理办法，查询相关人员的失信被执行人信息，对属于失信被执行人的招标从业人员应按照规定进行处理。（第四条）' /></unionPair></unionPairList><redblackDetailList><redblackDetail id='075ef9fe-b4e9-4de0-b307-b658fb34f6c5' name='重庆希尔康医药有限公司' code='915001087093211864' ><redblackDetailContent title='法院名称' content='重庆市九龙坡区人民法院' /><redblackDetailContent title='执行依据文号' content='(2017)渝0107民初11786号' /><redblackDetailContent title='民族名称' content='' /><redblackDetailContent title='数据更新时间' content='2018-06-19 16:05:33' /><redblackDetailContent title='政治面目名称' content='' /><redblackDetailContent title='被执行人类型' content='法人' /><redblackDetailContent title='注册地' content='' /><redblackDetailContent title='法定代表人' content='' /><redblackDetailContent title='姓名' content='重庆希尔康医药有限公司' /><redblackDetailContent title='具体情况' content='其他有履行能力而拒不履行生效法律文书确定义务的' /><redblackDetailContent title='执行依据文号单位' content='' /><redblackDetailContent title='照片地址' content='' /><redblackDetailContent title='数据来源' content='市高法院' /><redblackDetailContent title='id' content='075ef9fe-b4e9-4de0-b307-b658fb34f6c5' /><redblackDetailContent title='案号全称' content='(2017)渝0107执4344号' /><redblackDetailContent title='立案日期 ' content='2017-06-21 00:00:00' /><redblackDetailContent title='统一社会信用代码' content='915001087093211864' /><redblackDetailContent title='法院代码' content='重庆市九龙坡区人民法院' /><redblackDetailContent title='履行情况' content='全部未履行' /></redblackDetail><redblackDetail id='075ef9fe-b4e9-4de0-b307-b658fb34f6c5' name='重庆希尔康医药有限公司' code='915001087093211864' ><redblackDetailContent title='法院名称' content='重庆市九龙坡区人民法院' /><redblackDetailContent title='执行依据文号' content='(2017)渝0107民初11786号' /><redblackDetailContent title='民族名称' content='' /><redblackDetailContent title='数据更新时间' content='2018-06-19 16:05:33' /><redblackDetailContent title='政治面目名称' content='' /><redblackDetailContent title='被执行人类型' content='法人' /><redblackDetailContent title='注册地' content='' /><redblackDetailContent title='法定代表人' content='' /><redblackDetailContent title='姓名' content='重庆希尔康医药有限公司' /><redblackDetailContent title='具体情况' content='其他有履行能力而拒不履行生效法律文书确定义务的' /><redblackDetailContent title='执行依据文号单位' content='' /><redblackDetailContent title='照片地址' content='' /><redblackDetailContent title='数据来源' content='市高法院' /><redblackDetailContent title='id' content='075ef9fe-b4e9-4de0-b307-b658fb34f6c5' /><redblackDetailContent title='案号全称' content='(2017)渝0107执4344号' /><redblackDetailContent title='立案日期 ' content='2017-06-21 00:00:00' /><redblackDetailContent title='统一社会信用代码' content='915001087093211864' /><redblackDetailContent title='法院代码' content='重庆市九龙坡区人民法院' /><redblackDetailContent title='履行情况' content='全部未履行' /></redblackDetail></redblackDetailList></redblackType><redblackType unitedId='2' unitedType='black' unitedName='药品经营严重失信者' ><unionPairList><unionPair itemName='关于对食品药品生产经营严重失信者开展联合惩戒的合作备忘录的通知' originDept='食品药品监管总局' ><unionPairContent content='在申请政府性资金支持时，采取从严审核或降低支持力' /><unionPairContent content='在申请发行企业债券时，将其列入“从严审核”类，并在发行额度方面予以限制；依法限制公开发行公司债券。（第二部分第二条）' /><unionPairContent content='在申请粮食和食糖进口关税配额时，将其失信信息作为' /></unionPair></unionPairList><redblackDetailList><redblackDetail id='BC33B8A7542DB4C58A260CF17B34873B' name='重庆希尔康医药有限公司' code='915001087093211864' ><redblackDetailContent title='营业地址' content='' /><redblackDetailContent title='直接责任人姓名' content='' /><redblackDetailContent title='地方名称' content='市级部门' /><redblackDetailContent title='法人' content='' /><redblackDetailContent title='公布起始日期' content='' /><redblackDetailContent title='生产经营者名称' content='重庆希尔康医药有限公司' /><redblackDetailContent title='公布终止日期' content='' /><redblackDetailContent title='数据更新时间' content='2017-02-17 09:27:21' /><redblackDetailContent title='处罚时限' content='2015年4月20日' /><redblackDetailContent title='直接责任人职务' content='' /><redblackDetailContent title='违法事由' content='当事人违反《药品经营质量管理规范》经营药品复方磷酸可待因溶液，情节严重。' /><redblackDetailContent title='数据来源' content='市食品药品监管局' /><redblackDetailContent title='行政处罚决定内容' content='依据《中华人民共和国药品管理法》第七十九条、第八十五条，处以：吊销《药品经营许可证》。' /><redblackDetailContent title='id' content='BC33B8A7542DB4C58A260CF17B34873B' /><redblackDetailContent title='许可机关' content='市食品药品监管局' /><redblackDetailContent title='统一社会信用代码' content='915001087093211864' /></redblackDetail></redblackDetailList></redblackType></redblackTypeList></list><version>1</version><updateurl>centit.com</updateurl></rs>";
		String xmlStr = "<?xml version='1.0' encoding='utf-8'?><rs><result>success</result><resultcode>200</resultcode><message>查有红黑名单！</message><list><redblackTypeList requestId='ae770025-888f-4919-b996-89d75716f316'><redblackType unitedId='1' unitedType='black' unitedName='失信被执行人'><unionPairList><unionPair itemName='关于印发对失信被执行人实施联合惩戒的合作备忘录的通知' originDept='最高法'><unionPairContent content='限制失信被执行人发行企业债券。（第一条）'/><unionPairContent content='限制失信被执行人设立融资性担保公司；限制失信被执行人任职融资性担保公司或金融机构的董事、监事、高级管理人员。（第三条）'/><unionPairContent content='协助限制失信被执行人申请补贴性资金和社会保障资金支持。（第十条）'/><unionPairContent content='在实施投资、税收、进出口等优惠性政策时，查询相关机构及其法定代表人、实际控制人、董事、监事、高级管理人员是否为失信被执行人，对其享受该政策时审慎性参考。（第十一条）'/><unionPairContent content='将失信被执行人和以失信被执行人为法定代表人、实际控制人、董事、监事、高级管理人员的单位，作为重点监管对象，加大日常监管力度，提高随机抽查的比例和频次，并可依据相关法律法规对其采取行政监管措施。（第十二条）'/><unionPairContent content='将失信被执行人信息通过“信用中国”网站、企业信用信息公示系统向社会公布。（第十五条）'/><unionPairContent content='限制失信被执行人使用国有林地项目；限制其申报重点林业建设项目；限制失信被执行人申报国有草原占地项目；限制其申报重点草原保护建设项目。（第二十五条）'/></unionPair><unionPair itemName='关于在招标投标活动中对失信被执行人' originDept='最高法'><unionPairContent content='依法必须进行招标的工程建设项目，招标人应当在资格预审公告、招标公告、投标邀请书及资格预审文件、招标文件中明确规定对失信被执行人的处理方法和评标标准，在评标阶段，招标人或者招标代理机构、评标专家委员会应当查询投标人是否为失信被执行人，对属于失信被执行人的投标活动依法予以限制。'/><unionPairContent content='招标人委托招标代理机构开展招标事宜的，应当查询其失信被执行人信息，鼓励优先选择无失信记录的招标代理机构。（第二条）'/><unionPairContent content='依法建立的评标专家库管理单位在对评标专家聘用审核及日常管理时，应当查询有关失信被执行人信息，不得聘用失信被执行人为评标专家。对评标专家在聘用期间成为失信被执行人的，应及时清退。（第三条）'/><unionPairContent content='招标人、招标代理机构在聘用招标从业人员前，应当明确规定对失信被执行人的处理办法，查询相关人员的失信被执行人信息，对属于失信被执行人的招标从业人员应按照规定进行处理。（第四条）'/></unionPair></unionPairList><redblackDetailList><redblackDetail id='075ef9fe-b4e9-4de0-b307-b658fb34f6c5' name='重庆希尔康医药有限公司' code='915001087093211864'><redblackDetailContent title='法院名称' content='重庆市九龙坡区人民法院'/><redblackDetailContent title='执行依据文号' content='(2017)渝0107民初11786号'/><redblackDetailContent title='民族名称' content=''/><redblackDetailContent title='数据更新时间' content='2018-06-19 16:05:33'/><redblackDetailContent title='政治面目名称' content=''/><redblackDetailContent title='被执行人类型' content='法人'/><redblackDetailContent title='注册地' content=''/><redblackDetailContent title='法定代表人' content=''/><redblackDetailContent title='姓名' content='重庆希尔康医药有限公司'/><redblackDetailContent title='具体情况' content='其他有履行能力而拒不履行生效法律文书确定义务的'/><redblackDetailContent title='执行依据文号单位' content=''/><redblackDetailContent title='照片地址' content=''/><redblackDetailContent title='数据来源' content='市高法院'/><redblackDetailContent title='id' content='075ef9fe-b4e9-4de0-b307-b658fb34f6c5'/><redblackDetailContent title='案号全称' content='(2017)渝0107执4344号'/><redblackDetailContent title='立案日期 ' content='2017-06-21 00:00:00'/><redblackDetailContent title='统一社会信用代码' content='915001087093211864'/><redblackDetailContent title='法院代码' content='重庆市九龙坡区人民法院'/><redblackDetailContent title='履行情况' content='全部未履行'/></redblackDetail></redblackDetailList></redblackType><redblackType unitedId='2' unitedType='black' unitedName='药品经营严重失信者'><unionPairList><unionPair itemName='关于对食品药品生产经营严重失信者开展联合惩戒的合作备忘录的通知' originDept='食品药品监管总局'><unionPairContent content='在申请政府性资金支持时，采取从严审核或降低支持力'/><unionPairContent content='在申请发行企业债券时，将其列入“从严审核”类，并在发行额度方面予以限制；依法限制公开发行公司债券。（第二部分第二条）'/><unionPairContent content='在申请粮食和食糖进口关税配额时，将其失信信息作为'/></unionPair></unionPairList><redblackDetailList><redblackDetail id='BC33B8A7542DB4C58A260CF17B34873B' name='重庆希尔康医药有限公司' code='915001087093211864'><redblackDetailContent title='营业地址' content=''/><redblackDetailContent title='直接责任人姓名' content=''/><redblackDetailContent title='地方名称' content='市级部门'/><redblackDetailContent title='法人' content=''/><redblackDetailContent title='公布起始日期' content=''/><redblackDetailContent title='生产经营者名称' content='重庆希尔康医药有限公司'/><redblackDetailContent title='公布终止日期' content=''/><redblackDetailContent title='数据更新时间' content='2017-02-17 09:27:21'/><redblackDetailContent title='处罚时限' content='2015年4月20日'/><redblackDetailContent title='直接责任人职务' content=''/><redblackDetailContent title='违法事由' content='当事人违反《药品经营质量管理规范》经营药品复方磷酸可待因溶液，情节严重。'/><redblackDetailContent title='数据来源' content='市食品药品监管局'/><redblackDetailContent title='行政处罚决定内容' content='依据《中华人民共和国药品管理法》第七十九条、第八十五条，处以：吊销《药品经营许可证》。'/><redblackDetailContent title='id' content='BC33B8A7542DB4C58A260CF17B34873B'/><redblackDetailContent title='许可机关' content='市食品药品监管局'/><redblackDetailContent title='统一社会信用代码' content='915001087093211864'/></redblackDetail></redblackDetailList></redblackType></redblackTypeList></list><version>1</version><updateurl>centit.com</updateurl></rs>";
				RedBlackPageData redblack = readPage(xmlStr);
//		List<List<RedblackDetailContent>> llll = redblack.getRedblackDetailContentList();
//		for (List<RedblackDetailContent> list : llll) {
//			for (RedblackDetailContent redblackDetailContent : list) {
//				System.out.println(redblackDetailContent.getContent());
//				System.out.println(redblackDetailContent.getTitle());
//			}
			System.out.println("---------------------------------------------------");
//		}
		//System.out.println(xmlStr.indexOf("<result>"));
//		String b = xmlStr.substring(xmlStr.indexOf("<resultcode>")+12, xmlStr.indexOf("<resultcode>")+15);
//		System.out.println(b);
	}
	
	
	
}

