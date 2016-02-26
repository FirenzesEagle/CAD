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
window.onload = function() {
	var districtId = decodeURI(getQueryString("district_id"));
	var districtId="qwe123";
	initshop(districtId);
}
$(function(){
	$('.district-shop').delegate(".shop-div","click",function(){
		id=$(this).attr("id");
		var listid='#'+id;
		var shopId=encodeURI(id);
		var shopImg=encodeURI($(listid).find("img").attr("src"));
		var shopName=encodeURI($(listid).find("span").text());
		window.location.href=makeurl("../goods/goodslist.jspx?shopId="+shopId+"&shopName="+shopName+"&openid="+openid);
	});
	$(".menu-list").on("click","li",function(){
		if($(this).hasClass("menu-index")===true){
			
		}else if($(this).hasClass("menu-shopcart")===true){
			window.location.href="../ShoppingTrollyAction/togetAllShoppingTrollyDataByOpenid.jspx?openid="+openid;
		}else if($(this).hasClass("menu-mycenter")===true){
			//此处写导向个人中心的action
			window.location.href="../personal/tosearchpersonalinfo.jspx?openid="+openid;
		}
	});
})
function creatShopList(array){
	//alert(array.length);
	var html='';
	if(array.length % 2==0){
		for(var i=0;i<array.length;i=i+2){
			html+='<div class="shop-row">';
			html+='<div class="shop-div" id="'+array[i].id+'">';
			html+='<img src="'+array[i].image+'" /><span>'+array[i].name+'</span></div>';
			html+='<div class="shop-div" id="'+array[i+1].id+'">';
			html+='<img src="'+array[i+1].image+'" /><span>'+array[i+1].name+'</span></div>';
			html+='</div>';
		}
	}else{
		for(var i=0;i<array.length-1;i=i+2){
			html+='<div class="shop-row">';
			html+='<div class="shop-div" id="'+array[i].id+'">';
			html+='<img src="'+array[i].image+'" /><span>'+array[i].name+'</span></div>';
			html+='<div class="shop-div" id="'+array[i+1].id+'">';
			html+='<img src="'+array[i+1].image+'" /><span>'+array[i+1].name+'</span></div>';
			html+='</div>';
		}
		html+='<div class="shop-row">';
			html+='<div class="shop-div" id="'+array[array.length-1].id+'">';
			html+='<img src="'+array[array.length-1].image+'" /><span>'+array[array.length-1].name+'</span></div>';
			html+='<div class="shop-div" id="">';
			html+='</div>';
			html+='</div>';
		
	}
	$('.district-shop').append(html);		
}
function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
function initshop(districtId){
		var datatosend = {
		"district_id":districtId};
		 	
		//console.log(datatosend);
		$.ajax({
			type:"get",
			url:districtshops,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS=="true"){
					creatShopList(data.DATA);
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
function makeurl(url){
	var val1=encodeURI(val1);
	var val2=encodeURI(val2);
	var encode=encodeURI(url);
	return encode;
	}