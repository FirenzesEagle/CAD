<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<meta  name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<title>新增收货地址</title>
		<link rel="stylesheet" type="text/css" href="/wro/c_szb.addressadd.css"/>
	</head>
	<body>
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
					<input type="text" placeholder="请填写收货手机号码" class="userPhone" />
				</div>
				<div class="address-tag">
					<label for="">设为默认地址</label>
					<div class="addr-img" ><img src="/wro/wroResources?id=classpath:ui/image/lmb/addr-wxz.png" data-flag="0"/></div>
				</div>
				
			</div>
		</div>
		<div class="address-bt">
					<input type="submit" value="保存" />
		</div>
		<script type="text/javascript" src="/wro/c_common.common.js" ></script>
		<script type="text/javascript" src="/wro/c_szb.addressadd.js" ></script>
		
	</body>
</html>
