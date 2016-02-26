var Json=decodeURI(getQueryString("data"));
var openid=decodeURI(getQueryString("openid"));
var Json=JSON.parse(Json);
console.log(Json);
var shoplist='<li class="pay-shop" id="SHOPID"><div class="pay-shop-name"><p>SHOPNAME</p></div>'
		+'<div class="pay-shop-good"><ul class="shopgoods"></ul></div>'
		+'<div class="pay-money"><span class="pay-money-tag">订单金额</span>'
		+'<span class="pay-money-total">&yen;<span class="totalMoney">00</span></span></div>'
		+'</li>';
var goodlist='<li class="shopgood" id="GOODID" data-spec="SPEC"><span class="goodName">GOODNAME</span><span class="goodNum">x<span class="goodNumVal">GOODNUMBER</span></span>'
	+'<span class="goodPrice">&yen;<span class="goodPriceVal">GOODPRICE</span></span></li>'
var vipcard='<div class="vipcardDiv"><div class="vipcard-result">'
+'<span class="vipcard-result-tag">使用该店会员卡结算</span>'
+'<span class="vipcard-result-total">&yen;<span class="vipResultMoney" data-vipchoose="0">0</span></span></div>'
+'<div class="vipcard-choose"><input type="password" class="vipPass" style="display: none;" />'
+'<select class="vipSelect"><option value="no">请选择优惠方式</option>OPTION</select></div></div>';	
					
					
window.onload=function(){
	initPayAddress();
	initPayShop();
	//setTotaltoPay();		
	}
	
$(function(){
	var height=$(window).height();
	$('.addressDiv').css("height",height);
	$('.addressaddDiv').css("height",height);
	$('.order-confirm-bt').click(function(){
		var content=setTotaltoPay();
		var name=$(".addr-info-name").text();
		var phone=$(".addr-info-phone").text();
		var address=$(".addr-info-addr").text();
		console.log(create);
		console.log(content);
		if(name.length==0){
			$.MsgBox.Alert("提示","请选择联系人");
		}else{
			var card=new Array();
			var cardstring=null;
			$('.paylists').find("li").each(function(){
				var cardobj=new Object();
				var vipchoose=$(this).find('.vipResultMoney').data("vipchoose");
				if(vipchoose==1){
					
					cardobj.cardid=$(this).find('.vipSelect option:selected').attr("id");
					cardobj.password=$(this).find('.vipPass').val();
					cardobj.money=$(this).find('.vipResultMoney').text();
					cardobj.type=$(this).find('.vipSelect option:selected').data("type");
					card.push(cardobj);
					alert(cardobj.cardid+" "+cardobj.password+" "+cardobj.money+" "+cardobj.type);
				}
				
				
			});
			if(card.length>0){
				cardstring=JSON.stringify(card);
			}
			var datatosend={
					"content":JSON.stringify(content),
					"cards":cardstring,
					"order.price":$('.total-paymoney').text(),
					"order.status":"create",
					"order.name":name,
					"order.address":address,
					"order.phone":phone,
					"order.customerId":openid+"",
					"order.ordercomment":""
			};
			console.log(datatosend);
			
			$.ajax({
				type:"get",
				url:create,
				async:"false",
				data:datatosend,
				dataType:"json",
				crossDomain:true,
				success:function (data) {
					console.log(data);
					if(data.result=="success"){
						var orderid=data.map.orderid;
						var money=parseFloat($('.total-paymoney').text());
						var returnurl='http://182.92.4.200:8838/siims/szb/order/golsuccess.jspx?openid='+openid;
						var notifyurl='http://182.92.4.200:8838/siims/szb/order/updateStatus.jspx?id='+orderid+'&status=payed';
						style=$('.pay-mode').find(".mode .choosed").parents(".mode").data("mode");
						if(style=="0"){
							$("input[name='total_fee']").val(money);
							$("input[name='out_trade_no']").val(orderid);
							$("input[name='return_url']").val(returnurl);
							$("input[name='notify_url']").val(notifyurl);
							$('#formid').submit();
							
						}else if(style=="1"){
							
						}
					}else if(data.SUCCESS=="false"){
						console.log(data.ERROMSG);
						window.location.href='http://182.92.4.200:8838/siims/szb/order/golfail.jspx?openid='+openid;
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
	$(".confirm-title .nav").click(function(){
		history.go(-1);
	});
	$(".addressaddDiv .nav").click(function(){
		$('.page-orderconfirm').show();
		$('.addressaddDiv').hide();
	});
	$(".addressDiv .nav").click(function(){
		$('.page-orderconfirm').show();
		$('.addressDiv .address-list').empty();
		$('.addressDiv').hide();
	});
	$(".addressDiv .address-add").click(function(){
		$('.addressDiv .address-list').empty();
		$('.addressDiv').hide();
		$('.addressaddDiv').show();
	});
	$('.page-orderconfirm').delegate(".addr-nav","click",function(){
		if($(this).parent().children().hasClass("addr-null")){
			$('.page-orderconfirm').hide();
			var height=$(window).height();
			$('.addressaddDiv').css("height",height);
			$('.addressaddDiv').show();
		}else{
			var height=$(window).height();
			$('.addressDiv').css("height",height);
			$('.addressDiv').show();
			$('.page-orderconfirm').hide();
			initaddr();
		}
	});
	$('.page-orderconfirm').delegate(".edtiAddress","click",function(){
		var height=$(window).height();
		$('.addressaddDiv').css("height",height);
		$('.addressaddDiv').show();
		
	});

	$('.pay-mode').delegate("li","click",function(){
		$(this).siblings().find('.mode-nav').removeClass('choosed');
		$(this).find('.mode-nav').addClass('choosed');
	});
	$('.paylists').delegate(".vipChooseBox ","click",function(){
		if($(this).hasClass("vipBoxchoosed")){
			$(this).removeClass("vipBoxchoosed");
			$(this).data("choose","0");
			$(this).parent().find(".vipcard-result-total").hide();
			$(this).parent().parent().find(".vipcard-choose").hide();
		}else{
			$(this).addClass("vipBoxchoosed");
			$(this).parent().find(".vipcard-result-total").show();
			$(this).parent().parent().find(".vipcard-choose").show();
		}
	});
	$('.paylists').delegate(".vipSelect","change",function(){
		//alert("fff");
		var id=$(this).parents(".pay-shop").attr("id");
		var shopid='#'+id;
		if($(this).find("option:selected").index()==0){
			$(shopid).find('.vipResultMoney').data("vipchoose","0");
			$(shopid).find('.vipResultMoney').text("0");
			setTotaltoPay();
		}else{
					//alert($(this).find("option:selected").index());
					var type=$(this).find("option:selected").data("type");
					var lastmoney=parseFloat($(this).find("option:selected").val());
					
					var totalMoney=parseFloat($(shopid).find(".totalMoney").text());
					
					if(type==1){
						var discount=parseFloat($(this).find("option:selected").data("benefit"));
						currentmoney=discount*totalMoney;
						
					}else{
						currentmoney=totalMoney;
					}
					//alert(currentmoney+" "+lastmoney);
					if(currentmoney>lastmoney){
						$.MsgBox.Alert("提示","余额不足");
						$(this).get(0).selectedIndex=0;
						 $(shopid).find('.vipcardDiv').data("vipchoose","0");
					}else{
						var ip='<p class="passLabel">请输入密码:</p><input type="password" class="passInput"/>';
						var msgdata={
								"html":ip,
								"id":id
								};
						shopid='#'+id;
						
						$.MsgBox.ConfirmByID("提示",msgdata,function(){
							var password=$(shopid).find('.vipPass').val();
							var cardid=$(shopid).find('option:selected').attr("id");
							alert(cardid+" "+password);
							var datatosend={
									"cardId":cardid,
									"password": hex_md5(password)
							};
							
							$.ajax({
								type:"get",
								url:vipcardcheckpassword,
								async:"false",
								data:datatosend,
								dataType:"json",
								crossDomain:true,
								success:function (data) {
									console.log(data);
									if(data.SUCCESS==true){
										$(shopid).find('.vipResultMoney').text(currentmoney.toFixed(2));
										$(shopid).find('.vipResultMoney').data("vipchoose","1");
										setTotaltoPay();
										
									}else if(data.SUCCESS==false){
										 $.MsgBox.AlertNo("提示",data.MSG);
										 $(shopid).find('.vipSelect').get(0).selectedIndex=0;
									     $(shopid).find('.vipResultMoney').text("0");
										
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
							
							//alert($(shopid).find('.vipPass').val());
							//alert($(this).find("option:selected").index());
						});
					}
					
		}
	});
	$('.address-list').delegate(".list-item","click",function(){
		var name=$(this).find(".receiver-name").text();
		var phone=$(this).find(".receiver-phone").text();
		var addr=$(this).find(".address1").text()+" "+$(this).find(".address1").text();
		$(".addr-info-name").text(name);
		$(".addr-info-phone").text(phone);
		$(".addr-info-addr").text(addr);
		$(".addressDiv .address-list").empty();
		$(".addressDiv").hide();
		$('.page-orderconfirm').show();
	});
	
	$('.addr-img img').click(function(){
		if($(this).data("flag")=="0"){
			$(this).data("flag","1");
			//alert($(this).data("flag"));
			$(this).attr("src","/wro/wroResources?id=classpath:ui/image/lmb/addr-mr.png");
		}else if($(this).data("flag")=="1"){
			$(this).data("flag","0");
			//alert($(this).data("flag"));
			$(this).attr("src","/wro/wroResources?id=classpath:ui/image/lmb/addr-wxz.png");
		}
	});
	$('.address-bt input').click(function(){
		var name=$('.userName').val();
		var addr1=$('.userAddress1').val();
		var addr2=$('.userAddress2').val();
		var phone=$('.userPhone').val();
		var flag=$('.addr-img img').data("flag");
		if(name.length==0||addr1.length==0||addr2.length==0||phone.length==0||isNaN(phone)||phone.length<11){
			$.MsgBox.AlertNo("提示","内容填写错误");
		}else{
			var ob={
					"openID":openid,
					"receiving_name":name,
					"receiving_tel":phone,
					"default_receiving_info":flag,
					"receiving_address_area":addr1,
					"receiving_address_detail":addr2
					
			};
			//alert(JSON.stringify(ob));
			var datatosend={
					DATA:JSON.stringify(ob)
			};
			//alert(datatosend);
			$.ajax({
				type:"get",
				url:addReceivingInfo,
				async:"false",
				data:datatosend,
				dataType:"json",
				crossDomain:true,
				success:function (data) {
					console.log(data);
					if(data.SUCCESS=="true"){
						var html='<div class="addr-info">'
							+'<span class="addr-info-name">'+name+'</span>'
							+'<span class="addr-info-phone">'+phone+'</span>'
							+'<span class="addr-info-addr">'+addr1+' '+addr2+'</span></div><div class="addr-nav"></div>';
							$('.order-address').empty();
							$('.order-address').append(html);
							$('.page-orderconfirm').show();
								$(".addressaddDiv").hide(function(){
									$('.userName').val(null);
									$('.userAddress1').val(null);
									$('.userAddress2').val(null);
									$('.userPhone').val(null);
								});
						
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
	
	
})
function setTotaltoPay(){
	var totalmoney=0;
	var array=new Array();
	$('.pay-shop').each(function(){
		var vipflag=0;
		var paymode="";
		var vipchoose=$(this).find('.vipResultMoney').data("vipchoose");
		//alert(vipchoose);
		if(vipchoose==0||vipchoose!=1){
			totalmoney+=parseFloat($(this).find('.totalMoney').text());
			paymode=$('.pay-mode').find(".mode .choosed").parents(".mode").data("mode");
					
		}else{
			vipflag=1;
		}
		var shopId=$(this).attr("id");
		$(this).find('.shopgood').each(function(){
			var goodId=$(this).attr("id");
			var price=$(this).find('.goodPriceVal').text();
			var num=$(this).find('.goodNumVal').text();
			var paytype=-1;
			if(vipflag==0){
				paytype=paymode;
			}else{
				paytype=2;
			}
			
			var goodObj=new Object();
			goodObj.goodsIdString="";
			goodObj.goodsconfigIdString=goodId;
			goodObj.shopidString=shopId;
			goodObj.priceString=price;
			goodObj.numString=num;
			goodObj.paytypeString=paytype;
			array.push(goodObj);
		});
		
	});
	//console.log(array);
	$('.total-paymoney').text(totalmoney);
	return array;
	
}
function initPayShop(){
	var totalmoney=0;
	$.each(Json.data,function(i){ 
		var shopName=decodeURI(decodeURI(Json.data[i].shopName));
		var temp  = shoplist.replace("SHOPNAME", shopName).replace("SHOPID",Json.data[i].shopId);
		$('.paylists').append(temp);
		var data=Json.data[i].shopData;
		var money=0;
		$.each(data,function(i){
			var good=goodlist.replace("GOODNAME",data[i].goodName).replace("GOODID",data[i].goodId).replace("GOODNUMBER",data[i].goodNum).replace("SPEC",data[i].goodPaytype).replace("GOODPRICE",data[i].goodPrice);
			$('.shopgoods').append(good);
			money+=parseInt(data[i].goodNum)*parseInt(data[i].goodPrice);
		});
		totalmoney+=money;
		$('.totalMoney').text(money);
		$('.total-paymoney').text(totalmoney);
		
		initshopVipcard(Json.data[i].shopId);
	});
}
//加载店铺会员卡信息
function initshopVipcard(shopId){
	
	var datatosend={
			"shopid":shopId,
			"useropenid":openid
	};
	$.ajax({
		type:"get",
		url:querymycardoftheshop,
		async:"false",
		data:datatosend,
		dataType:"json",
		crossDomain:true,
		success:function (data) {
			console.log(data);
			var option='<option data-type="TYPE" data-benefit="BENEFIT" id="CARDID" value="MONEY">余额为：<span class="lastmoney">LASTMONEY</span>，折扣<span class="discount">CONTEXT</span></option>';
			var temp="";
			if(data.SUCCESS==true){
				for(var i=0;i<data.DATA.length;i++){
					temp+=option.replace("TYPE",data.DATA[i].TYPE).replace("CARDID",data.DATA[i].CARDID)
					.replace("CONTEXT",data.DATA[i].CONTEXT).replace("LASTMONEY",data.DATA[i].LASTMONEY)
					.replace("BENEFIT",data.DATA[i].BENEFIT).replace("MONEY",data.DATA[i].LASTMONEY);
				}
				var vip=vipcard.replace("OPTION",temp);
				$('#'+shopId).append(vip);
			}else if(data.SUCCESS==false){
				//$('.pay-shop').append(vipcard);
				console.log(data.ERROMSG);
				//creatAddressNull();
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
//加载店铺的一个默认地址
function initPayAddress(){
	var datatosend = {
			"openID":openid
			};
			
		$.ajax({
			type:"get",
			url:searchDefaultReceivingInfo,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				var html='<div class="addr-info">'
					+'<span class="addr-info-name">USERNAME</span>'
					+'<span class="addr-info-phone">USERPHONE</span>'
					+'<span class="addr-info-addr">USERADDRESS</span></div><div class="addr-nav"></div>';
				
				if(data.SUCCESS=="true"){
					if(data.DATA!=null&&data.DATA!=""){
						var temp=html.replace("USERNAME",data.DATA.receiving_name).replace("USERPHONE",data.DATA.receiving_tel).replace("USERADDRESS",data.DATA.receiving_address_area+" "+data.DATA.receiving_address_detail);
						$('.order-address').append(temp);
					}else{
						var temp='<div class="addr-null"><span class="edtiAddress">编辑地址</span></div><div class="addr-nav"></div>';
						$('.order-address').append(temp);
					}
					
				}else if(data.SUCCESS=="false"){
					console.log(data.ERROMSG);
					var temp='<div class="addr-null"><span class="edtiAddress">编辑地址</span></div><div class="addr-nav"></div>';
					$('.order-address').append(temp);
					//creatAddressNull();
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
//加载店铺所有地址信息
function initaddr(){
	var datatosend = {
			"openID":openid
			};
			
		$.ajax({
			type:"get",
			url:searchAllReceivingInfo,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS=="true"){
					for(var i=0;i<data.DATA.length;i++){
						createAddress(data.DATA[i]);
					}
					var height=$(window).height();
					var h=$('.address-list').height()+100;
					//alert(height);
					if(h>height){
						$('.address-lists').css("margin-bottom",60);
					}
					
				}else if(data.SUCCESS=="false"){
					console.log(data.ERROMSG);
					//creatAddressNull();
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
function createAddress(data){
	var count=0;
	var html='<li class="list-item" id="'+data.receiving_id+'">';
	html+='<div class="item-np"><span class="receiver-name">'+data.receiving_name+'</span><span class="receiver-phone">'+data.receiving_tel+'</span></div>';
	html+='<div class="item-address">';
	var flag=data.default_receiving_info;
	if(flag=="1"){
		html+='<div class="moren" data-flag="'+1+'">[<span>默认</span>]</div>';
	}
	html+='<div class="item-a"><span class="address1">'+data.receiving_address_area+'</span><span class="address2">'+data.receiving_address_detail+'</span></div>';
	//html+='<div class="item-edit"><img src="/wro/wroResources?id=classpath:ui/image/lmb/edit.png"/></div>';
	html+='</div>';
	html+='</li>';
	$('.address-list').append(html);
	
}
function getQueryString(name) {
 
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
