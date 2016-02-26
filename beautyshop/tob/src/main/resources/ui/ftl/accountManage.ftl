<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link rel="stylesheet" href="/wro/accountManage.accountManage.css" />
		<meta name="viewport" content="user-scalable=yes,width=device-width, initial-scale=1" user-scalable=no>
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav" >
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">账号管理</h2>
		</div>
		<!--商店概况-有图像背景层-->
		<div class="img-layer">
			<img class="img-bg" src="/wro/wroResources?id=classpath:ui/image/toB/cover.png"/>
			<div class="img-layer-l">
				<div id="accountimgCon">
					<!--存放商户自己的图片-->
					<img class="img-cover" src="" id="accountImg"/>
				</div>
				<input type="file" id="accountImgFile" onchange="accountImgSelect(this)" style="display: none;"/>
				<div class="img-info-div">
					<span>商户账号</span>
					<!--商户账号-->
					<span id="userAccout"></span>
				</div>				
			</div>

		</div>
		<!--单个信息层，包括地址、电话等-->
		<div class="singleinfo-layer">
			<img src="/wro/wroResources?id=classpath:ui/image/toB/tang/icon_address_2.png"  class="singleinfo-img"/>
			<span class="singleinfo-title">商户名称</span>
			<!--存放商户名称-->
			<div id="userName" tabindex="0" hidefocus="true" class="singleinfo-content" contenteditable="true"></div>
		</div>
		<div class="singleinfo-layer">
			<img src="/wro/wroResources?id=classpath:ui/image/toB/tang/icon_address_2.png"  class="singleinfo-img"/>
			<span class="singleinfo-title">商户地址</span>
			<!--存放商户地址-->
			<div id="userAddr" tabindex="0" hidefocus="true" class="singleinfo-content" contenteditable="true"></div>
		</div>
		<div class="singleinfo-layer">
			<img src="/wro/wroResources?id=classpath:ui/image/toB/icon_mobile.png"  class="singleinfo-img"/>
			<span class="singleinfo-title">商户电话</span>
			<!--存放商户电话-->
			<div id="userTel" tabindex="0" hidefocus="true" class="singleinfo-content" contenteditable="true"></div>
		</div>
		<div class="singleinfo-layer">
			<img src="/wro/wroResources?id=classpath:ui/image/toB/tang/icon_money.png"  class="singleinfo-img"/>
			<span class="singleinfo-title">起送价</span>
			<!--存放起送价-->
			<div id="priceLeast" tabindex="0" hidefocus="true" class="singleinfo-content" contenteditable="true"></div>
		</div>
		<div class="singleinfo-layer">
			<img src="/wro/wroResources?id=classpath:ui/image/toB/tang/icon_money.png"  class="singleinfo-img"/>
			<span class="singleinfo-title">配送费</span>
			<!--存放配送费-->
			<div id="pricePeisong" tabindex="0" hidefocus="true" class="singleinfo-content" contenteditable="true"></div>
		</div>
		<div class="singleinfo-layer">
			<img src="/wro/wroResources?id=classpath:ui/image/toB/icon_mobile.png"  class="singleinfo-img"/>
			<span class="singleinfo-title">营业时间</span>
			<!--存放营业时间-->
			<div id="openTime" tabindex="0" hidefocus="true" class="singleinfo-content" contenteditable="true"></div>
		</div>
		<!--退出登录按钮层-->
		<!--<div class="buttonDiv"><button class="button red">退出登录</button></div>-->
		<script type="text/javascript" src="/wro/common.common.js" ></script>
	<script type="text/javascript" src="/wro/accountManage.accountManage.js" ></script>
	</body>
</html>
