<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<meta  name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<title>会员信息确认</title>
		<link rel="stylesheet" type="text/css" href="/wro/c_vipcard.confirm-VIPInfo.css"/>
	</head>
	<body>
		<div class="page-confirm-VIPInfo">
			<div class="confirm-VIPInfo-title">
				<div class="nav">返回</div>
				<p>会员信息确认</p>
			</div>
			<!--会员信息-->
			<div class="VIPInfo">
				<div class="VIPInfo-row">
					<div class="VIPInfo-column-one">
						<p>名称：<span class="VIP-name-value">**</span></p>
					</div>
					<div class="VIPInfo-column-two">
						<p>卡号：<span class="VIPCard-num-value">**</span></p>
					</div>				
				</div>
				<div class="VIPInfo-row">
					<div class="VIPInfo-column-one">
						<p>会员账号：</p>
						<p class="VIP-account-value">**</p>
					</div>
					<div class="VIPInfo-column-two">
						<p>开卡时间：</p>
						<p class="VIPCard-time-value">**</p>
					</div>
				</div>
				<div class="VIPInfo-row">
					<div class="VIPInfo-column-one">
						<p>优惠规则：<span class="preferential-rules-value">**</span></p>
					</div>
					<div class="VIPInfo-column-two">
						<p>充值金额：<span class="VIPCard-balance-value">**</span></p>
					</div>
				</div>
			</div><!--VIPInfo-->
			<!--会员信息提示-->
			<div class="VIPInfo-point">
				
			</div>
			<!--选择支付方式-->
			<div class="styleOf-payment">
				<p>请选择支付方式：</p>
				<ul>
					<li class="payStyle" data-style="1">
						<div class="Alipay-style">
							<div class="Alipay-img">
								<img src="/wro/wroResources?id=classpath:ui/image/vipcardb2cimages/alipay.png" alt=""/>
							</div>
							<div class="Alipay-info">
								<p>支付宝支付</p>
								<p class="payment-info-point">推荐支付宝账户用户使用</p>
							</div>
							<div class="unselect-payment select-payment "></div>
						</div>
					</li>
					<li class="payStyle" data-style="2">
						<div class="weixin-style">
							<div class="weixin-icon">
								<img src="/wro/wroResources?id=classpath:ui/image/vipcardb2cimages/weixin.png" alt=""/>
							</div>
							<div class="weixin-info">
								<p>微信支付</p>
								<p class="payment-info-point">推荐安装微信5.0以上版本用户使用</p>
							</div>
							<div class="unselect-payment"></div>
						</div>
					</li>
				</ul>
				
				
			</div>  <!--styleOf-payment-->
			<!--确认支付按钮-->
			<form id="formid" name="alipayment" action="http://182.92.4.200:8838/siims/szb/alipay/alipay.jspx" method="post" >
				<div class="div-confirm-pay"><button class="confirm-pay" >确认支付</button></div>
				<input name="out_trade_no" type="hidden" />
				<input name="subject" type="hidden" value="会员卡购买" />
				<input name="total_fee" type="hidden" />
				<input name="return_url" type="hidden" />
				<input name="notify_url" type="hidden" />
			</form>
		</div>
	</body>
	<script type="text/javascript" src="/wro/c_common.common.js"></script>
	<script type="text/javascript" src="/wro/c_vipcard.confirm-VIPInfo.js" ></script>
	<!--<script type="text/javascript" src="/wro/c_common.common.js" ></script>-->
</html>
