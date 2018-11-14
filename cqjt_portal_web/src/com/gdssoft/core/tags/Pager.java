package com.gdssoft.core.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Pager extends TagSupport {
	/**
	 * @author Cube created @131009
	 */
	private static final long serialVersionUID = 1L;
	// private String url; // 链接地址
	private int curpage;// 当前页
	private int pagesize; // 页大小
	private int totalLines; // 总记录条数

	// private int displayPages = 10;// 显示页码个数

	/*
	 * public void setUrl(String url) { this.url = url; }
	 * 
	 * public String getUrl() { return url; }
	 */

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}

	/**
	 * 计算总页数
	 * 
	 * @return
	 */
	private int totalPages() {
		return totalLines % pagesize == 0 ? totalLines / pagesize : totalLines
				/ pagesize + 1;
	}

	@Override
	public int doStartTag() throws JspException {
		if (this.totalLines > this.pagesize) {
			StringBuilder s = new StringBuilder();
			
			s.append("<div class=\"page clearfix\">");
			s.append("<p class=\"page-count l\">");
			s.append("记录：<span>" + totalLines + "</span>  ");
			s.append("总页数：<span>" + totalPages() + "</span>  ");
			s.append("当前页：<span>" + curpage + "</span>");
			s.append("</p>");

			s.append("<ul class=\"page-num r\">");
			if (curpage <= 1) {
				s.append("<li>[<a>首页</a>]</li>");
				s.append("<li>[<a>上一页</a>]</li>");
			} else {
				s.append("<li>[<a href=\"javascript:jumpToPage(1);\" class=\"active\">首页</a>]</li>");
				s.append("<li>[<a href=\"javascript:jumpToPage(" + (curpage - 1) + ");\" class=\"active\">上一页</a>]</li>");
			}
			if (curpage >= totalPages()) {
				s.append("<li>[<a>下一页</a>]</li>");
				s.append("<li>[<a>尾页</a>]</li>");
			} else {
				s.append("<li>[<a href=\"javascript:jumpToPage(" + (curpage + 1) + ");\" class=\"active\">下一页</a>]</li>");
				s.append("<li>[<a href=\"javascript:jumpToPage(" + totalPages() + ");\" class=\"active\">尾页</a>]</li>");
			}
			
			String srp = "";
			Object srpObj = this.pageContext.getServletContext().getAttribute("StaticResourcePath");
			if (null != srpObj) {
				srp = srpObj.toString() + "/";
			}
			
			s.append("<li>");
			s.append("<input type=\"text\" id=\"turnToPage\" class=\"input-pageNum\">");
			s.append("<input type=\"image\" src=\"" + srp + "images/go.gif\" onclick=\"jumpToPage(document.getElementById('turnToPage').value);return false;\"/>");
			s.append("</li>");
			s.append("</ul>");

			
			s.append("</div>");
	
			JspWriter out = this.pageContext.getOut();
			
			try {
				out.println(s.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return EVAL_BODY_INCLUDE;
	}
}