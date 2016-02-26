
var goodsConfigId=decodeURI(getQueryString("goodId"));
//alert(goodsConfigId);
window.onload=function(){
	$('.page-gooddetail').append(shopDetail());
	
}
$(function(){
		$(".detail-title .nav").click(function(){
			history.go(-1);
		});
		$(".page-gooddetail").delegate(".num-add","click",function(){
					var num=parseInt($(this).parent().find(".num-text").val())+1;
					$(this).parent().find(".num-text").val(num);
					
					setTotal();
					
		});
		$(".page-gooddetail").delegate(".num-min","click",function(){
					var val=parseInt($(this).parent().find(".num-text").val());
					if(val>0){
						$(this).parent().find(".num-text").val(val-1);
						setTotal();
					}
		});
		$('.bt-confirm').click(function(){
					var array=setTotal();
					console.log(array);
				});
		
})
function shopDetail(data){
	var infoDetail = $.parseJSON(getDetail());
	var goodintro='<div class="good-detail"> '
		+'<div class="detail-ad"><img src="GOODAD" alt="" /></div>' 
		+'<div class="serviceinfo-tag" id="GOODID">' 
		+'<p><big>GOODNAME </big> <span><big>预定</big></span> </p> '
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
		+'<div class="good-introdetail"><div class="detail-bussiness"><p class="intro">商品介绍</p></div>'
		+'<ul class="goodinfo-list"></ul> </div></div>';
var introdetail='<li><div class="detail-seller"><div class="detail-ad"><img src="INTROIMG" alt="" /></div>'	
	 		+'<div class="detail-seller"> <p class="reason_tag">INTRODETAIL</p> </div> </div></li>'	 ;
	var imgDetail = '';
	
	for(var i=0;i<infoDetail.DATA[1].length;i++){
		 imgDetail+=introdetail.replace("INTROIMG", infoDetail.DATA[1][i].goodsDesImg).replace("INTRODETAIL", infoDetail.DATA[1][i].goodsDesDes);
		
	}
	
	var temp=goodintro.replace("GOODID", infoDetail.DATA[0].goodsConfigsId).replace("GOODAD", infoDetail.DATA[0].goodsShowImg).
		replace("GOODNAME", infoDetail.DATA[0].goodsName).replace("GOODSPEC", infoDetail.DATA[0].configName).replace("GOODPRICE", infoDetail.DATA[0].configPrice).
		replace("SHOPID", infoDetail.DATA[0].storeId). replace("SHOPNAME", infoDetail.DATA[0].storeName).replace("SHOPIMG", infoDetail.DATA[0].storeImage).replace("SHOPPHONE", infoDetail.DATA[0].phoneNumber);
	$('.page-gooddetail').append(temp);			  
	$('.goodinfo-list').append( imgDetail);					
}
/*function setTotal(){
	var total=0;
	var totalNum=0;
	var array=new Array();
	var data=new Object();
	var goodNum=$('.page-gooddetail').find('.num-text').val();
	if(parseInt(goodNum)>0){
			data.goodID=$('.page-gooddetail').find('.serviceinfo-tag').attr("id");
			data.goodNum=goodNum;
			data.goodPaytype=$('.page-gooddetail').find('.goodSpec').text();
			data.goodPrice=$('.page-gooddetail').find('.goodPrice').text();
			array.push(data);
			totalNum=parseInt(goodNum);
			total=parseInt(goodNum)*parseFloat(data.goodPrice);
			
		}
	
	$('.prompt span').text(totalNum);
	$('.totalMoney').text(total);
	checkPromp();
	return array;
}

function checkPromp(){
	var num=parseInt($('.prompt span').text());
	if(num>0){
		$('.prompt').show();
	}else(
		$('.prompt').hide()
	)
}*/
function getDetail(){
	var detail = "";
	var datatosend={
			"goodsConfigId":goodsConfigId
	};
	var getDetailUrl = "/siims/szb/goods/goodsInfoBygoodsIdAndgoodsConfigId.jspx";
	$.ajax({
		url : getDetailUrl,
		async : false,
		dataType : "text",
		data : datatosend,
		timeout : 5000,
		// 返回Json类型
		// contentType : "application/json;utf-8",
		success : function(result) {
			console.log(result);
			detail = result;
		},
		error : function() {
			console.log("无获取從" + getDetailUrl + "数据。。。");
		}
	});
	return detail;
}
function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}