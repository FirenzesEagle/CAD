<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建服务</title>
<link type="text/css" rel="stylesheet" href="/wro/szb.lf.addservice.css" />
</head>

<body>
	<header class="clearfix">
    	<img class="log" src="/wro/wroResources?id=classpath:ui/image/head2.png" />
        <div class="userinf">
        	<img class="touxiang" src="/wro/wroResources?id=classpath:ui/image/touxiang.png" />
            <label class="name"></label>
        </div>
   </header>
   <div class="content clearfix">
   		<aside class="left">
        	<button class="back">返回</button><br />
        </aside>
        <div class="right">
        	<h3>添加服务</h3><br /><hr />
            <div class="cont">
            	<h4>服务基本信息</h4>
            	<b>*</b>&nbsp;服务名称：<input type="text" class="servicename" /><br />
                <b>*</b>&nbsp;服务时长：<input type="text" class="servicestandard" /> <br />	
                <b>*</b>&nbsp;服务价格：<input type="text" class="serviceprice" />元 <br />	
                <label class="a1"><b class="a11">*</b>&nbsp;展示图片：</label><img class="a2" name="servicepic" src="/wro/wroResources?id=classpath:ui/image/null.png" width="100" id="file_up"/><button class="a3" id="file_up">上传展示图片</button>
                <input type="file" onchange="preImg(this.id)" accept="image/jpeg,image/png" class="file" name="uploadImg" id="imgOne" /><br />
                <br />
                <hr />
                
            <div class="desp">
               		<h4>商品描述信息</h4>
               		<div class="description">
                		<label class="b1"><b class="b11"></b>&nbsp;描述图片：</label><img class="imgTwo1" name="servicepics" src="/wro/wroResources?id=classpath:ui/image/null.png" id="file_up2"/><button class="b3" id="file_up2">上传描述图片</button><input type="file" onchange="preImg(this.id)" accept="image/jpeg,image/png" class="file2" name="uploadImg" id="imgTwo1" /><br /><br />
               			<label class="c1"><b></b>&nbsp;描述信息：</label><textarea class="c2 servicedescription" id="1"></textarea><button class="c4" id="1">删除该条</button><br /><br /><hr />
                		<br />
                	</div>
            </div>    
            <center><button class="c3">继续添加图文</button>&nbsp;<button class="view">预览</button>&nbsp;<button class="submit">发布</button></center>
        </div>
  </div>  
</body>
<script type="text/javascript" src="/wro/szb.jquery.js" ></script>

	<script type="text/javascript" src="/wro/szb.lf.addservice.js" ></script>
	<script type="text/javascript" src="/wro/szb.lf.baseUrl.js" ></script>
<script type="text/javascript" src="/wro/szb.lf.uploadimg.js" ></script>

<script type="text/javascript" src="/wro/szb.lf.ajaxupload.js" ></script>
</html>