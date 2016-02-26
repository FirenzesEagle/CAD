package com.siims.szb.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.order.enums.ResultType;
import com.siims.szb.order.facade.ResultFacade;
import com.siims.szb.order.service.OrderService;
import com.siims.szb.order.util.ReplyUtil;

@Namespace("/siims/szb/order")
public class UpdateAction extends StrutsAction{

	private String id;

	private String status;
	
	@Action(value="updateStatus")
	public void updateStatus() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
//			ResultFacade facade = new ResultFacade(ResultType.error, "订单Id无效");
//			ReplyUtil.reply(response, gson.toJson(facade));
			ReplyUtil.reply(response, "error");
		} else if(status==null || status.equals("")) {
//			ResultFacade facade = new ResultFacade(ResultType.error, "传入的订单状态无效");
//			ReplyUtil.reply(response, gson.toJson(facade));
			ReplyUtil.reply(response, "error");
		}else if(!status.equals("create") && !status.equals("payed") && !status.equals("receiveorder") && !status.equals("send") && !status.equals("takedelivery")){
//			ResultFacade facade = new ResultFacade(ResultType.error, "传入的订单状态不符合要求");
//			ReplyUtil.reply(response, gson.toJson(facade));
			ReplyUtil.reply(response, "error");
		}else {
			System.out.println("执行订单更新");
			Boolean result = ServiceContext.get(OrderService.class).updateStatus(id, status);
			if (result) {
//				ResultFacade facade = new ResultFacade(ResultType.success, "订单状态更新成功");
//				ReplyUtil.reply(response, gson.toJson(facade));
			} else {
//				ResultFacade facade = new ResultFacade(ResultType.error, "订单状态更新失败");
//				ReplyUtil.reply(response, gson.toJson(facade));
			}
			ReplyUtil.reply(response, "success");
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
