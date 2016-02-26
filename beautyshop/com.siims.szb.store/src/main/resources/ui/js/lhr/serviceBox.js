(function () {
  $.ServiceBox = {
  	
    Alert: function (msgData,callback) {
		GenerateHtml("alert",msgData);
			$("#mb_box").click(function () {
      		$("#mb_box,#mb_con").remove();
    	});
		btnOk(callback); //alert只是弹出消息，因此没必要用到回调函数callback
		btnNo();
    },
    AlertNo:function(msgData,callback){
    	GenerateHtml("alertno",msgData);
			$("#mb_box").click(function () {
      		$("#mb_box,#mb_con").remove();
    	});
		btnOk(callback); //alert只是弹出消息，因此没必要用到回调函数callback
		btnNo();
    }/*,
    Confirm: function (msgData, callback) {
      GenerateHtml("confirm", title, msg);
      btnOk(callback);
      btnNo();
    }*/
  }
 
  //生成Html
  var GenerateHtml = function (type,msgData) {
 
    var _html = "";
 		if(msgData.type=="true"){
 			 	_html += '<div id="mb_box"></div>';
				_html +='<div id="mb_con">';
				_html +='<span id="mb_tit"></span><a id="mb_ico">x</a>';
				_html+='<div id="mb_msg">';
				_html+='<div class="serviceInfo">';		
				_html+='<p>'+msgData.data+'</p>';
				_html+='</div></div>';
				_html += '<div id="mb_btnbox"><input id="mb_btn_ok" type="button" value="我的订单" />'
				+'</div></div>';
 		}else{
 				_html += '<div id="mb_box"></div>';
				_html +='<div id="mb_con">';
				_html +='<span id="mb_tit"></span><a id="mb_ico">x</a>';
				_html+='<div id="mb_msg">';
				_html+='<div class="serviceInfo">';		
				_html+='<p>对不起，预约失败</p><p>您可重新预约</p>';
				_html+='</div></div>';
				_html += '<div id="mb_btnbox"><input id="mb_btn_ok" type="button" value="重新预约" />'
				+'</div></div>';
 		}
   
		

 
    if (type == "alert") {
     
    }
    if (type == "alertno") {
      _html += '<div id="mb_btnbox"></div></div>';
    }
 
    //必须先将_html添加到body，再设置Css样式
    $("body").append(_html); 
    GenerateCss(type);
  }
 
  //生成Css
  var GenerateCss = function (type) {
 	if(type=='alert'){
 		$("#mb_btn_ok").css({width:'100%',border:'none'});
 	}
    $("#mb_box").css({ width: '100%', height: '100%', zIndex: '99999', position: 'fixed',
      filter: 'Alpha(opacity=60)', backgroundColor: 'grey', top: '0', left: '0', opacity: '0.4'
    });
 

    //右上角关闭按钮hover样式
    $("#mb_ico").hover(function () {
      $(this).css({ backgroundColor: 'Red', color: 'White' });
    }, function () {
      $(this).css({ backgroundColor: '#DDD', color: 'black' });
    });
 
    var _widht = document.documentElement.clientWidth; //屏幕宽
    var _height = document.documentElement.clientHeight; //屏幕高
 
    //var boxWidth = $("#mb_con").width();
    var boxWidth=250;
    //console.log(boxWidth);
    var boxHeight = $("#mb_con").height()+30;
 
    //让提示框居中
    $("#mb_con").css({ top: (_height - boxHeight) / 2 + "px", left: (_widht - boxWidth) / 2 + "px" });
  }
 

  //确定按钮事件
  var btnOk = function (callback) {
    $("#mb_btn_ok").click(function () {
      $("#mb_box,#mb_con").remove();
      if (typeof (callback) == 'function') {
        callback();
      }
    });
  }
 
  //取消按钮事件
  var btnNo = function () {
    $("#mb_btn_no,#mb_ico").click(function () {
      $("#mb_box,#mb_con").remove();
    });
  }
})();