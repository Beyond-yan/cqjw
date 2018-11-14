package com.gdssoft.cqjt.util;

import java.util.List;

public class PaginationRelated {
	//将List对象转化为page对象，从而实现分页
		@SuppressWarnings("rawtypes")
		public static Page listToPage(List list,Integer pageIndex,Integer pageSize){
			Page page=null;
			int index=pageIndex.intValue();
			int size=pageSize.intValue();
			long totalCount=list.size();
			int fromIndex=0;
			int toIndex=0;
			if(index*size<totalCount){
				fromIndex=(index-1)*size;
				toIndex=index*size;
			}else{
				fromIndex=(index-1)*size;
				toIndex=list.size();
			}
			list=list.subList(fromIndex, toIndex);
			page=new Page(index, size, totalCount, list);
			return page;
		}
}
