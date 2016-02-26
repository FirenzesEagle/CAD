$(document).ready(function(){
	$('.nav').click(function(){
		history.go(-1);
	});
});
function goshoplist(storeid,storename,openid){
	window.location.href='../../../siims/szb/goods/goodslist.jspx?shopId='+storeid+'&shopName='+encodeURI(encodeURI(storename))+'&openid='+openid;
}
function makeurl(url){
	var encode=encodeURI(url);
	return encode;
	}