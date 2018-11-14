/**
 * 用来在页面上创建分页栏
 * 作者：许健
 * 示例：
 * $(".page").buildPager({           -->  加载分页栏的容器
 *     totalLines:50,                -->  列表清单对象，这里是一个table的所有行
 *     startIndex:0,                 -->  起始页的下标，默认 0
 *     pageSize:12,                  -->  每页显示数量，默认 10
 *     displayNum:5                  -->  显示页码按钮的数量 默认 5
 * });
 */
(function($) {
	
	$.pager = function(target, args) {

		var nowPage = args.startIndex;
		var listNum = args.pageSize;
		var PageNum = args.displayNum;
		var j = args.totalLines
		
		//var obj = args.source;
		var PagesLen = Math.ceil(j/listNum);
		
		function clear() {
			
		}
		
		function build() {
			//内容变换
			//alert("j:" + j);
			//for (var i=0;i<j;i++){
			//	obj[i].style.display="none";
			//}
			//alert("nowPage:" + nowPage);
			//alert("listNum:" + listNum);
			//for (var i=nowPage*listNum; i<(nowPage+1)*listNum; i++){
			//	if (obj[i]) obj[i].style.display="";
			//}
			//分页链接变换
			var strPrevious="",strFrist="",strContent="",strLast="",strNext="";
			var startPage,endPage;
			//alert("PageNum:" + PageNum);
			//alert("PagesLen:" + PagesLen);
			if (PageNum>=PagesLen) {
				startPage=0;
				endPage=PagesLen-1;
			} else {
				var gap = Math.ceil(PageNum/2)-1;
				startPage = nowPage-gap;
				if (startPage<0) startPage=0;
				endPage = startPage+PageNum-1;
				if (endPage>=PagesLen) {
					endPage=PagesLen-1;
					startPage=endPage-PageNum+1;
				}
			}
			//alert("startPage:" + startPage);
			//alert("endPage:" + endPage);
			
			var strPrevious='<a href="javascript:void(0);" id="previous">&lt;上一页</a> ';
			if (startPage>0) {
				strFrist += ' <a href="javascript:void(0);" index="0" class="go">1</a> <span>&hellip;</span> ';
			}
			for (var i=startPage;i<=endPage;i++){
				if (i==(nowPage))
					strContent+='<a href="javascript:void(0);" index="'+i+'" class="cur">'+(i+1)+'</a> ';
				else 
					strContent+='<a href="javascript:void(0);" index="'+i+'" class="go">'+(i+1)+'</a> '
			}
			if (PagesLen-1>endPage) {
				strLast += ' <span>&hellip;</span>  <a href="javascript:void(0);" index="'+(PagesLen-1)+'" class="go">'+(PagesLen)+'</a> ';
			}
			var strNext=' <a href="javascript:void(0);" id="next">下一页&gt;</a>';
			/*
			var PageNum_2=PageNum%2==0?Math.ceil(PageNum/2)+1:Math.ceil(PageNum/2);
			var PageNum_3=PageNum%2==0?Math.ceil(PageNum/2):Math.ceil(PageNum/2)+1;
			var strContent="",startPage,endPage;
			if (PageNum>=PagesLen) {
				startPage=0;
				endPage=PagesLen-1;
			} else if (nowPage<PageNum_2) {
				//首页
				startPage=0;
				endPage=PagesLen-1>PageNum?PageNum:PagesLen-1;
			} else {
				startPage=nowPage+PageNum_3>=PagesLen?PagesLen-PageNum-1: nowPage-PageNum_2+1;
				var t=startPage+PageNum;
				endPage=t>PagesLen?PagesLen-1:t;
			}
			*/
			
			//显示分页链接
		$(target).html("共 "+PagesLen+" 页  "+strPrevious+strFrist+strContent+strLast+strNext+"(共"+ j + " 条记录)");
		     /*	$(target).html(
					"<div class=\"l\">共 " + j + " 条记录， 共 " + PagesLen + " 页</div><div class=\"r\">"
					+strPrevious+strFrist+strContent+strLast+strNext
					+"</div>");
			*/
			//alert(nowPage);
			$(target).attr("curPage", nowPage);
			
			//添加事件
			$(target).children("a.go").click(function(){
				var c = ($(this).attr("index")*1);
				go(c);
			});
			$(target).children("a#next").click(function(){
				next();
			});
			$(target).children("a#previous").click(function(){
				previous();
			});
		}
		function next() {
			var pi = nowPage+1;
			go(pi>PagesLen ? PagesLen : pi);
		}
		function previous() {
			var pi = nowPage-1;
			//alert(pi);
			go(pi>0 ? pi : 0);
		}
		function go(pageIndex) {
			if (nowPage==pageIndex)
				return;
			nowPage = pageIndex;
			$(target).attr("curPage", nowPage);
			clear();
			build();
			if (args.afterChange) {
				args.afterChange();
			}
		}
		
		build();
		
	}
	
	$.fn.buildPager = function(args) {
		
		args = args || {};
		args.totalLines = args.totalLines || 0;
		args.startIndex = args.startIndex || 0;
		args.pageSize = args.pageSize || 10;
		args.displayNum = args.displayNum || 5;
		args.afterChange = args.afterChange || false;
		
		if (args.totalLines <= 0)
			return;
		
		this.each(function() {
			new $.pager(this, args);
		});
		
		return this;
	};
	
})(jQuery);