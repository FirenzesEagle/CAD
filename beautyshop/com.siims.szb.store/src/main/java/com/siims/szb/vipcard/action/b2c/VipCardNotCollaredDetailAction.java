package com.siims.szb.vipcard.action.b2c;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.StorePersistence;
import com.siims.szb.szbldk.service.StoreService;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 未领取的会员卡点击时的会员卡详情
 * @author libo
 * 2015-09-16
 */
@Namespace("/vipcard/b2c")
public class VipCardNotCollaredDetailAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跳转Action到B2C的我的的会员卡
	 * 查询由另外一个Action来操作
	 * @author libo
	 * 2015-09-19
	 */
	@Action(value = "notcollareddetail", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/VIPCard-detail-notget.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	/**
	 * 根据typeId显示这张会员卡的详情
	 */
	@Action(value = "notcollareddetailmethod") 
	public void notCollaredDetail(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpServletRequest request = ServletActionContext.getRequest();
		String json;
		System.out.println("Fuck you");
		//获取参数并检查
		String typeId = request.getParameter("typeId");
		if(typeId == null || typeId.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//根据typeId查询出typeCard
		VipCardTypeData typeData = ServiceContext.get(VipCardTypeService.class).queryVipCardTypeDataById(typeId);
		if(typeData == null) {
			json = "{\"SUCCESS\":false, \"MSG\":\"无此会员卡\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//填充json
		json = "{\"SUCCESS\":true, \"TYPEID\":\""+typeData.getId()+"\",";
		if(typeData.getType() == 1) {
			json += "\"TYPE\":\"储值卡\",";
		}
		else {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		//json += "\"STOREID\":\""+ typeData.getStoreUserId() +"\",";
		
		//调用ldk的接口查询店铺名字和店铺图片
		StoreInfoData stores = ServiceContext.get(StorePersistence.class).getFakeShopInfoData(typeData.getStoreUserId());
		if(stores == null) {
			json += "\"STOREID\":\"NULL\",";
			json += "\"STORENAME\":\"NULL\",";
			json += "\"STOREIMAGE\":\"#\",";
			json += "\"CONFIGDATA\":[";
		}
		else {
			json += "\"STOREID\":\""+stores.getId()+"\",";
			json += "\"STORENAME\":\""+stores.getName()+"\",";
			json += "\"STOREIMAGE\":\""+stores.getImage()+"\",";
			json += "\"CONFIGDATA\":[";
		}
		
		List<VipCardConfigData> configDatas = ServiceContext.get(VipCardConfigService.class).getConfigDataByTypeId(typeId);
		for(int i = 0; i < configDatas.size(); i++) {
			VipCardConfigData config = configDatas.get(i);
			String context = "充值满"+config.getPrice()+",";
			if(config.getType() == 0) {
				context += "无优惠";
			}
			else if(config.getType() == 1) {
				context += config.getBenefit()+"折优惠";
			}
			else if(config.getType() == 2) {
				context += "返现"+config.getBenefit()+"元";
			} 
			else{
				context += "消费"+(int)config.getBenefit().doubleValue()+"次";
			}
			if(config.getIsdelete() == 1) {
				context += "(已停售)";
			}
			json += "{\"CONTEXT\":\""+context+"\"}";
			if(i < configDatas.size() - 1)
				json += ",";
		}
		json += "], \"MSG\": \"查找成功\"}";
		ActionUtil.sendJson(json);
		return;
	}
}
