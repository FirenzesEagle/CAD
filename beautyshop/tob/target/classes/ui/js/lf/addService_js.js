var shopId=decodeURI(getQueryString("shopId"));
//var shopId="123";
var desplength=1;//图文描述条数
var flag=new Array();
flag.push(true);//delete or not
window.onload=function(){
	initShopAdministerName(shopId);
}
$(function(){
		//返回
		$(".back").click(function(){
			window.location.href= "/siims/szb/service/servicelisttob.jspx"+"?shopId="+shopId;//带参数传递到商品编辑，带shopid和商品id
		})
		
		//点击展示图片上传按钮
		$('.cont').on("click","#file_up",function(){
			$('#imgOne').click();
		});
			
		//继续添加按钮
		$(".c3").click(function()
		{
			flag.push(true);
			desplength++;
			var $line=$('<hr /><br/>');
			var $div=$("<div class='description'></div>");
			var $pic=$('<label class="b1"><b class="b11"></b>&nbsp;描述图片：</label><img class="imgTwo'+desplength+'" name="servicepics" src="/wro/wroResources?id=classpath:ui/image/null.png" id="file_up2"/><button class="b3" id="file_up2">上传描述图片</button><input type="file" onchange="preImg(this.id)" accept="image/jpeg,image/png" class="file2" name="uploadImg" id="imgTwo'+desplength+'" /><br /><br />');
			var $des=$('<label class="c1"><b></b>&nbsp;描述信息：</label><textarea class="c2 servicedescription" id="'+desplength+'"></textarea><button class="c4" id="'+desplength+'">删除该条</button><br /><br />');
			$div.append($pic);
			$div.append($des);
			$div.append($line);
			$(".desp").append($div);

		});
		/**var flag[desplength]={true};
		for(var i=0;i<desplength;i++)
			{
			alert(flag[i]);
			}**/
		//删除一条描述
		
		$(".desp").delegate(".c4","click",function(){
			var num=parseInt($(this).attr("id"));
			flag[num-1]=false;
			//desplength--;
			$(this).parents(".description").remove();
		});
		//点击描述图片上传按钮
		$('.cont').on("click","#file_up2",function(){
			$(this).siblings("input").click();
			//$('#currentid').val($(this).parents(".dish-comm").attr("id"));
		});
		
		//提交
		$(".cont").delegate(".submit","click",function(){
			var servicename=$(".servicename").val();
			var serviceconfig=$(".servicestandard").val();
			var serviceprice=$(".serviceprice").val();
			var reg1=/^\d{1,4}(\.\d{1,2})$/;
			var reg2=/^\d{1,4}$/;
			if((!reg1.test(serviceprice))&&(!reg2.test(serviceprice)))
			{
				alert("请输入万元以下金额，小数点最多两位");
			}else
			{
			var servicepic=$(".a2").attr("src");
			var desparray=getMuti();
			if(servicename==""||serviceconfig==""||serviceprice==""||servicepic=="")
			{
				alert("请完整填写数据项");
			}
			else{
				addService(shopId,servicename,serviceconfig,serviceprice,servicepic,desparray);
			}	
			}
		});
	})// JavaScript Document
function getMuti()
{
	var serviceArray=new Array();
	var service;
	var f=0;
	for(var i=0;i< desplength;i++)
	{	
		if(flag[i]==true)
		{
			service=new Object();
			//alert($(this).html());
			var serviceId="";
			var serviceDesPicUrl=$(".imgTwo"+(i+1)).attr("src");
			var serviceDes=$(".b1").siblings("#"+(i+1)).val();
			service.serviceDesPicUrl=serviceDesPicUrl;
			service.serviceDes=serviceDes;
			service.serviceId=serviceId;
			serviceArray.push(service);
		}
	}
	return serviceArray;
}
function addService(shopId,servicename,serviceconfig,serviceprice,servicepic,desparray){
	var datatosend = {
		"storeId":shopId,
		"serviceName":servicename,
		"serviceTime":serviceconfig,
		"servicePrice":serviceprice,
		"serviceShowImg":servicepic,
		"DATA":"{"+"Data:"+JSON.stringify(desparray)+"}"
	};
		$.ajax({
			type:"get",
			url:addservices,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function(data) {
				//console.log(data);
				alert("添加成功");
				window.location.href="/siims/szb/service/servicelisttob.jspx"+"?shopId="+shopId;
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.responseText);
                console.log(errorThrown);
                console.log(textStatus);
                    }
		});
}
function initShopAdministerName(shopId){
	var datatosend = {
		"shop_id":shopId};
		$.ajax({
			type:"get",
			url:userlists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data!=null)
				{
					$(".name").text(data.DATA.person);
				}
				else
				{
					console.log("未获得数据");
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
function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}