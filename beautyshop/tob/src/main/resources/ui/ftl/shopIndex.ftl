<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<meta  name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<title>商铺首页</title>
		<script>
			var w=window.innerWidth|| document.documentElement.clientWidth|| document.body.clientWidth;
			if(w<500){
				var w2=w/320*20;
				//console.log(w2);
				document.getElementsByTagName("html")[0].style.fontSize=w2+"px";
			}else if(w<1000){
				var w2=w/600*35;
				document.getElementsByTagName("html")[0].style.fontSize=w2+"px";
			}else{
				var w2=w/1060*35;
				document.getElementsByTagName("html")[0].style.fontSize=w2+"px";
			}
			
			window.onresize=function(){
				var w=window.innerWidth|| document.documentElement.clientWidth|| document.body.clientWidth;
				if(w<500){
					var w2=w/320*20;
					//console.log(w2);
					document.getElementsByTagName("html")[0].style.fontSize=w2+"px";
				}else if(w<1000){
					var w2=w/600*35;
					document.getElementsByTagName("html")[0].style.fontSize=w2+"px";
				}else{
					var w2=w/1060*35;
					document.getElementsByTagName("html")[0].style.fontSize=w2+"px";
				}
			};
		</script>
		<link rel="stylesheet" href="/wro/shopIndex.shopIndex.css" />
	</head>
	<body>
		<div class="district-page">
			<div class="distirct-title">
				<p>北邮商圈</p>
			</div>
			<div class="district-ad"></div>
			<div class="district-shop">
					
			</div>
			
		</div>
		<div class="menu">
				<ul class="menu-list">
					<li class="menu-index"><img src="/wro/wroResources?id=classpath:ui/image/toC/menu-index.png" alt="" /><p>首页</p></li>
					<li class="menu-shopcart"><img src="/wro/wroResources?id=classpath:ui/image/toC/menu-shopcart.png" alt="" /><p>购物袋</p></li>
					<li class="menu-mycenter"><img src="/wro/wroResources?id=classpath:ui/image/toC/menu-my.png" alt="" /><p>我的</p></li>
				</ul>
		</div>
	</body>
	<script type="text/javascript" src="/wro/common.common.js" ></script>
	<script type="text/javascript" src="/wro/shopIndex.shopIndex.js" ></script>
</html>
