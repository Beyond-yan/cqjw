$(function() {
	if($("#pageIndex")){
		var indexPage = $("#pageIndex").val();
		var pageCount = $("#pageCount").val();
		var intIndexPage = parseInt(indexPage, 10);
		var intPageCount = parseInt(pageCount, 10);

		if (intIndexPage <= 1 && intIndexPage == intPageCount) {
			$('#firstPage').attr('disabled', true);
			$('#perPage').attr('disabled', true);
			$('#nextPage').attr('disabled', true);
			$('#lastPage').attr('disabled', true);
		}

		if (intIndexPage <= 1) {
			$('#firstPage').attr('disabled', true);
			$('#perPage').attr('disabled', true);
		}
		if (intIndexPage == intPageCount) {
			$('#nextPage').attr('disabled', true);
			$('#lastPage').attr('disabled', true);
		}
	}

});

function getPerPage() {
		//modified by tony f321462
		if($('#perPage').attr('disabled')){
			return;
		}
		var indexPage = $("#pageIndex").val();
		var pageCount = $("#pageCount").val();
		var intIndexPage = parseInt(indexPage, 10);
		var intPageCount = parseInt(pageCount, 10);
		//alert("当前页 "+intIndexPage);
		//alert("总页数 "+intPageCount);
		if (intIndexPage > 1) {
			jumpPage(intIndexPage - 1, 15);
		} else {
			//alert("这已经是第一页了！");
		}
	}

	function getNextPage() {
		//modified by tony f321462
		//alert("asdf"); commented by Cube @130222
		if($('#nextPage').attr('disabled')){
			return;
		}
		var indexPage = $("#pageIndex").val();
		var pageCount = $("#pageCount").val();
		var intIndexPage = parseInt(indexPage, 10);
		var intPageCount = parseInt(pageCount, 10);
		//alert("当前页 "+intIndexPage);
		//alert("总页数 "+intPageCount);
		if (intIndexPage < intPageCount) {

			jumpPage(intIndexPage + 1, 15);
		} else {
			//alert("这已经是最后一页了！（该处待改进）");
		}
	}

	function getLastPage() {
		//modified by tony f321462
		if($('#lastPage').attr('disabled')){
			return;
		}
		var pageCount = $("#pageCount").val();
		var intPageCount = parseInt(pageCount, 10);
		jumpPage(intPageCount, 15);
	}

	function getFirstPage(){
		//modified by tony f321462
		if($('#firstPage').attr('disabled')){
			return;
		}
		 jumpPage(1, 15);
		}

	function freeJump() {
		var jumpP = $("#jumpPage").val();
		var pageCount = $("#pageCount").val();
		var intJumpPage = parseInt(jumpP, 10);
		var intPageCount = parseInt(pageCount, 10);
		if (intJumpPage >= 1 && intJumpPage <= intPageCount) {
			jumpPage(intJumpPage, 15);
		} else {
			alert("需要跳转的页面超出了实际范围");
			var index = $("#pageIndex").val();
			$("#jumpPage").val(index);
		}
	}
	
	//add by tony
	function hanlderPageLinkAttr(firstPageLinkId,prePageLinkId,
			nextPageLinkId,lastPageLinkId,pageIndex,pageConut)
	{
		if (pageIndex <= 1 && pageIndex == pageConut) 
		{
			$('#'+firstPageLinkId).attr('disabled', true);
			$('#'+prePageLinkId).attr('disabled', true);
			$('#'+nextPageLinkId).attr('disabled', true);
			$('#'+lastPageLinkId).attr('disabled', true);
		}
		else if (pageIndex <= 1) 
		{
			$('#'+firstPageLinkId).attr('disabled', true);
			$('#'+prePageLinkId).attr('disabled', true);
			$('#'+nextPageLinkId).attr('disabled', false);
			$('#'+lastPageLinkId).attr('disabled', false);
		}
		else if (pageIndex == pageConut) 
		{
			$('#'+firstPageLinkId).attr('disabled', false);
			$('#'+prePageLinkId).attr('disabled', false);
			$('#'+nextPageLinkId).attr('disabled', true);
			$('#'+lastPageLinkId).attr('disabled', true);
		}
		else
		{
			$('#'+firstPageLinkId).attr('disabled', false);
			$('#'+prePageLinkId).attr('disabled', false);
			$('#'+nextPageLinkId).attr('disabled', false);
			$('#'+lastPageLinkId).attr('disabled', false);
		}
	}