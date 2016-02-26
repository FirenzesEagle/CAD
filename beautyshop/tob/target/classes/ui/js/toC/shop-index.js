window.onload=function(){
	var districtId=decodeURI(getQueryString("district_id"));
	initshop(districtId);
}
$(function(){
	$('.district-shop').delegate(".shop-div","click",function(){
		id=$(this).attr("id");
		var shopId=encodeURI(id);
		window.location.href=encodeURI("shop-goodlist.html?shopId="+shopId);
	});
})
function creatShopList(array){
	//alert(array.length);
	var html='';
	if(array.length % 2==0){
		for(var i=0;i<array.length;i=i+2){
			html+='<div class="shop-row">';
			html+='<div class="shop-div" id="'+array[i].id+'">';
			html+='<img src="'+array[i].image+'" /><span>'+array[i].name+'</span></div>';
			html+='<div class="shop-div" id="'+array[i+1].id+'">';
			html+='<img src="'+array[i+1].image+'" /><span>'+array[i+1].name+'</span></div>';
			html+='</div>';
		}
	}else{
		for(var i=0;i<array.length-1;i=i+2){
			html+='<div class="shop-row">';
			html+='<div class="shop-div" id="'+array[i].id+'">';
			html+='<img src="'+array[i].image+'" /><span>'+array[i].name+'</span></div>';
			html+='<div class="shop-div" id="'+array[i+1].id+'">';
			html+='<img src="'+array[i+1].image+'" /><span>'+array[i+1].name+'</span></div>';
			html+='</div>';
		}
		html+='<div class="shop-row">';
			html+='<div class="shop-div" id="'+array[array.length-1].id+'">';
			html+='<img src="'+array[array.length-1].image+'" /><span>'+array[array.length-1].name+'</span></div>';
			html+='<div class="shop-div" id="">';
			html+='</div>';
			html+='</div>';
		
	}
	$('.district-shop').append(html);		
}
function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
function initshop(districtId){
		var datatosend = {
		"district_id":districtId};
		 	
		//console.log(datatosend);
		$.ajax({
			type:"get",
			url:districtshops,
			async:"false",
			data:datatosend,
			dataType:"json",
			crossDomain:true,
			success:function (data) {
				console.log(data);
				if(data.SUCCESS=="true"){
					creatShopList(data.DATA);
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
function makeurl(url,val1,val2){
	var val1=encodeURI(val1);
	var val2=encodeURI(val2);
	var url='module-addryl.html';
	var encode=encodeURI(url+"?addr1="+val1+"&addr2="+val2);
	return encode;
	}