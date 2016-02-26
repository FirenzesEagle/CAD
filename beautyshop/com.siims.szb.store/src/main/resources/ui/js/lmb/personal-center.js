var code=decodeURI(getQueryString("code"));
var openid=decodeURI(getQueryString("openid"));
if(code!="null"&&code!="undefined"){
	$.ajax({
		type:"get",
		url:'http://182.92.4.200/xyhWeixinServer/getopenid?code='+code,
		async:"false",
		data:/*code*/"",
		dataType:"json",
		crossDomain:true,
		success:function (data) {
			openid = data.openid;
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
					//console.log(data);
	                console.log(XMLHttpRequest.status);
	              //  alert(XMLHttpRequest.readyState);
	              //  alert(textStatus);
	            }
	});
}
window.onload=function(){
	var datatosend = {
					"openID":openid
					};
			$.ajax({
						type:"get",
						url:searchPersonalInfo,
						async:"false",
						data:datatosend,
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							console.log(data);
							if(data.SUCCESS=="true"){
								console.log(data.DATA);
								$('.weixin-img img').attr("src",data.DATA.user_pic);
								$('.weixin-nickname').text(data.DATA.user_wx_name);
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
}
$(function(){
	$('.myorder').click(function(){
		window.location.href="../order/begin.jspx?openid="+openid;
	});
	$('.myaddress').click(function(){
		window.location.href="../personal/tosearchallreceivinginfo.jspx?openid="+openid;
	});
	$('.myvipcard').click(function(){
		window.location.href="../../../vipcard/b2c/querymycards.jspx?openid="+openid;
	});
	$(".menu-list").on("click","li",function(){
		if($(this).children().hasClass("menu-index")===true){
			window.location.href="../store/togetallstore.jspx?openid="+openid;
		}else if($(this).children().hasClass("menu-shopcart")===true){
			window.location.href="../ShoppingTrollyAction/togetAllShoppingTrollyDataByOpenid.jspx?openid="+openid;
		}else if($(this).children().hasClass("menu-mycenter")===true){
			//此处写导向个人中心的action
			
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