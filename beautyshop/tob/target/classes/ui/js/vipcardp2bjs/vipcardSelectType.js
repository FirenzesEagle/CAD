$(function(){
	$(".model-li").on("click",function(){
		if($(this).hasClass("li-multi")==true){
			return false;
		}else{
			var id=$(this).data("shopid");
			window.location.href="./vipcardmanage.jspx?type=1&shopid="+id;
		}
	})
});
