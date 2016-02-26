var openid;
$(function(){
	openid = getQueryString("openid");
	
	$("#timeStart").date_input();
	$("#timeEnd").date_input();
	
	$(".topReturn").on("click",function(){
		window.location.href="/siims/szb/tixian/toTiXianIndex.jspx?openid="+openid;
	});
	
	//搜索事件按下触发
	$("#withdrawSearch").on("click",function(){
		var timeStart=$("#timeStart").val();
		var timeEnd=$("#timeEnd").val();
		
		if(timeStart == ""){
			console.log("timeStart==null");
			timeStart = "0";
		}
		if(timeEnd == ""){
			console.log("timeEnd==null");
			timeEnd = "3";
		}
		
		//var shopId = "openid";
		var tixianInfo = getTixianInfo(openid);
		var tixianList = $.parseJSON(tixianInfo);
		var len = eval(tixianList).length;
		console.log(len);
		
		//添加搜索项示例
		var baseHTML="";//存放添加的HTML
		//var len=5;//记录条数
		var source="";//记录来源
		var money="";//记录钱数
		var time=""//记录时间
		for(var i=0;i<len;i++){
			source = tixianList[i].type;
			if(source == "1"){
				source = "支付宝"
			}else if(source == "2"){
				source = tixianList[i].cardType;
			}
			money = tixianList[i].money;
			time = tixianList[i].time;
			
			var timeDay = time.substring(0,10);
			
			if(timeDay >= timeStart && timeDay <= timeEnd){
				baseHTML+=singleLi(source,money,time);
			}
		}
		//清空UL
		$(".search-result-ul").empty();
		//添加新条目
		$(".search-result-ul").append(baseHTML);

	});
	
	$("#withdrawSearch").click();
	
});


//返回单条记录HTML字符串
function singleLi(source,money,time){
	var basisStr='<li class="clearfix"><div class="left"><span class="">WITHDRAWSOURCE</span>-&yen;'
		+'<span class="">WITHDRAWMONEY</span></div><div class="right"><span class="">WITHDRAWTIME</span>'
		+'</div></li>';
	
	var singleStr=basisStr.replace("WITHDRAWSOURCE",source).replace("WITHDRAWMONEY",money).replace("WITHDRAWTIME",time);
	
	return singleStr;
}


//根据商家id获取该商家的提现申请信息
function getTixianInfo(openid){
	var tixianInfo = "";
	var getTixianInfoUrl = "/siims/szb/tixian/getTixianRequestByShopId.jspx?openId="+openid;
	$.ajax({
		url : getTixianInfoUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		success : function(result) {
			//var orderList = $.parseJSON(result);
			//var len = eval(orderList).length;
			tixianInfo = result;
		},
		error : function() {
			tixianInfo = "";
			console.log("无获取從" + getTixianInfoUrl + "数据。。。");
		}
	});
	return tixianInfo;
}

//获取链接参数
function getQueryString(name) {	 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
