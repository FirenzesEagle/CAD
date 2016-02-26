var timelist='<div class="VIPCard-remain-times" ><p>您当前会员卡剩余消费次数<span class="lastMoney">LASTMONEY</span>次</p></div>'
			+'<div class="payment-info-form" data-type="TYPE"><div class="payment-layer"><p class="payment-label">消费次数</p>'
			+'<div class="consume-times-content"><div class="timeResult">'		
			+'<label class="times-min">-</label><span class="times-text">1</span><label class="times-add" >+</label></div>'			
			+'<div class="prompt"><p class="no-remain-money">对不起，您的次数不足</p></div></div></div>'
			+'<div class="payment-info-layer"><p class="payment-info-label">支付密码</p><div class="payment-info-content">'	
			+'<input type="password" id="pay-pswID" class="pay-psw-class" placeholder="支付密码" value=""/>'	
			+'</div></div></div>';
var moneylist='<div class="VIPCard-balance"  data-discount="DISCOUNT"><p>您当前会员卡余额<span class="lastMoney">LASTMONEY</span>元</p></div>'
			+'<div class="payment-info-form" data-type="TYPE"><div class="payment-info-layer"><p class="payment-info-label">消费单价</p>'
			+'<div class="payment-info-content"><input type="text" id="priceID" class="priceClass" placeholder="请输入本次消费单价" value=""/>'
			+'<p class="preferential-price">会员优惠价格：<span class="youhuiMoney">0</span>元</p></div></div>'
			+'<div class="payment-info-layer"><p class="payment-info-label">消费数量</p><div class="consume-num-content">'
			+'<div class="timeResult"><label class="times-min">-</label><span class="times-text">1</span><label class="times-add" >+</label></div>'
			+'<div class="prompt"><p class="no-remain-money">对不起，您的余额不足</p></div></div></div>'	
			+'<div class="payment-info-layer"><p class="payment-info-label">支付密码</p><div class="payment-info-content">'	
			+'<input type="password" id="pay-pswID" class="pay-psw-class" placeholder="支付密码" value=""/>'	
			+'</div></div></div>';
var vipcardId=getQueryString("vipcardId");
window.onload=function(){
	datatosend={
		"vipcardId":vipcardId
	};
	$.ajax({
			type:"get",
			url: queryvipcardpay,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==true){
					if(data.TYPE==3){
						var temp=timelist.replace("LASTMONEY",data.LASTMONEY).replace("TYPE",data.TYPE);
						$('.div-confirm-pay').prepend(temp);
					}else{
						
						var discount=parseFloat(1.00);
						if(data.TYPE==1){
							discount=parseFloat(data.DISCOUNT/10);
						}
						var temp=moneylist.replace("LASTMONEY",data.LASTMONEY).replace("DISCOUNT",discount).replace("TYPE",data.TYPE);
						$('.div-confirm-pay').prepend(temp);
						
					}
				}else{
					
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
$(document).ready(function(){
	$('.VIPCardPay-byTimes-title nav').click(function(){
		history.go(-1);
	});
	$('.page-payment-VIPCard').delegate(".times-add","click",function(){
		var count=parseInt($('.times-text').text());
		count++;
		$('.times-text').text(count);
		 checktext();
		
		
	});
	$('.page-payment-VIPCard').delegate(".times-min","click",function(){
		var count=parseInt($('.times-text').text());
		if(count>0){
			count--;
			$('.times-text').text(count);
			checktext();
		}
		
	});
	$('.page-payment-VIPCard').delegate(".bt-confirm-payment","click",function(){
		var type=$('.payment-info-form').data("type");
		var  consumetype;
		var money;
		var password;
		if(type==3){
			if(checktext()==true){
				lastcount=parseFloat($('.lastMoney').text());
				money=parseInt($('.times-text').text());
				password=$('.pay-psw-class').val();
				if(password.length==0){
					$.MsgBox.Alert("提示","密码不能为空");
					return false;
				}else{
					password=hex_md5(password);
					consumetype=4;
					var datatosend={
									"vipcardId":vipcardId,
									"money":money,
									"consumetype":consumetype,
									"password":password
								};
								console.log(datatosend);
								$.ajax({
									type:"get",
									url: vipcardpay2,
									async:"false",
									data:datatosend,
									dataType:"json",
									crossDomain:true,
									success:function (data) {
										console.log(data);
										if(data.SUCCESS==true){
											var shopName=encodeURI(data.STORENAME);
											var consume=encodeURI(data.CONSUME);
											var openid=data.OPENID;
											window.location.href=makeurl("./vipcardpaysuccess.jspx?shopName="+shopName+"&consume="+consume+"&vipcardId="+vipcardId+"&openid="+openid);
										}else{
											$.MsgBox.Alert("提示",data.MSG);
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

			}
		}else{
				if(checktext()==true){
					password=$('.pay-psw-class').val();
					count=parseInt($('.times-text').text());
					price=parseFloat($(".youhuiMoney").text());
					money=count*price;
					if(password.length==0){
					$.MsgBox.Alert("提示","密码不能为空");
						return false;
					
					}else{
						password=hex_md5(password);
						consumetype=3;
						var datatosend={
									"vipcardId":vipcardId,
									"money":money,
									"consumetype":consumetype,
									"password":password
								};
								console.log(datatosend);
								$.ajax({
									type:"get",
									url: vipcardpay2,
									async:"false",
									data:datatosend,
									dataType:"json",
									crossDomain:true,
									success:function (data) {
										console.log(data);
										if(data.SUCCESS==true){
											var shopName=encodeURI(data.STORENAME);
											var consume=encodeURI(data.CONSUME);
											var openid=data.OPENID;
											window.location.href=makeurl("./vipcardpaysuccess.jspx?shopName="+shopName+"&consume="+consume+"&vipcardId="+vipcardId+"&openid="+openid);
										}else{
											$.MsgBox.Alert("提示",data.MSG);
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
			}
		}
		
	});
	$('.page-payment-VIPCard').delegate(".priceClass","click",function(){
			$(this).watch(function(value) {  
			if(value!=undefined&&value!=null&&value!=""){
				var discount=parseFloat($('.VIPCard-balance').data("discount"));
				$(".youhuiMoney").text(value*discount);
			}else{
				$(".youhuiMoney").text(0);
			}
		}); 
	});
	
	
	
});
 (function($) {  
        $.fn.watch = function(callback) {  
            return this.each(function() {  
                //缓存以前的值  
                $.data(this, 'originVal', $(this).val());  
      
                //event  
                $(this).on('keyup paste', function() {  
                    var originVal = $(this, 'originVal');  
                    var currentVal = $(this).val();  
      
                    if (originVal !== currentVal) {  
                        $.data(this, 'originVal', $(this).val());  
                        callback(currentVal);  
                    }  
                });  
            });  
        }  
    })(jQuery);  
 function checktext(){
 	var type=$('.payment-info-form').data("type");
 	if(type==3){
 		var lastcount=parseFloat($('.lastMoney').text());
			var count=parseInt($('.times-text').text());
			if(lastcount<count){
				$('.prompt').css("display","block");
				return false;
			}else{
				$('.prompt').css("display","none");
				return true;
			}
 	}else{
 		var count=parseInt($('.times-text').text());
 		var price=parseFloat($(".youhuiMoney").text());
		var money=count*price;
		var lastmoney=parseFloat($('.lastMoney').text());
		if(lastmoney<money){
			$('.prompt').css("display","block");
			return false;
		}else{
			$('.prompt').css("display","none");
			return true;
		}
 	}
 	
 }
  function makeurl(url){
	var encode=encodeURI(url);
	return encode;
	}
function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }