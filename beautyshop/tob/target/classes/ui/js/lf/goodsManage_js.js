// JavaScript Document
var shopId=decodeURI(getQueryString("shopId"));
//alert(shopId);
//var shopId="123";
window.onload=function(){
	//var shopId=decodeURI(getQueryString("shopId"));
	initShopAdministerName(shopId);
	//alert(shopId);
	/*加载商品信息*/
	var upornot="2";//"0"下架，"1"上架，"2"全部
	var saleornot="all";//"sale"现货，"book"预定，"all"全部
	initTables(shopId,upornot,saleornot);
	initGoodStorage(shopId,upornot,saleornot);
}

$(function()
	{

		var saleornot="all";//现货、预定分类
		var upornot="2";//未上架、已上架、全部分类
		//状态面板切换
		$(".tabmenu").delegate(".state li","click",function()
		{
			$(this)
				.addClass("selected")
				.siblings()
				.removeClass("selected");
			upornot=$(this).attr("id");
			var saleornotflag=$("#choose").find("option:selected").text();
			if(saleornotflag=="现货")//现货
			{
				saleornot="sale";
			}else if(saleornotflag=="全部")//全部
			{
				saleornot="all";
			}else//预定
			{
				saleornot="book";
			}
			//清空内容
			$(".cont").empty();
			initTables(shopId,upornot,saleornot);
			initGoodStorage(shopId,upornot,saleornot);
		});
		
		//现货、预订筛选
		$(".tabmenu").delegate("#choose","change",function(){
			var saleornotflag=$("#choose").find("option:selected").text();
			if(saleornotflag=="现货")//现货
			{
				saleornot="sale";
			}else if(saleornotflag=="全部")//全部
			{
				saleornot="all";
			}else//预定
			{
				saleornot="book";
			}
			//清空内容
			$(".cont").empty();
			initTables(shopId,upornot,saleornot);
			initGoodStorage(shopId,upornot,saleornot);
		})
		
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
		
		//多选框
		//选中的改变颜色
		$(".cont").delegate("tbody :checkbox","click",function()
		{
			if($(this).is(":checked")==true)
			{
				$(this)
					.parents("tr")
					.addClass("selectedBcg");
			}
			else
			{
				$(this)
					.parents("tr")
					.removeClass("selectedBcg");
			}
		});
		$(".cont").delegate("thead :checkbox","click",function()
		{
			if($(this).is(":checked")==true)
			{
				$(this)
					.parents("table")
					.find(":checkbox")
					.prop("checked",true)
					.parents("tbody tr")
					.addClass("selectedBcg");
			}
			else
			{
				$(this)
					.parents("table")
					.find(":checkbox")
					.prop("checked",false)
					.parents("tbody tr")
					.removeClass("selectedBcg");
			}
			
		});
		
		//修改是否现货
		$(".cont").delegate("#has","change",function(){
			var saleornotflag=$("#has").find("option:selected").text();
			//alert(saleornotflag);
			if(saleornotflag=="当前有货")//现货
			{
				saleornot="sale";
			}else//预定
			{
				saleornot="book";
			}
			var goodsid=$(this).parents("td").siblings(".GID").text();
			changeSaleornot(goodsid,saleornot);
			renewTable();
		});
		
		//添加商品
		$(".additem").click(function(){
			//alert(shopId);
			window.location.href= "/siims/szb/goods/addgoods.jspx"+"?shopId="+shopId;//带参数传递到添加商品页
		})
		
		//一键上架
		$(".upall").click(function()
		{
			alert("确认一键上架？");
			//var goodsid=$(this).parents("td").siblings(".GID").text();
			var listlength=$("tbody input:checkbox:checked").length;
			for(var i=0;i<listlength;i++)
			{
				goodsid=$("tbody input:checkbox:checked:eq("+i+")").parents("td").siblings(".GID").text();
				changeUpornot(goodsid,"1"); 
			}
			renewTable();
		});
		
		
		//编辑
		$(".cont").delegate(".edit","click",function(){
			goodsid=$(this).parents("td").siblings(".GID").text();
			//alert(goodsid);
			window.location.href= "/siims/szb/goods/editgoods.jspx?goodsId="+goodsid+"&shopId="+shopId;//带参数传递到商品编辑，带shopid和商品id
		})
		
		//删除
		$(".cont").delegate(".delete","click",function()
		{
			var result=confirm("确认删除此商品？");
			if(result)
			{
				var goodsid=$(this).parents("td").siblings(".GID").text();
				deleteGoods(goodsid);	
				renewTable();
				//alert("删除成功！");
			}
		});
		
		//上架下架
		$(".cont").delegate(".upload","click",function()
		{
			var goodsid=$(this).parents("td").siblings(".GID").text();
			//alert($(this).text());
			if($(this).text()=="上架")
			{
				 upornot="1";
				 changeUpornot(goodsid,upornot); 
			}else
			{
				 upornot="0";
				 changeUpornot(goodsid,upornot); 
			}
			
			$(".cont").empty();
			upornot=$(".tabmenu .selected").attr("id");
			var saleornotflag=$("#choose").find("option:selected").text();
			if(saleornotflag=="现货")//现货
			{
				saleornot="sale";
			}else if(saleornotflag=="全部")//全部
			{
				saleornot="all";
			}else//预定
			{
				saleornot="book";
			}
			initTables(shopId,upornot,saleornot);
			initGoodStorage(shopId,upornot,saleornot);
		});
		
		
		//商品管理
		$(".goods").click(function(){
			window.location.href= "/siims/szb/goods/goodslisttob.jspx"+"?shopId="+shopId;//带参数传递到商品管理
		});
		//服务管理
		$(".services").click(function(){
			window.location.href= "../service/servicelisttob.jspx"+"?shopId="+shopId;//带参数传递到服务管理
		})
	})

function initTables(shopId,upornot,saleornot){
	var datatosend = {
		"storeId":shopId,
		"isOnShelf":upornot,
		"type":saleornot};
		$.ajax({
			type:"get",
			url:goodlists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
					console.log(data);
					//alert("1成");
						var num=data.DATA.length;
						$("b").text("共"+num+"件商品");
						if(num!=0)
						{	
							var goodsclass=data.DATA[0].goodsDistribution;//分类名
							//var classname=1;//分类的class后缀
							$(".cont").append(createTables(goodsclass));
							for(var i=1;i<num;i++)
							{
								if(data.DATA[i].goodsDistribution!=goodsclass)
								{
									goodsclass=data.DATA[i].goodsDistribution;
									//classname++;
									$(".cont").append(createTables(goodsclass));                                                                                                                                                                                                                      	
								}
							}
						}
						
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(data);
						alert("表格加载失败");
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                    }
		});
}
function createTables(goodsclass){
	var html='<table class="goodslist'+goodsclass+'" table-layout="fixed"><thead class="title"><td colspan="7" class="innertitle" width="918px">'+goodsclass+'</td><td id="goods" width="115px">点击折叠</td></thead>';
		html+='<thead id="list_goods_01"><td width="10%"><input type="checkbox" id="check_01"/>全选</td><td width="14%">商品图片</td><td width="10%">商品ID</td><td width="10%">商品名称</td><td width="12%">规格</td><td width="14%">价格</td><td width="12%">销量</td><td width="12%">操作</td></thead>';
		html+='<tbody id="list_goods_01"></tbody></table>';
	return html;
}

function initGoodStorage(shopId,upornot,saleornot){
	var datatosend = {
		"storeId":shopId,
		"isOnShelf":upornot,
		"type":saleornot};//参数要改
		$.ajax({
			type:"get",
			url:goodlists,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				
					console.log(data);
					//alert("2成");
					for(var i=0;i<data.DATA.length;i++){
						var classnum=data.DATA[i].goodsDistribution;
						$('.goodslist'+classnum).append(creatGoods(data.DATA[i]));
					}
					changeColor();
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
						alert("商品加载失败");
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
                    }
		});
}
function creatGoods(data){
	//var description=data.goodsShowDes;
	//alert(description);
	var onshelf;
	if(data.onShelf=="0")
	{
		onshelf="上架";
	}else
	{
		onshelf="下架";
	}
	
	var onsale;
	var html='<tr><td><input type="checkbox" id="goods_check_01" /></td>';
	    html+='<td><img src="'+data.goodsShowImg+'" alt="" style="max-width:150px;"/></td>';
		html+='<td class="GID">'+data.goodsId+'</td>';
		html+='<td>'+data.goodsName+'</td>';
		html+='<td>'+data.configName+'</td>';
		html+='<td>'+data.configPrice+'</td>';
		html+='<td>'+data.sale+'</td>';
		//html+='<td>'+description+'</td>';
		html+='<td class="management"><button class="edit">编辑</button><button class="delete">删除</button><br /><button class="upload">'+onshelf+'</button><br />';
        if(data.goodsType=="预定")
		{
			html+='<select id="has"><option value="sale">当前有货</option><option value="book" selected="true">当前无货</option></select></td></tr>';
		}else
		{
			html+='<select id="has"><option value="sale" selected="true">当前有货</option><option value="book">当前无货</option></select></td></tr>';
		}
		return html;
}
function renewTable()
{
	//清空内容
			
			$(".cont").empty();
			upornot=$(".tabmenu .selected").attr("id");
			var saleornotflag=$("#choose").find("option:selected").text();
			if(saleornotflag=="现货")//现货
			{
				saleornot="sale";
			}else if(saleornotflag=="全部")//全部
			{
				saleornot="all";
			}else//预定
			{
				saleornot="book";
			}
			initTables(shopId,upornot,saleornot);
			initGoodStorage(shopId,upornot,saleornot);	
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


function changeSaleornot(goodsid,saleornot){
	var datatosend = {
		"goodsId":goodsid,
		"type":saleornot};
		$.ajax({
			type:"get",
			url:changesaleornot,
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
function changeUpornot(goodsid,upornot){
	var datatosend = {
		"goodsId":goodsid,
		"isOnShelf":upornot};
		$.ajax({
			type:"get",
			url:changeupornot,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
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
function deleteGoods(goodsid){
	var datatosend = {
		"goodsId":goodsid};
		$.ajax({
			type:"get",
			url:deletegoods,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				alert("删除成功！");
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
