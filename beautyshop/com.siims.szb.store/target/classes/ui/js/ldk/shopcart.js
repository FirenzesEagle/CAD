var windowHeight=window.outerHeight;
var code=decodeURI(getQueryString("code"));
var openid=decodeURI(getQueryString("openid"));
if(code!="null"&&code!="undefined"){
	$.ajax({
		type:"get",
		url:'http://182.92.4.200/xyhWeixinServer/getopenid?code='+code,
		async:"false",
		data:/*code*/"",
		dataType:"json",
		crossDomain:true,
		success:function (data) {
			openid = data.openid;
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
					//console.log(data);
	                console.log(XMLHttpRequest.status);
	              //  alert(XMLHttpRequest.readyState);
	              //  alert(textStatus);
	            }
	});
}


//var districtId=decodeURI(getQueryString("district_id"));
var districtId="qwe123";
window.onload=function(){
	initshopcart();
	$(".shop-cart").children().each(function(){
			id='#'+$(this).attr("id");
			subtotal(id);	
		});
	totalmoney();
	
}
$(function(){
	$('.nav').click(function(){
		history.go(-1);
	});
	$('.shop-cart').delegate(".shop-none-sub input","click",function(){
		window.location.href="../store/togetallstore.jspx?openid="+openid;
	});
	$(".menu-list").on("click","li",function(){
		if($(this).children().hasClass("menu-index")===true){
			
			window.location.href="../store/togetallstore.jspx?openid="+openid;
		}else if($(this).children().hasClass("menu-shopcart")===true){
			
		}else if($(this).children().hasClass("menu-mycenter")===true){
			//此处写导向个人中心的action
			window.location.href="../personal/tosearchpersonalinfo.jspx?openid="+openid;
		}
	});
	$(".shop-cart").delegate(".num-add","click",function(){
		var num=parseInt($(this).parent().find(".num-text").val())+1;
		var id='#'+$(this).parents('.cart-list').attr("id");
		var shop_id=$(id).find('.cart-shop').attr("id");
		var item_id=$(this).parents('.item-list').attr("id");
		goodid='#'+item_id;
		var user_id="000000";
		var item_count=num;
		var item_price=parseFloat($(goodid).find('.goodPrice').text());
		var ob = new Object();
		ob.shop_id=shop_id;
		ob.item_id=item_id;
		ob.user_id="000000";
		ob.openid=openid;
		ob.item_count=num;
		ob.item_price=item_price;
		console.log(JSON.stringify(ob));
		var datatosend={
			"DATA":JSON.stringify(ob)
		};
		
	//	console.log(datatosend);
		$.ajax({
			type:"get",
			url:shoppingTrollyAdd,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS=="true"){
					console.log(data.DATA);
					$(goodid).find(".num-text").val(num);
					subtotal(id);
					totalmoney();
					
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
		
		
	});
	$(".shop-cart").delegate(".num-min","click",function(){
		var val=parseInt($(this).parent().find(".num-text").val());
		if(val>0){
			
		var id='#'+$(this).parents('.cart-list').attr("id");
		var shop_id=$(id).find('.cart-shop').attr("id");
		var item_id=$(this).parents('.item-list').attr("id");
		goodid='#'+item_id;
		var user_id="000000";
		var item_count=val-1;
		var item_price=parseFloat($(goodid).find('.goodPrice').text());
		var ob = new Object();
		ob.shop_id=shop_id;
		ob.item_id=item_id;
		ob.user_id="000000";
		ob.openid=openid;
		ob.item_count=item_count;
		ob.item_price=item_price;
		console.log(JSON.stringify(ob));
		var datatosend={
			"DATA":JSON.stringify(ob)
		};
		
		//console.log(datatosend);
		$.ajax({
			type:"get",
			url:shoppingTrollyAdd,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS=="true"){
					$(goodid).find(".num-text").val(val-1);
					subtotal(id);
					totalmoney();
					if(val-1==0){
						$('.shop-cart').empty();
						initshopcart();
					}
					
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
	});
	$(".shop-cart").delegate(".checkbox","click",function(){
		var flag=$(this).find("input").data("choose");
		var id='#'+$(this).parents('.cart-list').attr("id");
			if(flag=="0"){
				$(this).find("input").data("choose","1");
				$(this).find("label").addClass("shopChoosed");
				selectAllShopGood(id,"1");
			}else if(flag=="1"){
				$(this).find("input").data("choose","0");
				$(this).find("label").removeClass("shopChoosed");
				selectAllShopGood(id,"0");
			}
			traverseShop();
			subtotal(id);
			totalmoney();
	});
	$(".shop-cart").delegate(".item-checkbox label","click",function(){
		var flaggood=$(this).parent().find("input").data("choose");
		//alert(flaggood);
		var id='#'+$(this).parents('.cart-list').attr("id");
		if(flaggood=="0"){
			$(this).parent().find("input").data("choose","1");
			$(this).addClass("goodChoosed");
			
		}else if(flaggood=="1"){
			$(this).parent().find("input").data("choose","0");
			$(this).removeClass("goodChoosed");
			
		}
		traverseGood(id);
		traverseShop();
		subtotal(id);
		totalmoney();
	});
	$('.checkAll label').click(function(){
		var allflag=$(this).parent().find("input").data("choose");
		if(allflag=="0"){
			$(this).parent().find("input").data("choose","1")
			$(this).addClass("allChoosed");
			selectAllShop("1");
		}else if(allflag=="1"){
			$(this).parent().find("input").data("choose","0")
			$(this).removeClass("allChoosed");
			selectAllShop("0");
		}
		
	});
	$('.shop-cart').delegate(".item-gooddel","click",function(){
		var item_id=$(this).parents(".item-list").attr("id");
		var goodid='#'+item_id;
		var id='#'+$(this).parents('.cart-list').attr("id");
		var shop_id=$(id).find('.cart-shop').attr("id");
		$.MsgBox.Confirm("提示","确认删除吗？",function(){
			var datatosend={
			"openid":openid,
			"item_id":item_id,
			"shop_id":shop_id
		};
		console.log(datatosend);
			$.ajax({
			type:"get",
			url:shoppingTrollyDelete,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS=="true"){
					console.log(data.DATA);
					$(goodid).remove();
					subtotal(id);
					totalmoney();
					traverseIsNull();
					
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
		});
	})
	$('.cart-accounts').delegate(".accounts-pay","click",function(){
		var alldata=new Array();
		$('.shop-cart').find('.cart-list').each(function(){
			var shopID=$(this).find('.cart-shop').attr('id');
			var id=$(this).attr('id');
			var ID='#'+id;
			var shopObj=new Object();
			
			var shopdata=new Array();
			$(this).find('.item-list').each(function(){
				var goodID=$(this).attr("id");
				var listid='#'+goodID;
				var obj=new Object();
				if($(this).find(".item-checkbox input").data("choose")=="1"){
					
					obj.goodId=goodID;
					obj.goodName=encodeURI(encodeURI($(listid).find('.goodName').text()));
					obj.goodPrice=$(listid).find('.goodPrice').text();
					obj.goodNum=$(listid).find('.num-text').val();
					obj.goodPaytype=encodeURI(encodeURI($(listid).find('.goodPrice').data("paytype")));
					//alert(obj.goodId+obj.num+obj.goodName+obj.goodPrice+obj.goodPaytype);
					shopdata.push(obj);
				}
				
			});
			if(shopdata.length>0){
				shopObj.shopId=shopID;
				shopObj.shopName=encodeURI(encodeURI($(ID).find('.shopName').text()));
				shopObj.shopData=shopdata;
				alldata.push(shopObj);
			}
			
		});
		//console.log(alldata);
		if(alldata.length>0){
			var Json=new Object();
			Json.data=alldata;
			Json=JSON.stringify(Json);
			window.location.href="../ShoppingTrollyAction/orderConfirm.jspx?data="+Json+"&openid="+openid;
		}else{
			$.MsgBox.Alert("提示","请先选中要结算的商品");
		}
	});
	
})
function initshopcart(){
	
	var datatosend = {
			"openid":openid
			};
			
$.ajax({
	type:"get",
	url:shoppingTrollyAllShoppingTrollyData,
	async:"false",
	data:datatosend,
	dataType:"json",
	crossDomain:true,
	success:function (data) {
		console.log(data);
		if(data.SUCCESS=="true"){
			//console.log(data.DATA);
			if(data.DATA.length>0){
				for(var i=0;i<data.DATA.length;i++){
				creatList(data.DATA[i]);
				}
			}else{
				creatShopCartNull();
			}
		}else if(data.SUCCESS=="false"){
			console.log(data.ERROMSG);
			creatShopCartNull();
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
/*设置全选店铺，或者全不选店铺*/
function selectAllShop(flag){
	if(flag=="1"){
		$('.shop-cart').find('.cart-list').each(function(){
			$(this).find('.checkbox label').addClass("shopChoosed");
			$(this).find(".checkbox input").data("choose","1");
			var id='#'+$(this).attr("id");
			selectAllShopGood(id,"1");
			subtotal(id);
			totalmoney();
		});
	}else if(flag=="0"){
		$('.shop-cart').find('.cart-list').each(function(){
			$(this).find('.checkbox label').removeClass("shopChoosed");
			$(this).find(".checkbox input").data("choose","0");
			var id='#'+$(this).attr("id");
			selectAllShopGood(id,"0");
			subtotal(id);
			totalmoney();
		});
	}
	
}
/*设置全选店铺里面的商品，或者全不选店铺里面的商品*/
function selectAllShopGood(id,flag){
	var target=id;
	$(target).find('.item-list').each(function(){
		if(flag=="1"){
			$(this).find('.item-checkbox input').data("choose","1");
			$(this).find('.item-checkbox label').addClass("goodChoosed");
		}else{
			$(this).find('.item-checkbox input').data("choose","0");
			$(this).find('.item-checkbox label').removeClass("goodChoosed");
		}
		
		
	});
}
/*遍历商铺里面的商品是否全选*/
function traverseGood(id){
	var flag="1";
	var target=id;
	$(target).find('.item-list').each(function(){
		if($(this).find('.item-checkbox input').data("choose")=="0"){
			flag="0";
			return false;
		}
	});
	if(flag=="1"){
		$(target).find('.checkbox input').data("choose","1");
		$(target).find('.checkbox label').addClass("shopChoosed");
	}else{
		$(target).find('.checkbox input').data("choose","0");
		$(target).find('.checkbox label').removeClass("shopChoosed");
	}
}
/*遍历各个商铺是否全选*/
function traverseShop(){
	var flag="1";
	$('.shop-cart').find('.cart-list').each(function(){
		if($(this).find('.checkbox input').data("choose")=="0"){
			flag="0";
			return false;
		}
	});
	if(flag=="1"){
		$('.checkAll input').data("choose","1");
		$('.checkAll label').addClass("allChoosed");
	}else{
		$('.checkAll input').data("choose","0");
		$('.checkAll label').removeClass("shopChoosed");
	}
}
/*计算各个商铺的小计*/
function subtotal(id){
	var target=id;
	//alert(t.html());
	var total=parseFloat(0);
	$(target).find('.item-list').each(function(){
		var choose=$(this).find('.item-checkbox input').data("choose");
		//alert(choose);
		//alert($(this).find('.goodPrice').text());
		
		if(choose=="1"){
			var price=parseFloat($(this).find('.goodPrice').text());
			//alert(price);
			var num=parseInt($(this).find('.num-text').val());
			//alert(num);
			total+=price*num;
			//alert(total);
		}
	});
	$(target).find('.subtotal').text(total.toFixed(2));
}

/*计算总计*/
function totalmoney(){
	var totalMoney=parseFloat(0);
	$('.shop-cart').find('.cart-list').each(function(){
		totalMoney+=parseFloat($(this).find('.subtotal').text());
		//alert(totalMoney);
	});
	$('.totalMoney').text(totalMoney);
}
/*生成商品列表*/
function creatList(data){
	console.log(data);
	var html='<li class="cart-list" id="'+data[0].shop_id+'">';
		html+='<div class="cart-shop" id="'+data[0].shop_id+'">';		
		html+='<div class="checkbox" id="">';
  		html+='<input type="checkbox" value="" id="" name="" data-choose="0"/>';
	  	html+='<label for="checkboxInput" class="checklabel"></label>';
  		html+='</div>';
		html+='<div id="" class="cart-shopname">';
		html+='<span class="shopName">'+data[0].shop_name+'</span>';
		//html+='<div class="cart-shopnav"></div>';
		html+='</div>';
		html+='</div>';
		var goodlist='<ul class="cart-ordermain">';
		
		for(var i=0;i<data.length;i++){
			goodlist+='<li class="item-list" id="'+data[i].item_id+'">';
			goodlist+='<div class="item-checkbox">';
			goodlist+='<input type="checkbox" value="1" id="checkbox" name="" data-choose="0" />';
			goodlist+='<label for="checkbox"></label>';
			goodlist+='</div>';
			goodlist+='<div id="" class="item-good">';
			goodlist+='<div class="item-goodimg"><img src="'+data[i].item_img_url+'"/></div>';
			goodlist+='<div class="item-goodinfo">';
			goodlist+='<p class="goodName">'+data[i].item_name+'</p>';
			goodlist+='<p class="goodPrice-tag">&yen;<span class="goodPrice" data-paytype="'+data[i].item_spec+'">'+data[i].item_price+'</span></p>';
			goodlist+='<div class="num-choose">';
			goodlist+='<button class="num-min"  type="button" >-</button>';
			goodlist+='<input class="num-text" name="" type="text" value="'+data[i].item_count+'" " readonly="readonly" />';
			goodlist+='<button class="num-add" type="button">+</button>';
			goodlist+='</div>';
			goodlist+='</div>';
			goodlist+='<div class="item-gooddel"></div>';
			goodlist+='</div>';
			goodlist+='</li>';
		}
		
		html+=goodlist;
		html+='</ul>';
		html+='<div class="cartlist-total">';
		html+='<span>小计：<span class="yen">&yen;</span><span class="subtotal">0</span></span>';
		html+='</div>';
		html+='</li>';
		$('.shop-cart').append(html);				
}
/*删除遍历函数*/
function traverseIsNull(){
	$('.shop-cart').find('.cart-list').each(function(){
		var id='#'+$(this).attr("id");
		if($(this).find('.item-list').size()<=0){
			$(id).remove();
		}
		subtotal(id);
		totalmoney();
	});
	if($('.shop-cart .cart-list').size()<=0){
		$('.cart-accounts').hide();
		$('.shop-content').append(creatShopCartNull());
		$('.shop-content').css("height",windowHeight-100);
	}
}
function creatShopCartNull(){
	var html='<div class="shopNull">';
		html+='<div class="shop-none-pic">'	;			
		html+='<img src="/wro/wroResources?id=classpath:ui/image/ldk/shopNull.png" />';
		html+='<p>购物车是空的</p>';
		html+='</div>';
		html+='<div class="shop-none-sub"><input type="submit" value="去逛逛" /></div>';
		$('.shop-cart').append(html);
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}