var openid=decodeURI(getQueryString("openid"));
$(function(){
	$('.my-order').click(function(){
		window.location.href="../order/begin.jspx?openid="+openid;
	});
})
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}