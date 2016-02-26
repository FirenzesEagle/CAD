var vipcardId=getQueryString("vipcardId");
window.onload=function(){
	datatosend={
		"vipcardId":vipcardId
	};
	$.ajax({
			type:"get",
			url:queryconsumercard,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==true){
					 $('.VIP-name-value').text(data.NAME);
					 $('.VIPCard-num-value').text(data.NUMBER);
					 $('.VIP-account-value').text(data.ACCOUNT);
					 $('.VIPCard-time-value').text(data.TIME);
					 $('.preferential-rules-value').text(data.YOUHUI);
					 $('.VIPCard-balance-value').text(data.PRICE);
					 var html='<p>您需要支付金额<span>'+data.PRICE+
					 '</span>元即将获得会员专享权益<span>'+data.YOUHUI+'</span>优惠</p>';
					 $('.VIPInfo-point').append(html);
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
$(function(){
	$('.nav').click(function(){
		history.go(-1);
	});
	$('.styleOf-payment').delegate(".unselect-payment","click",function(){
		$(this).addClass('select-payment');
		var style=$(this).parents('.payStyle').data("style");
		$(this).parents('.payStyle').siblings().find('.unselect-payment').removeClass("select-payment");
		
	});
	/*确认支付*/
	$('.confirm-pay').click(function(){
		var style=$('.select-payment').parents('.payStyle').data("style");
		var money=parseFloat($('.VIPCard-balance-value').text());
		//var cardnumber=$('.VIPCard-num-value').text();
		var returnurl = "http://182.92.4.200:8838/vipcard/b2c/paysuccess.jspx?cardId="+vipcardId;
		var notifyurl ='http://182.92.4.200:8838/vipcard/b2c/paysuccessfromalipay.jspx?cardId='+vipcardId;
		//alert(style+" "+money+" "+vipcardId);
		if(style=="1"){
			$("input[name='total_fee']").val(money);
			$("input[name='out_trade_no']").val(vipcardId);
			$("input[name='return_url']").val(returnurl);
			$("input[name='notify_url']").val(notifyurl);
			//$('#formid').submit();
			
		}else if(style=="2"){
			
		}
	});
			
})
function makeurl(url){
	var encode=encodeURI(url);
	return encode;
	}
function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }