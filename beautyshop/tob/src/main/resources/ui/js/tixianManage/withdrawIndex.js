var tixianCount;
var openid;
$(function(){
	//搜索跳转事件
	openid = getQueryString("openid");
	console.log(openid);
	$(".button-1").on("click",function(){
		//var shopId = "openid";
		location.href = "/siims/szb/tixian/toTiXianPage.jspx?openid="+openid;
	});
	
	$(".clause-1").on("click",function(){
		//var shopId = "openid";
		location.href = "/siims/szb/tixian/toWithdrawRec.jspx?openid="+openid;
	});
	
	$(".topReturn").on("click",function(){
		window.location.href="/siims/szb/queryflow/toFlow.jspx?openid="+openid;
	});
	
	
	var shop = getShopIdByOpenId(openid);
	var shopIdList = $.parseJSON(shop);
	console.log(shopIdList.SUCCESS);
	if(shopIdList.SUCCESS == "true")
	{
		var leng = shopIdList.DATA.length;
		console.log(leng);
	}
	
	//添加条目DEMO
	//var shopId = "shopidString";
	//var orderInfo = getOrderInfo(shopId);
	//var orderList = $.parseJSON(orderInfo);
	//var len = eval(orderList.list).length;
	
	//var len=10;
	//var number=2312313112312,money=50,time="2015-08-12 12:12",name="李大强",source="支付宝";
	var number,money,time="",name="",source="";
	var tixianMoney = 0;
	
	var shopList = eval(shopIdList.DATA);
	$(shopList).each(function(){
		var shopId = this.id;
		console.log(shopId);
		var orderInfo = getOrderInfo(shopId);
		var orderList = $.parseJSON(orderInfo);
		var len = eval(orderList.list).length;
		
		for(var i=0;i<len;i++){
			number = orderList.list[i].orderId;
			money = orderList.list[i].price;
			time = orderList.list[i].createtime;
			name = orderList.list[i].name;
			source = orderList.list[i].paytype;
			if(source == "0"){
				source = "支付宝支付";
			}else if(source == "1"){
				source = "微信支付";
			}else if(source == "2"){
				source = "会员卡支付";
			}	
			if(source != "会员卡支付"){
				tixianMoney += parseInt(money);
			}	
		}
		
		//shopId = "yangting3";
		shopId = this.id;
		orderInfo = getOrderInfo2(shopId);
		orderList = $.parseJSON(orderInfo);
		if(orderList.SUCCESS == false){
			len = 0;
		}else if(orderList.SUCCESS == true){
			len = eval(orderList.DATA).length;
		}else{
			len = 0;
		}	
		//len = eval(orderList.DATA).length;
		for(var i=0;i<len;i++){
			money = orderList.DATA[i].MONEY;
			tixianMoney += parseInt(money);
		}
	});
		
	if(parseInt(getTiXianCount(openid)) == -1){
		alert("error");
	}else{
		tixianMoney = tixianMoney - parseInt(getTiXianCount(openid));
	}		
	console.log(parseInt(getTiXianCount(openid)));
	//tixianMoney = tixianMoney - parseInt(getTiXianCount("qq"));
	setMoney(tixianMoney);
});

//设置可提现金额
function setMoney(num){
	$("p.money span").text(num);
}

//根据商家id获取该商家已经提现的总金额
//返回金额如果为-1则为获取失败，否则获取金额为该商家已提现的总金额
function getTiXianCount(openid){
	var tixianMoney;
	var getTixianMoneyReqUrl = "/siims/szb/tixianMoney/tixianMoneyRequest.jspx?openId="+openid;
	$.ajax({
		url : getTixianMoneyReqUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		success : function(result) {
			var tixianMoneyList = $.parseJSON(result);
			var len = eval(tixianMoneyList).length;
			if(len == 1){
				tixianMoney = tixianMoneyList[0].money;
			}
			else if(len == 0){
				tixianMoney = 0;
			}
		},
		error : function() {
			tixianMoney = -1;
			console.log("无获取從" + getTixianMoneyReqUrl + "数据。。。");
		}
	});
	return tixianMoney;
}

//根据商家id获取该商家的订单信息
function getOrderInfo(openid){
	var orderInfo = "";
	var getOrderUrl = "/siims/szb/order/queryAllInfoByShopId.jspx?id="+openid;
	$.ajax({
		url : getOrderUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		success : function(result) {
			//var orderList = $.parseJSON(result);
			//var len = eval(orderList).length;
			orderInfo = result;
		},
		error : function() {
			orderInfo = "";
			console.log("无获取從" + getOrderUrl + "数据。。。");
		}
	});
	return orderInfo;
}

//根据商家id获取该商家的订单信息
function getOrderInfo2(openid){
	var orderInfo = "";
	var getOrderUrl = "/vipcard/p2b/billsopencard.jspx?shopId="+openid;
	$.ajax({
		url : getOrderUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		success : function(result) {
			//var orderList = $.parseJSON(result);
			//var len = eval(orderList).length;
			orderInfo = result;
		},
		error : function() {
			orderInfo = "";
			console.log("无获取從" + getOrderUrl + "数据。。。");
		}
	});
	return orderInfo;
}

//根据openid查询商家id
function getShopIdByOpenId(openid){
	var shopIdInfo = "";
	var getShopIdByOpenIdUrl = "/siims/szb/StoreInfoAction/getshopidbyopenid.jspx?openid="+openid;
	$.ajax({
		url : getShopIdByOpenIdUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		success : function(result) {
			shopIdInfo = result;
		},
		error : function() {
			shopIdInfo = "";
			console.log("无获取從" + getShopIdByOpenIdUrl + "数据。。。");
		}
	});
	return shopIdInfo;
}

//获取链接参数
function getQueryString(name) {
	 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}