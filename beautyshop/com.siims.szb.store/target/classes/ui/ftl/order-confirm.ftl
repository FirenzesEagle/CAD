<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<title>我的订单</title>
		<link rel="stylesheet" href="/wro/c_shopCart.orderConfirm.css" />
	</head>
	<body>
		<div class="page-orderconfirm">
			<div class="confirm-title">
				<div class="nav">返回</div>
				<p>订单支付</p>
			</div>
			<div class="order-address">
				
				
			</div><!--order-address-->
			<div class="paylistDiv">
				<ul class="paylists">
					
					
				</ul>
			</div><!--pay-list-->
			<div class="pickup"><span class="pickup-name">到店自提</span><span class="pickup-price">&yen;0.0</span></div>
			<div class="confirm-paymode">
				<div class="pay-mode">
					<ul>
						<li class="mode" data-mode="0">
							<div class="mode-img"><img src="/wro/wroResources?id=classpath:ui/image/ldk/tb.png"/></div>
							<div class="mode-info">
								<p class="mode-info1">支付宝支付</p>
								<p class="mode-info2">推荐有支付宝账户的用户使用</p>
							</div>
							<div class="mode-choose">
								<div class="mode-nav choosed"></div>
							</div>
						</li>
						<li class="mode" data-mode="1">
							<div class="mode-img"><img src="/wro/wroResources?id=classpath:ui/image/ldk/wx.jpg"/></div>
							<div class="mode-info">
								<p class="mode-info1">微信支付</p>
								<p class="mode-info2">推荐安装微信5.0及以上版本使用</p>
							</div>
							<div class="mode-choose">
								<div class="mode-nav"></div>
							</div>
						</li>
					</ul>
				</div><!--pay-mode-->
				
			</div>
			<div style="margin-bottom:60px ;"></div>
		</div>
		<div class="order-confirm">
			<p>付款金额：&yen;<span class="total-paymoney"></span></p>
			<div class="order-confirm-bt"><span>提交订单</span></div>
			<form id="formid" name="alipayment" action="http://182.92.4.200:8838/siims/szb/alipay/alipay.jspx" method="post" method=post style="display:none;" >
				<input name="out_trade_no" type="hidden" />
				<input name="subject" type="hidden" value="商品订单支付			" />
				<input name="total_fee" type="hidden" />
				<input name="return_url" type="hidden" />
				<input name="notify_url" type="hidden" />
			</form>
		</div>
		<!--地址列表展示-->
		<div class="addressDiv">
			
			<div class="address-title1">
				<div class="nav">返回</div>
				<p>我的收货地址</p>
			</div>
			<div class="address-add">
			<input type="button" name="" id="" value="新增收货地址" />
			</div>
			<div class="address-lists">
				<ul class="address-list">
					
				</ul>
			</div>
			
		</div>
		<!--新增收货地址-->
		<div class="addressaddDiv">
					<div class="page-address">
						<div class="address-title">
							<div class="nav">返回</div>
							<p>新增收货地址</p>
						</div>
						<div class="address-info">
							<div class="address-tag">
								<label for="">收货人</label>
								<input type="text" placeholder="请填写收货人的姓名" class="userName" />
							</div>
							<div class="address-tag">
								<label for="">送达地址</label>
								<input type="text" placeholder="请填写小区、写字楼或学校" class="userAddress1" />
							</div>
							<div class="address-tag">
								<label for="">详细地址</label>
								<input type="text" placeholder="请输入楼号、门牌号等详细信息" class="userAddress2"/>
							</div>
							<div class="address-tag">
								<label for="">手机号码</label>
								<input type="text" placeholder="请填写手机号码(11位)" class="userPhone" />
							</div>
							<div class="address-tag">
								<label for="">设为默认地址</label>
								<div class="addr-img" ><img src="/wro/wroResources?id=classpath:ui/image/lmb/addr-wxz.png" data-flag="0"/></div>
							</div>
							
						</div>
					</div>
					<div class="address-bt">
								<input type="submit" value="保存并使用" />
					</div>
		
		</div>
	</body>
	<script type="text/javascript" src="/wro/c_common.common.js" ></script>
	<script type="text/javascript" src="/wro/c_md5.js" ></script>
	<script type="text/javascript" src="/wro/c_shopCart.orderConfirm.js" ></script>
	
</html>
