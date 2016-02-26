var openid="123";
window.onload=function(){
	$('.mainorder-good').find('.order-content').remove();
	$('.mainorder-service').find('.order-content').remove();
	var orderType=getQueryString("orderState");
	if(orderType=="1"){
		$('.order-type').find("li").each(function(){
			$(this).removeClass("current");
			if($(this).data("type")==2){
				$(this).addClass("current");
			}
		});
		$('.mainorder-good').hide();
		$('.mainorder-service').show();
		$('.mainorder-service').find('.order-content').remove();
		creatServiceOrder(1);
		
	}else{
		var status="create";
		creatGoodOrder(status);
	}
	
}
$(document).ready(function(){
	$('.order-type').delegate("li","click",function(){
		var type=$(this).data("type");
		$(this).siblings().removeClass("current");
		$(this).addClass("current");
		if(type==1){
			$('.mainorder-service').hide();
			$('.mainorder-good').show();
			alert($('.mainorder-good').find('.order-content').remove());
			creatGoodOrder(1);
		}else if(type==2){
			$('.mainorder-good').hide();
			$('.mainorder-service').show();
			$('.mainorder-service').find('.order-content').remove();
			creatServiceOrder(1);
		}
	});
	$('.order-state ').delegate(".state-list-data","click",function(){
		$(this).parent().children().each(function(){
			$(this).removeClass("stateChoosed");
		});
		$(this).addClass("stateChoosed");
		var status=$(this).data("status");
		var type=$('.current').data("type");
		if(type==1){
			$('.mainorder-good').find('.order-content').remove();
			creatGoodOrder(status);
		}else if(type==2){
			$('.mainorder-service').find('.order-content').remove();
			creatServiceOrder(status);
		}
		
	});
	/*商品订单中的按钮操作*/
	$('.mainorder-good').delegate(".bt-cancle","click",function(){
		id=$(this).parents(".order-list-detail").attr("id");
		orderid='#'+id;
		$.MsgBox.Confirm("提示","确认取消订单吗？",function(){
			datatosend={
			"id":id
		};
					
			$.ajax({
						type:"get",
						url:deleteGoodsByOrderId,
						async:"false",
						data:datatosend,
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							
							if(data.result=="success"){
								console.log(data);
								$(orderid).remove();
								$.MsgBox.AlertNo("提示","取消成功")
								//setTimeout($("#mb_box").click(),10000);
							}else if(data.result=="error"){
								console.log(data.detail);
							}
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
									//console.log(data);
			                        console.log(XMLHttpRequest.status);
			                      //  alert(XMLHttpRequest.readyState);
			                      //  alert(textStatus);
			                    }
			});
			
		});
		
		
	});
	$('.mainorder-good').delegate(".bt-confirm","click",function(){
		id=$(this).parents(".order-list-detail").attr("id");
		orderid='#'+id;
		datatosend={
			"id":id,
			"status":"takedelivery"
		};
					
			$.ajax({
						type:"get",
						url:updatestatus,
						async:"false",
						data:datatosend,
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							
							if(data.result=="success"){
								console.log(data);
								$(orderid).remove();
								$.MsgBox.AlertNo("提示","确认收货成功");
								//setTimeout($("#mb_box").click(),10000);
								
							}else if(data.result=="error"){
								console.log(data.detail);
							}
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
									//console.log(data);
			                        console.log(XMLHttpRequest.status);
			                      //  alert(XMLHttpRequest.readyState);
			                      //  alert(textStatus);
			                    }
			});
	});
	$('.mainorder-good').delegate(".bt-del","click",function(){
		id=$(this).parents(".order-list-detail").attr("id");
		orderid='#'+id;
		$.MsgBox.Confirm("提示","确认删除订单吗？",function(){
			datatosend={
			"id":id
		};
					
			$.ajax({
						type:"get",
						url:deleteGoodsByOrderId,
						async:"false",
						data:datatosend,
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							
							if(data.result=="success"){
								console.log(data);
								$(orderid).remove();
								$.MsgBox.AlertNo("提示","删除成功")
								
							}else if(data.result=="error"){
								console.log(data.detail);
							}
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
									//console.log(data);
			                        console.log(XMLHttpRequest.status);
			                      //  alert(XMLHttpRequest.readyState);
			                      //  alert(textStatus);
			                    }
			});
			
		});
		
		
	});
	/* 服务订单点击取消预约按钮*/
	$('.mainorder-service').delegate(".total-button input","click",function(){
		var recordid=$(this).parents(".order-list-detail").data("record");
		var id='#'+$(this).parents(".order-list-detail").attr("id");
		//console.log(recordid+' '+id);
		$.MsgBox.Confirm("提示","确认取消预约吗？",function(){
			
			var datatosend = {
					"personid":1,
					"bespeakid":recordid
					};
					
			$.ajax({
						type:"get",
						url:cancelbespeak,
						async:"false",
						data:datatosend,
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							
							if(data.SUCCESS=="true"){
								$(id).remove();
								//var type=$('.order-type').find('.current').data("type");
								var status=$('.order-state').find('.stateChoosed').data("status");
								$('.mainorder-service').find('.order-content').remove();
								creatServiceOrder(status);
							}else if(data.SUCCESS=="false"){
								console.log(data.ERROMSG);
							}
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
									//console.log(data);
			                        console.log(XMLHttpRequest.status);
			                      //  alert(XMLHttpRequest.readyState);
			                      //  alert(textStatus);
			                    }
			});
			
		});
	});
	
});
/*生成商品订单*/
var orderlist='<li class="order-list-detail" id="ORDERID"><div class="detail-ordernum"><p class="ordernum">订单号：<span class="orderNumber">ORDERNUMBER</span></p>'
+'<p class="orderState">ORDERSTATUS</p></div><div class="order-list-goods"><ul class="good-lists"></ul></div></li>';
var goodlist='<li class="good-list" ><div class="good-list-img"><img src="GOODPICURL" alt="" /></div>'
+'<div class="good-list-detail"><div class="good-list-name"><p class="goodName">GOODNAME</p></div>'
+'<div class="good-list-spec"><div class="service-list-info"><p class="goodDetail">&yen;<span class="goodPrice">GOODPRICE</span>/<span class="goodSpec">GOODCONFIG</span></p>'
+'<p class="goodNum">数量：x<span>GOODNUMBER</span></p></div>'
+'<div class="good-list-shopname"><p class="shopName">SHOPNAME</p></div></div></div></li>';
function creatGoodOrder(a){
	var html='<div class="order-content">';
		html+='<ul class="order-list">';
		html+='</ul>';				
		html+='</div>';
	$('.mainorder-good').append(html);
	var datatosend={
		"id":openid,
		"status":a
	};
	$.ajax({
						type:"get",
						url:querybasicByCustomerId,
						async:"false",
						data:datatosend,
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							
							if(data.result=="success"){
								console.log(data);
								$.each(data.list,function(i){ 
									var status;
									if(data.list[i].status=="create"){
										status="待付款";
									}else if(data.list[i].status=="takedelivery"){
										status="已完成";
									}else{
										
										status="进行中";
									}
									var temp  = orderlist.replace("ORDERID", data.list[i].id).replace("ORDERNUMBER",data.list[i].code).replace("ORDERSTATUS",status);
									$(".order-list").append(temp);
									createGoodList(data.list[i]);
									
								});
								
							}else if(data.result=="error"){
								console.log(data.ERROMSG);
							}
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
									//console.log(data);
			                        console.log(XMLHttpRequest.status);
			                      //  alert(XMLHttpRequest.readyState);
			                      //  alert(textStatus);
			                    }
			});
		
}


function createGoodList(data){
	var orderid='#'+data.id;
	var num=0;
	var datatosend={
			"id":data.id,
		};
		$.ajax({
						type:"get",
						url:queryOrderGoodsByOrderId,
						async:"false",
						data:datatosend,
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							
							if(data.result=="success"){
								console.log(data);
								
								$.each(data.list,function(i){ 
									num+=parseInt(data.list[i].num);
									var temp  = goodlist.replace("GOODPICURL", data.list[i].goodPicUrl).replace("GOODCONFIG",data.list[i].goodsConfig).replace("GOODNAME",data.list[i].goodsName);
									temp=temp.replace("GOODPRICE",data.list[i].goodsconfigPrice).replace("SHOPNAME",data.list[i].shopName).replace("GOODNUMBER",data.list[i].num);
									$(orderid).find('.good-lists').append(temp);
								});
								
								
							}else if(data.result=="error"){
								console.log(data.ERROMSG);
							}
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
									//console.log(data);
			                        console.log(XMLHttpRequest.status);
			                      //  alert(XMLHttpRequest.readyState);
			                      //  alert(textStatus);
			                    }
			});
	creatStateButton(data,num);	
}
function creatStateButton(data,num){
	var html;
	var orderid='#'+data.id;
	switch (data.status){
		case "create":
				html='<div class="order-list-total">';
				html+='<div class="total-ammounts">';
				html+='<p>小计：&yen;<span class="totalNum">'+data.price+'</span></p>';
				html+='<p>数量：<span class="totalNum">'+num+'</span>件</p>';
				//html+='<p >付款剩余<span class="restTime">21min</span></p>';
				html+='</div>';					
				html+='<div class="total-button">'	;
				html+='<input type="button" value="取消订单" class="bt-cancle"/>';
				html+='<input type="button" value="去支付" class="bt-pay"/>';
				html+='</div></div>';	
				$(orderid).append(html);
			break;
		
		case "takedelivery":
				html='<div class="order-list-total">';
				html+='<div class="total-ammounts">';
				html+='<p>小计：&yen;<span class="totalNum">'+data.price+'</span></p>';
				html+='<p>数量：<span class="totalNum">'+num+'</span>件</p>';
				html+='</div>';					
				html+='<div class="total-button">'	;
				html+='<input type="button" value="删除订单" class="bt-del"/>';
				html+='</div></div>';
				$(orderid).append(html);
			break;	
		default:
				html='<div class="order-list-total">';
				html+='<div class="total-ammounts">';
				html+='<p>小计：&yen;<span class="totalNum">'+data.price+'</span></p>';
				html+='<p>数量：<span class="totalNum">'+num+'</span>件</p>';
				html+='</div>';					
				html+='<div class="total-button">'	;
				html+='<input type="button" value="确认收货" class="bt-confirm"/>';
				html+='</div></div>';	
				$(orderid).append(html);
			break;	
					
	}
	
}
/*生成服务订单*/
function creatServiceOrder(state){
	var datatosend = {
			"personid":1,
			"type":state
			};
			
	$.ajax({
				type:"get",
				url:GetbespeakorderforC,
				async:"false",
				data:datatosend,
				dataType:"json",
				crossDomain:true,
				success:function (data) {
					
					if(data.SUCCESS=="true"){
						console.log(data);
						var html='<div class="order-content">';
						html+='<ul class="order-list">';
						if(state=="1"){
							for(var i=0;i<data.DATA.length;i++){
								html+=creatServiceOrderYuyue(data.DATA[i]);
							}
							
						}else if(state=="2"){
							for(var i=0;i<data.DATA.length;i++){
								html+=creatServiceFinished(data.DATA[i]);
							}
							
						}
						
						html+='</ul>';				
						html+='</div>';			
						$('.mainorder-service').append(html);
					}
					
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
							//console.log(data);
	                        console.log(XMLHttpRequest.status);
	                      //  alert(XMLHttpRequest.readyState);
	                      //  alert(textStatus);
	                    }
	});
			
					
						
}
/*生成预约成功订单*/
function creatServiceOrderYuyue(serviceData){
	var html='<li class="order-list-detail" id="'+serviceData.id+'" data-record="'+serviceData.recordid+'">';
		html+='<div class="detail-ordernum"  >';
		html+='<p class="ordernum">订单号：<span class="orderNumber">'+serviceData.id+'</span></p>';
		html+='<p class="orderState">预约成功</p>';
		html+='</div>';			
		html+='<div class="order-list-service">';
		html+='<ul class="service-lists">';
		html+='<li class="service-list">';
		html+='<div class="service-list-img">';
		html+='<img src="'+serviceData.serviceimage+'" alt="" /></div>';
		html+='<div class="service-list-detail">';
		html+='<div class="service-list-name">';
		html+='<p class="serviceName">'+serviceData.servicename+'</p>';
		html+='</div>';
		html+='<div class="service-list-spec">';
		html+='<div class="service-list-info">';
		html+='<p class="serviceDetail">&yen;<span class="goodPrice">'+serviceData.price+'</span>/<span class="goodSpec">1人</span></p>';
		html+='<p class="serviceTime">'+serviceData.timepart+'</p>';
		html+='</div>';
		html+='<div class="service-list-shopname"><p class="shopName">'+serviceData.shopname+'</p></div>';
		html+='</div></div></li>';
		html+='</ul></div>';								
		html+='<div class="order-list-total">';
		html+='<div class="total-ammounts">';				
		html+='<p>小计：&yen;<span class="totalNum">'+serviceData.price+'</span></p>';					
		html+='</div>'	;						
		html+='<div class="total-button">';						
		html+='<input type="button" value="取消预约" class="bt-cancle"/>';						
		html+='</div>';					
		html+='</div>';					
		html+='</li>';		
		return html;
}
/*生成预约已完成订单*/
function creatServiceFinished(serviceData){
	var html='<li class="order-list-detail">';
		html+='<div class="detail-ordernum">';
		html+='<p class="ordernum">订单号：<span class="orderNumber">'+serviceData.id+'</span></p>';
		html+='<p class="orderState">已完成</p>';
		html+='</div>';			
		html+='<div class="order-list-service">';
		html+='<ul class="service-lists">';
		html+='<li class="service-list">';
		html+='<div class="service-list-img">';
		html+='<img src="'+serviceData.serviceimage+'" alt="" /></div>';
		html+='<div class="service-list-detail">';
		html+='<div class="service-list-name">';
		html+='<p class="serviceName">'+serviceData.servicename+'</p>';
		html+='</div>';
		html+='<div class="service-list-spec">';
		html+='<div class="service-list-info">';
		html+='<p class="serviceDetail">&yen;<span class="goodPrice">'+serviceData.price+'</span>/<span class="goodSpec">1人</span></p>';
		html+='<p class="serviceTime">'+serviceData.timepart+'</p>';
		html+='</div>';
		html+='<div class="service-list-shopname"><p class="shopName">'+serviceData.shopname+'</p></div>';
		html+='</div></div></li>';
		html+='</ul></div>';								
		html+='<div class="order-list-total">';
		html+='<div class="total-ammounts">';				
		html+='<p>小计：&yen;<span class="totalNum">'+serviceData.price+'</span></p>';					
		html+='</div>'	;										
		html+='</div>';					
		html+='</li>';		
		return html;
}
function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}