var vipcardId=getQueryString("cardId");
var shopName=getQueryString("shopName");
var shopImg=getQueryString("shopImg");
var openid=getQueryString("openid");
var introlist='<li><i class="icon-money"></i><span>TEXTMONY</span></li>';
						
					
window.onload=function(){
	var datatosend={
		"vipcardId":vipcardId
	}
	$.ajax({
			type:"get",
			url:collareddetailmethod,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==true){
					$('.shop-name').text(data.STORENAME);
					$('.shopImg').attr("src",data.STOREIMAGE);
					$('.VIPCard-number').text(data.NUMBER);
					$('.balance-money').text(data.LASTMONEY);
					$('.card-type-name').text(data.TYPE);
					$('.card-discount').text(data.BENEFIT);
					$('.rightDiscount').text(data.BENEFIT);
					$('.shop-index').attr("id",data.STOREID);
					$.each(data.CONFIGDATA,function(i){ 
									var temp = introlist.replace("TEXTMONY", data.CONFIGDATA[i].CONTEXT);
									$(".get-VIPCardIntro-list").append(temp);
								});
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
	$('.my-bill p').click(function(){
		window.location.href=makeurl("./mycardbill.jspx?vipcardId="+vipcardId);
	});
	$('.VIPCardDetail-title .nav').click(function(){
		history.go(-1);
	});
	$('.bt-payByVIPCard').click(function(){
		window.location.href=makeurl("./vipcardpay1.jspx?vipcardId="+vipcardId);
	});
	$('.shop-index span').click(function(){
		var shopid=$('.shop-index').attr("id");
		var shopname=encodeURI(encodeURI($('.shop-name').text()));
		window.location.href='../../../siims/szb/goods/goodslist.jspx?shopId='+shopid+'&shopName='+shopname+'&openid='+openid;
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