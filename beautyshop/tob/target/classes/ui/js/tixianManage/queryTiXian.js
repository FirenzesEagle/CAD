$(function(){
	$(".query").on("click",function(){
		var tixianRes = getAllTiXian();
		//alert(tixianRes);
		var tixianList = $.parseJSON(tixianRes);
		var len = eval(tixianList).length;
		var tixianHtml = "";
		for(var i=0;i<len;i++){
			var fangshi = tixianList[i].type;
			var state = tixianList[i].isDeal;
			if(fangshi == "1"){
				fangshi = "支付宝账号:"+tixianList[i].zhiFuBaoAccount;
			}
			else if(fangshi == "2"){
				fangshi = tixianList[i].cardType + ":"+tixianList[i].cardNumber;
			}
			if(state == "0"){
				state = "待处理";
			}
			else if(state == "1"){
				state = "已处理";
			}
			var htmlTmp=tixianContent(tixianList[i].time,tixianList[i].shoperName,tixianList[i].money,fangshi,state,"<button>查看</button>");
			//var orderGoods = eval(newOrderList.DATA[i]);
			tixianHtml+=htmlTmp;
		}
		tixianHtml = '<tr><th>申请时间</th><th>申请人</th><th>提现金额</th><th>提现方式</th><th>状态</th><th>操作</th></tr>'+tixianHtml;
		$(".tixian").empty();
		$(".tixian").append(tixianHtml);
	});
});

//建立提现申请单项，返回其HTML字符串
function tixianContent(time,name,price,fangshi,state,operation){
	var contentStr='<tr><th>'+time
	+'</th><th>'+name
	+'</th><th>'+price
	+'</th><th>'+fangshi
	+'</th><th>'+state
	+'</th><th>'+operation					
	+'</th></tr>';
	
	return contentStr;					
}

//获取订单查询接口
function getAllTiXian(){
	var tixianRes = "";
	var getAllTiXianUrl = "/siims/szb/tixian/notOthers/but/wangyun/yes/getTixianRequest.jspx";
	$.ajax({
		url : getAllTiXianUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		// 返回Json类型
		// contentType : "application/json;utf-8",
		success : function(result) {
			tixianRes = result;
		},
		error : function() {
			console.log("无获取從" + getOpenidByCodeUrl + "数据。。。");
		}
	});
	return tixianRes;
}

