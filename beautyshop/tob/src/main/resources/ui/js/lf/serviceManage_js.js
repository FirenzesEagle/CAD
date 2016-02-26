// JavaScript Document
var shopId=decodeURI(getQueryString("shopId"));
//var shopId="123";
var servicedetailid;
window.onload=function(){
	//var shopId=decodeURI(getQueryString("shopId"));
	initShopAdministerName(shopId);
	//alert(shopId);
	/*加载商品信息*/
	initTables(shopId);
	initServiceStorage(shopId);
}

$(function()
	{
		//var shopId=decodeURI(getQueryString("shopId"));
	//子类的折叠与展开
	var flag=0;//0是展开的状态
	$(".cont").delegate("#goods","click",function()
	{	
		if(flag==0)
		{
			$(this)
				.text("点击展开")
				.parents()
				.siblings("thead,tbody")//折叠
				.hide();
				flag=1;
		}
		else
		{
			$(this)
				.text("点击折叠")
				.parents()
				.siblings("thead,tbody")
				.show();
				flag=0;
		}	
	});
		
		
		//添加服务
		$(".additem").click(function(){
			window.location.href= "/siims/szb/service/addservice.jspx"+"?shopId="+shopId;//带参数传递到添加商品页
		})
				
		
		//编辑
		$(".cont").delegate(".edit","click",function(){
			serviceid=$(this).parents("td").siblings(".SID").text();
			window.location.href="/siims/szb/service/editservice.jspx?serviceId="+serviceid+"&shopId="+shopId;//带参数传递
		})
		
		//删除
		$(".cont").delegate(".delete","click",function()
		{
			var result=confirm("确认删除此服务？");
			if(result)
			{
				var serviceid=$(this).parents("td").siblings(".SID").text();
				deleteService(serviceid);
				renewTable();
				//alert("删除成功！");
			}
				
		});
			
		//商品管理
		$(".goods").click(function(){
			window.location.href= "../goods/goodslisttob.jspx"+"?shopId="+shopId;//带参数传递到商品管理
		});
		//服务管理
		$(".services").click(function(){
			window.location.href= "/siims/szb/service/servicelisttob.jspx"+"?shopId="+shopId;//带参数传递到服务管理
		})
	});
function renewTable()
{
	//清空内容
			$(".cont").empty();
			initTables(shopId);
			initServiceStorage(shopId);	
}
function initTables(shopId){
	var datatosend = {
		"storeId":shopId
	};
		$.ajax({
			type:"get",
			url:servicelists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
					console.log(data);
					var num=data.DATA.length;
					$("b").text("共"+num+"项服务");
					$(".cont").append(createTables());
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
						alert("表格加载失败");
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                    }
		});
}
function createTables(){
	var html='<table class="serviceslist" table-layout="fixed"><thead class="title"><td colspan="5" class="innertitle" width="918px">服务列表</td><td id="goods" width="115px">点击折叠</td></thead>';
		html+='<thead id="list_goods_01"><td width="17%">服务图片</td><td width="17%">服务ID</td><td width="17%">服务名称</td><td width="16%">服务时长</td><td width="16%">服务价格</td><td width="17%">操作</td></thead>';
		html+='<tbody id="list_goods_01"></tbody></table>';
	return html;
}

function initServiceStorage(shopId){
	var datatosend = {
		"storeId":shopId
		};//参数要改
		$.ajax({
			type:"get",
			url:servicelists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				//alert("2成");
				for(var i=0;i<data.DATA.length;i++){
					$('.serviceslist').append(creatGoods(data.DATA[i]));
				}
				changeColor();
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
						alert("服务加载失败");
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
                    }
		});
}
function creatGoods(data){
	var html='<tr><td><img src="'+data.serviceShowImg+'" alt="" style="max-width:150px;"/></td>';
		html+='<td class="SID">'+data.serviceId+'</td>';
		html+='<td>'+data.serviceName+'</td>';
		html+='<td>'+data.serviceTime+'</td>';
		html+='<td>'+data.servicePrice+'</td>';
		html+='<td class="management"><button class="edit">编辑</button><button class="delete">删除</button><br /></td></tr>';
        return html;
}

function deleteService(serviceid){
	var datatosend = {
		"serviceId":serviceid};
		$.ajax({
			type:"get",
			url:deleteservice,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				alert("删除成功！");
				//window.location.href= "servicelisttob.jspx"+"?shopId='"+shopId+"'";
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
						alert("删除失败！");
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
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
					$(".name").text(data.DATA.person);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                    }
		});
}



function makeurl(url){
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

function upGoods(shopId,goodsid){
	var datatosend = {
		"shopId":shopId,
		"goodsId":goodsid };
		$.ajax({
			type:"get",
			url:goodlists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				alert("上架成功！");
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
						alert("上架失败！")
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
                    }
		});
}
function changeSaleornot(shopid,goodsid){
	var datatosend = {
		"shopId":shopId,
		"goodsId":goodsid };
		$.ajax({
			type:"get",
			url:goodlists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
						alert("失败！")
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
                    }
		});
}
function changeColor(){
	//奇偶行变色
		$("tbody>tr:even").addClass("even");
		$("tbody>tr:odd").addClass("odd");
}
