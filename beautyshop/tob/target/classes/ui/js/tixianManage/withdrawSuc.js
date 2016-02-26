var openid;
$(function(){
	openid = getQueryString("openid");
	
	$(".topReturn").on("click",function(){
		window.location.href="/siims/szb/tixian/toTiXianPage.jspx?openid="+openid;
	});	
	
	$(".button-1").on("click",function(){
		window.location.href="/siims/szb/tixian/toTiXianPage.jspx?openid="+openid;
	});	
});

//获取链接参数
function getQueryString(name) {	 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
