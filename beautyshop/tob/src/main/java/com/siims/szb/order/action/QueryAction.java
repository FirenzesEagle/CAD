package com.siims.szb.order.action;

import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.order.enums.ResultType;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.facade.MapFacade;
import com.siims.szb.order.facade.ResultFacade;
import com.siims.szb.order.service.OrderGoodsService;
import com.siims.szb.order.service.OrderService;
import com.siims.szb.order.util.ReplyUtil;

@Namespace("/siims/szb/order")
public class QueryAction extends StrutsAction {
	
	private String id;
	
	private String status;
	
	@Action(value="querybasic")
	public void querybasic() {
		System.out.println("go here");
		if (id == null) {
			System.out.println("id is null");
		} else if(id.equals("")){
			System.out.println("id is empty");
		}else {
			System.out.println("id is "+id);
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
			ResultFacade facade = new ResultFacade(ResultType.error, "订单的Id无效");
			ReplyUtil.reply(response, gson.toJson(facade));
		} else {
			MapFacade facade = ServiceContext.get(OrderService.class).querybasic(id);
			ReplyUtil.reply(response, gson.toJson(facade));
		}
	}
	
	@Action(value="querybasicByCustomerId")
	public void querybasicByCustomerId() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
			ResultFacade facade = new ResultFacade(ResultType.error, "用户Id无效");
			ReplyUtil.reply(response, gson.toJson(facade));
		} else if(status == null || status.equals("")){
			ListFacade facade = ServiceContext.get(OrderService.class).querybasicByCustomerId(id, "");
			ReplyUtil.reply(response, gson.toJson(facade));
		}else {
			ListFacade facade = ServiceContext.get(OrderService.class).querybasicByCustomerId(id, status);
			ReplyUtil.reply(response, gson.toJson(facade));
		}
	}
	

	@Action(value="queryOrderGoods")
	public void queryOrderGoods(){
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
			ResultFacade facade = new ResultFacade(ResultType.error, "传入的中间表Id无效");
			ReplyUtil.reply(response, gson.toJson(facade));
		}else {
			ListFacade facade = ServiceContext.get(OrderGoodsService.class).queryOrderGoods(id);
			ReplyUtil.reply(response, gson.toJson(facade));
		}
	}
	
	@Action(value="queryWithOrderids")
	public void queryByShopId() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "传入的店铺Id无效")));
		}else if(status == null){ 
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "传入的状态不能为空")));
		}else if(status.equals("0") || status.equals("1") || status.equals("2") || status.equals("3") || status.equals("4") || status.equals("5")) {
			Set<String> orderIds = ServiceContext.get(OrderGoodsService.class).queryByShopId(id);
			ReplyUtil.reply(response, gson.toJson(ServiceContext.get(OrderService.class).queryWithOrderids(orderIds, status)));
		}else {
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "传入的状态参数无效")));
		}
	}
	
	@Action(value="queryOrderGoodsByOrderId")
	public void queryOrderGoodsByOrderId() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "传入的订单id无效")));
		}else {
			ReplyUtil.reply(response, gson.toJson(ServiceContext.get(OrderGoodsService.class).queryOrderGoodsByOrderId(id)));
		}
	}
	
	@Action(value="queryAllInfoByShopId")
	public void queryAllInfoByShopId() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "传入的店家id无效")));
		}else {
			ReplyUtil.reply(response, gson.toJson(ServiceContext.get(OrderGoodsService.class).queryAllInfoByShopId(id)));
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}




















