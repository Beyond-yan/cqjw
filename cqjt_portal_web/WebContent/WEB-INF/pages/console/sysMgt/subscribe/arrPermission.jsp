<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>选择部门</title>
	
<link rel="stylesheet" type="text/css" href="${StaticResourcePath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${StaticResourcePath}/easyui/themes/icon.css">   
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="${StaticResourcePath}/easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript">
		/* 下面的代码为内容页value04.html页面里的代码，请自行打开此文件查看代码 */
		var api = frameElement.api, W = api.opener;
		api.button({
		    id:'valueOk',
		    name:'确定',
		    callback:ok
		});
		/* 函数ok即为上面添加按钮方法中callback回调函数调用的函数 */
		function ok(){
		    document.getElementById('trigger').click();
		}
	    function ok1(){
			var nodes = $('#tt').tree('getChecked');
			var jj = "";
			for(i = 0;i<nodes.length;i++){
					jj += nodes[i].id + ',';
			}
			var deptName = jj.substring(0, jj.lastIndexOf(','));
			W.document.getElementById('subsRecvDeptName').value = deptName.replace(/undefined,/g,'');
		}
	    
		function checked(){
			var x;
			var roots = $('#tt').tree('getChildren');
			var array = $("input[name='xxx']").val().split(',');
			$.each(roots,function(i){
				$.each(array,function(j){
					if(roots[i].id == array[j]){
						x = $('#tt').tree('find',array[j]);
						$('#tt').tree('check',x.target);
					}
				})
			})
			
		}
	</script>

</head>
<body onload="checked();">
	<div>
		<ul id="tt" class="easyui-tree" checkbox="true" animate="true"  style="font-size: 50px;">   
		    <c:forEach items="${deptCategoryList }" var="deptCategory">
				<li class="expandable">
					<span>${deptCategory.deptCategory }</span>
					<ul style="display: none;">
						<c:forEach items="${deptList }" var="dept">
							<c:if test="${deptCategory.deptCategory == dept.deptCategory }">
								<li id="${dept.deptName }" name="${dept.deptID }">
									<span>${dept.deptName }</span>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>  
	</div>
	<div>
		<input type="hidden" name="xxx" value="${subsRecvDeptName }">
		<input type="hidden" id="trigger" onclick="ok1();">
	</div>
</body>

</html>