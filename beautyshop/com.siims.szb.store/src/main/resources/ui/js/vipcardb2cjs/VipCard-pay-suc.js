var shopName=decodeURI(getQueryString("shopName"));
var consume=decodeURI(getQueryString("consume"));
var vipcardId=getQueryString("vipcardId");
var openid=getQueryString("openid");
$(document).ready(function(){
	$('.nav').click(function(){
		history.go(-1);
	});
	$('.shopName').text(shopName);
	$('.Consume').text(consume);
	$('.bt-my-bill').click(function(){
		window.location.href=makeurl("./mycardbill.jspx?vipcardId="+vipcardId);
	});
	$('.bt-my-VIPCard').click(function(){
		window.location.href=makeurl("./querymycards.jspx?openid="+openid);
	});
});
function makeurl(url){
	var encode=encodeURI(url);
	return encode;
	}
function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }