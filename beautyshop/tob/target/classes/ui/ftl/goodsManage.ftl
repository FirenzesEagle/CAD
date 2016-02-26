<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品仓库</title>
<link type="text/css" rel="stylesheet" href="/wro/szb.lf.goodsmanage.css" />

</head>

<body>
	<header>
    	<img class="log" src="/wro/wroResources?id=classpath:ui/image/head2.png" />
        <div class="userinf">
        	<img class="touxiang" src="/wro/wroResources?id=classpath:ui/image/touxiang.png" />
            <label class="name"></label>
        </div>
   </header>
   <div class="content clearfix">
   		<aside class="pannel">
        	<button class="goods">商品仓库</button><br />
            <button class="services">服务管理</button>
        </aside>
        <div class="list">
        	<h3>商品仓库</h3><hr />
            <div class="clearfix btnlist">
                <div class="tabmenu">
                    <ul class="state">
                        <li id="0">未上架</li>
                        <li id="1">已上架</li>
                        <li id="2" class="selected">全部</li>
                        <select name="" id="choose">
                        	<option>全部</option>
                        	<option>现货</option>
                            <option>预订</option>
                        </select>
                    </ul>
                </div>
                <div class="upadd">
                     <ul class="state">
                        <span><b></b><span>
                        <li class="upall">一键上架</li>
                        <li class="additem">添加商品</li>
                    </ul>
                </div>
            </div>
            <div class="cont">
            	
            </div>
            <br />
           
        </div>
   <div>

</body>
<script type="text/javascript" src="/wro/szb.jquery.js" ></script>
	<script type="text/javascript" src="/wro/szb.lf.goodsmanage.js" ></script>
	<script type="text/javascript" src="/wro/szb.lf.baseUrl.js" ></script>
</html>
