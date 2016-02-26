<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
		<meta  name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<title>会员信息填写</title>
		<link rel="stylesheet" type="text/css" href="/wro/c_vipcard.fillout-VIPInfo.css"/>
		<link rel="stylesheet" href="/wro/c_vipcard.MsgBox.css" />
	</head>
	<body>
		<div class="page-fillout-VIPInfo">
			<div class="fillout-VIPInfo-title">
				<div class="nav">返回</div>
				<p>会员信息填写</p>
			</div>
			<!--领取会员卡，会员需要填写的信息：会员名称、手机账号等-->
			<div class="info-form">
				<div class="info-layer">
					<span>会员名称</span>
				    <!--存放会员名称-->
				    <div class="info-content">
				    	<input type="text" value="" placeholder="请填写真实姓名" class="VIP-name-class" id="VIP-name-ID"/>
				    </div>
				    <!--<div id="VIP-name" class="info-content" contenteditable="true" >请填写真实姓名</div>-->
			    </div>
			    <div class="info-layer">
				    <span>手机账号</span>
				    <!--存放会员账号-->
				    <div class="info-content">
				    	<input type="text" value="" placeholder="默认为会员账号" class="VIP-tel-class" id="VIP-tel-ID"/>
				    </div>
				    <!--<div id="VIP-tel" class="info-content" contenteditable="true" >默认为会员账号</div>-->
			    </div>
			    <div class="info-layer">
				   <span>充值等级</span>
				   <!--存放会员卡等级-->
				   <div id="VIP-rank" class="info-content" contenteditable="true" >
				   	<select name="VIPCard-rank" id="rankID">
				   		<!--<option value="8.0折优惠(充值1000元)">8.0折优惠(充值1000元)</option>
						<option value="9.0折优惠(充值500元)">9.0折优惠(充值500元)</option>
						<option value="9.5折优惠(充值300元)">9.5折优惠(充值300元)</option>-->
					</select>
				   </div>
			    </div>
			    <div class="info-layer">
				   <span>设置密码</span>
				   <!--存放密码-->
				   <div class="info-content">
				   	<input type="password" id="pswID" class="pswClass" placeholder="支付密码" value=""/>
				   </div>
				   <!--<div id="VIP-psw" class="info-content" contenteditable="true" >支付密码</div>-->
			    </div>
			    <div class="info-layer">
				   <span>确认密码</span>
				   <!--确认密码-->
				   <div class="info-content">
				   	<input type="password" name="psw-again-input" id="again-pswID" class="again-psw-class" placeholder="请再次输入支付密码" value=""/>
				   </div>
				   <!--<div id="pws-again" class="info-content" contenteditable="true" >请再次输入支付密码</div>-->
			    </div>
			</div> <!--info-form-->
			<!--确认按钮-->
			<div class="bt-div"><button class="confirm-VIPInfo">确认</button></div>
		</div>
		
	</body>
	<script type="text/javascript" src="/wro/c_common.common.js" ></script>
	<script type="text/javascript" src="/wro/c_vipcard.MsgBox.js" ></script>
	<script type="text/javascript" src="/wro/c_vipcard.fillout-VIPInfo.js" ></script>
	<!--<script type="text/javascript" src="/wro/c_vipcard.baseUrl.js" ></script>-->
	<script type="text/javascript" src="/wro/c_vipcard.md5.js" ></script>
	
</html>
