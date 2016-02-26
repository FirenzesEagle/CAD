<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建商品</title>
<link type="text/css" rel="stylesheet" href="/wro/szb.lf.addgoods.css" />


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
        	<h3>创建商品</h3><br /><hr />
            <div class="cont">
            	<h4>商品基本信息</h4>
            	<b>*</b>&nbsp;商品名称：<input type="text" class="goodsname" />	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属类别：<select name="goodsclass" class="selectclass"></select><button class="new">新增分类</button><br />
                <div class="newclass">
                	<input type="text" placeholder="输入分类名称" class="classname"/><br />
                    <button class="submitclass">新增</button><button class="cancel">取消</button>
                </div>
                <b>*</b>&nbsp;商品规格：<input type="text" class="goodsstandard" /> 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="sale">是否预订：<input type="radio" name="book" value="sale"/>现货<input type="radio" name="book" value="book"/>预订</span><br />
                <b>*</b>&nbsp;商品价格：<input type="text" class="goodsprice" />元 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="up">是否上架：<input type="radio" name="up" value="1"/>上架 <input type="radio" name="up" value="0" />稍后上架</span><br />
                <label class="a1"><b class="a11">*</b>&nbsp;展示图片：</label><img class="a2" name="goodspic" src="/wro/wroResources?id=classpath:ui/image/null.png" width="100" id="file_up"/><button class="a3" id="file_up">上传展示图片</button>
                <input type="file" onchange="preImg(this.id)" accept="image/jpeg,image/png" class="file" name="uploadImg" id="imgOne" /><br />
                <br />
                <hr />
                
                <div class="desp">
               		<h4>商品描述信息</h4>
               		<div class="description">
                		<label class="b1"><b class="b11"></b>&nbsp;描述图片：</label><img class="imgTwo1" name="goodspics" src="/wro/wroResources?id=classpath:ui/image/null.png" id="file_up2"/><button class="b3" id="file_up2">上传描述图片</button><input type="file" onchange="preImg(this.id)" accept="image/jpeg,image/png" class="file2" name="uploadImg" id="imgTwo1" /><br /><br />
               			<label class="c1"><b></b>&nbsp;描述信息：</label><textarea class="c2 goodsdescription" id="1"></textarea><button class="c4" id="1">删除该条</button><br /><br /><hr />
                		<br />
                	</div>
                </div>
                
                <center><button class="c3">继续添加图文</button>&nbsp;<button class="view">预览</button>&nbsp;<button class="submit">发布</button></center>
            </div>
        </div>
  </div>  
</body>
<script type="text/javascript" src="/wro/szb.jquery.js" ></script>
<script type="text/javascript" src="/wro/szb.lf.ajaxupload.js" ></script>
	<script type="text/javascript" src="/wro/szb.lf.baseUrl.js" ></script>
	<script type="text/javascript" src="/wro/szb.lf.addgoods.js" ></script>

<script type="text/javascript" src="/wro/szb.lf.uploadimg.js" ></script>


</html>