var serviceConfigId=getQueryString("serviceId");
var openid=getQueryString("openid");
window.onload=function(){
	//alert(getDetail());
	creatServicedetail();
}
$(function(){
	$('.detail-title .nav').click(function(){
		history.go(-1);
	});
	$('.page-servicedetail').delegate(".serviceinfo-bt","click",function(){
			var shopId=$('.shopdetail-info').attr("id");
			var specId=$('.goodSpec').text();
			var serviceId=$('.serviceinfo-tag').attr("id");
			var servicePrice=$('.goodPrice').text();
			var serviceName=encodeURI(encodeURI($('.serviceName').text()));
			//alert(servicePrice+" "+serviceName);
			window.location.href="../bespeak/togetbespeakbydate.jspx"+"?shopid=" + shopId +"&openid="+openid+"&specId="+specId+"&serviceId="+serviceId+"&servicePrice="+servicePrice+"&serviceName="+serviceName;
	
	});
})
function creatServicedetail(data){
	var infoDetail = $.parseJSON(getDetail());
	var goodintro='<div class="good-detail"> '
		+'<div class="detail-ad"><img src="GOODAD" alt="" /></div>' 
		+'<div class="serviceinfo-tag" id="GOODID">' 
		+'<div class="serviceinfo-bt">  <span>立即<br/>预约</span> </div>'
		+'<p><big class="serviceName">GOODNAME </big></p> '
		+'<p>价格：<span id="price_tag">&yen; <span class="goodPrice">GOODPRICE</span></span> '
		//+'<span class="service-tag-sales">销量：<span id="price_tag" class="goodSales">GOODSALE</span></span>' 		
		+'<p>规格：<span class="goodSpec">GOODSPEC</span></p> 	'	
		//+'<p class="serviceinfo-tag-num"><span>数量：</span>' 
		//+'<div class="num-choose"><button class="num-min"  type="button" > - </button><input class="num-text" name="" type="text" value="0" " readonly="readonly" />' 
		//+'<button class="num-add"   type="button" > + </button></div></p>'
		+'</div></div>	'		 
		+'<div class="shop-detail"><div class="detail-bussiness"><p>店铺详情</p></div>'			 
		+'<div class="logo-putting" > <div class="shopdetail-info" id="SHOPID">'
		+'<img class="logo-left" src="SHOPIMG" alt="" /><p class="logo-mid"> <big class="shopName">SHOPNAME</big><br/>联系电话：<span class="shopPhone">SHOPPHONE<span></p><div class="logo-nav"></div>'
		+' </div></div></div>' 
		+'<div class="good-introdetail"><div class="detail-bussiness"><p class="intro">服务介绍</p></div>'
		+'<ul class="goodinfo-list"></ul> </div></div>';
var introdetail='<li><div class="detail-seller"><div class="detail-ad"><img src="INTROIMG" alt="" /></div>'	
	 		+'<div class="detail-seller"> <p class="reason_tag">INTRODETAIL</p> </div> </div></li>'	 ;
	var imgDetail = '';
	
	for(var i=0;i<infoDetail.DATA[1].length;i++){
		 imgDetail+=introdetail.replace("INTROIMG", infoDetail.DATA[1][i].serviceDesImg).replace("INTRODETAIL", infoDetail.DATA[1][i].serviceDes);
		
	}
	
	var temp=goodintro.replace("GOODID", infoDetail.DATA[0].serviceConfigsId).replace("GOODAD", infoDetail.DATA[0].serviceShowImg).
		replace("GOODNAME", infoDetail.DATA[0].serviceName).replace("GOODSPEC", infoDetail.DATA[0].configName).replace("GOODPRICE", infoDetail.DATA[0].configPrice).
		replace("SHOPID", infoDetail.DATA[0].storeId). replace("SHOPNAME", infoDetail.DATA[0].storeName).replace("SHOPIMG", infoDetail.DATA[0].storeImage).replace("SHOPPHONE", infoDetail.DATA[0].phoneNumber);
	$('.page-servicedetail').append(temp);		  
	$('.goodinfo-list').append( imgDetail);	
			
}
function getDetail(){
	var infoDetail = "";
	var getDetailUrl = "/siims/szb/service/serviceInfoByServiceIdAndServiceConfigId.jspx";
	var datatosend={
			"serviceConfigId":serviceConfigId
	};
	$.ajax({
		url : getDetailUrl,
		async : false,
		dataType : "text",
		data : datatosend,
		timeout : 5000,
		// 返回Json类型
		// contentType : "application/json;utf-8",
		success : function(result) {
			infoDetail = result;
		},
		error : function() {
			console.log("无获取從" + getDetailUrl + "数据。。。");
		}
	});
	return infoDetail;
}
function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}