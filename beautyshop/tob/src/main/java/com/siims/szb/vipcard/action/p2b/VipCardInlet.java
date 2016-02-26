package com.siims.szb.vipcard.action.p2b;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.mvc.struts.StrutsAction;

@Namespace("/vipcard/p2b")
public class VipCardInlet extends StrutsAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311083457851410347L;

	@Action(value = "vipcardinlet", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/vipcardinlet.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String vipsDetail() {
		return SUCCESS;
	}
}
