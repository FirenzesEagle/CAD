/*
 * 商品管理页面的JS文件
 */
//var shopId=decodeURI(getQueryString("shopID"));
var shopId=getQueryString("id");
var noneconStatus;
//console.log(shopId);
//任烁鑫  默认加载新订单
window.onload=function(){	
	var status=0;
	getGoodsOrderIds(status);	
}
$(function(){

	//初始化今天的状态
	$('.form-date').append(creatDate());
	$('.yy-info').append(creatForm());
	loadForm(new Date());
	//点击两个顶级菜单事件
	$('.line-tab').delegate("li","click",function(){

		var tab=$(this).data("tab");
		$(this).siblings().children().removeClass("choosed");
		$(this).children().addClass("choosed");
		if(tab=="1"){
			$('.serviceCon').hide();
			$('.goodsCon').show();
			if(noneconStatus==1){
				$('.none-con').show();
			}else{
				$('.none-con').hide();
			}
		}else if(tab=="2"){
			$('.goodsCon').hide();
			$('.serviceCon').show();
			$('.none-con').hide();
		}
	});
	//点击三个商品订单状态框事件
	$(".page-tab li").on("click",function(){
		//点击订单tab样式CSS变化
		$(".page-tab li").removeClass("tab-active");
		$(this).addClass("tab-active");
		
		getGoodsOrderIds($(this).index());
	});
	
	//接单事件
	$(".order-sort-con").on("click",".button-b",function(){
		$(this).parents(".order-sort").remove();
		
		var orderId=$(this).parents(".order-sort").attr("id");
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
			    }else if(data.result=="error"){
				    console.log(data.ERROMSG);
			    }
	    },
		error: function(XMLHttpRequest, textStatus, errorThrown) {
	                        console.log(XMLHttpRequest.status);
	                }
		});
	});
	//任烁鑫  到货通知事件 
	$(".order-sort-con").on("click",".bt-goods-arrive",function(){
		var orderId=$(this).parents(".order-sort").attr("id");
		
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
	//搜索跳转事件
	$(".order-search").on("click",function(){
		if($(".tabbox.choosed").parent().data("tab")==1){
			window.location.href="";
		}else if($(".tabbox.choosed").parent().data("tab")==2){
			window.location.href="../bespeakorder/tosearchbespeakorder.jspx?shopid="+shopId;
		}
	});
	//服务订单点击日期事件
	$('.form-date').delegate(".date-items li","click",function(){
		if($(this).hasClass("date-all")===true){
			window.location.href="../bespeakorder/tosearchbespeakorder.jspx?shopid="+shopId;
		}else{
			$(this).parent().children().each(function(){
				$(this).children().removeClass("active");
			});
			$(this).children().addClass("active");
			$('.yy-info').children().remove();
			var rq=$(this).children().data("rq");
			$('.yy-info').append(creatForm());
			loadForm(rq);
		}
		
	});
	//查看某一时间点预约详情事件
	$('.yy-info').delegate(".tdDiv","click",function(){
		if($(this).hasClass("td-wc")===true||$(this).hasClass("td-nowc")===true){
			var speakid=($(this).data("id"));
			var getonedata={
				"speakid":speakid
			};
			$element=$(this);
			/*var data={
			    "SUCCESS": "true",
			    "DATA": {
			        "id": "1",
			        "isdelete": 1,
			        "price": 20,
			        "state": 0,
			        "shopid": "1",
			        "recordid": "1",
			        "personid": "1",
			        "personname": "12",
			        "persontel": "12",
			        "servicename": "12",
			        "serviceimage": "",
			        "timepart": "4:00 ~ 5:00",
			        "createtime": "Sep 16, 2015 10:22:20 AM",
			        "finishtime": "Sep 16, 2015 10:22:24 AM"
			    }
			};*/
			
			$.ajax({
				type:"get",
			    url:"../bespeakorder/getbespeakorderforone.jspx",
			    async:"false",
			    data:getonedata,
			    dataType:"json",
			    crossDomain:true,
			    success:function (data) {
				    if(data.SUCCESS==="true"){
				    	//console.log(data);
				    	var numid=data.DATA.id;
				    	var msgData={
						  	"num":"***"+numid.substr(numid.length-6),
						  	"picUrl":data.DATA.serviceimage,
						  	"serviceName":data.DATA.servicename,
						  	"serviceMoney":data.DATA.price,
						  	"serviceStatus":"",
						  	"personNum":1,
						  	"time":data.DATA.timepart,
						  	"personName":data.DATA.personname,
						  	"personTel":data.DATA.persontel
						};
				    	if($element.hasClass("td-wc")===true){
				    		msgData.serviceStatus="已付款";
							$.ServiceBox.AlertNo(msgData);
						}else if($element.hasClass("td-nowc")===true){
							msgData.serviceStatus="未付款";
							
							$.ServiceBox.Alert(msgData,function(){
								var orderid=$element.data("orderid");
								var statedata={
									"orderid":orderid,
									"type":2
								};
								$.ajax({
									type:"get",
									url:'../bespeakorder/changebespeakstate.jspx',
									data:statedata,
									crossDomain:true,
									dataType:"json",
									success:function(data){
										//console.log(data);
										if(data.SUCCESS==="true"){
											$element.removeClass("td-nowc");
											$element.addClass("td-wc");
											
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
						}
				    }else{
					    console.log(data.ERROMSG);
				    }
		    },
			error: function(XMLHttpRequest, textStatus, errorThrown) {
		                        console.log(XMLHttpRequest.status);
		                }
			});
		}
	});
});
//建立订单通用模板，返回其HTML字符串
function orderCom(orderId,orderNum,orderPrice,orderPeisong,statusIndex){
	//console.log(statusIndex);
	var orderSortstr='<li class="order-sort" id="ORDERID" data-status="STATUS"><div class="order-sort-l1">'
			+'<div class="order-num">订单号：ORDERNUM</div>'
			+'ORDERSTATUS</div><div class="order-sort-r">合计：&yen;<span class="order-sort-price">ORDERPRICE'
			+'</span>'
			+'(含配送费&yen;<span class="order-sort-peisong">ORDERPEISONG'
			+'</span>)</div>IFNEW</li>';
		
	var status1='<div class="order-status">待接单</div>';
	var status2='<div class="order-status">已接单</div>';
	var status3='<div class="order-status">交易完成</div>';
		
	if(statusIndex==="payed"){
		orderSortstr=orderSortstr.replace("IFNEW",'<div class="order-sort-b"><button class="button-b">我要接单</button></div>').replace("ORDERSTATUS",status1).replace("STATUS",statusIndex);
	}else if(statusIndex==="receiveorder"){
		//任烁鑫   添加了到货通知按钮，同时修改了CSS文件
		orderSortstr=orderSortstr.replace("IFNEW",'<div class="order-sort-b"><button class="bt-goods-arrive">到货通知</button></div>').replace("ORDERSTATUS",status2).replace("STATUS",statusIndex);
	}else if(statusIndex==="send"){
		orderSortstr=orderSortstr.replace("IFNEW",'<div class="order-sort-b"><div class="sendToUser">已通知用户到货</div></div>').replace("ORDERSTATUS",status2).replace("STATUS",statusIndex);
	}else if(statusIndex==="takedelivery"){
		orderSortstr=orderSortstr.replace("IFNEW",'').replace("ORDERSTATUS",status3).replace("STATUS",statusIndex);
	}
	orderSortstr=orderSortstr.replace("ORDERID",orderId).replace("ORDERNUM",orderNum).replace("ORDERPRICE",orderPrice).replace("ORDERPEISONG",orderPeisong);
	return orderSortstr;
}
//建立订单单项，返回其HTML字符串
function orderContent(img,name,price,guige,num){
	var contentStr='<a href="javascript:void(0)" class="orderone-con" onclick="orderDetail(this)">'
	+'<div class="orderone-img"><img src="'+img+'" /></div>'
	+'<div class="orderone-text"><div class="orderone-left">'
	+'<span class="orderone-name">'+name+'</span><span class="orderone-guige">规格：<em class="one-num">'
	+guige+'</em></span></div><div class="orderone-right"><div class="orderone-price">&yen;'
	+'<span class="price-num">'+price+'</span></div><div class="orderone-num">x'					
	+'<span class="orderone-num-n">'+num+'</span></div></div></div></a>';
	
	return contentStr;					
}

//改变新订单数目项
function newNum(num){
	if(num>0){
		$(".new").text(num);
	    $(".new").show();
	}
}
//隐藏新订单数目
function hideNum(){
	$(".new").hide();
}
//订单为空界面
function noneCon(){
	$(".none-con").show();
	noneconStatus=1;
}
function fullCon(){
	$(".none-con").hide();
	noneconStatus=0;
}
//跳转链接
function orderDetail(obj){
	var Json=new Object();
	var orderId=$(obj).parent(".order-sort").attr("id");
	var code=$(obj).parent(".order-sort").data("code");
	//console.log(code);
	var userName=$(obj).parent(".order-sort").data("userName");
	var phone=$(obj).parent(".order-sort").data("phone");
	var address=$(obj).parent(".order-sort").data("address");
	var createtime=$(obj).parent(".order-sort").data("createtime");
	var status=$(obj).parent(".order-sort").data("status");
	var peisong=$(obj).parent(".order-sort").find(".order-sort-peisong").text();
	var totalPrice=$(obj).parent(".order-sort").find(".order-sort-price").text();
	Json.orderId=orderId;
	Json.code=code;
	Json.userName=userName;
	Json.phone=phone;
	Json.address=address;
	Json.createtime=createtime;
	Json.status=status;
	Json.peisong=peisong;
	Json.totalPrice=totalPrice;
	var goodArray=new Array();
	$(obj).parent(".order-sort").find(".orderone-con").each(function(){
		var goods=new Object();
		//alert($(this).html());
		var goodPic=$(this).find(".orderone-img img").attr("src");
		var goodName=$(this).find(".orderone-name").text();
		var goodConfig=$(this).find(".one-num").text();
		var goodPrice=$(this).find(".price-num").text();
		var goodNum=$(this).find(".orderone-num-n").text();
		goods.goodPic=goodPic;
		goods.goodName=goodName;
		goods.goodConfig=goodConfig;
		goods.goodPrice=goodPrice;
		goods.goodNum=goodNum;
		goodArray.push(goods);
	});
	//console.log(goodArray);
	Json.goods=goodArray;
    console.log(Json);
	Json=JSON.stringify(Json);
	obj.target="_self";
	obj.href="../order/detail.jspx?orderdata="+makeurl("",Json);
	obj.click();
}

function makeurl(url,data){
	var url=encodeURI(url);
	var data=encodeURI(data);
	//var encode=encodeURI(url+"?orderdata="+data);
	var encode=encodeURI(data);
	return encode;
}
//服务订单生成日期
function creatDate(){
	var date=new Date();
	var riqi=date;
	var html='<ul class="date-items">';
	html+='<li class="date-all"><div class="itembox">全部</div></li>';
	for(var count=0;count<7;count++){
		if(count==0){
			html+='<li><div class="itembox active" data-rq="'+riqi+'">今天</div></li>';
		}else if(count==1){
			html+='<li><div class="itembox " data-rq="'+riqi+'">明天</div></li>';
		}else{
			var month=date.getMonth()+1;
			var day=date.getDate();
			html+='<li><div class="itembox" data-rq="'+riqi+'">'+month+'/'+day+'</div></li>';
		}
		date=date.valueOf();
		date=new Date(date+24*3600*1000);
		riqi=date;
	}
	html+='</ul>';
	return html;
}

function creatForm(){
	var html='<table class="yy-table">';
	for(var i=8;i<22;i++){
		html+='<tr class="yy-tr" id="yy-tr'+i+'" data-tr="'+i+'">';
		html+='<th>'+i+':00-'+(i+1)+':00</th>';
		for(var j=1;j<4;j++){
			html+='<td data-td='+j+' id="yy-td'+j+'" class="yy-td"><div class="tdDiv"></div></td>';
		}
		html+='</tr>';
	}			
		html+='</table>';
		return html;				
}
//初始化表格状态
function loadForm(date){
	date=new Date(date);
	time=date.getDate();
	var currentDate=new Date();
	var currentTime=currentDate.getHours();
	var dateFormat=formatDate(date);
	
	//console.log(date);
	var datatosend={
		"date":dateFormat,
		"shopid":shopId
	};
	
	$.ajax({
		type:"get",
	    url:"../bespeakB/getbespeakbydateB.jspx",
	    async:"false",
	    data:datatosend,
	    dataType:"json",
	    crossDomain:true,
	    success:function (data) {
	    	console.log(data);
		    if(data.SUCCESS==="true"){
		    	//var dataService=JSON.stringify(msgData);
				for(var d=0;d<data.DATA.length;d++){
					var line=data.DATA[d].line;
					var trID='#yy-tr'+line;
					var tdID='#yy-td'+data.DATA[d].row;
					if(data.DATA[d].state===1){
						$(trID).find(tdID).children().addClass("td-nowc");
						$(trID).find(tdID).children().attr("data-id",data.DATA[d].id);
						$(trID).find(tdID).children().attr("data-orderid",data.DATA[d].orderid);
					}else if(data.DATA[d].state===2){
						$(trID).find(tdID).children().addClass("td-wc");
						$(trID).find(tdID).children().attr("data-id",data.DATA[d].id);
						$(trID).find(tdID).children().attr("data-orderid",data.DATA[d].orderid);
					}
				}
				//如果是当前时间初始化过期信息
				if(date.getDate()===currentDate.getDate()){
					$('.yy-table').children().find('tr').each(function(){
						if($(this).data("tr")<currentTime){
							$(this).children().each(function(){
								if($(this).children().hasClass("td-wc")===false&&$(this).children().hasClass("td-nowc")===false){
									$(this).children().addClass("td-gq");
								}
							});
						}
					});
				}
		    }else{
			    console.log(data.ERROMSG);
		    }
    },
	error: function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log(XMLHttpRequest.status);
                }
	});

	
}

/*//获取URL中商铺ID
function getQueryString(name) {
	
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}*/

//根据shopId获取所有所需的订单本身的信息
var orderHtml="";
function getGoodsOrderIds(status){
	
	var allOrderId=new Array();    //所有orderId
	//var status=status; 
	
	var datatosend = {"id":shopId};
	$.ajax({
				type:"get",
				url:getOrderIdByShopId,
				async:"false",
				data:datatosend,
				dataType:"json",
				crossDomain:true,
				success:function (data){
					console.log(data);
					if(data.result=="success"){
						//console.log(data);
						var i=0;
						var htmlTmp;
						var indexOfStatus;
						$(".order-sort-con").empty();
						fullCon();
						for(var d=0;d<data.list.length;d++){
							var flag=true;
							if(data.list[d].status=="payed"){
								indexOfStatus=0;
							}else if(data.list[d].status=="takedelivery"){
								indexOfStatus=2;
							}else if(data.list[d].status=="create"){
								indexOfStatus=-1;
							}else{
								indexOfStatus=1;
							}
							if(indexOfStatus==status){
								//console.log(data.list[d].status);
								var orderId=data.list[d].orderId;
							    for(var j=0;j<allOrderId.length;j++){
								    if(orderId!=allOrderId[j]){
									    continue;
								    }else{
									    flag=false;
									    break;
								    }
								}
							    if(flag){
								    allOrderId[i]=orderId;
					                var peisongfei=0;
					                htmlTmp=orderCom(orderId,data.list[d].code,data.list[d].price,peisongfei,data.list[d].status);
								    if(htmlTmp){
								    	$(".order-sort-con").append(htmlTmp);
								    	var orderid="#"+orderId;
								    	$(orderid).data("code",data.list[d].code);
								    	$(orderid).data("status",data.list[d].status);
								    	$(orderid).data("userName",data.list[d].name);
								    	//console.log(data.list[d].name);
								    	$(orderid).data("phone",data.list[d].phone);
								    	$(orderid).data("address",data.list[d].address);
								    	$(orderid).data("createtime",data.list[d].createtime);
								    	createGoodList(orderId);
								    }
								    i++;
							    }
							}
						}
						if(allOrderId.length<=0){
							noneCon();
						}
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
//根据orderId获取商品的信息
function createGoodList(orderId){
    
    var orderid="#"+orderId;
	var datatosend={"id":orderId};
	$.ajax({
		type:"get",
		url:getGoodListByOrderId,
		async:"false",
		data:datatosend,
		dataType:"json",
		crossDomain:true,
		success:function (data) {
			console.log(data);
			var totalPrice=0;
			if(data.result=="success"){
				var goodInfo="";
				for(var i=0;i<data.list.length;i++){
					totalPrice+=parseFloat(data.list[i].goodsconfigPrice)*parseInt(data.list[i].num);
					goodInfo+=orderContent(data.list[i].goodPicUrl,data.list[i].goodsName,data.list[i].goodsconfigPrice,data.list[i].goodsConfig,data.list[i].num);
				}
				$(orderid).find(".order-sort-l1").after(goodInfo);
				$(orderid).find(".order-sort-price").text(totalPrice);
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
//格式化日期为年月日方式
function formatDate(date) {  
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    //console.log(m);
    return y +""+ m +""+ d;  
}; 