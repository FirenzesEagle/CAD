<html>
	<head>
		<title>商站宝</title>
		<meta charset="utf-8" />
		<script src="/wro/jquery.js"></script>
	</head>
	<body>
		<script>
			function getQueryString(name) {
			 
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			    var r = window.location.search.substr(1).match(reg);
			    if (r != null) return unescape(r[2]); return null;
			}
			$(function(){
				var code=decodeURI(getQueryString("code"));
				var openid;
				if(code!="null"&&code!="undefined"){
					$.ajax({
						type:"get",
						url:'http://182.92.4.200/BxyhWeixinServer/getopenid?code='+code,
						async:"false",
						data:/*code*/"",
						dataType:"json",
						crossDomain:true,
						success:function (data) {
							openid = data.openid;
							window.location.href="selecttype.jspx?openid="+openid;
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
									//console.log(data);
					                console.log(XMLHttpRequest.status);
					              //  alert(XMLHttpRequest.readyState);
					              //  alert(textStatus);
					            }
					});
				}				
			});
			
		</script>
	</body>
</html>