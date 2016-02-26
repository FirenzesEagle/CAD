<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<meta  name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<title>个人中心</title>
		<link rel="stylesheet" type="text/css" href="/wro/c_szb.personCenter.css"/>
	</head>
	<body>
		<div class="page-content">
			<div class="title">个人中心</div>
			<div class="content-one">
				<div class="weixin-info">
					<!--微信头像-->
					<div class="weixin-img">
						<img src="" alt="" id="weixinIcon"/>
					</div>
					<p class="weixin-nickname">用户微信昵称</p>
				</div>
			</div>
			<div class="content-two">
				<!--我的订单-->
				<div class="content-two-link myorder" >       <!--跳转到我的订单页面-->
					<div class="content-two-info">
						<img src="/wro/wroResources?id=classpath:ui/image/lmb/myOrder.png" alt=""/>
					    <span class="info-content">我的订单</span>
					    <div class="info-forward"></div>
				    </div>
				</div>
				<!--收货地址-->
				<div class="content-two-link myaddress" >               <!--跳转到收货地址页面-->
				    <div class="content-two-info">
					    <img src="/wro/wroResources?id=classpath:ui/image/lmb/address.png" alt=""/>
					    <span class="info-content">收货地址</span>
					    <div class="info-forward"></div>
				    </div>
				</div>
				<!--我的会员卡-->
				<!--<div class="content-two-link myvipcard" >                <!--跳转到我的会员卡页面-->
				    <div class="content-two-info">
				    	<img src="/wro/wroResources?id=classpath:ui/image/lmb/myVIPCard.png" alt=""/>
					    <span class="info-content">我的会员卡</span>
					    <div class="info-forward"></div>
				    </div>
				</div>-->
				<!--联系客服-->
				<div class="content-two-info content-two-phone">
					<img src="/wro/wroResources?id=classpath:ui/image/lmb/contact.png" alt=""/>
					<span class="info-content">联系客服</span>
					<!--电话触发-->
					<a href="tel:010-61198779" class="phonenumber">010-61198779</a>
				</div>
			</div>
			<!--商站宝信息-->
			<div class="content-three">
				<img src="/wro/wroResources?id=classpath:ui/image/lmb/logo.jpg" alt=""/>
				<p class="contact-us">联系我们<br>
					<a href="http://www.91dzsw.com" class="SZBsite-link">www.91dzsw.com</a>
				</p>

			</div>
			<!--底部菜单-->
			<div class="menu">
				<ul class="menu-list">
					<li><div class="menu-index"><img src="/wro/wroResources?id=classpath:ui/image/lmb/menu-index.png"/><p>首页</p></div></li>
					<!--<li><div class="menu-shopcart"><img src="/wro/wroResources?id=classpath:ui/image/lmb/menu-shopcart.png"/><p>购物车</p></div></li>-->
					<li><div class="men-mycenter"><img src="/wro/wroResources?id=classpath:ui/image/lmb/menu-my.png"/><p>我的</p></div></li>
				</ul>
		</div>
		</div>
	</body>
	<script type="text/javascript" src="/wro/c_common.common.js" ></script>
	<script src="/wro/c_szb.personCenter.js"></script>
	
</html>
