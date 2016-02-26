$(function(){
	$(".input-consumeNum").val(1);
	var typeId=getQueryString("typeId");
	var $element=$('input:radio[name="membership"]:checked');
	if($element.data("type")===3){
		$("#consumePrice").parent().hide();
	}
	$(".membership-detail").on("click",function(){
		$(this).parents("li").find(".radio-none").trigger("click");
		var unitNum=$(".input-consumeNum").val();
		calculate(unitNum);
	});
	$(".radio-none").on("click",function(){
		var unitNum=$(".input-consumeNum").val();
		if($(this).data("type")===3){
			$("#consumePrice").parent().hide();
		}else{
			$("#consumePrice").parent().show();
		}
		calculate(unitNum);
	});
	$(".num-min").on("click",function(){
		var unitNum=parseInt($(".input-consumeNum").val());
		unitNum=unitNum-1;
		if(parseInt(unitNum)<1){
			return false;
		}
		
		calculate(unitNum);
		
		$(".input-consumeNum").val(unitNum);

	});
	
	$(".num-add").on("click",function(){
		var unitNum=parseInt($(".input-consumeNum").val());
		unitNum++;
		if(calculate(unitNum)){
			$(".input-consumeNum").val(unitNum);
		}
	});
	$("#consumePrice").watch(function(value) {  
		if(value!=undefined&&value!=null&&value!=""){
			var unitNum=$(".input-consumeNum").val();
			calculate(unitNum);
		}else{
			$("#payMoney").text("0");
		}
	}); 
	
	$(".liushui").on("click",function(){
		window.location.href="./vipcardvipslist.jspx?typeId="+typeId;
	});
	$(".guanli").on("click",function(){
		window.location.href="./vipcardmanage.jspx?type=1";
	});
	$("#confirmPay").on("click",function(){
		if($(this).hasClass("button-1")){
			var $element=$('input:radio[name="membership"]:checked');
			var eleTel=$element.parents("li").find(".detail-text-tel").text();
			console.log(eleTel);
		
			if($("#payPass").val()===""||$("#payPass").val()===null||$("#payPass").val()===undefined){
				$("#pageWarn").css({"display":"block"});
				console.log("pass");
				$("#pageWarn").find(".beizhu-red").text("请输入支付密码！");
				
				return false;
			}
			if($element.data("type")!==3){
				if($("#consumePrice").val()===""||$("#consumePrice").val()===null||$("#consumePrice").val()===undefined){
					$("#pageWarn").css({"display":"block"});
					$("#pageWarn").find(".beizhu-red").text("请输入消费单价！");
					return false;
				}
				var nameRxp=/^\d+(\.\d+)?$/;
				var singlePay=$("#consumePrice").val();
				if(!nameRxp.test(singlePay)){
					$("#pageWarn").css({"display":"block"});
					$("#pageWarn").find(".beizhu-red").text("请输入正确单价格式！");
					return false;
				}
				
			}
			
			//console.log("hide");
			$("#pageWarn").css({"display":"none"});
			var payPass=$("#payPass").val();
			payPass=hex_md5(payPass);
			console.log(payPass);
			//记录会员卡类型
			var type=$element.data("type");
			if(type!==3){
				type=3;
			}else{
				type=4;
			}
			var vipcardid=$element.data("id");
			var money=calculate(parseInt($(".input-consumeNum").val()));
			var datatosend={
				"vipcardid":vipcardid,
				"money":money,
				"password":payPass,
				"type":type
			};
			console.log(datatosend);
			$.ajax({
				type:"get",
				url:"./vipcardconsume.jspx",
				async:"false",
				data:datatosend,
				crossDomain:true,
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.SUCCESS===true){
						paySuc(eleTel,money,type);
					}else{
						console.log(data.MSG);
						$("#pageWarn").css({"display":"block"});
						$("#pageWarn").find(".beizhu-red").text(data.MSG);
						return false;
					}
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(XMLHttpRequest.readyState);
					console.log(textStatus);
		        }
			});
		}
	});
});
//计算总数
function calculate(num){
	var unitNum,unitPrice,totalNum,totalPrice,thisPrice,thisZhekou;//记录数量，单价，剩余数量，余额
	//记录选中元素
	var $element=$('input:radio[name="membership"]:checked');
	//记录会员卡类型
	var type=$element.data("type");
	
	if(type===0||type===2){//返现或无优惠
		//初始化
		unitNum=parseInt(num);
		unitPrice=parseFloat($("#consumePrice").val());
		if(isNaN(unitPrice)){
			unitPrice=0;
		}
		if(unitPrice===NaN){
			unitPrice=0;
		}
		totalPrice=parseFloat($element.parents("li").find(".cardMoney").text());
		
		thisPrice=unitNum*unitPrice;
		thisPrice=thisPrice.toFixed(2);
		
		if(thisPrice>totalPrice){
			cannotPay();
			return false;
		}else{
			canPay(thisPrice);
			return thisPrice;
		}
	}else if(type===3){//计次优惠
		//初始化
		unitNum=parseInt(num);
		totalNum=parseInt($element.parents("li").find(".cardCishu").text());
		if(unitNum>totalNum){
			cannotPay();
			return false;
		}else{
			canPay(thisPrice);
			return unitNum;
		}
	}else if(type===1){//折扣优惠
		//初始化
		unitNum=parseInt(num);
		unitPrice=parseFloat($("#consumePrice").val());
		totalPrice=parseFloat($element.parents("li").find(".cardMoney").text());
		thisZhekou=parseFloat($element.parents("li").find(".cardZhekou").text());
		
		if(isNaN(unitPrice)){
			unitPrice=0;
		}
		thisPrice=unitNum*unitPrice*thisZhekou/10;
		thisPrice=thisPrice.toFixed(2);
		
		if(thisPrice>totalPrice){
			cannotPay();
			return false;
		}else{
			canPay(thisPrice);
			return thisPrice;
		}
	}
	//validate();
	//return;
}
//如果超出会员卡所能支付范畴，执行cannotPay()
function cannotPay(){
	$("#confirmPay").removeClass("button-1").addClass("button-1-grey");
	$("#payWarn").css({"display":"inline-block"});
}
//如果price有效，执行canPay()
function canPay(price){
	$("#confirmPay").removeClass("button-1-grey").addClass("button-1");
	$("#payWarn").css({"display":"none"});
	$("#payMoney").text(price);
}
//显示收款成功
function paySuc(tel,money,type){
	$(".container").hide();
	$(".topTitle").text("收款成功");
	$(".paySuc-container").show();
	$(".paySuc-mobile").text(tel);
	if(type!=4){
		$(".paySuc-money").text(money+"元");
	}else{
		$(".paySuc-money").text(money+"次");
	}
	
}
//显示收款页面
function payShow(){
	$(".container").show();
	$(".topTitle").text("会员卡收款");
	$(".paySuc-container").hide();
}
//实时获取
(function($) {
    $.fn.watch = function(callback) {  
        return this.each(function() {  
            //缓存以前的值  
            $.data(this, 'originVal', $(this).val());  
  
            //event  
            $(this).on('keyup paste', function() {  
                var originVal = $(this, 'originVal');  
                var currentVal = $(this).val();  
  
                if (originVal !== currentVal) {  
                    $.data(this, 'originVal', $(this).val());  
                    callback(currentVal);  
                }  
            });  
        });  
    }  
})(jQuery);