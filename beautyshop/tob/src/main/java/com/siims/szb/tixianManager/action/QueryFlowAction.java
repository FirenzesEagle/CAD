package com.siims.szb.tixianManager.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.mvc.struts.StrutsAction;

@Namespace("/siims/szb/queryflow")
public class QueryFlowAction extends StrutsAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "toFlow", results = { @Result(name = "success", type = "freemarker", location = "/ui/ftl/tixianManager/turnoverList.ftl") })
	public String toFlow() {

		return SUCCESS;
	}
}
