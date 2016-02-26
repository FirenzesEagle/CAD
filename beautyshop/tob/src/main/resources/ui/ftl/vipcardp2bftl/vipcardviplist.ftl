<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/vipcard.vipcard.css" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="user-scalable=no,initial-scale=1,width=device-width" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">会员列表</h2>
		</div>
		<!--内容层-->
		<div class="container">
			<table class="memberlist-table" align="center">
				<thead>
			<#if (list?size=0)>
				<tr>当前无会员卡信息 </tr></thead>
			<#else>
				 	<tr>
						<th>会员卡号</th>
						<th>会员名称</th>
						<th>会员账号</th>
						<th>卡内余额</th>
					</tr>
				</thead>
				<tbody>
				<#list list as a>
					<tr data-id=${a.id}>
						<td>${a.cardNumber?string('00000')}</td>
						<td>${a.consumerName}</td>
						<td>${a.consumerPhone}</td>
						<td>${a.lastMoney}</td>
					</tr>
				</#list>
				</tbody>
			</table>
			</#if>	
		</div>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>
		<script src="/wro/vipcard.vipcardviplist.js"></script>
	</body>
</html>
