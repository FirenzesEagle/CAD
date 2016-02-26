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
		<script src="/wro/wx.withdrawRec.js"></script>
		<script src="/wro/wx.withdrawBase.js"></script>
		
	</head>
	<body>
		<!--头部菜单层 -->
		<div class="topNav">
			<a href="javascript:void(0)" class="topReturn">返回</a>
			<h2 class="topTitle"></h2>
		</div>
		<!--内容层-->
		<div class="container">
			<!--<form>-->
				<!--存放搜索组件-->
				<div class="timeselect-div">
					<input type="text" class="time-input" id="timeStart" />
					<input type="text" class="time-input" id="timeEnd" />
					<button id="withdrawSearch" class="button-search"></button>
				</div>
			<!--</form>-->
			<ul class="search-result-ul">

			</ul>
		</div>
	</body>
</html>
