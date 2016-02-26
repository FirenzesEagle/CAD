package com.siims.szb.vipcard.action.p2b;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import ro.isdc.wro.model.resource.support.hash.MD5HashStrategy;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.util.PasswordUtil;

/**
 * 发起收款的Action
 * @author libo <br/>
 * 2015-09-16
 */
@Namespace("/vipcard/p2b")
public class VipCardCollectMoneyAction extends StrutsAction {

	private static final long serialVersionUID = 1056225796608176517L;
	private String typeId;
	private List<ConsumerVipCardData> list;
	private Map<String, VipCardConfigData> map;
	
	/**
	 * 发起收款的第一个步骤，输入账号即手机号
	 * @author libo <br/>
	 * 2015-09-16
	 */
	@Action(value = "vipcardcollectmoney1", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/vipcardcollectmoney1.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	/**
	 * 根据步骤一输入的账号进行查询，根据typeId以及输入的账号
	 * @author libo <br/>
	 * 2015-09-16
	 */
	@Action(value = "vipcardcollectmoney2", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/vipcardcollectmoney2.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String query() {
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
		String phone = request.getParameter("phone");
		if(phone == null || phone.equals(""))
			return ERROR;
		
		//根据typeId和phone查找出所有的会员卡
		setList(ServiceContext.get(ConsumerVipCardService.class).queryConsumerVipCardDataByTypeIdAndSearch(typeId, phone));
		map = new HashMap<String, VipCardConfigData>();
		for(int i = 0; i < list.size(); i++) {
			ConsumerVipCardData data = list.get(i);
			VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(data.getVipCardConfigId());
			map.put(data.getId(), config);
		}
		
		
		return SUCCESS;
	}
	
	/**
	 * 根据会员卡的ID进行消费，并将流水写入到流水表中
	 * @author libo <br/>
	 * 2015-09-16 
	 */
	@Action(value = "vipcardconsume")
	public void consume() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String json = "{\"SUCCESS\":true, \"MSG\":\"消费成功\"}";
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//从POST里面取参数
		String vipCardId = request.getParameter("vipcardid");
	    double money = Double.valueOf(request.getParameter("money"));
		String password = request.getParameter("password");
		int consumetype = Integer.valueOf(request.getParameter("type"));
		
		String comments = request.getParameter("comments");
		String orderId = request.getParameter("orderId");
		
		//参数检查
		if(vipCardId == null || password == null) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		if(consumetype < 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//存在性和密码检查
		ConsumerVipCardData data = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(vipCardId);
		if(data == null) {
			json = "{\"SUCCESS\":false, \"MSG\":\"用户不存在\"}";
			ActionUtil.sendJson(json);
			return;
		}
		if(!data.getPassword().equals(password)) {
			System.out.println("OLD:"+data.getPassword());
			System.out.println("NEW:"+password);
			json = "{\"SUCCESS\":false, \"MSG\":\"密码错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//余额是否足够性检查
		if(data.getLastMoney() < money) {
			json = "{\"SUCCESS\":false, \"MSG\":\"余额不足\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//如果上面的检查均通过，则进行消费
		//先更新会员卡余额
		data.setLastMoney(data.getLastMoney() - money);
		ServiceContext.get(ConsumerVipCardService.class).updateConsumerVipCardData(data);
		//插入流水记录
		ServiceContext.get(VipCardBillService.class).addVipCardBillData(vipCardId, money, data.getLastMoney(), consumetype, comments, orderId, null);
		
		json = "{\"SUCCESS\":true, \"MSG\":\"消费成功\"}";
		ActionUtil.sendJson(json);
		return;
	
	}
	
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public List<ConsumerVipCardData> getList() {
		return list;
	}

	public void setList(List<ConsumerVipCardData> list) {
		this.list = list;
	}

	public Map<String, VipCardConfigData> getMap() {
		return map;
	}

	public void setMap(Map<String, VipCardConfigData> map) {
		this.map = map;
	}
	
	
}
