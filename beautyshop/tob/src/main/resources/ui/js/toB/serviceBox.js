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
 
    _html += '<div id="mb_box"></div><div class="serviceBox" id="mb_con">'
			+'<span id="mb_tit"></span><a id="mb_ico">x</a>'
			+'<div id="mb_id"><p>预约编号</p><p>No. '+msgData.num+'</p></div>'
			+'<div id="mb_msg"><img id="msg_img" src="'+msgData.picUrl+'"/>'
			+'<div class="serviceInfo"><p>'+msgData.serviceName+'</p><p>'+msgData.serviceStatus+'/&yen;'+msgData.serviceMoney+'</p><p>'+msgData.personNum+'人</p></div>'
			+'<div class="serviceTime"><span>预约时间：'+msgData.time+'</span></div></div>'
			+'<div id="mb_person"><img src="/wro/wroResources?id=classpath:ui/image/lhr/tang/icon_contact.png" />'
			+'<span>'+msgData.personName+'</span><a class="mbA" href="tel:'+msgData.personTel+'">'
			+'<img class="mb-last" src="/wro/wroResources?id=classpath:ui/image/lhr/tang/icon_appointment.png" />'
			+'<span>'+msgData.personTel+'</span></a></div>';

 
    if (type == "alert") {
      _html += '<div id="mb_btnbox"><input id="mb_btn_ok" type="button" value="确定收款" />'
				+'</div></div>';
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
    var boxWidth=290;
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