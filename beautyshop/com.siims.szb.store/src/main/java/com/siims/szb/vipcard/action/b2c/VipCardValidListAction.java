package com.siims.szb.vipcard.action.b2c;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.StorePersistence;
import com.siims.szb.szbldk.service.StoreService;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 查询可领取的会员卡列表
 * @author libo
 * 2015-09-19
 */
@Namespace("/vipcard/b2c")
public class VipCardValidListAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跳转Action到B2C的会员卡可领取列表
	 * 查询由另外一个Action来操作
	 * @author libo
	 * 2015-09-19
	 */
	@Action(value = "validlist", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/get-VIPCard.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	/**
	 * 由前端调用，查询出所有可用的会员卡
	 * 返回json
	 * @author libo
	 * 2015-09-19
	 */
	@Action(value = "validlistmethod")
	public void validList() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//查询
		List<VipCardTypeData> list = ServiceContext.get(VipCardTypeService.class).queryAllValidVipCardTypeData();
		String json = "";
		
		//如果为空
		if(list.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"暂无会员卡\"}";
			ActionUtil.sendJson(json);
			return;
		}
		//生成json字符串
		json = "{\"SUCCESS\":true, \"DATA\":[";
		for(int i = 0; i < list.size(); i++) {
			VipCardTypeData data = list.get(i);
			json += "{\"VIPCARDTYPEID\":\""+data.getId()+"\",";
			if(data.getType() == 1) {
				json += "\"TYPE\":\"储值卡\",";
			}
			else {
				json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			//json += "\"STOREID\":\""+ typeData.getStoreUserId() +"\",";
			
			//调用ldk的接口查询店铺名字和店铺图片
			StoreInfoData stores = ServiceContext.get(StorePersistence.class).getFakeShopInfoData(data.getStoreUserId());
			if(stores == null) {
				json += "\"STORENAME\":\"暂时的名字\",";
				json += "\"STOREIMAGE\":\"#\"}";
			}
			else {
				json += "\"STORENAME\":\""+stores.getName()+"\",";
				json += "\"STOREIMAGE\":\""+stores.getImage()+"\"}";
			}
			
			if(i < list.size() - 1)
				json += ",";
		}
		json += "], \"MSG\": \"查找成功\"}";
		ActionUtil.sendJson(json);
		return;
	}
}
