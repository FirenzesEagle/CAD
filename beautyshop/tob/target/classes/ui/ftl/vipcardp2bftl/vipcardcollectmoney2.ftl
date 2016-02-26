<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/vipcard.vipcard.css" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="user-scalable=no,initial-scale=1,width=device-width" />
	</head>
	<body>

		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">会员卡收款</h2>
		</div>
		<!--内容层-->
		<div class="container">
			<ul class="membership-list">
			<#if (list?size=0)>
				<li>当前无会员卡信息</li>
				</ul>
			<#else>
			 	
				<#list list as a>
					<li>
						<label for="membership">
							<input data-id=${a.id} data-type=${map["${a.id}"]["type"]} checked="checked" type="radio" name="membership"  class="radio-none"/>
							<i class="radio-i"></i>
						</label>
						<div class="membership-detail clearfix">
							<div class="detail-text-div-1 left">
								<p>${a.consumerName}（${a.cardNumber?string('00000')}）</p>
								<p>
									<#switch map["${a.id}"]["type"]>
										<#case 0> 无优惠 <#break>
										<#case 1> 折扣优惠-<span class="cardZhekou">${map["${a.id}"]["benefit"]}</span>折<#break>
										<#case 2> 返现优惠<#break>
										<#case 3> 计次优惠-共<span class="cardJici">${map["${a.id}"]["benefit"]}</span>次<#break>
									</#switch>
								</p>
							</div>
							<div class="detail-text-div-2 right">
								<p class="detail-text-tel">${a.consumerPhone}</p>
								<p>
									<#switch map["${a.id}"]["type"]>
										<#case 0> 
										<#case 1> 
										<#case 2> 卡内余额<span  class="cardMoney">${a.lastMoney?string('#.00')}</span>元<#break>
										<#case 3> 剩余次数<span class="cardCishu">${a.lastMoney}</span>次<#break>
									</#switch>	
							</div>
						</div>
					</li>
				</#list>
				<ul>
			</#if>
			
			<#if (list?size>0)>		
			<div class="input-group">
				<label for="consumePrice" class="input-text-label">消费单价</label>
				<input type="text" name="consumePrice" id="consumePrice" placeholder="请输入本次消费单价" class="input-text-1"/>
			</div>
			<div class="input-group">
				<label for="consumeNum" class="input-text-label">消费数量</label>
				<div class="consume-input">
					<button class="num-min" type="button">-</button>
					<input readonly type="text" name="consumeNum" class="input-consumeNum"/>
					<button type="button"  class="num-add">+</button>
				</div>
				
				<div class="input-beizhu">
					<p>会员优惠价格：
						<span class="beizhu-red" id="payMoney">0</span>元（实付金额）<span class="beizhu-red" id="payWarn">卡内余额不足！</span>
					</p>
				</div>
			</div>
			<div class="input-group">
				<label for="payPass" class="input-text-label">支付密码</label>
				<div class="consume-input-2">
					<input type="password" name="payPass" id="payPass" class="input-text-1"/>
				</div>
				<div class="input-beizhu" id="pageWarn">
					<p>
						<span class="beizhu-red"></span>
					</p>
				</div>
			</div>
			<button type="button" class="button-1" id="confirmPay">确认付款</button>
			</#if>
		</div>
		<div class="paySuc-container">
			<div class="result-layer">
				<img src="/wro/wroResources?id=classpath:ui/image/vipcardp2bimages/tang/icon_success.png" />
				<div class="result-text">
					账号为<span class="paySuc-mobile"></span>的会员已支付<span class="paySuc-money"></span>，您可到“我的账单”里查看您的交易记录
				</div>
			</div>
			<div class="button-group clearfix">
				<a href="javascript:void(0);" class="button-a left"><button class="button-2 liushui">查看流水</button></a>
				<a href="javascript:void(0);" class="button-a right"><button class="button-2 guanli">会员管理</button></a>
			</div>
		</div>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>
		<script src="/wro/md5.js"></script>
		<script src="/wro/vipcard.collectmoney.js"></script>
		
	</body>
</html>
