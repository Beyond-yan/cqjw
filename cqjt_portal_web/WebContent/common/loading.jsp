<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.load-bg {
	width: 100%;
	height: 100%;
	top: 0px;
	left: 0px;
	position: fixed;
	background: rgba(0, 0, 0, 0.3);
	display: block;
	z-index: 9900
}

.load-box {
	position: fixed;
	width: 180px;
	height: 180px;
	left: 50%;
	top: 50%;
	margin: -90px 0 0 -90px;
	display: block;
	background: #fff;
	border-radius: 10px;
	z-index: 9901;
	line-height: 30px;
	text-align: center;
	color: #000;
	font-size: 18px;
}

.load-box img.icon_loading {
	width: 45%;
	margin: 30px 0 10px;
}

</style>
<div class="load-bg" style="display: none"></div>
<div class="load-box" style="display: none">
    <img id="load_img" class="icon_loading" src="${StaticResourcePath}/images/loading.gif" alt=""/>
    <p>加载中...</p>
</div>
<div class="fail-box" style="display: none">
    <img id="fail_img" class="icon_loading" src="${StaticResourcePath}/images/fail.png" alt=""/>
    <p>失败！</p>
</div>
<div class="success-box" style="display: none">
    <img id="success_img" class="icon_loading" src="${StaticResourcePath}/images/success.png" alt=""/>
    <p>成功！</p>
</div>
<script>

function showLoad(){
	$('.load-bg' ).show();
	$('.load-box').show();
	//setInterval(hideLoad, 1000);//自动隐藏
}
function hideLoad(){
	$('.load-bg' ).hide();
	$('.load-box').hide();
}

function showFial(){
	$('.load-bg' ).show();
	$('.fail-box').show();
	//setInterval(hideFail, 1000);//自动隐藏
}
function hideFail(){
	$('.load-bg' ).hide();
	$('.fail-box').hide();
}

function showSuccess(){
	$('.load-bg' ).show();
	$('.success-box').show();
	//setInterval(hideSuccess, 1000);//自动隐藏
}
function hideSuccess(){
	$('.load-bg' ).hide();
	$('.success-box').hide();
}
</script>