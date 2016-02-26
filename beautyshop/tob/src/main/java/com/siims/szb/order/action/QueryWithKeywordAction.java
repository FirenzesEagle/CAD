package com.siims.szb.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.order.enums.ResultType;
import com.siims.szb.order.facade.ResultFacade;
import com.siims.szb.order.util.ReplyUtil;

@Namespace("/siims/szb/order")
public class QueryWithKeywordAction extends StrutsAction {

	private String customerId;
	
	private String keyword;
	
	@Action(value="queryWithKeywordAction")
	public void queryWithKeywordAction () {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (customerId == null || customerId.equals("")) {
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "用户Id无效")));
		} else if(keyword == null || keyword.equals("")){
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "关键词不能为空")));
		}else {
			
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
}
