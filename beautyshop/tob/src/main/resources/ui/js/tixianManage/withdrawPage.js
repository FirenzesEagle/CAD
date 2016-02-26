var tab = "1";
var openid;
$(function(){
	openid = getQueryString("openid");
	
	$('.line-tab').delegate("li","click",function(){
		tab=$(this).data("tab");
		$(this).siblings().children().removeClass("choosed");
		$(this).children().addClass("choosed");
		if(tab=="1"){
			$('.main-creditcard').hide();
			$('.main-Alipay').show();
		}else if(tab=="2"){
			$('.main-Alipay').hide();
			$('.main-creditcard').show();
		}
	});
	
	$(".topReturn").on("click",function(){
		window.location.href="/siims/szb/tixian/toTiXianIndex.jspx?openid="+openid;
	});
	
	
	var bankClass;//记录哪个银行
	
	//选择银行，跳出选择银行页面
	$("#bankSelect").on("click",function(){
		$(".container").hide();
		$(".bank-select-con").show();
	});
	
	//选择具体银行
	$(".bank-select-con .clause-2-ul li").on("click",function(){
		var bankClass=$(this).data("bank");
		console.log(bankClass);
		
		$(".container").show();
		$(".bank-select-con").hide();
		
		$("#bankType").val(bankClass);
	});
	
	//确认提现
	$(".button-1").on("click",function(){
		var name = "";
		var zhifubao = "";
		var secret = "";
		var money = "";
		var cardType = "";
		var cardNumber = "";
		if(tab == "1"){
			name = $("#name1").val();
			zhifubao = $("#zhifubao").val(); 
			secret = $("#secret1").val();
			money = $("#money1").val();
			if(name == "" || name == "请输入您的姓名"){
				alert("请填写姓名"); 
				return;
			}
			if(zhifubao == "" || zhifubao == "请输入您的支付宝账户"){
				alert("请填写支付宝账号");
				return;
			}
			if(secret == "" || secret == "请输入您的密码"){
				alert("请填写密码");
				return;
			}
			if(money == "" || money == "请输入提现金额"){
				alert("请填写提现金额");
				return;
			}
		}else if(tab == "2"){
			name = $("#name2").val();
			cardType = $("#bankType").val();
			cardNumber = $("#cardNumber").val();
			secret = $("#secret2").val();
			money = $("#money2").val();
			if(name == "" || name == "请输入您的姓名"){
				alert("请填写姓名");
				return;
			}
			if(cardType == "" || cardType == "请选择银行"){
				alert("请填写银行卡类型");
				return;
			}
			if(cardNumber == "" || cardNumber == "请输入银行卡号"){
				alert("请填写银行卡卡号");
				return;
			}
			if(secret == "" || secret == "请输入您的密码"){
				alert("请填写密码");
				return;
			}
			if(money == "" || money == "请输入提现金额"){
				alert("请填写提现金额");
				return;
			}
		}
		secret = hex_md5(secret);
		console.log(openid);
		console.log(secret);
		var yes = "";
		//var res = confirmauth(openid,secret);
		
		
		var confirmauthUrl = "/siims/szb/StoreInfoAction/confirmauth.jspx";
		var datatosend={
			"openid" : openid,
			"password" : secret
		};
		$.ajax({
			type : "post",
			url : confirmauthUrl,
			async : false,
			dataType : "json",
			data : datatosend,
			timeout : 5000,
			success : function(result) {
				console.log(result.SUCCESS);
				if(result.SUCCESS == "true"){
					yes = tixianReq(openid,name,zhifubao,secret,tab,money,cardType,cardNumber);
				}else if(result.SUCCESS == "false"){
					alert("密码输入有误。");
					return;
				}else{
					alert("参数输入有误。");
					return;
				}
				console.log(yes);
			},
			error : function() {
				console.log("无获取從" + confirmauthUrl + "数据。。。");
			}
		});
		
		/*
		console.log(res);
		if(res == "error"){
			alert("参数有误。。。");
			return;
		}else{
			res = $.parseJSON(res);
			console.log(res.SUCCESS);
			
			if(res == "true"){
				result = tixianReq(openid,name,zhifubao,secret,tab,money,cardType,cardNumber);
			}else if(res == "false"){
				alert("密码输入有误。");
				return;
			}else{
				alert("参数输入有误。");
				return;
			}
		}
		*/
		console.log(yes);
		//var result = tixianReq(openid,name,zhifubao,secret,tab,money,cardType,cardNumber);
		if(yes != ""){
			location.href = "/siims/szb/tixian/toTiXianSuc.jspx?openid="+openid;
		}
		else{
			alert("提现申请失败");
		}
	});
});

//提现申请,,,,该页面还需要获取openid参数，这个交给设置微信公众号的人弄。。。。
//提现申请,,,,该页面还需要获取openid参数，这个交给设置微信公众号的人弄。。。。
//提现申请,,,,该页面还需要获取openid参数，这个交给设置微信公众号的人弄。。。。
function tixianReq(openid,name,zhifubao,secret,type,money,cardType,cardNumber){
	var tixianReqUrl = "";
	if(type == "1"){
		tixianReqUrl = "/siims/szb/tixian/tixianRequest.jspx?openId="+openid+"&shoperName="+name+"&zhiFuBaoAccount="+zhifubao+"&secret="+secret+"&type="+type+"&money="+money;
	}
	else if(type == "2"){
		tixianReqUrl = "/siims/szb/tixian/tixianRequest.jspx?openId="+openid+"&shoperName="+name+"&secret="+secret+"&type="+type+"&money="+money+"&cardType="+cardType+"&cardNumber="+cardNumber;
	}
	
	var res="";
	$.ajax({
		url : tixianReqUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		success : function(result) {
			//res = $.parseJSON(result);
			//var msg = res.MSG;
			//alert(msg);
			updateTixianMoney(openid,money);
			res = result;
			
		},
		error : function() {
			res = "";
			console.log("无获取從" + tixianReqUrl + "数据。。。");
		}
	});
	return res;
}

function updateTixianMoney(openid,money){
	var updateTixianMoneyReqUrl = "/siims/szb/tixianMoney/updateTixianMoneyRequest.jspx?openId="+openid+"&money="+money;
	$.ajax({
		url : updateTixianMoneyReqUrl,
		async : false,
		dataType : "text",
		data : "",
		timeout : 5000,
		success : function(result) {
			
		},
		error : function() {
			console.log("无获取從" + updateTixianMoneyReqUrl + "数据。。。");
		}
	});
}

//获取链接参数
function getQueryString(name) {
	 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function confirmauth(openid,password){
	var confirmauthUrl = "/siims/szb/StoreInfoAction/confirmauth.jspx";
	var datatosend={
		"openid" : openid,
		"password" : password
	};
	$.ajax({
		type : "post",
		url : confirmauthUrl,
		async : false,
		dataType : "text",
		data : datatosend,
		timeout : 5000,
		success : function(result) {
			console.log(result);
			return result;
		},
		error : function() {
			console.log("无获取從" + confirmauthUrl + "数据。。。");
			return "error";
		}
	});
}
//payPass=hex_md5(payPass);  密码加密
