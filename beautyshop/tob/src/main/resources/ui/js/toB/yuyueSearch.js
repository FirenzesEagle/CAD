/*
 * 服务搜索页面的JS文件
 */
$(function(){
	var shopId=getQueryString("shopid");
	createYuyue("","",shopId);
	//接单事件
	$(".yuyue-ul").on("click",".button-b",function(){
		var $element=$(this);
		$.MsgBox.Confirm("","确认付款？",function(){
			var orderid=$element.parent().parent().data("id");
			var datatosend={
				"orderid":orderid,
				"type":2
			};
			$.ajax({
				type:"get",
				url:'changebespeakstate.jspx',
				data:datatosend,
				crossDomain:true,
				dataType:"json",
				success:function(data){
					if(data.SUCCESS==="true"){
						$element.parents(".yuyue-b").remove();
					}else{
						$.MsgBox.Alert("",data.ERROMSG);
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(XMLHttpRequest.readyState);
					console.log(textStatus);
		        }
			});
			
		});
		
	});
	$(".topReturn1").on("click",function(){
		window.location.href="../order/toOrderManage.jspx?id="+shopId;
	});
	$("#searchButton").on("click",function(){
		var searchinfo=$(".searchInput").val();
		createYuyue(searchinfo,"",shopId);
	});
});
function createYuyue(searchInfo,openId,shopId){
	var datatosend={
		"shopid":shopId,
		"key":searchInfo
	};
	$.ajax({
		type:"get",
		url:'searchbespeakorder.jspx',
		data:datatosend,
		crossDomain:true,
		dataType:"json",
		success:function(data){
			if(data.SUCCESS==="true"){
				//console.log(data);
				if(data.DATA.length===0){
					noneCon();
				}else{
					fullCon();
					
					var yuyueHtml="";
					$.each(data.DATA, function() {
						yuyueHtml+=loadForm(this);
					});
					$(".yuyue-ul").empty();
					$(".yuyue-ul").append(yuyueHtml);
				}
			}else{
				noneCon();
				$.MsgBox.Alert("","搜索结果为空！");
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.readyState);
			console.log(textStatus);
        }
	});

}
function loadForm(itemData){
	var yuyueStr='<li class="yuyue-li" data-id='+itemData.id+'><div class="yuyue-li-l1">'
				+'<div class="yuyue-num">预约编号：'+itemData.id+'</div><div class="yuyue-status">YUYUESTATUS</div></div>'
				+'<div class="yuyue-detail"><div class="yuyue-img">'
				+'<img src='+itemData.serviceimage+'></div>'
				+'<div class="yuyue-text"><div class="yuyue-name">'+itemData.servicename+'</div>'
				+'<div class="price-flex"><div class="yuyue-price">'
				+'STATUSNAME:&yen;'+itemData.price+'</div><div class="yuyue-person-num">'
				+'*1人</div></div></div></div>'
				+'<div class="item-div">'
				+'<span class="person-name">'+itemData.personname+'</span>'
				+'<span class="person-tel">'+itemData.persontel+'</span></div>'
				+'<div class="yuyue-time item-div">'
				+'<span class="time-title">预约时间：</span>'
				+'<span class="time-piece">'+itemData.timepart+'</span></div>'
				+'ISFORPAY'
				+'</li>';
	
	var status1='<div class="yuyue-b"><button class="button-b">确认付款</button></div>';
	var status2="";
	//不同状态不同显示
	if(itemData.state===1){
		yuyueStr=yuyueStr.replace("ISFORPAY",status1);
		yuyueStr=yuyueStr.replace("YUYUESTATUS","待付款").replace("STATUSNAME","待付款");
		
	}else if(itemData.state===0){
		yuyueStr=yuyueStr.replace("ISFORPAY",status2);
		yuyueStr=yuyueStr.replace("YUYUESTATUS","已取消").replace("STATUSNAME","未付款");
		
	}else if(itemData.state===2){
		yuyueStr=yuyueStr.replace("ISFORPAY",status2);
		yuyueStr=yuyueStr.replace("YUYUESTATUS","已完成").replace("STATUSNAME","已付款");
		
	}else{
		yuyueStr=yuyueStr.replace("ISFORPAY",status2);
		yuyueStr=yuyueStr.replace("YUYUESTATUS","已失败").replace("STATUSNAME","未付款");
	}
	return yuyueStr;
}
//订单为空界面
function noneCon(){
	$(".goodsCon").hide();
	$(".none-con").show();
}
function fullCon(){
	$(".none-con").hide();
	$(".goodsCon").show();
}
