var openid=decodeURI(getQueryString("openid"));
window.onload=function(){
	var datatosend = {
					"openID":openid
					};
					
	$.ajax({
			type:"get",
			url:searchAllReceivingInfo,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS=="true"){
					for(var i=0;i<data.DATA.length;i++){
						createAddress(data.DATA[i]);
					}
					
					
				}else if(data.SUCCESS=="false"){
					console.log(data.ERROMSG);
					creatAddressNull();
				}
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
                        console.log(XMLHttpRequest.status);
                        console.log(errorThrown);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
                    }
			});
}
$(function(){
	$('.address-lists').delegate(".item-edit","click",function(){
		var addrObj=new Object();
		var id=$(this).parents('.list-item').attr("id");
		var addrid='#'+id;
		//alert($(addrid).html());
		addrObj.openid=openid;
		addrObj.name=$(addrid).find(".receiver-name").text();
		addrObj.phone=$(addrid).find(".receiver-phone").text();
		addrObj.addr1=$(addrid).find(".address1").text();
		addrObj.addr2=$(addrid).find(".address2").text();
		addrObj.receiving_id=id;
		addrObj.flag=0;
		if($(addrid).find('.moren').data("flag")=="1"){
			addrObj.flag=1;
		}
		addrObj=JSON.stringify(addrObj);
		window.location.href=makeurl("toaddreceivinginfo.jspx?openid="+openid+"&addrdata="+addrObj);
		//console.log(addrObj);
	});
	$('.address-add input').click(function(){
		window.location.href=makeurl("toaddreceivinginfo.jspx?openid="+openid);
	});
	$('.address-title .nav').click(function(){
		//history.go(-1);
		window.location.href="../personal/tosearchpersonalinfo.jspx?openid="+openid;
	});
})
function createAddress(data){
	var count=0;
	var html='<li class="list-item" id="'+data.receiving_id+'">';
	html+='<div class="item-np"><span class="receiver-name">'+data.receiving_name+'</span><span class="receiver-phone">'+data.receiving_tel+'</span></div>';
	html+='<div class="item-address">';
	var flag=data.default_receiving_info;
	if(flag=="1"){
		html+='<div class="moren" data-flag="'+1+'">[<span>默认</span>]</div>';
	}
	html+='<div class="item-a"><span class="address1">'+data.receiving_address_area+'</span><span class="address2">'+data.receiving_address_detail+'</span></div>';
	html+='<div class="item-edit"><img src="/wro/wroResources?id=classpath:ui/image/lmb/edit.png"/></div>';
	html+='</div>';
	html+='</li>';
	$('.address-list').append(html);
}
function creatAddressNull(){
	var html='<div class="address-null">';
		html+='<img src="/wro/wroResources?id=classpath:ui/image/lmb/addrNull.png" />';
		html+='<p>你还没有添加地址哦</p>';
		html+='</div>';
		$('.address-lists').append(html);
}
function makeurl(url,dataname,data){
	var url=encodeURI(url);
	var data=encodeURI(data);
	var encode=encodeURI(url);
	return encode;
	}
function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}



						
						
							
							
							
						
					
					