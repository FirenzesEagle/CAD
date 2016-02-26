$(function(){
	$(".submit").on("click",function(){
		var name=$(".dishName").val();
		var price=$(".dishPrice").val();
		if(name===""||price===""){
			alert("信息不能为空！");
			return false;
		}
		var datatosend={
			"food_name":name,
			"food_price":price
		};
		$.ajax({
			type:"get",
			url:"AddFood.jspx",
			async:"false",
			crossDomain:true,
			data:datatosend,
			dataType:"json",
			success:function(data){
				
				if(data.SUCCESS=="true"){
					//成功时代码
					console.log(data);
					$(".result").text("发送AJAX信息成功，返回DATA信息为：  "+data.DATA);
				}else{
					//出错时代码
					console.log("error:"+data);
				}

			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//错误代码，一般为连接错误或者返回类型等等。
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			}
			
		});	
	});
});