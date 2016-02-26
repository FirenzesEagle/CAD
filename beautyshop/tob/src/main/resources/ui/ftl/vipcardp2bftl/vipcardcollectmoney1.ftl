<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/vipcard.vipcard.css" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="user-scalable=no,initial-scale=1,width=device-width" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle">会员卡收款</h2>
		</div>
		<!--内容层-->
		<form action="./vipcardcollectmoney2.jspx" method="GET">
			<div class="container">
				<div class="largeDiv">
					<div class="input-group">
						<input type="hidden" name="typeId" value="${typeId}"/>
						<label for="consumePrice" class="input-text-label">会员账号</label>
						<input type="text" name="phone" placeholder="请输入会员账号" class="input-text-1"/>
					</div>
				</div>
				<span class="beizhu-red" style="display: none;"></span>
				<button type="submit" class="button-1">确认</button>
			</div>
		</form>
		<script src="/wro/jquery.js"></script>
		<script src="/wro/global.js"></script>
		<script>
			$(function(){
				
				$("form").submit(function(e){
					var $element=$(".beizhu-red");
					var inputString=$(".input-text-1").val();
					var partten = /^1\d{10}$/;
					if(!partten.test(inputString)){
						e.preventDefault();
						$element.text("请输入正确格式！");
						$element.show();
					}
					
					
				});
			});
		</script>
	</body>
</html>
