<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商站宝</title>
		<link href="/wro/szb.orderManage.css" rel="stylesheet" type="text/css" />
		<link href="/wro/szb.serviceBox.css" rel="stylesheet" type="text/css" />
		<link href="/wro/szb.MsgBox.css" rel="stylesheet" type="text/css"/>
		<meta name="viewport" content="user-scalable=no,width=device-width, initial-scale=1">
	</head>
	<body>
		<div class="container">
			<div class="topNav">
				<a href="" class="topReturn">返回</a>
				<h2 class="topTitle">我的订单</h2>
				<div class="order-search"></div>
			</div>
			<ul class="line-tab">
				<li data-tab="1"><p class="tabbox choosed" >商品订单</p></li>
				<li data-tab="2"><p class="tabbox " >服务订单</p></li>
			</ul>
			<div class="goodsCon">
				<ul class="page-tab">
					<li class="tab-active">新订单<div class="new">99+</div></li>
					<li>已接单</li>
					<li>已完成</li>
				</ul>
				<ul class="order-sort-con">
	
				</ul>
			</div>
			<div class="serviceCon">
				<div class="form-date">
					
				</div>
				<div class="yy-detail">
					<ul class="status-description">
						<li class="description-s"><p>已完成</p></li>
						<li class="description-w"><p>未完成</p></li>
						<li class="description-g"><p>已过期</p></li>
					</ul>
					<div class="yy-info">
						
					</div>
				</div><!--yy-detail-->
			</div>
			<div class="none-con">
				<i class="icon-noneorder"></i>
				<span class="text-noneorder"></span>
			</div>
		</div>
		<div class="search-bg"></div>
		<div class="search-con">
			<form class="search-form">
				<input type="search" placeholder="请输入订单编号、商品名称等" class="search-input" />
				<button class="search-button" type="button">取消</button>
			</form>
		</div>
		<script src="/wro/szb.jquery.js"></script>
		<script src="/wro/szb.baseurl.js"></script>
		<script src="/wro/szb.global.js"></script>
		<script src="/wro/szb.serviceBox.js"></script>
		<script src="/wro/szb.MsgBox.js"></script>
		<script src="/wro/szb.orderManage.js"></script>
	</body>
</html>
