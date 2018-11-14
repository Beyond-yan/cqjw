package com.gdssoft.cqjt.pojo;

import java.util.Date;

import com.gdssoft.core.tools.DateUtil;
/**
 * 信息工作通知实体类
 * @author:GuoY
 * @Date:2016/01/07
 */

public class InfoWorkNotice extends BasePojo
{
	/*
	 * 唯一标识
	 */
	public String WorkId;
	/*
	 * 文档标题
	 */
	
	public String WorkTitle;
	/*
	 * 文档内容
	 */
	public String WorkContent;
	/*
	 * 是否置顶，不为空表示置顶
	 */
	
	public Date WorkTop;
	private String IsTop;
	/*
	 * 是否删除，0表示不删除，1表示删除
	 */
	
	public int WorkDel;
	/*
	 * 创建时间
	 */
	public Date WorkCreateDate;
	private String WorkCreateDateStr;

	/*
	 * 修改时间
	 */
	public Date WorkEditDate;
	private String WorkEditDateStr;

	/*
	 * 创建人
	 */
	public String WorkCreateUserName;
	
	public int WorkPublish;
	public String isPublish;
	
	/*
	 * 发布日期
	 */
	public Date WorkPublishDate;
	private String WorkPublishDateStr;
	
	public Date getWorkPublishDate()
	{
		return WorkPublishDate;
	}

	public void setWorkPublishDate(Date workPublishDate)
	{
		WorkPublishDate = workPublishDate;
	}

	public String getWorkPublishDateStr()
	{
		if(WorkPublishDate != null)
			WorkPublishDateStr = DateUtil.dateFormat(WorkPublishDate);
		return WorkPublishDateStr;
	}

	public int getWorkPublish()
	{
		return WorkPublish;
	}

	public void setWorkPublish(int workPublish)
	{
		WorkPublish = workPublish;
	}
	

	public String getIsPublish()
	{
		isPublish = WorkPublish== 0 ? "未发布":"已发布";
		return isPublish;
	}

	public void setIsPublish(String isPublish)
	{
		this.isPublish = isPublish;
	}

	public String getWorkId()
	{
		return WorkId;
	}

	public void setWorkId(String workId)
	{
		WorkId = workId;
	}

	public String getWorkTitle()
	{
		return WorkTitle;
	}

	public void setWorkTitle(String workTitle)
	{
		WorkTitle = workTitle;
	}

	public String getWorkContent()
	{
		return WorkContent;
	}

	public void setWorkContent(String workContent)
	{
		WorkContent = workContent;
	}

	public Date getWorkTop()
	{
		return WorkTop;
	}

	public void setWorkTop(Date workTop)
	{
		WorkTop = workTop;
	}
	public String getIsTop()
	{
		IsTop= WorkTop == null? "否" : "是";
		return IsTop;
	}

	public void setIsTop(String isTop)
	{
		IsTop = isTop;
	}

	public int getWorkDel()
	{
		return WorkDel;
	}

	public void setWorkDel(int workDel)
	{
		WorkDel = workDel;
	}

	public Date getWorkCreateDate()
	{
		return WorkCreateDate;
	}

	public void setWorkCreateDate(Date workCreateDate)
	{
		WorkCreateDate = workCreateDate;
	}
	
	//格式化日期,将Date转为String
	public String getWorkCreateDateStr()
	{
		if(WorkCreateDate != null)
			WorkCreateDateStr = DateUtil.dateFormat(WorkCreateDate);
		return WorkCreateDateStr;
	}

	public Date getWorkEditDate()
	{
		return WorkEditDate;
	}
	
	public String getWorkEditDateStr()
	{
		if( WorkEditDate != null)
		{
			WorkEditDateStr = DateUtil.dateFormat(WorkEditDate);
		}
		return WorkEditDateStr;
	}

	public void setWorkEditDate(Date workEditDate)
	{
		WorkEditDate = workEditDate;
	}
	
	public String getWorkCreateUserName()
	{
		return WorkCreateUserName;
	}

	public void setWorkCreateUserName(String workCreateUserName)
	{
		this.WorkCreateUserName = workCreateUserName;
	}

}
