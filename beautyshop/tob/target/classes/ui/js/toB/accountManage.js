$(function(){
	//$.MsgBox.Alert("","sfkfajkl");

	var id="dheyf1";//shopid需要更改
	var data={
		"shop_id":id
	}
	$.ajax({
		type:"get",
		url:'http://192.168.1.108:8580/siims/szb/StoreInfoAction/getshopinfo.jspx',
		data:data,
		crossDomain:true,
		dataType:"json",
		success:function(data){
			if(data.SUCCESS==="true"){
				var username=data.DATA.username;
				var name=data.DATA.name;
				var address=data.DATA.address;
				var tel=data.DATA.tel;
				var price_least=data.DATA.price_least;
				var peisong=data.DATA.distribution;
				var opentime=data.DATA.opentime;
				var img=data.DATA.image;
				//图片处理
				var url="http://192.168.1.108:8580"+img;
				$("#accountImg").attr("src",url);
				var newImg = new Image(); // 新建一个图片对象
				$(newImg).attr("src" , url); // 将图片的src属性赋值给新建图片对象的src
				
				newImg.onload=function(){
					var imgWidth=newImg.width;
					var imgHeight=newImg.height;
					
					if(imgHeight>=imgWidth){
						$("#accountImg").removeClass("aw");
						$("#accountImg").removeClass("ah");
						$("#accountImg").addClass("aw");
					}else{
						$("#accountImg").removeClass("aw");
						$("#accountImg").removeClass("ah");
						$("#accountImg").addClass("ah");
					}
				};
				//账户处理
				$("#userAccout").text(username);
				$("#userName").text(name);
				$("#userAddr").text(address);
				$("#userTel").text(tel);
				$("#priceLeast").text(price_least);
				$("#pricePeisong").text(peisong);
				$("#openTime").text(opentime);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.readyState);
			console.log(textStatus);
        }
	});
	//更改通项选择
	$(".singleinfo-content").on("blur",function(){
		//var text=$(this).prev(".singleinfo-title").text();
		//console.log($(this).parent().index(".singleinfo-layer"));
		var num=$(this).parent().index(".singleinfo-layer");
		var info=$(this).text();
		
		if(num===2){
			var teltest=/^[0-9 -]+$/;
			if(!teltest.test(info)){
				$.MsgBox.Alert("","请输入有效电话格式");
				$(this).focus();
				return false;
			}
			
			
		}else if(num===3||num===4){
			var moneytest=/^\d+(\.{1}\d+)?$/;
			if(!moneytest.test(info)){
				$.MsgBox.Alert("","请输入有效金额");
				$(this).focus();
				return false;
			}
			
		}else{
			if(info===null||info===""||info===undefined){
				$.MsgBox.Alert("","该内容不能为空！");
				$(this).focus();
				return false;
			}
			
		}
		num=num+3;
		var id="dheyf1";
		console.log(info);
		
		var datatosend={
			"shop_id":id,
			"which":num,
			"info":info
		};
		$.ajax({
			type:"get",
			url:'http://192.168.1.108:8580/siims/szb/StoreInfoAction/modifyStoreInfo.jspx',
			data:datatosend,
			crossDomain:true,
			dataType:"json",
			success:function(data){
				//alert(data.DATA.id);
				console.log("success");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
	        }
		});
	});
	//更改商店图片
	$("#accountimgCon").on("click",function(){
		$("#accountImgFile").click();
	});/*
	//更改商户名称
	$("#userName").on("blur",function(){
		
	});
	//更改商户地址
	$("#userAddr").on("blur",function(){
		
	});
	//更改商户电话
	$("#userTel").on("blur",function(){
		
	});*/
});
function accountImgSelect(file){
	var url=getFileUrl($(file).attr("id"));
	var imgPre=document.getElementById("accountImg");
	$("#accountImg").attr("src",url);
	
	var newImg = new Image(); // 新建一个图片对象
	$(newImg).attr("src" , url); // 将图片的src属性赋值给新建图片对象的src
	
	newImg.onload=function(){
		var imgWidth=newImg.width;
		var imgHeight=newImg.height;
		
		if(imgHeight>=imgWidth){
			$("#accountImg").removeClass("aw");
			$("#accountImg").removeClass("ah");
			$("#accountImg").addClass("aw");
		}else{
			$("#accountImg").removeClass("aw");
			$("#accountImg").removeClass("ah");
			$("#accountImg").addClass("ah");
		}
	};
}
