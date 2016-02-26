var shopId=decodeURI(getQueryString("shopId"));
//var	shopId="123";
var desplength=1;//图文描述条数
var flag=new Array();
flag.push(true);//delete or not
window.onload=function(){
	/*加载商品信息*/
	initShopAdministerName(shopId);
	var saleornot="all";
	var upornot="2";
	initClasses(shopId,saleornot,upornot);
}
$(function(){
		//initClasses(shopId);
		//新增一个分类
		$(".new").click(function()
		{
			$(".newclass").attr("style","display:block");
		});
		$(".cancel").click(function()
		{
			$(".newclass").attr("style","display:none");
		});
		$(".newclass").delegate(".submitclass","click",function()
		{
			var classname=$(".classname").val();
			var $option=$("<option selected='true'>"+classname+"</option>");
			$(".selectclass").append($option);
			$(".newclass").hide();
		});
		
		
		
		//返回
		$(".back").click(function(){
			window.location.href= "/siims/szb/goods/goodslisttob.jspx"+"?shopId="+shopId;//带参数传递到商品编辑，带shopid和商品id
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
			var $pic=$('<label class="b1"><b class="b11"></b>&nbsp;描述图片：</label><img class="imgTwo'+desplength+'" name="goodspics" src="/wro/wroResources?id=classpath:ui/image/null.png" id="file_up2"/><button class="b3" id="file_up2">上传描述图片</button><input type="file" onchange="preImg(this.id)" accept="image/jpeg,image/png" class="file2" name="uploadImg" id="imgTwo'+desplength+'" /><br /><br />');
			var $des=$('<label class="c1"><b></b>&nbsp;描述信息：</label><textarea class="c2 goodsdescription" id="'+desplength+'"></textarea><button class="c4" id="'+desplength+'">删除该条</button><br /><br />');
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
			var goodsname=$(".goodsname").val();
			var goodsconfig=$(".goodsstandard").val();
			var goodsprice=$(".goodsprice").val();
			var reg1=/^\d{1,4}(\.\d{1,2})$/;
			var reg2=/^\d{1,4}$/;
			if((!reg1.test(goodsprice))&&(!reg2.test(goodsprice)))
			{
				alert("请输入万元以下金额，小数点最多两位");
			}else
			{
			var goodspic=$(".a2").attr("src");
			//alert(goodspic);
			var goodsclass=$(".selectclass :selected").val();
			var saleornot=$(".sale input:radio:checked").val();
			var upornot=$(".up input:radio:checked").val();
			//var goodspics="描述图片";
			//var goodsdesp=$(".goodsdescription").val();
			var desparray=getMuti();
			//alert(desparray[0].goodDes);
			if(goodsname==""||goodsconfig==""||goodsprice==""||goodsclass==""||saleornot==""||upornot==""||goodspic=="")
			{
				alert("请完整填写数据项");
			}
			else{
				addGoods(shopId,goodsname,goodsconfig,goodsprice,goodspic,goodsclass,saleornot,upornot,desparray);
			}	
			}
		});
			
	})// JavaScript Document
function getMuti()
{
	var goodArray=new Array();
	var goods;
	var f=0;
	for(var i=0;i< desplength;i++)
	{	
		if(flag[i]==true)
		{
			goods=new Object();
			//alert($(this).html());
			var goodId="";
			var goodDesPicUrl=$(".imgTwo"+(i+1)).attr("src");
			var goodDes=$(".b1").siblings("#"+(i+1)).val();
			goods.detailId=goodId;
			goods.goodDesPicUrl=goodDesPicUrl;
			goods.goodDes=goodDes;
			goodArray.push(goods);
		}
	}
	return goodArray;
}
function addGoods(shopId,goodsname,goodsconfig,goodsprice,goodspic,goodsclass,saleornot,upornot,desparray){
	var datatosend = {
		"storeId":shopId,
		"goodsName":goodsname,
		"configName":goodsconfig,
		"configPrice":goodsprice,
		"goodsDistribution":goodsclass,
		"goodsType":saleornot,
		"onShelf":upornot,
		"goodsShowImg":goodspic,
		"DATA":"{"+"Data:"+JSON.stringify(desparray)+"}"
	};
		
		$.ajax({
			type:"get",
			url:addgoods,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				alert("添加成功");
				window.location.href= "/siims/szb/goods/goodslisttob.jspx"+"?shopId="+shopId;
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
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
function initClasses(shopId,saleornot,upornot){
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
				//console.log(data);
				var num=data.DATA.length;
				if(num!=0)
				{
					var goodsclass=data.DATA[0].goodsDistribution;//分类名
					//var classname=1;//分类的class后缀
					$(".selectclass").append(createOptions(goodsclass));
					for(var i=1;i<num;i++)
					{
						if(data.DATA[i].goodsDistribution!=goodsclass)
						{
							goodsclass=data.DATA[i].goodsDistribution;
							$(".selectclass").append(createOptions(goodsclass));                                                                                                                                                                                                                      	
						}
					}
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
function createOptions(goodsclass)
{
	var html="<option id="+goodsclass+">"+goodsclass+"</option>";
	return html;
}


function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
