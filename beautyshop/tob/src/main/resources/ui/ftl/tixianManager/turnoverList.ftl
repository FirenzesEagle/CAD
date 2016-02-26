<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/wx.withdrawIndex.css" rel="stylesheet" type="text/css" />
		<link href="/wro/szb.time.css" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="user-scalable=no,initial-scale=1,width=device-width" />
		<script src="/wro/wx.jquery.js"></script>
		<script src="/wro/szb.time.js"></script>
		<script src="/wro/wx.turnoverList.js"></script>
		<script src="/wro/wx.withdrawBase.js"></script>
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">每日流水</h2>
			<a href="javascript:void(0)" onclick="toTixian(this)" class="tixian">提现</a>
		</div>
		<!--内容层-->
		<div class="container">
			<!--<form>-->
				<!--存放搜索组件-->
				<div class="timeselect-div">
					<input type="text" class="time-input" id="timeStart" />
					<input type="text" class="time-input" id="timeEnd" />
					<button id="turnoverSearch" class="button-search"></button>
				</div>
			<!--</form>-->
			<!--订单次数，UL存放记录-->
			<div class="order-con">
				<div class="clause-line clearfix">
					<div class="left">
						<p class="clause-3-title">交易记录</p>
						<p class="clause-3-num"><span></span>次</p>
					</div>
					<div class="right">
						<p><i class="icon-turnover-1"></i></p>
						<p class="clause-3-more">查看详情</p>
					</div>
				</div>
				<ul class="clause-3-ul">
					
				</ul>
			</div>
			<!--流水，UL存放记录-->
			<div class="turnover-con">
				<div class="clause-line clearfix">
					<div class="left">
						<p class="clause-3-title">流水统计</p>
						<p class="clause-3-num"><span></span>元</p>
					</div>
					<div class="right">
						<p><i class="icon-turnover-2"></i></p>
						<p class="clause-3-more">查看详情</p>
					</div>
				</div>
				<ul class="clause-3-ul">
					
				</ul>
			</div>
			<!--提现金额，UL存放记录-->
			<div class="withdraw-con">
				<div class="clause-line clearfix">
					<div class="left">
						<p class="clause-3-title">可提现金额</p>
						<p class="clause-3-num"><span></span>元</p>
					</div>
					<div class="right">
						<p><i class="icon-turnover-3"></i></p>
						<p class="clause-3-more">查看详情</p>
					</div>
				</div>
				<ul class="clause-3-ul">
					
				</ul>
			</div>
		</div>
	</body>
</html>
