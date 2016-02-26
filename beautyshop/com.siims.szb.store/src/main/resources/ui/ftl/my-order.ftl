<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<meta  name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<title>我的订单</title>
		<link rel="stylesheet" type="text/css" href="/wro/c_szb.goodsOrder.css"/>
	</head>
	<body>
		<div class="page-order">
			<div class="order-title">
				<div class="nav">返回</div>
				<p>我的订单</p>
			</div>
			<div class="order-type">
				<ul>
					<li data-type="1" class="current"><span class="order-type-list">商品订单</span></li>
					<li data-type="2"><span class="order-type-list">服务订单</span></li>
				</ul>
			</div>
			<div class="mainorder-service">
				<div class="order-state">
					<ul class="state-list">
						<li class="state-list-data stateChoosed" data-status = "1"><span>预约成功</span></li>
						<li class="state-list-data" data-status = "2"><span>已完成</span></li>
					</ul>
				</div><!--order-state-->
			</div><!--mainorder-service-->
			<!--商品订单-->
			<div class="mainorder-good">
				<div class="order-state">
					<ul class="state-list">
						<li class="state-list-data stateChoosed" data-status = "create"><span>待付款</span></li>
						<li class="state-list-data" data-status = "going"><span>进行中</span></li>
						<li class="state-list-data" data-status = "takedelivery"><span>已完成</span></li>
					</ul>
				</div><!--order-state-->
			</div><!--mainorder-good-->
		</div><!--page-order-->
		<form id="formid" name="alipayment" action="http://182.92.4.200:8838/siims/szb/alipay/alipay.jspx" method="post" style="display:none;" >
				<input name="out_trade_no" type="hidden" />
				<input name="subject" type="hidden" value="商品订单支付" />
				<input name="total_fee" type="hidden" />
				<input name="return_url" type="hidden" />
				<input name="notify_url" type="hidden" />
			</form>
	</body>
	<script type="text/javascript" src="/wro/c_common.common.js" ></script>
	<script type="text/javascript" src="/wro/c_szb.myOrder.js" ></script>
</html>
