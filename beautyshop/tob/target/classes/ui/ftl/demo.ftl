
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="user-scalable=yes,width=device-width, initial-scale=1" user-scalable=no>
		<title>上传菜品</title>
		<link rel="stylesheet" type="text/css" href="/wro/demo.demo.css"/>
	</head>
	<body>
		<div class="container">
			<h1>上传菜品Demo</h1>
			<div class="tips">上传菜品Demo，展示HTML图片显示，JS及CSS文件引用，AJAX调用案例</div>
			<div class="btn-group">
				<label for="dishName">菜品名称：</label>
				<input type="text" name="dishName" class="dishName"/>
			</div>
			<div class="btn-group">
				<label for="dishPrice">菜品单价：</label>
				<input type="text" name="dishPrice" class="dishPrice"/>
			</div>
			
			<button class="submit" id="submit"><img src="/wro/wroResources?id=classpath:ui/image/dh4.png"/>提交</button>
		</div>
		<div class="result"></div>

  		<script src="/wro/demo.jquery.js"></script>
  		<script src="/wro/demo.demo.js"></script>
	</body>
</html>

	