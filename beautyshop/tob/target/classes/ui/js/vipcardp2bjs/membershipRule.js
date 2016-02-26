$(function(){
	var selects=$(".ruleSelect");
	var typeId=getQueryString("typeId");
	for(var i=0;i<selects.length;i++){
		createSelect(selects[i],i);
		//console.log(i);
	}
	$(".add-rule").on("click",function(){
		if($(".membership-rule-con").length!==0){
			var $lastelement=$(".membership-rule-con:last-child");
			//console.log($lastelement);
				//验证为空项
				if($lastelement.find(".price").val()===""||$lastelement.find(".benefit").val()===""||
					$lastelement.find(".price").val()===null||$lastelement.find(".benefit").val()===null||
					$lastelement.find(".price").val()===undefined||$lastelement.find(".benefit").val()===undefined){
						$.MsgBox.Alert("","请将以上信息填写完整！");
						return false;
				}			
		}

		var ruleHtml='<li class="membership-rule-con"><div class="rule-flex">'
					+'<select class="ruleSelect">'
					+'<option selected="selected">请选择优惠方式</option>'
					+'<option>无优惠</option><option>返现优惠</option>'
					+'<option>打折优惠</option><option>计次卡优惠</option>'
					+'</select></div>'
					+'<img src="/wro/wroResources?id=classpath:ui/image/vipcardp2bimages/membership/icon_cancel.png" class="rule-cancel-button" />'
					+'</li>';
		var $element=$(ruleHtml);
		
		$(".rule-ul").append($element);
		createSelect($element.find(".ruleSelect"),$element.find(".ruleSelect").index());
				
	});
	$(".rule-ul").delegate(".rule-cancel-button","click",function(){
		$element=$(this);
		$.MsgBox.Confirm("","确认删除？",function(){
			$element.parents(".membership-rule-con").remove();
		});
		
	});
	$(".button-1").on("click",function(){
		var len=0;
		var judge=1;
		var wrongMsg;
		var $lastelement=$(".membership-rule-con:last-child");
		if($lastelement.find(".price").val()===""||$lastelement.find(".benefit").val()===""||
				$lastelement.find(".price").val()===null||$lastelement.find(".benefit").val()===null||
				$lastelement.find(".price").val()===undefined||$lastelement.find(".benefit").val()===undefined){
					$.MsgBox.Alert("","请将以上信息填写完整！");
					return false;
			}
		$(".membership-rule-con").each(function(){
			var cardType;
			//验证为空项
			if($(this).find(".price").val()===""||$(this).find(".benefit").val()===""||
				$(this).find(".price").val()===null||$(this).find(".benefit").val()===null||
				$(this).find(".price").val()===undefined||$(this).find(".benefit").val()===undefined){
					$.MsgBox.Alert("","请填写完整所有信息！");
					return false;
			}
			//设置type
			if($(this).find(".select-showbox").attr("value")==1){
				cardType=0;
			}else if($(this).find(".select-showbox").attr("value")==2){
				cardType=2;
			}else if($(this).find(".select-showbox").attr("value")==3){
				cardType=1;
			}else if($(this).find(".select-showbox").attr("value")==4){
				cardType=3;
			}else if($(this).find(".select-showbox").attr("value")==0){
				return true;
			}
			var price=$(this).find(".price").val();
			var benefit=$(this).find(".benefit").val();
			//初始化datatosend
			var datatosend={
				"typeId":typeId,
				"type":cardType,
				"price":price,
				"benefit":benefit
			};
			//addConfig(datatosend);
			$.ajax({
				type:"get",
				url:"./addconfig.jspx",
				async:false,
				data:datatosend,
				crossDomain:true,
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.SUCCESS===true){
						//console.log("success!");
						len++;
					    
					}else{
						wrongMsg=data.MSG;
						console.log(data.MSG);
						judge=0;
						return false;
					}
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(XMLHttpRequest.readyState);
					console.log(textStatus);
		        }
			});
			console.log(judge);
			if(judge==0){
				$.MsgBox.Confirm("","<p>已成功添加"+len+"条</p><p>第"+(len+1)+"条出错</p><p>出错原因："+wrongMsg+"</p>",function(){
					window.location.href="./vipcardconfigmanage.jspx?typeId="+typeId;
				});
				return false;
			}
			
		});
		if(judge==1){
			$.MsgBox.Confirm("","恭喜，已成功添加所有数据,请点击确定返回",function(){
				window.location.href="./vipcardconfigmanage.jspx?typeId="+typeId;
			});
			
		}
	});
	function createSelect(select_container,index){
			//创建select容器，class为select_box，插入到select标签前
			var tag_select=$('<div></div>');//div相当于select标签
			tag_select.attr('class','select-box');
			//console.log(tag_select.data("num"));
			tag_select.insertBefore(select_container);
			//显示框class为select_showbox,插入到创建的tag_select中
			var select_showbox=$('<div></div>');//显示框
			select_showbox.attr("value",0);
			select_showbox.css('cursor','pointer').attr('class','select-showbox').appendTo(tag_select);
			//创建option容器，class为select_option，插入到创建的tag_select中
			var ul_option=$('<ul></ul>');//创建option列表
			ul_option.attr('class','select-option');
			ul_option.appendTo(tag_select);
			createOptions(index,ul_option);//创建option
			//点击显示框
			tag_select.toggle(function(){
				$(this).parent().find(".select-option").show();
			},function(){
				$(this).parent().find(".select-option").hide();
			});
			var li_option=ul_option.find('li');
			//点击具体条目事件
			li_option.on('click',function(){
				$(this).addClass('selected').siblings().removeClass('selected');
				var value=$(this).text();
				select_showbox.text(value);
				var thisnum=$(this).index();
				$(this).parents(".select-box").find(".select-showbox").attr("value",thisnum);
				ul_option.hide();
				if(thisnum===0){
					$(this).parents(".rule-flex").find(".rule-add").remove();
				}else if(thisnum===1){
					var addRule='<div class="rule-add"><span>充值满</span>'
						+'<input type="text" class="input-shorradius-white price" />'
						+'<span>元</span><span class="add2 jiange">无优惠</span></div>'
						+'<input class="input-hidden benefit" value="0"/>';
					
					$(this).parents(".rule-flex").find(".rule-add").remove();
					$(this).parents(".rule-flex").append(addRule);
				}else if(thisnum===2){
					var addRule='<div class="rule-add"><span>充值满</span>'
						+'<input type="text" class="input-shorradius-white price" />'
						+'<span>元</span><span class="jiange">赠送</span>'
						+'<input type="text" class="input-shorradius-white benefit" />'
						+'<span>元</span></div>';
					
					$(this).parents(".rule-flex").find(".rule-add").remove();
					$(this).parents(".rule-flex").append(addRule);
				}else if(thisnum===3){
					var addRule='<div class="rule-add"><span>充值满</span>'
						+'<input type="text" class="input-shorradius-white price" />'
						+'<span>元</span><input type="text" class="input-shorradius-white jiange benefit" /><span>折优惠</span></div>';
					
					$(this).parents(".rule-flex").find(".rule-add").remove();
					$(this).parents(".rule-flex").append(addRule);
				}else if(thisnum===4){
					var addRule='<div class="rule-add"><span>充值满</span>'
						+'<input type="text" class="input-shorradius-white price" />'
						+'<span>元</span><span class="jiange">可消费</span>'
						+'<input type="text" class="input-shorradius-white benefit" />'
						+'<span>次</span></div>';
					
					$(this).parents(".rule-flex").find(".rule-add").remove();
					$(this).parents(".rule-flex").append(addRule);
				}
			});
			li_option.hover(function(){
				$(this).addClass('hover').siblings().removeClass('hover');
			},function(){
				li_option.removeClass('hover');
			});
	}
	function createOptions(index,ul_list){
			//获取被选中的元素并将其值赋值到显示框中
			var options=selects.eq(index).find('option'),
				selected_option=options.filter(':selected'),
				selected_index=selected_option.index(),
				showbox=ul_list.prev();
				showbox.text(selected_option.text());
			//为每个option建立个li并赋值
			for(var n=0;n<options.length;n++){
				var tag_option=$('<li></li>'),//li相当于option
					txt_option=options.eq(n).text();
				tag_option.text(txt_option).css('cursor','pointer').appendTo(ul_list);
				//为被选中的元素添加class为selected
				if(n==selected_index){
					tag_option.attr('class','selected');
				}
			}
	}
});
