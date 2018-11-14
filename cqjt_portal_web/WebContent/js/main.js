
var tabType = 0;

$(function(){
	//考核排名滚动背景颜色
	//$("#colee1 tbody tr:odd").css("background-color","#e8f6fd");
	//$("#colee2 tbody tr:odd").css("background-color","#e8f6fd");
	
	//待办事项  
	$("#sign .tab li").click(function(){
		toggleEvent(this,"#sign .tab li","active","","#sign .tabCon",1);
	});
	//动态 
	$("#sign1 .tab li").click(function(){
		toggleEvent(this,"#sign1 .tab li","active","","#sign1 .tabCon",1);
	});
	//公开公文、公告公示 
	$("#sign2 .tab li").click(function(){
		toggleEvent(this,"#sign2 .tab li","active","","#sign2 .tabCon",1);
	});
	//每日信息、专报信息 
	$("#sign3 .tab li").click(function(){
		toggleEvent(this,"#sign3 .tab li","active","","#sign3 .tabCon",1);
	});
	//考核信息 
	$("#ranking .tab li").click(function(){
		toggleEvent(this,"#ranking .tab li","active","","#ranking .tabCon",1);
	});
});

$(function(){
	$.ajax({
		   type: "POST",
		   url: "some.php",
		   data: "name=John&location=Boston",
		   success: function(msg){
		     alert( "Data Saved: " + msg );
		   }
		});
		
	
});

function checkTab(){
	var a = $("#checkTab .active").html();
	if(a == "每日信息"){
		location.href = '../guest/news.xhtml?face=dailyInfoList';
	}else{
		location.href = '../guest/specialInformation.xhtml?face=specialVoListView';
	}
}

function checkTabRanking(months){
	var a = $("#checkTabRanking .active").html();
	if(a.indexOf("考核(1-")>-1){
		location.href = '../guest/rank.xhtml?face=queryRankView&months='+months+'&flag=2';
	}else{
		location.href = '../guest/rank.xhtml?face=queryRankView&months='+months+'&flag=1';
	}
}

//Tab切換JS_20140411
function toggleEvent(curTrigger,trigger,curClass,usualClass,showContent,toggleSort){
	/*
	* "1"表示菜单切换,当前只显示showContent有且只有一个内容
	* "0"表示只要点击就show出来，再次点击就hide，可显示多个showContent内容
	* curTrigger:當前點擊對象
	* trigger:所有要點擊的對象
	* curClass:顯示當前狀態的class或 展开状态的class
	* usualClass:非當前狀態下的class或 隐藏状态的class
	* showContent:點擊當前對象對應要顯示的內容對象
	*/
	var curTab = $(trigger).index($(curTrigger));
	if("li_weather" == $(curTrigger).attr("id")){
		tabType = 1;
	} else {
		tabType = 0;
	}
	if(toggleSort == 1){
		$(trigger).removeClass(curClass).addClass(usualClass);
		$(curTrigger).removeClass(usualClass).addClass(curClass);
		$(showContent).hide().eq(curTab).show();
		return;
	}
	if(toggleSort == 0){
		if($(showContent).eq(curTab).is(":visible")){
			$(curTrigger).removeClass(curClass).addClass(usualClass);
			$(showContent).eq(curTab).hide();
			return;
		}else{
			$(curTrigger).removeClass(usualClass).addClass(curClass);
			$(showContent).eq(curTab).show();
			return;
		}	
	}
}

//翻滚代码 20140508
function AutoScroll(obj,scrollHeight){
$(obj).find("tbody").animate({
	marginTop:scrollHeight
	},500,function(){
		$(this).css({marginTop:"0px"}).find("tr:first").appendTo(this);
	});
}

//网站搜索
function searchInfo(){
    var key = $("input[name='keyStr']").val();
    var keyStr =  encodeURI(encodeURI(key));
	document.location.href="../guest/index.xhtml?face=siteSearchInfoView&keyStr="+keyStr+"&pageIndex=0";
}

/*=========================================
@ 视频播放JS 
===========================================*/
function videoPlay(playerSrc,videoSrc,vW,vH,videoPic,videoWrapID,vSound){
	//playerSrc:播放器地址
	//videoSrc:视频地址
	//vW：宽
	//vH：高
	//videoPic：视频预载入图片
	//videoWrapID：视频父元素ID
	var s1 = new SWFObject(playerSrc,"single",vW,vH,"6");
	s1.addParam("allowfullscreen","false");
	s1.addParam("wmode","transparent");
	s1.addVariable("file",videoSrc);
	s1.addVariable("image",videoPic);
	s1.addVariable("width",vW);
	s1.addVariable("height",vH);
	s1.addVariable("autostart","true");
	s1.addVariable("repeat","always");
	s1.addVariable("volume",vSound);//*volume：音量值，默认90。参数可以填写0-100之间的整数值，并将存放在浏览器的cookie
	s1.write(videoWrapID);
}

(function($) {
	$.fn.extend({
		RollTitle : function(opt, callback) {
			if (!opt)
				var opt = {};
			var _this = this;
			_this.timer = null;
			_this.lineH = _this.find("tr:first").height();
			_this.line = opt.line ? parseInt(opt.line, 0) : parseInt(_this
					.height()
					/ _this.lineH, 0);
			_this.speed = opt.speed ? parseInt(opt.speed,10) : 3000, //卷动速度，数值越大，速度越慢(毫秒 )
			_this.timespan = opt.timespan ? parseInt(opt.timespan, 13) : 5000; //滚动的时间间隔(毫秒 )
			if (_this.line == 0)
				this.line = 1;
			_this.upHeight = 0 - _this.line * _this.lineH;
			_this.scrollUp = function() {
				_this.animate({
					marginTop : _this.upHeight
				}, _this.speed, function() {
					for (i = 1; i <= _this.line; i++) {
						_this.find("tr:first").appendTo(_this);
					}
					_this.css({
						marginTop : 0
					});
				});
			}
			_this.hover(function() {
				clearInterval(_this.timer);
			}, function() {
				_this.timer = setInterval(function() {
					_this.scrollUp();
				}, _this.timespan);
			}).mouseout();
		}
	})
})
(jQuery);