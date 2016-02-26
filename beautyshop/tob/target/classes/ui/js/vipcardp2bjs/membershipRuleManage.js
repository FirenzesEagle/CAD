$(function(){
	var typeId=getQueryString("typeId");
	$(".icon-add-top").on("click",function(){
		window.location.href="./addconfigs.jspx?typeId="+typeId;
	});
	$(".button-absolute").on("click",function(){
		var $element=$(this).parent();
		var id=$element.data("id");
		var datatosend={
			"configid":id
		};
		console.log(datatosend);
		if($(this).hasClass("card-recover")===true){//恢复发售
			
			$.ajax({
				type:"get",
				url:"./recoverconfig.jspx",
				async:"false",
				data:datatosend,
				crossDomain:true,
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.SUCCESS===true){
						$element.find(".rule-m-red").remove();
						$element.find(".button-absolute").removeClass("card-recover");
						$element.find(".button-absolute").text("停止发售");
					}else{
						$.MsgBox.Alert("",data.MSG);
						console.log(data.MSG);
						return false;
					}
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(XMLHttpRequest.readyState);
					console.log(textStatus);
		        }
			});			
		}else{//停止发售
			

			$.ajax({
				type:"get",
				url:"./stopconfig.jspx",
				async:"false",
				data:datatosend,
				crossDomain:true,
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.SUCCESS===true){
						var date=new Date();
						var y = date.getFullYear();  
					    var m = date.getMonth() + 1;  
					    var d = date.getDate(); 
					    var dateStr=y+"年"+m+"月"+d+"日";
					    var htmlStr='<p class="rule-m-red">该卡于 '+dateStr+' 停止发售</p>';
					    $element.find(".rule-m-red").remove();
					    $element.find(".button-absolute").before(htmlStr);
					    $element.find(".button-absolute").addClass("card-recover");
						$element.find(".button-absolute").text("恢复发售");
					    
					}else{
						$.MsgBox.Alert("",data.MSG);
						console.log(data.MSG);
						return false;
					}
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(XMLHttpRequest.readyState);
					console.log(textStatus);
		        }
			});
		}
		
	});
	
});
