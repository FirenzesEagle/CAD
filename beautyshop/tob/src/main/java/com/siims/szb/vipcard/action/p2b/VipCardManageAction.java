package com.siims.szb.vipcard.action.p2b;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Results;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 储值卡管理入口，跳转的页面是储值卡规则概览页面
 * @author libo
 * 2015-09-14
 */

@Namespace("/vipcard/p2b")
public class VipCardManageAction extends StrutsAction{

	private static final long serialVersionUID = 1L;
	private int lenOfList;
	private int type;
	private List<VipCardConfigData> list;
	private List<ConsumerVipCardData> list2;
	private String typeId;
	/**
	 * 会员卡的管理入口，目前只有储值卡，根据储值卡的ID查询出所有的规格，并准备显示
	 * @author libo
	 * 2015-09-14
	 * @return
	 */
	@Action(value = "vipcardmanage", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/vipcardmanage.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String vipcardManage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String shopid = request.getParameter("shopid");
		if(shopid == null || shopid.equals(""))
			return ERROR;
		session.setAttribute("shopid", shopid);
		
		type = Integer.valueOf(request.getParameter("type"));
		System.out.println("shopid 为："+shopid+" type为："+type);
		
		
		//根据商家的ID和Type查询出商家所拥有的config实体
		VipCardTypeData data = ServiceContext.get(VipCardTypeService.class).getVipCardTypeDataByStoreUserIdAndType(shopid, type);
		list = ServiceContext.get(VipCardConfigService.class).getConfigDataByTypeId(data.getId());
		if(list != null)
			lenOfList = list.size();
		else
			lenOfList = 0;
		
		//根据商家的typeId查询该typeId下领取的会员卡数量
		list2 = ServiceContext.get(ConsumerVipCardService.class).queryConsumerVipCardDataByTypeId(data.getId());
		typeId = data.getId();
		return SUCCESS;
	}

	public int getLenOfList() {
		return lenOfList;
	}

	public void setLenOfList(int lenOfList) {
		this.lenOfList = lenOfList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<VipCardConfigData> getList() {
		return list;
	}

	public void setList(List<VipCardConfigData> list) {
		this.list = list;
	}

	public List<ConsumerVipCardData> getList2() {
		return list2;
	}

	public void setList2(List<ConsumerVipCardData> list2) {
		this.list2 = list2;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	
	

}
