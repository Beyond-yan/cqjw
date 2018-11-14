package com.gdssoft.core.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    /*
	public static String toJsonString(Object o) {
		if(o instanceof Collection) {
			StringBuffer buf = new StringBuffer("");
			Object[] objs = JSONArray.fromObject(o).toArray();
			for (int i = 0; i < objs.length; i ++) {
				if(i!=0) buf.append(",");
				buf.append(JSONObject.fromObject(objs[i]).toString());
			}
			return "{totalCount:" + objs.length + ",root:[" + buf.toString() + "]}";
		}
		return JSONObject.fromObject(o).toString();
	}
	
	public static String listToJsonString(ArrayList<String> list) {
		if(list != null) {
			StringBuffer buf = new StringBuffer("");
			buf.append("{pageDatas:");
			int length = list.size();
			int i = 0;
			for(String t : list) {
				buf.append("[{data:");
				buf.append(t);
				i++;
				if(i == length) {
					buf.append("}]}");
				} else {
					buf.append("},");
				}				
			}
			return buf.toString();
		} 
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static String transferParamsToJsonString(Map<Object,Object> m) {
		if(m != null) {
			List<String> title = (ArrayList<String>)m.get("title");
			StringBuffer buf = new StringBuffer("");
			buf.append("{pageTitle:[{");
			int length = title.size();
			int i = 0;
	    	for(String t : title) {
	    		buf.append("title:\"");
	    		buf.append(t);
	    		i++;
				if(i == length) {
					buf.append("\"}],");
				} else {
					buf.append("\"},{");
				}			
	    	}	    	
	    	buf.append("filename:\"");
	    	buf.append((String)m.get("filename"));
	    	buf.append("\",");
	    	List<ArrayList<String>> data = (List<ArrayList<String>>)m.get("data");		
			buf.append("pageDatas:[{");
			int temlength = length;
			length = data.size() * length;	
			i = 0;
			int j;
			for(ArrayList<String> te : data) {		
				j = 0;
				for(String t : te) {						
					buf.append("data" + j + ":\"");					
					buf.append(t);
					j++;
					i++;
					if(i==length) {
						buf.append("\"}]}");
					} else if(j != temlength){
						buf.append("\",");
					}			
				}	
				if(i < length) {
					buf.append("\"},{");
				}
				
			}
			return buf.toString();
		} 
		return null;
	}
	
	public static String transferPagedDataToJSon(PageBean page) 
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{\"pageIndex\":\"");
		buf.append(page.getPageIndex());
		buf.append("\",\"pageSize\":\"");
		buf.append(page.getPageSize());
		buf.append("\",\"totalCount\":\"");
		buf.append(page.getTotalCount());
		buf.append("\",\"pageCount\":\"");
		buf.append(page.getPageCount());
		buf.append("\",\"pageData\":[");
		int dataListSize = page.getResult().size();
		for(int i=0;i<dataListSize;i++)
		{
			buf.append("{");
			Object obj = page.getResult().get(i);
			
			Method[] methods = obj.getClass().getDeclaredMethods();
			for(int j=0;j<methods.length;j++)
			{
				Method method = methods[j];
				String methodName = method.getName();
				if(methodName.indexOf("get") != -1)
				{
					String fieldName = methodName.substring(3, methodName.length());
					Object value = null;
					try
					{
						value = method.invoke(obj, new Object[]{});
					}
					catch (IllegalArgumentException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (IllegalAccessException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					catch (InvocationTargetException e)
					{
						LogFactory.getLog(JsonUtil.class).warn(e);
					}
					
					buf.append("\"" + fieldName.toUpperCase() + "\"");
					buf.append(":\"");
					buf.append(value == null ? "":value.toString().replace("\\", "&#92;").replace("\n", "&#10;").replace("\r", "&#13;").replace("\"", "\'"));
					buf.append("\",");
				}
			}
			buf.delete(buf.length()-1, buf.length());
			if(i == dataListSize-1)
			{
				buf.append("}");
			}
			else
			{
				buf.append("},");
			}
		}
		buf.append("]}");
		return buf.toString();
	}
	
	
	public static String transferObjectToJSon(Object obj) 
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		Method[] methods = obj.getClass().getDeclaredMethods();
		for(int j=0;j<methods.length;j++)
		{
			Method method = methods[j];
			String methodName = method.getName();
			if(methodName.indexOf("get") != -1)
			{
				String fieldName = methodName.substring(3, methodName.length());
				Object value = null;
				try
				{
					value = method.invoke(obj, new Object[]{});
				}
				catch (IllegalArgumentException e)
				{
					LogFactory.getLog(JsonUtil.class).warn(e);
				}
				catch (IllegalAccessException e)
				{
					LogFactory.getLog(JsonUtil.class).warn(e);
				}
				catch (InvocationTargetException e)
				{
					LogFactory.getLog(JsonUtil.class).warn(e);
				}
				buf.append("\n"+fieldName.toUpperCase()+"\"");
				buf.append(":\"");
				buf.append(value == null ? "":value);
				buf.append("\",");
			}
		}
		buf.delete(buf.length()-1, buf.length());
		buf.append("}");
		return buf.toString();
	}
	*/
	
	public static String toJson(Object obj) {
		Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return g.toJson(obj);
	}
	
}
