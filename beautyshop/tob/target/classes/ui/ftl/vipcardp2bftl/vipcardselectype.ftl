<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/vipcard.vipcard.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">会员卡模式选择</h2>
		</div>
		<div class="container">
			<h3>会员卡模式选择</h3>
			<#if storelist ?? && storelist?size != 0>
				
				<ul class="model-ul">
				<#list storelist as store>
					<li class="model-li" data-shopid=${store.id}>
						<p>储值卡模式---${store.name}</p>
					</li>
				</#list>
				
				<li class="model-li li-multi">
					<p>积分卡模式</p>
					<span>(尚未开通)</span>
				</li>
			</ul>
			<#else>
				<ul class="model-ul">
				<li class="model-li li-multi"><p style="line-height:80px">当前您未拥有店铺</p></li>
				</ul>
			</#if>
			
		</div>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>
		<script src="/wro/vipcard.selectType.js"></script>
	</body>
</html>
