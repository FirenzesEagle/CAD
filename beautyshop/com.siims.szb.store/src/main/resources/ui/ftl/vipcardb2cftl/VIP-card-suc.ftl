<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<meta  name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<title>办理成功</title>
		<link rel="stylesheet" type="text/css" href="/wro/c_vipcard.suc.css"/>
	</head>
	<body>
		<div class="page-VIPCard-suc">
			<div class="VIPCard-suc-title">
				
				<p>办理成功</p>
			</div>
			<div class="container">
				<div class="result-layer">
				    <img src="/wro/wroResources?id=classpath:ui/image/vipcardb2cimages/success.png" />
				    <div class="result-text">
					    <p>恭喜您~您在<span>${storename}</span>办理储值会员卡一张，卡内余额<span>${lastmoney}</span>，您获得专享权益消费<span>${benefit}</span></p>
				    </div>
			    </div>
			    <div class="button-group clearfix">
				    <div class="button-a"><button class="bt-in-shop" onclick=goshoplist("${storeid}","${storename}","${openid}")>进入店铺</button></div>
				    <a href="./querymycards.jspx?openid=${openid}" class="button-a"><button class="bt-look-VIPCard">查看会员卡</button></a>
			    </div>
		    </div>
		</div>
	</body>
	<script type="text/javascript" src="/wro/c_common.common.js" ></script>
	<script type="text/javascript" src="/wro/c_vipcard.suc.js" ></script>
	<!--<script type="text/javascript" src="js/baseUrl.js" ></script>-->
</html>
