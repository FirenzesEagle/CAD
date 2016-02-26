package com.siims.szb.vipcard.action.p2b;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;

/**
 * 已领取会员卡的会员的列表
 * @author libo <br/>
 * 2015-09-15
 */
@Namespace("/vipcard/p2b")
public class VipCardVipsListAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	private List<ConsumerVipCardData> list;
	
	/**
	 * 进入到已领取会员卡会员列表
	 * @author libo <br/>
	 * 2015-09-15
	 * @return
	 */
	@Action(value = "vipcardvipslist", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/vipcardviplist.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String vipCardVipsList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//权限检测
		String shopid = (String) session.getAttribute("shopid");
		if(shopid == null || shopid.equals(""))
			return ERROR;
		
		//从GET里面去typeId
		String typeId = request.getParameter("typeId");
		if(typeId == null || typeId.equals(""))
			return ERROR;
		
		//从GET里面搜寻search中的关键字，如果是空，或者是空白，则全部搜索，否则匹配搜索
		String search = request.getParameter("search");
		if(search == null) //如果为空，则置为空白
			search = "";
		
		//根据typeId来查询所有已领取的会员卡的信息
		if(search == "")
			setList(ServiceContext.get(ConsumerVipCardService.class).queryConsumerVipCardDataByTypeId(typeId));
		else
			list = ServiceContext.get(ConsumerVipCardService.class).queryConsumerVipCardDataByTypeIdAndSearch(typeId, search);
		
		return SUCCESS;
	}

	public List<ConsumerVipCardData> getList() {
		return list;
	}

	public void setList(List<ConsumerVipCardData> list) {
		this.list = list;
	}
}
