package com.gdssoft.cqjt.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.gdssoft.core.tools.WebServiceCall;
import com.gdssoft.cqjt.dao.OADao;
import com.gdssoft.cqjt.dao.OAPublicArchivesDao;
import com.gdssoft.cqjt.pojo.ListItem;
import com.gdssoft.cqjt.service.OAService;

/**
 * @author Jimxu
 */
@Service("oaService")
public class OAServiceImpl implements OAService {

	protected transient final Log logger = LogFactory.getLog(getClass());

	@Value("${oa.integration.enable}")
	private boolean oaIntegrationEnable;

	@Value("${oa.service.username}")
	private String oaServiceUsername;

	@Value("${oa.service.password}")
	private String oaServicePassword;

	@Autowired
	@Resource(name = "OADao")
	private OADao oADao;

	@Override
	public List<ListItem> getTodoList(String userNo, int count) {
		List<ListItem> todoList = new ArrayList<ListItem>();
		if (!oaIntegrationEnable)
			return todoList;

		ListItem todo = oADao.getUserSchemacode(userNo);
		todoList = oADao.getNewFlowTaskList(todo.getUserId(),
				todo.getSchemaCode(), count);

		return todoList;
		/*
		 * if (!oaIntegrationEnable) return todoList;
		 * 
		 * try { WebServiceCall call = new WebServiceCall();
		 * call.setUrl(oaTodoService); call.setOperation(oaTodoOperation);
		 * call.setArgs(new String[]{userNo, String.valueOf(count)}); Document
		 * doc = call.getDocumentResult();
		 * 
		 * Element root = doc.getRootElement();
		 * 
		 * @SuppressWarnings("unchecked") List<Element> data =
		 * root.getChildren(); boolean isFirst = true; for (Iterator<Element>
		 * iter = data.iterator();iter.hasNext();) { Element element =
		 * iter.next(); ListItem todo = new ListItem(); //将计数信息填入第一个待办对象中 if
		 * (isFirst) { todo.setTotlecount(getElementAttributeInteger(root,
		 * "totlecount")); todo.setResultcount(getElementAttributeInteger(root,
		 * "resultcount")); isFirst = false; }
		 * todo.setTitle(getElementAttributeString(element, "title"));
		 * todo.setDate(getElementAttributeDate(element, "date"));
		 * todo.setLink(getElementAttributeString(element, "link"));
		 * todoList.add(todo); } } catch(Exception e){ logger.error("获取待办信息失败！",
		 * e); }
		 */
	}

	@Value("${oa.public.archives.service}")
	private String oaPublicArchivesService;

	@Value("${oa.public.archives.operation}")
	private String oaPublicArchivesOperation;

	@Override
	public String getPublicArchives(int count) {

		if (!oaIntegrationEnable)
			return "{\"Status\":\"1\",\"Error_Msg\":\"未启用OA集成服务！\"}";

		String result = "{\"Status\":\"1\",\"Error_Msg\":\"调用OA服务失败！\"}";

		logger.debug("调用交委公开公文服务！");

		try {
			WebServiceCall call = new WebServiceCall();
			call.setUrl(oaPublicArchivesService);
			call.setOperation(oaPublicArchivesOperation);
			call.setArgs(new String[] { oaServiceUsername, oaServicePassword,
					String.valueOf(count) });

			result = call.getStringResult();

			logger.debug("返回结果：" + result);
		} catch (Exception e) {
			logger.error("获取公开公文失败！", e);
		}

		return result;
	}

	@Value("${oa.archives.detail.service}")
	private String oaPublicArchiveDetailService;

	@Value("${oa.archives.detail.operation}")
	private String oaPublicArchiveDetailOperation;

	@Override
	public String getPublicArchivesDetail(String archivesId, String schema) {

		if (!oaIntegrationEnable)
			return "{\"Status\":\"1\",\"Error_Msg\":\"未启用OA集成服务！\"}";

		String result = "{\"Status\":\"1\",\"Error_Msg\":\"调用OA服务失败！\"}";

		logger.debug("调用交委公开公文详情查询接口！");

		try {
			WebServiceCall call = new WebServiceCall();
			call.setUrl(oaPublicArchiveDetailService);
			call.setOperation(oaPublicArchiveDetailOperation);
			call.setArgs(new String[] { oaServiceUsername, oaServicePassword,
					archivesId, schema });

			result = call.getStringResult();

			logger.debug("返回结果：" + result);
		} catch (Exception e) {
			logger.error("获取公开公文详情失败！", e);
		}

		return result;
	}

	@Autowired
	@Resource(name = "oaPublicArchivesDao")
	private OAPublicArchivesDao oaPublicArchivesDao;

	@Override
	public String getUnReceiveArchives(String userNo, int count) {
		String result;
		List<ListItem> list = oaPublicArchivesDao.getUnReceiveArchives(userNo,
				count);
		StringBuffer buff = new StringBuffer("{\"Status\":0");
		buff.append(",\"Data\":[");
		for (int i = 0; i < list.size(); i++) {
			buff.append("{");
			buff.append("\"sendDep\":\"" + list.get(i).getLink() + "\"");
			buff.append(",\"subject\":\"" + list.get(i).getTitle() + "\"");
			buff.append("},");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");
		result = buff.toString();
		return result;
	}

	@Override
	public String getUnReceiveArchivesCount(String userNo) {
		List<ListItem> list = oaPublicArchivesDao.getUnReceiveArchives(userNo,
				1);
		String totleCount = "";
		if (list.size() > 0) {
			totleCount = list.get(0).getTotlecount().toString();
		} else {
			totleCount = "0";
		}

		return totleCount;
	}
}
