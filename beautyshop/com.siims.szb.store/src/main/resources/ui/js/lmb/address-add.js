var openid=decodeURI(getQueryString("openid"));
var receiving_id=null;
window.onload=function(){
	var addrdata=decodeURI(getQueryString("addrdata"));
	var data=JSON.parse(addrdata);
	//console.log(data);
	if(data!=null){
		$('.userName').val(data.name);
		$('.userAddress1').val(data.addr1);
		$('.userAddress2').val(data.addr2);
		$('.userPhone').val(data.phone);
		$('.addr-img img').data("flag",data.flag);
		receiving_id=data.receiving_id;
		if(data.flag=="1"){
			$('.addr-img img').attr("src","/wro/wroResources?id=classpath:ui/image/lmb/addr-mr.png");
		}else if(data.flag=="0"){
			$('.addr-img img').attr("src","/wro/wroResources?id=classpath:ui/image/lmb/addr-wxz.png");
		}
		
	}
}
$(function(){
				$('.addr-img img').click(function(){
					if($(this).data("flag")=="0"){
						$(this).data("flag","1");
						//alert($(this).data("flag"));
						$(this).attr("src","/wro/wroResources?id=classpath:ui/image/lmb/addr-mr.png");
					}else if($(this).data("flag")=="1"){
						$(this).data("flag","0");
						//alert($(this).data("flag"));
						$(this).attr("src","/wro/wroResources?id=classpath:ui/image/lmb/addr-wxz.png");
					}
				});
				$('.address-bt input').click(function(){
					var name=$('.userName').val();
					var addr1=$('.userAddress1').val();
					var addr2=$('.userAddress2').val();
					var phone=$('.userPhone').val();
					var flag=$('.addr-img img').data("flag");
					if(name.length==0||addr1.length==0||addr2.length==0||phone.length==0||isNaN(phone)||phone.length!=11){
						$.MsgBox.AlertNo("提示","内容有错误,请重新填写");
					}else{
						if(receiving_id!=null){
							var ob={
									"openID":openid,
									"receiving_name":name,
									"receiving_tel":phone,
									"default_receiving_info":flag,
									"receiving_address_area":addr1,
									"receiving_address_detail":addr2,
									"receiving_id":receiving_id
							};
							//alert(JSON.stringify(ob));
							var datatosend={
									DATA:JSON.stringify(ob)
							};
							//alert(datatosend);
							$.ajax({
								type:"get",
								url:editReceivingInfo,
								async:"false",
								data:datatosend,
								dataType:"json",
								crossDomain:true,
								success:function (data) {
									console.log(data);
									if(data.SUCCESS=="true"){
										window.location.href=makeurl("tosearchallreceivinginfo.jspx?openid="+openid);
										
									}else if(data.SUCCESS=="false"){
										console.log(data.ERROMSG);
										
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
						}else{
							//alert(1);
							var ob={
									"openID":openid,
									"receiving_name":name,
									"receiving_tel":phone,
									"default_receiving_info":flag,
									"receiving_address_area":addr1,
									"receiving_address_detail":addr2
									
							};
							//alert(JSON.stringify(ob));
							var datatosend={
									DATA:JSON.stringify(ob)
							}
							//alert(datatosend);
							$.ajax({
								type:"get",
								url:addReceivingInfo,
								async:"false",
								data:datatosend,
								dataType:"json",
								crossDomain:true,
								success:function (data) {
									console.log(data);
									if(data.SUCCESS=="true"){
										window.location.href=makeurl("tosearchallreceivinginfo.jspx?openid="+openid);
										
									}else if(data.SUCCESS=="false"){
										console.log(data.ERROMSG);
										
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
						
					}
					
				});
				$('.address-title .nav').click(function(){
					window.location.href="tosearchallreceivinginfo.jspx?openid="+openid;
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