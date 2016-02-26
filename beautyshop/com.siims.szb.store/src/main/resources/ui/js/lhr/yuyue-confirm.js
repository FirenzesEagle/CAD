var Json=decodeURI(getQueryString("servicedata"));
var openid=decodeURI(getQueryString("openid"))
var Json=JSON.parse(Json);

window.onload=function(){
	$('.yuyue-info').append(creatServiceInfo(Json));
}
$(function(){
	$('.nav').click(function(){
		history.go(-1);
	});
	$('.yuyue-contact').watch(function(){
		$('.yuyue-confirm input').removeClass("inputChanged");
		var Name=$('.userName').val();
		var Phone=$('.userPhone').val();
		if(Name!=undefined&&Name!=null&&Name!=""&&Phone!=undefined&&Phone!=null&&Phone!=""&&Phone.length==11){
				$('.yuyue-confirm input').addClass("inputChanged");
			}else{
				$('.yuyue-confirm input').removeClass("inputChanged");
			}	
			
		}); 
	$('.yuyue-confirm').delegate(".inputChanged","click",function(){
		console.log(Json.data);
		var personname=$('.userName').val();
		var persontel=$('.userPhone').val();
		for(var i=0;i<Json.data.length;i++){
			//Json.data[i].personname=encodeURI(encodeURI(personname));
			Json.data[i].personname=encodeURI(personname);
			Json.data[i].persontel=persontel;
			Json.data[i].personid=Json.openid;
		}
		
		var ob=new Object();
		ob.record=Json.data;
		var datatosend={
				DATA:JSON.stringify(ob)
		};
		console.log( datatosend);
			$.ajax({
				type:"get",
				url:addbespeak,
				async:"false",
				data:datatosend,
				dataType:"json",
				crossDomain:true,
				success:function (data) {
					console.log(data);
					if(data.SUCCESS=="true"){
						msgData={
							"type":"true",
							"data":data.DATA
						};
						$.ServiceBox.Alert(msgData,function(){
							window.location.href=makeurl("../bespeakorder/togetbespeakorderforc.jspx",1);
						});
					}else if(data.SUCCESS=="false"){
						msgData={
								"type":"false",
								"data":data.DATA
							};
							$.ServiceBox.Alert(msgData,function(){
								history.go(-1);
							});
					}
					
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
							//console.log(data);
	                        console.log(XMLHttpRequest.status);
	                      //  alert(XMLHttpRequest.readyState);
	                      //  alert(textStatus);
	                    }
			});
  	
		
	});
})
function getQueryString(name) {
 
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
function makeurl(url,data){
	var url=encodeURI(url);
	var data=encodeURI(data);
	var encode=encodeURI(url+"?orderState="+data+"&openid="+openid);
	return encode;
	}
function creatServiceInfo(data){
	var html='<div class="info-servicename" id="'+data.serviceId+'">';
		html+='<label>预约服务：</label>';
		html+='<p>'+data.serviceName+'</p>';
		html+='</div>';
		html+='<div class="info-time">';
		html+='<label for="">预约时间：</label>';
		var time='<div class="timelist">';
		
		for(var i=0;i<data.data.length;i++){
			var rq=data.data[i].year+'-'+data.data[i].month+'-'+data.data[i].day;
			var tm=data.data[i].line+':00-'+(parseInt(data.data[i].line)+1)+':00';
			time+='<p>'+rq+' '+tm+'</p>';
		}
		html+=time+'</div></div>';
		var total=data.data.length*parseInt(data.servicePrice);	
		$('.yuyue-tm').text(total);
		$('.yuyue-num').text(data.data.length);
		$('.yuyue-price').text(data.servicePrice);
		return html;
					
				
}

(function($) {  
    $.fn.watch = function(callback) {  
        return this.each(function() {  
            //缓存以前的值  
            $.data(this, 'originVal', $(this).val());  
  
            //event  
            $(this).on('keyup paste', function() {  
                var originVal = $(this, 'originVal');  
                var currentVal = $(this).val();  
  
                if (originVal !== currentVal) {  
                    $.data(this, 'originVal', $(this).val());  
                    callback(currentVal);  
                }  
            });  
        });  
    }  
})(jQuery);  

