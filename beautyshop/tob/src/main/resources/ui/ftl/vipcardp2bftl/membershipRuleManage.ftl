<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/vipcard.vipcard.css" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1" />
		<link href="/wro/vipcard.p2b.MsgBox.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">会员卡规则管理</h2>
			<a href="javascript:void(0)" class="topAdd"><i class="icon-add-top"></i></a>
		</div>
		<!--内容层-->
		<div class="container">
			<h3 class="h3-1">当前会员卡规则</h3>
			<ul class="rule-ul r-m">
				<#if list?size = 0>
					<li class="li-mid">
						暂元会员卡发布
					</li>
				<#else>
					<#list list as a>
						<li class="membership-rule-con" data-id=${a.id}>
							<p class="rule-m-text">一次性充值满${a.price}元,
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
							</#switch> </p>
							<#if a.isdelete = 1>
								<p class="rule-m-red">该会员卡已于${a.time?string("yyyy-MM-dd")} 停止发售</p>
								<button type="button" class="button-absolute card-recover">恢复发售</button>
							<#else>
								<button type="button" class="button-absolute">停止发售</button>
							</#if>
						</li>
					</#list>
				</#if>
			</ul>
		</div>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>
		<script src="/wro/vipcard.p2b.MsgBox.js"></script>
		<script src="/wro/vipcard.p2b.membershipRuleManage.js"></script>
	</body>
</html>
