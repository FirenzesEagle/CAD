var openid;
$(function(){
	var code=decodeURI(getQueryString("code"));
	//取得openid
	if(code!="null"&&code!="undefined"){
		$.ajax({
			type:"get",
			url:'http://182.92.4.200/BxyhWeixinServer/getopenid?code='+code,
			async:false,
			data:"",
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
	}else{
		var codeHtml='<li class="model-li li-multi"><p style="line-height:80px">返回openid出错！</p></li>';
		$(".model-ul").empty;
		$(".model-ul").append(codeHtml);
		return false;
	}
	//通过openid取得shopId
	$.ajax({
		type:"get",
		url:'../StoreInfoAction/getShopGroupByOpenid.jspx?openid='+openid,
		async:false,
		data:"",
		dataType:"json",
		crossDomain:true,
		success:function (data) {
			if(data.SUCCESS==true){
				if(data.DATA.length==0){
					var noHtml='<li class="model-li li-multi"><p style="line-height:80px">请进行账号绑定！</p></li>';
					$(".model-ul").empty;
					$(".model-ul").append(noHtml);
				}else{
					var liHtml='';
					$.each(data.DATA,function(){
						liHtml+='<li class="model-li" data-shopid='+this.id+'>'
								+'<p>'+this.name+'</p>'
								+'</li>';
					});
					$(".model-ul").empty;
					$(".model-ul").append(liHtml);
				}
			}else{
				//console.log(url);
				alert(data.ERROMSG);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
					//console.log(data);
	                console.log(XMLHttpRequest.status);
	              //  alert(XMLHttpRequest.readyState);
	              //  alert(textStatus);
	            }
	});
	$(".model-li").on("click",function(){
		var shopid=$(this).data("shopid");
		window.location.href="toOrderManage.jspx?id="+shopid;
	});
});