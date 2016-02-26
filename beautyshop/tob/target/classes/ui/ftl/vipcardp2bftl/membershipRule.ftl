<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/vipcard.vipcard.css" rel="stylesheet" type="text/css"/>
		<link href="/wro/vipcard.p2b.MsgBox.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="user-scalable=no,initial-scale=1,width=device-width" />
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">会员卡规则设置</h2>
		</div>
		<!--内容层-->
		<div class="container">
			<ul class="rule-ul">
				
						<!--<div class="select-box">
							<div class="select-showbox">请选择优惠方式</div>
							<ul class="select-option">
								<li class="select-selected">请选择优惠方式</li>
								<li>无优惠</li>
								<li>返现优惠</li>
								<li>打折优惠</li>
								<li>计次卡优惠</li>
							</ul>
						</div>-->
						
						
				<li class="membership-rule-con">
					<div class="rule-flex">
						<select class="ruleSelect">
							<option selected="selected">请选择优惠方式</option>
							<option>无优惠</option>
							<option>返现优惠</option>
							<option>打折优惠</option>
							<option>计次卡优惠</option>
						</select>
						
					</div>
					<img src="/wro/wroResources?id=classpath:ui/image/vipcardp2bimages/membership/icon_cancel.png" class="rule-cancel-button" />
				</li>
			</ul>
			<div class="add-rule"><span>添加规则</span><i class="icon-add"></i></div>
			<button type="button" class="button-1">确认设置</button>
		</div>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>
		<script src="/wro/vipcard.p2b.MsgBox.js"></script>
		<script src="/wro/vipcard.p2b.membershipRule.js"></script>
	</body>
</html>
