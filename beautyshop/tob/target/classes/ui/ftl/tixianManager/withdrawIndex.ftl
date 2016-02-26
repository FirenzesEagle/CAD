<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/wx.withdrawIndex.css" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="user-scalable=no,initial-scale=1,width=device-width" />
		<script src="/wro/wx.jquery.js"></script>
		<script src="/wro/wx.withdrawIndex.js"></script>
		<script src="/wro/wx.withdrawBase.js"></script>
	</head>
	<body>
		<#--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">收入提现</h2>
		</div>
		<#--内容层-->
		<div class="container">
			<img src="/wro/wroResources?id=classpath:ui/image/tang/icon_turnover_index.png" class="icon-turnover-index" />
			<h3>可提现金额</h3>
			<p class="money">&yen;<span>--</span></p>
			<button type="button" class="button-1" onclick="clickTixian(this)">提现</button>
			<div class="clause-1">
				<span>提现记录</span>
				<i class="icon-right"></i>
			</div>
		</div>
	</body>
</html>
