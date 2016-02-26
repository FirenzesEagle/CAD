package com.siims.szb.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.mvc.struts.StrutsAction;

@Namespace("/siims/szb/order")
public class OrderSuccessAction extends StrutsAction{

	@Action(value = "golsuccess", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/book-success.ftl")})
	public String tochangebespeakstate() {
		return SUCCESS;
	}
	
}
