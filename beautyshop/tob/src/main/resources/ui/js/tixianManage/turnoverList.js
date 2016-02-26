var openid;
$(function(){
	var code=decodeURI(getQueryString("code"));
	if(code!="null"&&code!="undefined"){
		$.ajax({
			type:"get",
			url:'http://182.92.4.200/BxyhWeixinServer/getopenid?code='+code,
			async:false,
			data:/*code*/"",
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				openid = data.openid;
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
		                console.log(XMLHttpRequest.status);
		              //  alert(XMLHttpRequest.readyState);
		              //  alert(textStatus);
		            }
		});
	}
	$("#timeStart").date_input();
	$("#timeEnd").date_input();
	
	$("#turnoverSearch").click();
	
	$(".tixian").on("click",function(){
		window.location.href="";
	});
	$(".clause-line").on("click",function(){
		$(this).toggleClass("line-active");
		$(this).next(".clause-3-ul").toggleClass("ul-active");
	});
	
	//openid = getQueryString("openid");
	
	//var openid = "123";//这个获取网页的openid先
	var shop = getShopIdByOpenId(openid);
	//console.log(shop);
	var shopIdList = $.parseJSON(shop);
	console.log(shopIdList.SUCCESS);
	if(shopIdList.SUCCESS == "true")
	{
		var leng = shopIdList.DATA.length;
		console.log(leng);
	}
	
	
	//搜索按钮点击事件
	$("#turnoverSearch").on("click",function(){
		console.log("clickButton....");
		//记录开始结束时间
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
		
		//var len=10;
		//var number=2312313112312,money=50,time="2015-08-12 12:12",name="李大强",source="支付宝";
		var number,money,time="",name="",source="";
		var baseHTML="";
		var baseHTML1 = "";
		var baseHTML2 = "";
		var flowMoney = 0;
		var tixianMoney = 0;
		var count = 0;
		
		//通过循环获取shopid获取流水信息
		var shopList = eval(shopIdList.DATA);
		$(shopList).each(function(){
			console.log(this.id);
			
			//添加条目DEMO
			//var shopId = "shopidString";
			//var shopId = "shopidString";
			var shopId = this.id;
			var orderInfo = getOrderInfo(shopId);
			console.log(orderInfo);
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
				var timeDay = time.substring(0,10);
				if(source != "会员卡支付"){
					if(timeDay >= timeStart && timeDay <= timeEnd){
						baseHTML1 += addLi(number,money,time,name,source);
						tixianMoney += parseInt(money);
					}
				}
				
				if(timeDay >= timeStart && timeDay <= timeEnd){
					baseHTML+=addLi(number,money,time,name,source);
					baseHTML2 += addLi(number,money,time,name,source);
					flowMoney += parseInt(money);
					count++;
				}		
			}
			
			//shopId = "yangting3";
			orderInfo = getOrderInfo2(shopId);
			orderList = $.parseJSON(orderInfo);
			if(orderList.SUCCESS == false){
				len = 0;
			}else if(orderList.SUCCESS == true){
				len = eval(orderList.DATA).length;
			}else{
				len = 0;
			}		
			for(var i=0;i<len;i++){
				number = orderList.DATA[i].TYPE;
				money = orderList.DATA[i].MONEY;
				time = orderList.DATA[i].TIME;
				name = orderList.DATA[i].NAME;
				source = orderList.DATA[i].PAYTYPE;
				
				var timeDay = time.substring(0,10);
				if(timeDay >= timeStart && timeDay <= timeEnd){
					baseHTML+=addLi(number,money,time,name,source);
					baseHTML1+=addLi(number,money,time,name,source);
					tixianMoney += parseInt(money);
					count++;
				}
			}
		});
			
		
		$(".order-con").find(".clause-line").find(".left").find(".clause-3-num span").html(count);
		$(".turnover-con").find(".clause-line").find(".left").find(".clause-3-num span").html(flowMoney);		
		if(parseInt(getTiXianCount(openid)) == -1){
			alert("error");
		}else{
			tixianMoney = tixianMoney - parseInt(getTiXianCount(openid));
		}		
		console.log(parseInt(getTiXianCount(openid)));
		$(".withdraw-con").find(".clause-line").find(".left").find(".clause-3-num span").html(tixianMoney);
		
		//添加到订单里
		$(".order-con").find(".clause-3-ul").empty();
		$(".order-con").find(".clause-3-ul").append(baseHTML);
		//添加到流水里
		$(".turnover-con").find(".clause-3-ul").empty();
		$(".turnover-con").find(".clause-3-ul").append(baseHTML2);
		//添加到提现里
		$(".withdraw-con").find(".clause-3-ul").empty();
		$(".withdraw-con").find(".clause-3-ul").append(baseHTML1);
	});
	
	$("#turnoverSearch").click();
});

function addLi(number,money,time,name,source){
	var basisStr='<li class="clearfix"><div class="left"><p class="ul-big-font">订单号：ORDERNUM</p>'
		+'<p class="ul-small-font">ORDERTIME</p></div><div class="right">'
		+'<p class="ul-big-font">&yen;ORDERMONEY</p><p class="ul-small-font">ORDERNAME-ORDERSOURCE</p></div></li>';
	
	var singleStr=basisStr.replace("ORDERNUM",number).replace("ORDERTIME",time).replace("ORDERMONEY",money).replace("ORDERNAME",name).replace("ORDERSOURCE",source);
	
	return singleStr;					
					
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

//点击提现按跳到提现申请页面
function toTixian(obj){
	//var shopId = "openid";
	obj.href = "/siims/szb/tixian/toTiXianIndex.jspx?openid="+openid;
}
//获取链接参数
function getQueryString(name) {
	 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
