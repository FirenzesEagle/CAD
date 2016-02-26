<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1" />
		<link href="/wro/vipcard.vipcard.css" rel="stylesheet" type="text/css"/>
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">会员管理</h2>
		</div>
		<!--内容层-->
		<div class="container">
			<!--当前会员数层-->
			<div class="title-layer">
				<!--#memberNum存放当前会员数-->
				<p>当前会员数:<span id="memberNum">${list2?size}</span></p>
				<a href="./vipcardvipslist.jspx?typeId=${typeId}" class="layer-text">全部会员详情 >></a>
			</div>
			<div class="rule-text-con">
				<div class="rule-text-label">
					<label>会员卡规则设置：</label>
					<span class="rule-text-edit">编辑 <i class="icon-edit"></i></span>
				</div>
				<!--规则文字书写-->
				<ul class="ul-text">
				<#if lenOfList = 0>
					<li class="li-mid">
						暂元会员卡发布
					</li>
				<#else>
					<#list list as a>
						<li><i class="icon-money"></i>
						<span>一次性充值满<i class="text-money">${a.price}</i>元，
						<#switch a.type>
							<#case 0>
								无优惠</span>
								<#break>
							<#case 1>
								${a.benefit}折优惠</span>
								<#break>
							<#case 2>
								返现${a.benefit}元</span>
								<#break>
							<#case 3>
								消费${a.benefit}次</span>
								<#break>
						</#switch>
						<#if a.isdelete=1>
							(停售)
						</#if> 
						</li>
					</#list>
					</ul>
					<#if (list2?size>0)>
						<a class="button-a" href="./vipcardcollectmoney1.jspx?typeId=${typeId}"><button class="button-1">发起收款</button></a>
					</#if>
				</#if>

			</div>
		</div>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>	
		<script>
			$(function(){
				var shopId=getQueryString("shopid");
				$(".rule-text-edit").on("click",function(){
					window.location.href="./vipcardconfigmanage.jspx?typeId=${typeId}";
				});
			});
		</script>
		</body>
</html>
