package com.siims.szb.vipcard.action.p2b;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.http.HttpRequest;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.StorePersistence;

/**
 * 会员卡管理入口，对会员卡的Type进行选择
 * @author libo
 * 2015-09-14
 */

@Namespace("/vipcard/p2b")
public class VipCardTypeSelectAction extends StrutsAction{
	private static final long serialVersionUID = 1L;
	private String openid;
	List<StoreInfoData> storelist;
	/**
	 * 会员卡管理入口，进入到会员卡Type的选择页面
	 * @author libo
	 * 2015-09-14
	 */
	@Action(value = "selecttype", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/vipcardselectype.ftl")})
	public String VipCardSelect() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//这里最好是能做一个对openid的真伪判断
		if(openid == null || openid.equals(""))
			return ERROR;
		
		storelist = ServiceContext.get(StorePersistence.class).getShopGroupByOpenid(openid);
		
		for(int i = 0; i < storelist.size(); i++) {
			StoreInfoData tmp = storelist.get(i);
			System.out.println("shopId："+ tmp.getId() + " name: " + tmp.getName());
		}
		
		session.setAttribute("vipcardopenid", openid);
		return SUCCESS;
	}

	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public List<StoreInfoData> getStorelist() {
		return storelist;
	}

	public void setStorelist(List<StoreInfoData> storelist) {
		this.storelist = storelist;
	}
	
}
