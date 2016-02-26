var shopId=decodeURI(getQueryString("shopId"));
var shopName=decodeURI(getQueryString("shopName"));
var openid=getQueryString("openid");
window.onload=function(){
	
	$('.shop-title').find("p").text(shopName);
	//alert(shopId);
	/*加载商品信息*/
	//initGoodlist(shopId);
	/*加载服务信息*/
	initservicelist(shopId);
	var height=window.outerHeight;
	$('.container-goodlist').css("height",height);
	$('#toservices').click();
	}
$(function(){
				$('.nav').click(function(){
					history.go(-1);
				});

				$('#togoods').click(function(){
					$('.goodsDiv').show();
					$('.servicesDiv').hide();
					$(this).addClass("tabChoosed");
					$(this).siblings().removeClass("tabChoosed");
					if($('.menu-shopcart').hide()){
						$('.menu-shopcart').show();
					}
				});
				$('#toservices').click(function(){
					$('.goodsDiv').hide();
					$(this).addClass("tabChoosed");
					$(this).siblings().removeClass("tabChoosed");
					$('.servicesDiv').show();
					if($('.menu-shopcart').show()){
						$('.menu-shopcart').hide();
					}
				});
				$(".goodlist").delegate(".num-add","click",function(){
					var num=parseInt($(this).parent().find(".num-text").val())+1;
					var item_id=$(this).parents('.good').attr("id");
					var goodid='#'+item_id;
					var item_price=parseFloat($(goodid).find('.goodPrice').text());
					var ob = new Object();
					ob.shop_id=shopId;
					ob.item_id=item_id;
					ob.user_id="000000";
					ob.openid=openid;
					ob.item_count=num;
					ob.item_price=item_price;
					console.log(JSON.stringify(ob));
					var datatosend={
					"DATA":JSON.stringify(ob)
						};
		
					console.log(datatosend);
				$.ajax({
					type:"get",
					url:shoppingTrollyAdd,
					async:"false",
					data:datatosend,
					dataType:"json",
					crossDomain:true,
					success:function (data) {
						console.log(data);
						if(data.SUCCESS=="true"){
							console.log(data.DATA);
							$(goodid).find(".num-text").val(num);
							setTotal();
							
						}else if(data.SUCCESS=="false"){
							console.log(data.ERROMSG);
						}
						
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
								//console.log(data);
		                        console.log(XMLHttpRequest.status);
		                        console.log(errorThrown);
		                      //  alert(XMLHttpRequest.readyState);
		                      //  alert(textStatus);
		                    }
					});
					
					
				});
				$(".goodlist").delegate(".num-min","click",function(){
					var val=parseInt($(this).parent().find(".num-text").val());
					if(val>0){
						var item_id=$(this).parents('.good').attr("id");
						var goodid='#'+item_id;
						var item_price=parseFloat($(goodid).find('.goodPrice').text());
						var user_id="000000";
						var item_count=val-1;
						var item_price=parseFloat($(goodid).find('.goodPrice').text());
						var ob = new Object();
						ob.shop_id=shopId;
						ob.item_id=item_id;
						ob.user_id="000000";
						ob.openid=openid;
						ob.item_count=item_count;
						ob.item_price=item_price;
						console.log(JSON.stringify(ob));
						var datatosend={
							"DATA":JSON.stringify(ob)
						};
						
						console.log(datatosend);
						$.ajax({
							type:"get",
							url:shoppingTrollyAdd,
							async:"false",
							data:datatosend,
							dataType:"json",
							crossDomain:true,
							success:function (data) {
								console.log(data);
								if(data.SUCCESS=="true"){
									$(goodid).find(".num-text").val(val-1);
									setTotal();
									
								}else if(data.SUCCESS=="false"){
									console.log(data.ERROMSG);
								}
								
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) {
										//console.log(data);
				                        console.log(XMLHttpRequest.status);
				                        console.log(errorThrown);
				                      //  alert(XMLHttpRequest.readyState);
				                      //  alert(textStatus);
				                    }
							});
					}
				});
				$(".goodlist").delegate(".num-text","change",function(){
						setTotal();
				});
				$(".goodlist").delegate(".goodImg","click",function(){
					var configId=$(this).parents(".good").attr("id");
					var goodsId=$(this).parents(".good").data("config");
					//alert(configId);
					window.location.href=makeurl("togoodsInfoBygoodsIdAndgoodsConfigId.jspx"+"?goodId=" + configId +"&openid="+openid);
			});
				$(".servicelist").delegate(".serviceImg","click",function(){
						var serviceId=$(this).parents(".service").attr("id");
						window.location.href=makeurl("../service/toserviceInfoByServiceIdAndServiceConfigId.jspx"+"?serviceId=" + serviceId +"&openid="+openid);
				});
				//于2015年10月27日 09:19:43添加
				$(".servicelist").delegate(".bt-order","click",function(){
					
					
					var serviceId=$(this).parents('.service').attr("id");
					var id='#'+serviceId;
					var servicePrice=$(id).find('.servicePrice').text();
					var specId=$(id).data("serviceid");
					var serviceName=encodeURI($(id).find('.serviceItem-name p').text());
					//alert(servicePrice+" "+serviceName);
					window.location.href=makeurl("../bespeak/togetbespeakbydatebyc.jspx"+"?shopid=" + shopId +"&openid="+openid+"&specId="+specId+"&serviceId="+serviceId+"&servicePrice="+servicePrice+"&serviceName="+serviceName);
			});
				
				$('.bt-confirm').click(function(){
					var Json=new Object();
					Json.data=setTotal();
					console.log(Json.data);
					if(Json.data.length>0){
						Json=JSON.stringify(Json);
						window.location.href="../ShoppingTrollyAction/orderConfirm.jspx?data="+Json+"&openid="+openid;
					}else{
						$.MsgBox.Alert("提示","请先添加商品");
					}
					
				});
				$('.shopcart').click(function(){
					window.location.href="../ShoppingTrollyAction/togetAllShoppingTrollyDataByOpenid.jspx?openid="+openid;
				});
})
function initGoodlist(shopId){
	var datatosend = {
		"storeId":shopId};
		
		$.ajax({
			type:"get",
			url:goodlists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==="true"){
					for(var i=0;i<data.DATA.length;i++){
						$('.goodlist').append(creatGoods(data.DATA[i]));	
					}
					var height=window.outerHeight;
					var goodlistHeight=$('.goodlist').height();
					if(goodlistHeight>=height){
						$('.goodlist').css("height",goodlistHeight+60);
					}
					//initCart();
					checkPromp();
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
function initservicelist(shopId){
	var datatosend = {
		"storeId":shopId};
		
		$.ajax({
			type:"get",
			url:servicelists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==="true"){
					for(var i=0;i<data.DATA.length;i++){
						$('.servicelist').append(creatServices(data.DATA[i]));
					}
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

function initCart(){
	var total=0;
	var totalNum=0;
	var array=new Array();
	var n=0;
	var datatosend = {
		"openid":openid,
		"shop_id":shopId
	};
		
		$.ajax({
			type:"get",
			url:getShoppingTrollyByStore,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.DATA.length>0){
					for(var i=0;i<data.DATA.length;i++){
						totalNum+=data.DATA[i].item_count;
						goodid="#"+data.DATA[i].item_id;
						$('.goodlist').find(goodid).find('.num-text').val(data.DATA[i].item_count);
						total+=data.DATA[i].item_count*data.DATA[i].item_price;
						$('.prompt span').text(totalNum);
						
					}
					$('.totalMoney').text(total.toFixed(2));
						checkPromp();
						//return array;
				}
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
                    }
		});
	
	
	//console.log(array);
	//console.log(totalNum);
	//console.log(total);	
}
function makeurl(url){
	var val1=encodeURI(val1);
	var val2=encodeURI(val2);
	var encode=encodeURI(url);
	return encode;
	}
function getQueryString(name) {
 
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }

function creatGoods(data){
	
	var html='<li class="good" id="'+data.goodsId+'" data-config="'+data.realGoodsId+'"><div class="goodInfo"><div class="goodImg">';
		html+='<img src="'+data.goodsShowImg+'" alt="" /></div>';
		html+='<div class="goodItem"><div class="goodItem-name">';
		html+='<p>'+data.goodsName+'</p></div>';
		html+='<div class="goodItem-spec"><div class="goodItem-spec-ps">';
		html+='<p>&yen;:<span class="goodPrice" data-paytype="'+data.configName+'">'+data.configPrice+'</span></p>';
		html+='<p>销量：'+data.sale+'</p></div>';
		html+='<div class="goodItem-spec-num"><button class="num-min"  type="button" >-</button>';
		html+='<input class="num-text" name="" type="text" value="0" readonly="readonly"  />';
		html+='<button class="num-add"   type="button" >+</button></div></div></div></div></li>';
		return html;
}

function creatServices(data){
	var html='<li class="service" id="'+data.realServiceId+'" data-serviceid="'+data.serviceId+'"><div class="serviceInfo"><div class="serviceImg">';			
		html+='<img src="'+data.serviceShowImg+'" alt="" /></div>';
		html+='<div class="serviceItem"><div class="serviceItem-name">';
		html+='<p>'+data.serviceName+'</p></div>';
		html+='<div class="serviceItem-spec"><div class="serviceItem-spec-ps">';
		html+='<p>&yen;:<span class="servicePrice" data-paytype="'+data.configName+'">'+data.servicePrice+'</span></p>';
		html+='</div>';
		html+='<div class="serviceItem-spec-order"><buton class="bt-order"  type="button">预约</button>';
		html+='</div></div></div></div>';
		return html;
}

function setTotal(){
	var total=0;
	var totalNum=0;
	var alldata=new Array();
	var array=new Array();
	var shopObj=new Object();
	var n=0;
	$('.goodlist').find('.good').each(function(){
		var data=new Object();
		var goodNum=$(this).find('.num-text').val();
		if(parseInt(goodNum)>0){
			
			data.goodId=$(this).attr("id");
			data.goodNum=goodNum;
			data.goodName=encodeURI(encodeURI($(this).find('.goodItem-name p').text()));
			data.goodPaytype=encodeURI(encodeURI($(this).find('.goodPrice').data("paytype")));
			data.goodPrice=$(this).find('.goodPrice').text();
			array.push(data);
			totalNum+=parseInt(goodNum);
			total+=parseInt(goodNum)*parseFloat(data.goodPrice);
		}
	});
	if(array.length>0){
		shopObj.shopName=encodeURI(encodeURI(shopName));
		shopObj.shopId=shopId;
		shopObj.shopData=array;
		alldata.push(shopObj);
		//console.log(alldata);
	}
	$('.prompt span').text(totalNum);
	$('.totalMoney').text(total.toFixed(2));
	checkPromp();
	
	return alldata;
	//console.log(array);
	//console.log(totalNum);
	//console.log(total);	
}
function checkPromp(){
	var num=parseInt($('.prompt span').text());
	if(num>0){
		$('.prompt').show();
	}else(
		$('.prompt').hide()
	)
}
