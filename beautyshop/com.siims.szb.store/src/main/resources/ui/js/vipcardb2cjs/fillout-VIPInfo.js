var typeid=getQueryString("typeid");
var option='<option value="" id="CONFIGID">CONTEXT</option>';
window.onload=function(){
	datatosend={
		"typeId":typeid
	};
	$.ajax({
			type:"get",
			url:queryconfig,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==true){
					$.each(data.DATA,function(i){ 
						var temp = option.replace("CONFIGID", data.DATA[i].CONFIGID).replace("CONTEXT",data.DATA[i].CONTEXT);
						$("#rankID").append(temp);
						});
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
	$('.confirm-VIPInfo').click(function(){
		var name=$('.VIP-name-class').val();
		var phone=$('.VIP-tel-class').val();
		var config=$('#rankID option:selected').attr("id");
		//alert(config);
		var password1=$('.pswClass').val();
		var password2=$('.again-psw-class').val();
		if(name.length==0){
			$.MsgBox.Alert("提示","会员名称不能为空");
		}else if(isNaN(phone)||phone.length!=11){
			$.MsgBox.Alert("提示","手机账号格式不对");
		}else if(password1===password2){
			var password=hex_md5(password1);
			var datatosend={
				"name":name,
				"phone":phone,
				"configid":config,
				"password":password
			}
			$.ajax({
			type:"get",
			url:addconsumervipcard,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==true){
					 id=data.ID;
					 window.location.href=makeurl("./confirmandpay.jspx?vipcardId="+id);
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
			
		}else{
			$.MsgBox.Alert("提示","密码不一致");
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