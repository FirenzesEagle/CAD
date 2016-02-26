package com.siims.szb.szbldk.action;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.ShoppingTrollyData;
import com.siims.szb.szbldk.service.ShoppingTrollyService;
import com.siims.szb.szbldk.utils.SzbConstant;

import java.lang.reflect.Type;
/**
 * @author storm 购物车action
 */
@Namespace("/siims/szb/ShoppingTrollyAction")
public class ShoppingTrollyAction extends StrutsAction {

	public static final String shopConfirm = "orderConfirm";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "togetAllShoppingTrollyDataByOpenid", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/shop-cart.ftl")})
	public String servicelist() {
		return SUCCESS;
	}
	@Action(value = "orderConfirm", results = {@Result(name = "orderConfirm", type = "freemarker", location = "/ui/ftl/order-confirm.ftl")})
	public String shopConfirm() {
		return shopConfirm;
	}
	
	/**
	 * 添加或者修改购物车 添加之前没有添加过的新商品，或者修改已经添加商品的数量 返回添加或者修改商品的trolly_id，暨此条目的唯一标识
	 */
	@Action(value = "AddShoppingTrolly")
	public void AddShoppingTrolly() {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		
		String json = request.getParameter("DATA");
		System.out.println(json);
		if(json == null || json.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"无数据\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		System.out.println("step1");
		Type type = new TypeToken<AddShoppingTrollyData>() {
		}.getType();
		
		Gson gson = new Gson();
		AddShoppingTrollyData ad = gson.fromJson(json, type);
		
		System.out.println("step1**&^%");
		if(ad == null){
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"解析数据失败\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		System.out.println("step2");
		if(ad != null)
		{
			System.out.println("shop_id="+ad.getShop_id()+" item_count="+ad.getItem_count()+" item_price="+ad.getItem_price());
		}
//		//获取提交的信息
//		//String user_id = request.getParameter("user_id");
//		String shop_id = request.getParameter("shop_id");
//		String item_id = request.getParameter("item_id");
//		String str_item_count = request.getParameter("item_count");
//		String str_item_price = request.getParameter("item_price");
//		float item_price = Float.parseFloat(str_item_price);
//		int item_count = Integer.parseInt(str_item_count);
		
		
		
		
		/**
		 * 生成实体对象
		 */
		ShoppingTrollyData data = new ShoppingTrollyData();
		data.setCreate_time(new Date());
		data.setItem_count(ad.getItem_count());
		data.setItem_id(ad.getItem_id());
		data.setShop_id(ad.getShop_id());
		data.setItem_price(ad.getItem_price());
		data.setOpenid(ad.getOpenid());
//		data.setItem_name(ad.getItem_name());
//		data.setShop_name(ad.getShop_name());
		data.setStatus(SzbConstant.SHOPPING_TROLLY_ORDINARY);
		//data.setUser_id(user_id);
		
		//将信息提交至数据库进行存储,并获得自动生成的主键id
		ShoppingTrollyData dataUpdated = ServiceContext.get(ShoppingTrollyService.class).addShoppingTrolly(data);
		
		String msg= gson.toJson(dataUpdated);
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + msg + "}";
		ActionUtil.sendJson(responseResult);
		
	}

	@Action(value = "deleteShoppingTrolly")
	public void deleteShoppingTrolly() {
		
		response.setHeader("Access-Control-Allow-Origin", "*");

		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";

		// 获取提交的信息
		String openid = request.getParameter("openid");
		String shop_id = request.getParameter("shop_id");
		String	item_id = request.getParameter("item_id");

		System.out.println("******openid = "+openid+"  shop_id="+shop_id+" item_id="+item_id);
		ShoppingTrollyData sd = new ShoppingTrollyData();
		sd.setOpenid(openid);
		sd.setItem_id(item_id);
		sd.setShop_id(shop_id);
		
		// 将信息提交至数据库进行存储,并获得自动生成的主键id
		int res = ServiceContext.get(ShoppingTrollyService.class)
				.deleteShoppingTrollyData(sd);

		if (res == SzbConstant.SHOPPING_TROLLY_SUCCESS) {
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"成功删除购物车条目\"}";
		} else {
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"无法删除此购物车条目，参数不正确\"}";
		}

		ActionUtil.sendJson(responseResult);

	}

	@Action(value = "getAllShoppingTrollyDataByOpenid")
	public void getAllShoppingTrollyDataByOpenid() {
		response.setHeader("Access-Control-Allow-Origin", "*");

		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";

		// 获取提交的信息
		String openid = request.getParameter("openid");
		if(openid == null){
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"空的\"}";
		}
		System.out.println("openid=" + openid);

		// 将信息提交至数据库进行存储,并获得自动生成的主键id
		List<List<ShoppingTrollyData>> list = ServiceContext.get(
				ShoppingTrollyService.class).getAllShoppingTrollyDataByOpenid(openid);

		String listJsonString = JSON.toJSONString(list);
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + listJsonString
				+ "}";

		ActionUtil.sendJson(responseResult);

	}

	@Action(value = "getShoppingTrollySum")
	public void getShoppingTrollySum() {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";

		// 获取提交的信息
		String user_id = request.getParameter("user_id");

		System.out.println("getShoppingTrollySum user_id=" + user_id);

		float sum = ServiceContext.get(ShoppingTrollyService.class)
				.getShoppingTrollySum(user_id);

		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + sum + "\"}";

		ActionUtil.sendJson(responseResult);
	}

	@Action(value = "emptyShoppingTrolly")
	public void emptyShoppingTrolly() {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";

		// 获取提交的信息
		String openid = request.getParameter("openid");

		System.out.println("emptyShoppingTrolly openid=" + openid);

		int res = ServiceContext.get(ShoppingTrollyService.class)
				.emptyShoppingTrolly(openid);

		if (res == SzbConstant.SHOPPING_TROLLY_SUCCESS) {
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + res + "\"}";
		} else {
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"" + res + "\"}";
		}

		ActionUtil.sendJson(responseResult);
	}

	@Action(value = "getShoppingTrollyByStore")
	public void getShoppingTrollyByStore() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";

		// 获取提交的信息
		String openid = request.getParameter("openid");
		String shop_id = request.getParameter("shop_id");

		System.out.println("getShoppingTrollyByStore user_id=" + openid
				+ " shop_id=" + shop_id);

		List<ShoppingTrollyData> resList = ServiceContext.get(
				ShoppingTrollyService.class).getShoppingTrollyByStore(openid,
				shop_id);

		Gson gson = new Gson();
		String msg = gson.toJson(resList);
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + msg + "}";
		ActionUtil.sendJson(responseResult);
	}

	class AddShoppingTrollyData {

		/**
		 * 商铺ID
		 */
		private String shop_id;
//		/**
//		 *  商铺名称
//		 */
//		private String shop_name;
		/**
		 * 商品ID
		 */
		private String item_id;
//		/**
//		 * 商品名称
//		 */
//		private String item_name;
//		/**
//		 * 商品图片地址
//		 */
//		private String item_img_url;
//		/**
//		 * 商品规格
//		 */
//		private String item_spec;

		/**
		 * 用户ID
		 */
		private String user_id;
		/**
		 * 商品数量
		 */
		private Integer item_count;
		
		/**
		 * 商品单价
		 */
		private float item_price;
		/**
		 * openid
		 */
		private String openid;

		public String getShop_id() {
			return shop_id;
		}

		public void setShop_id(String shop_id) {
			this.shop_id = shop_id;
		}

		public String getItem_id() {
			return item_id;
		}

		public void setItem_id(String item_id) {
			this.item_id = item_id;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public Integer getItem_count() {
			return item_count;
		}

		public void setItem_count(Integer item_count) {
			this.item_count = item_count;
		}

		public float getItem_price() {
			return item_price;
		}

		public void setItem_price(float item_price) {
			this.item_price = item_price;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}


		
		
	}
}
