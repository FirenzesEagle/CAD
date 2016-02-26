$(function(){
	$(".memberlist-table tbody tr").on("click",function(){
		var id=$(this).data("id");
		window.location.href="./vipcardvipsdetail.jspx?vipcardId="+id;
	})
});
