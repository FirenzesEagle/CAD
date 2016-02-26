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
			<h2 class="topTitle">会员详情</h2>
		</div>
		<!--内容层-->
		<div class="container">
			<div class="detail-con clearfix">
				<div class="detail-text-div-1">
					<p>会员名称：${consumerData.consumerName}</p>
					<p>会员账号：${consumerData.consumerPhone}</p>
					<p>优惠：${config.price}元-<#switch config.type>
						<#case 0>无优惠
						<#case 1>${config.benefit}折优惠<#break>
						<#case 2>返现${config.benefit}元<#break>
						<#case 3>消费${config.benefit}次<#break></#switch>
					</p>
				</div>
				<div class="detail-text-div-2">
					<p>会员卡号：${consumerData.cardNumber?string('00000')}</p>
					<p>开卡时间：${consumerData.createTime?string("yyyy-MM-dd")}</p>
					<p>卡内余额：${consumerData.lastMoney}
						<#switch config.type>
							<#case 0>
							<#case 1>
							<#case 2>元<#break>
							<#case 3>次<#break>
						</#switch>
					</p>
				</div>
			</div>
			<table class="memberlist-table">
				<caption>会员账单：</caption>
				<thead>
					<tr>
						<th>交易日期</th>
						<th>摘要</th>
						<th>交易金额</th>
						<th>卡内余额</th>
					</tr>
				</thead>
				<#if (bills?size>0)>
				<tbody>
					<#list bills as a>
					<tr>
						<td>${a.time?string("yyyy-MM-dd")}</td>
						<td><#switch a.consumeType>
								<#case 0>开卡<#break>
								<#case 1>充值<#break>
								<#case 2>商品收款<#break>
								<#case 3>现场收款<#break>
								<#case 4>现场扣次<#break>
							</#switch>
						</td>
						<td>
							<#switch a.consumeType>
								<#case 0>
								<#case 1>
									+
								<#break>
								<#case 2>
								<#case 3>
								<#case 4>
									-
								<#break>
							</#switch> 
							${a.money}
							<#switch config.type>
								<#case 0>
								<#case 1>
								<#case 2>
									元
									<#break>
								<#case 3>
									次
									<#break>
							</#switch>
						</td>
						<td>
							${a.lastMoney}
							<#switch config.type>
								<#case 0>
								<#case 1>
								<#case 2>
									元
									<#break>
								<#case 3>
									次
									<#break>
							</#switch>
						</td>
					</tr>
					</#list>
				</tbody>
				</#if>
			</table>
		</div>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>
	</body>
</html>
