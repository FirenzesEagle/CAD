var Json=decodeURI(getQueryString("orderdata"));
var Json=JSON.parse(Json);
$(function(){
	//load
	var orderId=Json.orderId;
	var code=Json.code;
	var phone=Json.phone;
	var createtime=Json.createtime;
	var userName=Json.userName;
	var address=Json.address;
	var peisong=Json.peisong;
	var totalPrice=Json.totalPrice;
	var status=Json.status;
	//顺序：订单号，电话，时间，收货人，收货地址，配送费，商品价格，订单状态
	initInfo(code,phone,createtime,userName,address,peisong,totalPrice,status);
	var htmlStr='';
	for(var i=0;i<Json.goods.length;i++){
		var tmpStr='';
		tmpStr+=orderCon(Json.goods[i].goodPic,Json.goods[i].goodName,Json.goods[i].goodPrice,Json.goods[i].goodConfig,Json.goods[i].goodNum);
		htmlStr+=tmpStr;
	}
	$(".orderone-con-con").append(htmlStr);
	
	//点击接单事件
	$(".button-b").on("click",function(){

		var datatosend={
			"id":orderId,
			"status":"receiveorder"
		};
		$.ajax({
			type:"get",
		    url:getGoodOrder,
		    async:"false",
		    data:datatosend,
		    dataType:"json",
		    crossDomain:true,
		    success:function (data) {
			    console.log(data);
			    if(data.result=="success"){
			    	$.MsgBox.Alert("消息","成功接单！");
			    	window.location.href="orderManage.html";
			    }else if(data.result=="error"){
				    console.log(data.ERROMSG);
			    }
	    },
		error: function(XMLHttpRequest, textStatus, errorThrown) {
	                        console.log(XMLHttpRequest.status);
	                }
		});
	});
	
	//点击到货通知
	$(".bt-goods-arrive").on("click",function(){
		
		var htmlTmp='<div class="sendToUser">已通知用户到货</div>';
		$(this).parents(".order-sort-b").append(htmlTmp);
		$(this).remove();
		
		var datatosend={
			"id":orderId,
			"status":"send"
		};
		$.ajax({
			type:"get",
		    url:getGoodOrder,
		    async:"false",
		    data:datatosend,
		    dataType:"json",
		    crossDomain:true,
		    success:function (data) {
			    if(data.result=="success"){
			    	console.log("已通知用户到货");
			    }else if(data.result=="error"){
				    console.log(data.ERROMSG);
			    }
	    },
		error: function(XMLHttpRequest, textStatus, errorThrown) {
	                        console.log(XMLHttpRequest.status);
	                }
		});
	});
	
});
function initInfo(orderNum,orderTel,orderTime,personName,personAddr,orderPeisong,orderPrice,orderStatus){
	var htmlTmp="";
	//console.log(orderStatus);
	if(orderStatus=="payed"){
		htmlTmp='<button class="button-b">我要接单</button>';
		$(".order-status").text("待接单");
		$(".order-sort-b").show();
		$(".order-sort-b").append(htmlTmp);
	}else if(orderStatus=="takedelivery"){
		$(".order-status").text("交易完成");
		$(".order-sort-b").hide();
	}else if(orderStatus=="receiveorder"){
		htmlTmp='<button class="bt-goods-arrive">到货通知</button>';
		$(".order-status").text("已接单");
		//console.log(htmlTmp);
		$(".order-sort-b").show();
		$(".order-sort-b").append(htmlTmp);
	}else if(orderStatus=="send"){
		htmlTmp='<div class="sendToUser">已通知用户到货</div>';
		$(".order-status").text("已接单");
		$(".order-sort-b").show();
		$(".order-sort-b").append(htmlTmp);
	}else{
		$(".order-sort-b").hide();
	}
	$(".order-num span").text(orderNum);
	$(".nat-name span").text(personName);
	$(".nat-time").text(orderTime);
	$(".d-phone").text(orderTel);
	$(".addr-d span").text(personAddr);
	$(".order-sort-peisong").text(orderPeisong);
	$(".order-sort-price").text(orderPrice);
	
}
function orderCon(img,name,price,guige,num){
	var tmpStr='<div class="orderone-con"><div class="orderone-img">'
		+'<img src="'+img+'" /></div>'
		+'<div class="orderone-text"><div class="orderone-left">'
		+'<span class="orderone-name">'+name+'</span>'
		+'<span class="orderone-guige">规格：<em class="one-num">'+guige+'</em>个/份</span>'
		+'</div><div class="orderone-right">'
		+'<div class="orderone-price">&yen;<span class="price-num">'+price+'</span></div>'
		+'<div class="orderone-num">x<span class="orderone-num-n">'+num+'</span></div>'
		+'</div></div></div>';
	
	return tmpStr;
}

/*//获取URL中商铺ID
function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}*/

