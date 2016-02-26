<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/wx.withdrawIndex.css" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="user-scalable=no,initial-scale=1,width=device-width" />
		<script src="/wro/wx.jquery.js"></script>
		<script src="/wro/wx.withdrawPage.js"></script>
		<script src="/wro/wx.withdrawBase.js"></script>
		<script src="/wro/md5.js"></script>
	</head>
	<body>
		<#--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">提现</h2>
		</div>
		<#--内容层-->
		<div class="container">
			<!--选择支付方式tab-->
			<ul class="line-tab">
				<li data-tab="1"><p class="tabbox choosed" >支付宝</p></li>
				<li data-tab="2"><p class="tabbox " >银行卡</p></li>
			</ul>
			<#--列表内容-->
			<div class="main-Alipay">
				<ul class="clause-2-ul">
					<li>
						<span>姓名</span>
						<input type="text" class="input-text-white" id="name1" placeholder="请输入您的姓名"/>
					</li>
					<li>
						<span>账户</span>
						<input type="text" class="input-text-white" id="zhifubao" placeholder="请输入您的支付宝账户"/>
					</li>
					<li>
						<span>密码</span>
						<input type="password" class="input-text-white" id="secret1" placeholder="请输入您的密码" />
					</li>
					<li>
						<span>金额</span>
						<input type="text" class="input-text-white" id="money1" placeholder="请输入提现金额" />
					</li>
				</ul>
			</div>
			<div class="main-creditcard">
				<ul class="clause-2-ul">
					<li>
						<span>姓名</span>
						<input type="text" class="input-text-white" id="name2" placeholder="请输入您的姓名"/>
					</li>
					<li id="bankSelect">
						<span>银行</span>
						<input type="text" class="input-text-white" id="bankType" placeholder="请选择银行"/>
					</li>
					<li>
						<span>卡号</span>
						<input type="text" class="input-text-white" id="cardNumber" placeholder="请输入银行卡号" />
					</li>
					<li>
						<span>密码</span>
						<input type="password" class="input-text-white" id="secret2" placeholder="请输入您的密码" />
					</li>
					<li>
						<span>金额</span>
						<input type="text" class="input-text-white" id="money2" placeholder="请输入提现金额" />
					</li>
				</ul>
			</div>
			<#--按钮-->
			<button type="button" class="button-1">确认提现</button>
		</div>
		<div class="bank-select-con">
			<h3>热门银行</h3>
			<ul class="clause-2-ul">
				<li data-bank="中国工商银行">中国工商银行</li>
				<li data-bank="招商银行">招商银行</li>
				<li data-bank="中国光大银行">中国光大银行</li>
				<li data-bank="中信银行">中信银行</li>
				<li data-bank="浦发银行">浦发银行</li>
				<li data-bank="广发银行">广发银行</li>
				<li data-bank="华夏银行">华夏银行</li>
				<li data-bank="中国建设银行">中国建设银行</li>
				<li data-bank="交通银行">交通银行</li>
				<li data-bank="中国银行">中国银行</li>
				<li data-bank="中国民生银行">中国民生银行</li>
				<li data-bank="兴业银行">兴业银行</li>
				<li data-bank="平安银行">平安银行</li>
			</ul>
		</div>
	</body>
</html>
