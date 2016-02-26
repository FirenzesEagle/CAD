var vipcardId= getQueryString("vipcardId");
var billlist='<tr><td>TIME</td><td>ZHAIYAO</td><td>MONEY</td><td>LASTMONEY</td></tr>';
window.onload=function(){
	datatosend={
		"vipcardId":vipcardId
	};
	$.ajax({
			type:"get",
			url:mycardbillmethod,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS==true){
					
					$.each(data.DATA,function(i){ 
									var temp = billlist.replace("TIME", data.DATA[i].TIME).replace("LASTMONEY",data.DATA[i].LASTMONEY).replace("ZHAIYAO",data.DATA[i].ZHAIYAO).replace("MONEY",data.DATA[i].MONEY);
									$(".bill-tbody").append(temp);
								});
				}else{
					
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log(data);
                        console.log(XMLHttpRequest.status);
                      //  alert(XMLHttpRequest.readyState);
                      //  alert(textStatus);
                    }
		});
}
$(function(){
	$('.my-bill-title .nav').click(function(){
		history.go(-1);
	});
})
function makeurl(url){
	var encode=encodeURI(url);
	return encode;
	}
function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }