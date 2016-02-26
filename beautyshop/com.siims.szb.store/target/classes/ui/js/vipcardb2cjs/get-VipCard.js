var openid=getQueryString("openid");
var cardList='<li class="VIPCard-list-detail VIPCARDCLASS" id="CARDID">'
			+'<div class="shopInfo"><img src="IMGURL" alt=""/>'
			+'<span class="shop-name">SHOPNAME</span></div>'
			+' <span class="get-atOnce"><strong>立即领取</strong></span>'
			+'<div class="card-type"><p>CARDTYPE</p></div>'
			+'</li>';
window.onload=function(){
	var datatosend = {}; 	
		//console.log(datatosend);
		$.ajax({
			type:"get",
			url:validlistmethod,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==true){
					for(var i=0;i<data.DATA.length;i++){
						var num=getRandom(3);
						var VIPCARD='VIPCard'+num;
						var temp=cardList.replace("CARDID",data.DATA[i].VIPCARDTYPEID).replace("CARDTYPE",data.DATA[i].TYPE);
						temp=temp.replace("IMGURL",data.DATA[i].STOREIMAGE).replace("SHOPNAME",data.DATA[i].STORENAME).replace("VIPCARDCLASS",VIPCARD);
						$('.all-VIPCard-list').append(temp);
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
$(function(){
	$('.nav').click(function(){
		history.go(-1);
	});
	$('.all-VIPCard-list').delegate(".shopInfo","click",function(){
		var id=$(this).parent().attr("id");
		var cardid='#'+id;
		var shopImg=$(cardid).find("img").attr("src");
		var shopName=$(cardid).find(".shop-name").text();
		//alert(id);
		window.location.href=makeurl("./notcollareddetail.jspx?cardId="+id+"&shopName="+shopName+"&shopImg="+shopImg+"&openid="+openid);
	});
	$('.all-VIPCard-list').delegate(".get-atOnce","click",function(){
		var id=$(this).parents('.VIPCard-list-detail').attr("id");
		window.location.href=makeurl("./infowrite.jspx?typeid="+id);
	});
})

function getRandom(n){
        return Math.floor(Math.random()*n+1)
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