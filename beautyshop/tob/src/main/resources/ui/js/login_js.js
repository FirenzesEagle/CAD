$(function()
{
	$(".username").empty();
	$(".pwd").empty();
	$(".login").click(function(){
	
		var username=$(".username").val();
		var password=$(".pwd").val();
		if((username!="")&&(password!=""))
		{
			console.log("tag1");
			initUser(username,hex_md5(password));
		}
		else{
			alert("请完整填写用户名和密码");
		}
		//console.log(hex_md5(password));
	})
})

function initUser(username,password){
	var loginPC='/siims/szb/StoreInfoAction/storeLoginpc.jspx';//ldk
	var datatosend = {
		"username":username,
		"password":password};
		console.log(datatosend);
		$.ajax({
			type:"get",
			url:loginPC,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
		        if(data.SUCCESS == "true")
				{
					window.location.href= "/siims/szb/goods/goodslisttob.jspx"+"?shopId="+data.DATA.id;
				}
				else
				{
					alert("用户名或密码错误");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
						alert("登录失败！");
                        //console.log(XMLHttpRequest.status);
                    }
		});
}