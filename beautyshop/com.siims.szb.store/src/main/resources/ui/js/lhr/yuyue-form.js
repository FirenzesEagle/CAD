var shopId=decodeURI(getQueryString("shopId"));
var openid=decodeURI(getQueryString("openid"));
var serviceId=decodeURI(getQueryString("serviceId"));
var serviceName=decodeURI(getQueryString("serviceName"));
var servicePrice=decodeURI(getQueryString("servicePrice"));
var specId=decodeURI(getQueryString("specId"));
window.onload=function(){
	$('.form-date').append(creatDate());
	$('.yy-info').append(creatForm());
	loadForm(new Date());
}
$(function(){
	$('.nav').click(function(){
		history.go(-1);
	});
	$('.form-date').delegate(".date-items li","click",function(){
		$(this).parent().children().each(function(){
			$(this).children().removeClass("active");
		});
		$(this).children().addClass("active");
		$('.yy-info').children().remove();
		var rq=$(this).children().data("rq");
		$('.yy-info').append(creatForm());
		loadForm(rq);
	});
	
	/*var date=new Date(date1+24*3600*1000);
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	var hour=date.getHours();
	var riqi=date.toLocaleDateString();
	var riqi=year.toString()+month.toString()+day.toString();
	alert(riqi);*/
	$('.yy-info').delegate(".tdDiv","click",function(){
		
		if($(this).hasClass("td-gq")!==true&&$(this).hasClass("td-choosed")!==true){
			
				$(this).toggle(function(){
					$(this).addClass("td-current");
					if($('.td-current').size()>0){
						$('.yy-confirm').show();
						
					}else{
						$('.yy-confirm').hide();
					}
					currentMoney();
				},function(){
					$(this).removeClass("td-current");
					if($('.td-current').size()>0){
						$('.yy-confirm').show();
						
					}else{
						$('.yy-confirm').hide();
					}
					currentMoney();
				}).trigger("click");
		
		}
		
	});
	$('.yy-button').click(function(){
		var Json=new Object();
		Json.serviceId=serviceId;
		Json.serviceName=serviceName;
		Json.servicePrice=servicePrice;
		Json.openid=openid;
		Json.data=currentMoney();
		//console.log(Json.data);
		//console.log(Json);
		Json=JSON.stringify(Json);
		window.location.href=makeurl("toaddbespeak.jspx",Json);
	});
})

function creatDate(){
	var date=new Date();
	var riqi=date;
	var html='<ul class="date-items">';
	for(var count=0;count<7;count++){
		if(count==0){
			html+='<li><div class="itembox active" data-rq="'+riqi+'">今天</div></li>';
		}else if(count==1){
			html+='<li><div class="itembox " data-rq="'+riqi+'">明天</div></li>';
		}else{
			var month=date.getMonth()+1;
			var day=date.getDate();
			html+='<li><div class="itembox" data-rq="'+riqi+'">'+month+'/'+day+'</div></li>';
		}
		date=date.valueOf();
		date=new Date(date+24*3600*1000);
		riqi=date;
	}
	html+='</ul>';
	return html;
}
function creatForm(){
	var html='<table class="yy-table" data-price="'+servicePrice+'" data-name="'+serviceName+'" id="'+serviceId+'">';
	for(var i=8;i<22;i++){
		html+='<tr class="yy-tr" id="yy-tr'+i+'" data-tr="'+i+'">';
		html+='<th>'+i+':00-'+(i+1)+':00</th>';
		for(var j=1;j<4;j++){
			html+='<td data-td='+j+' id="yy-td'+j+'" class="yy-td"><div class="tdDiv"></div></td>';
		}
		html+='</tr>';
	}			
		html+='</table>';
		return html;				
}
function loadForm(date){
	date=new Date(date);
	time=date.getDate();
	//alert(time.getMonth());
	var currentDate=new Date();
	//alert( currentDate.getDate());
	var currentTime=currentDate.getHours();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	if(month<10){
		month='0'+month.toString();
	}
	
	var day=date.getDate();
	if(day<10){
		day='0'+day.toString();
	}
	var riqi=year.toString()+month.toString()+day.toString();
	var datatosend = {
			"shopid":shopId,
			"date":riqi};
			
			$.ajax({
				type:"get",
				url:yyGetData,
				async:"false",
				data:datatosend,
				dataType:"json",
				crossDomain:true,
				success:function (data) {
					console.log(data);
					if(data.SUCCESS=="true"){
						for(var d=0;d<data.DATA.length;d++){
							var trID='#yy-tr'+data.DATA[d].line;
							var tdID='#yy-td'+data.DATA[d].row;
							$(trID).find(tdID).children().addClass("td-choosed");
						}
					}
					
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
							//console.log(data);
	                        console.log(XMLHttpRequest.status);
	                      //  alert(XMLHttpRequest.readyState);
	                      //  alert(textStatus);
	                    }
			});
	
	//alert(date.getDate()<=currentDate.getDate());
	if(date.getDate()<=currentDate.getDate()){
		$('.yy-table').children().find('tr').each(function(){
			if($(this).data("tr")<currentTime){
				//alert($(this).data("tr")<currentTime);
				$(this).children().each(function(){
					if($(this).children().hasClass("td-choosed")==false){
						$(this).children().addClass("td-gq");
					}
				});
			}
		});
	}
}
function currentMoney(){
	var totalMoney=0;
	var price=parseFloat($('.yy-table').data("price"));
	var number=0;
	var serviceArray=new Array();
	$('.yy-table').find('.yy-tr').each(function(){
		var tr=$(this).data("tr");
		$(this).find('.yy-td').each(function(){
			td=$(this).data("td");
			if($(this).find('.tdDiv').hasClass("td-current")){
				var data=new Object();
				number++;
				var date=$('.form-date').find('.active').data("rq");
				date=new Date(date);
				var year=date.getFullYear();
				var month=date.getMonth()+1;
				var day=date.getDate();
				data.year=year;
				data.month=month;
				data.day=day;
				data.line=tr;/*行*/
				data.row=td;/*列*/
				data.serviceid=serviceId;
				data.specid=specId;
				data.shopid=shopId;
				serviceArray.push(data);
				
			}
		});
	});
	totalMoney=price*number;
	$('.servicePrice').text(totalMoney);
	return serviceArray;
}
function makeurl(url,data){
	var url=encodeURI(url);
	var data=encodeURI(data);
	var encode=encodeURI(url+"?servicedata="+data+"&openid="+openid);
	return encode;
	}
function getQueryString(name) {
	 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}