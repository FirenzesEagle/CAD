var cardList='<li class="VIPCard-list-detail VIPCARDCLASS" id="CARDID">'
			+'<img src="IMGURL" alt=""/>'
			+'<span class="shop-name">SHOPNAME</span>'
			+'<span class="discount">DISCOUNT</span>'
			+'<div class="card-type"><p>CARDTYPE</p></div>'
			+'</li>';
var openid=getQueryString("openid");
window.onload=function(){
	var datatosend = {
		"openid":openid}; 	
		//console.log(datatosend);
		$.ajax({
			type:"get",
			url:querymycardsmethod,
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
						var temp=cardList.replace("CARDID",data.DATA[i].VIPCARDID).replace("CARDTYPE",data.DATA[i].TYPE).replace("DISCOUNT",data.DATA[i].CONFIG);
						temp=temp.replace("IMGURL",data.DATA[i].STOREIMAGE).replace("SHOPNAME",data.DATA[i].STORENAME).replace("VIPCARDCLASS",VIPCARD);
						$('.VIPCard-list').append(temp);
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
	$('.bt-get-VIPCard').click(function(){
		window.location.href=makeurl("./notcollareddetail.jspx?openid="+openid);
	});
	$('.VIPCard-list').delegate(".VIPCard-list-detail","click",function(){
		var id=$(this).attr("id");
		var cardid='#'+id;
		var shopImg=$(cardid).find("img").attr("src");
		var shopName=$(cardid).find(".shop-name").text();
		window.location.href=makeurl("./collareddetail.jspx?cardId="+id+"&shopName="+shopName+"&shopImg="+shopImg+"&openid="+openid);
	});
	$('.myVIPCard-title .nav').click(function(){
		history.go(-1);
	});
	$('.bt-get-VIPCard').click(function(){
		window.location.href=makeurl("./validlist.jspx?openid="+openid);
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