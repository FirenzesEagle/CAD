package com.siims.szb.szbldk.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;
@Namespace("/siims/szb/StoreInfoAction")
public class StoreInfoAction extends StrutsAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private static final String SHOPPING_TROLLY = "shoppingIndex";
private static final String SHOP_CART = "shop-cart";
private static final String BIND = "bind";
private static final String LOGINPC = "loginPC";
private static final String ACCOUNT_MANAGE = "account_manage";

	
	@Action(value = "shopIndex", results = {@Result(name = "shoppingIndex", type = "freemarker", location = "/ui/ftl/shopIndex.ftl")})
	public String shoppingTrolly() {
		return SHOPPING_TROLLY;
	}
	@Action(value = "shop-cart", results = {@Result(name = "shop-cart", type = "freemarker", location = "/ui/ftl/shop-cart.ftl")})
	public String shopCart() {
		return SHOP_CART;
	}
	@Action(value = "bind", results = {@Result(name = "bind", type = "freemarker", location = "/ui/ftl/bind.ftl")})
	public String bind() {
		return BIND;
	}
	@Action(value = "loginpc", results = {@Result(name = "loginPC", type = "freemarker", location = "/ui/ftl/loginpc.ftl")})
	public String loginpc() {
		System.out.println("**********用户登陆页面***************");
		return LOGINPC;
	}
	@Action(value = "accountManage", results = {@Result(name = "bind", type = "freemarker", location = "/ui/ftl/accountManage.ftl")})
	public String accountManage() {
		return ACCOUNT_MANAGE;
	}
	/**
	 * @author storm
	 * 用户注册
	 */
	@Action(value = "registerStore")
	public void reg(){
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		//获取提交的信息
		String openid = request.getParameter("openid");
		String username= request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String adress = request.getParameter("adress");
		String image = request.getParameter("image");
		
		String area = request.getParameter("area");
		String person_charge = request.getParameter("person_charge");
		String tel = request.getParameter("tel");
		String email =  request.getParameter("email");
		String districtID = request.getParameter("districtID");
		String zing = request.getParameter("zing");
		
		
		
		StoreInfoData storeInfoData = new StoreInfoData();
		storeInfoData.setOpenid(openid);
		storeInfoData.setUsername(username);
		storeInfoData.setPassword(password);
		storeInfoData.setName(name);
		storeInfoData.setDesc(desc);
		storeInfoData.setAddress(adress);
		storeInfoData.setImage(image);
		storeInfoData.setArea(area);
		storeInfoData.setPerson(person_charge);
		storeInfoData.setTel(tel);
		storeInfoData.setEmail(email);
		storeInfoData.setDistrictID(districtID);
		storeInfoData.setZing(zing);
		storeInfoData.setCreatetime(new Date());
		
		String store_id = ServiceContext.get(StoreService.class).addStoreInfo(storeInfoData);
		
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + store_id + "\"}";
		ActionUtil.sendJson(responseResult);
		
		
	
		
	}
	
	/**
	 * @author storm
	 * 用户信息修改
	 */
	@Action(value = "modifyStoreInfo")
	public void modifyStoreInfo(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
				
		//获取提交的信息
		String shop_id = request.getParameter("shop_id");
		
		String which = request.getParameter("which");
		int intWhich = Integer.parseInt(which);
		String infoTemp = request.getParameter("info");
		String info = URLDecoder.decode(infoTemp);
		System.out.println("***************shop_id="+shop_id+" which="+which+" info="+info+"***************");
		if (which == null || info == null || intWhich <0) {
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"参数错误\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
				
//				String name = request.getParameter("name");
//				String desc = request.getParameter("desc");
//				String adress = request.getParameter("adress");
//				String image = request.getParameter("image");
//				
//				String area = request.getParameter("area");
//				String person_charge = request.getParameter("person_charge");
//				String tel = request.getParameter("tel");
//				String email =  request.getParameter("email");
//				String districtID = request.getParameter("districtID");
//				String zing = request.getParameter("zing");
//				
//				
//				StoreInfoData storeInfoData = new StoreInfoData();
//				storeInfoData.setId(store_id);
//				storeInfoData.setName(name);
//				storeInfoData.setDesc(desc);
//				storeInfoData.setAddress(adress);
//				storeInfoData.setImage(image);
//				storeInfoData.setArea(area);
//				storeInfoData.setPerson(person_charge);
//				storeInfoData.setTel(tel);
//				storeInfoData.setEmail(email);
//				storeInfoData.setDistrictID(districtID);
//				storeInfoData.setZing(zing);
//				storeInfoData.setCreatetime(new Date());
				
				StoreInfoData data = ServiceContext.get(StoreService.class).editStoreInfo(shop_id,intWhich,info);
				
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + JSON.toJSONString(data) + "}";
				ActionUtil.sendJson(responseResult);
	}
	/**
	 * @author storm
	 * 用户登陆，微信端登陆
	 */
	@Action(value="storeLogin")
	public void storeLogin(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"\"}";
		
		//获取提交的信息
		String openid = request.getParameter("openid");
		String username= request.getParameter("username");
		String password = request.getParameter("password");
		if(openid == null || username == null || password == null){
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"参数错误\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		StoreInfoData storeInfoData = new StoreInfoData();
		storeInfoData.setOpenid(openid);
		storeInfoData.setUsername(username);
		storeInfoData.setPassword(password);
		
		StoreInfoData res = ServiceContext.get(StoreService.class).storeLogin(storeInfoData);
		if(res == null){
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"账号和密码不匹配或者账号不存在\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		res.setPassword("*********");
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + JSON.toJSONString(res) + "}";
			System.out.println("**********"+responseResult+"**********");
			ActionUtil.sendJson(responseResult);
		
			
	}
	/**
	 * pc端登陆使用此action
	 */
	@Action(value="storeLoginpc")
	public void storeLoginPC(){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"\"}";
		
		//获取提交的信息
	
		String username= request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == null || password == null){
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"缺少必要的参数\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		StoreInfoData storeInfoData = new StoreInfoData();
	
		storeInfoData.setUsername(username);
		storeInfoData.setPassword(password);
		
		StoreInfoData res = ServiceContext.get(StoreService.class).storeLoginPC(storeInfoData);
			if(res == null){
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"账号和密码不匹配或者账号不存在\"}";
				ActionUtil.sendJson(responseResult);
				return;
			}
			res.setPassword("*********");
			String json = JSON.toJSONString(res);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":"+json+"}";
			ActionUtil.sendJson(responseResult);
	}
	
	/**获取商圈下所有的网店的信息通过商圈ID
	 * @author ling
	 * @since 2015年9月7日10:09:10
	 */	
	@Action(value = "getallstore")
	public void GetAllStoreByDistrictID() throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
				
		//获得商圈的ID
		String id = request.getParameter("district_id");
				
		//判断ID是否为空
		if(id == null || id.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No DistrictID\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		//获取所有的网店实体
		List<StoreInfoData> list = ServiceContext.get(StoreService.class).getAllStoreByDistrictId(id);
		
		if(list == null || list.size() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Stores\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}else{
			
			String res = JSON.toJSONString(list);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" +res +",\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
	}
	
	@Action(value = "getshopinfo")
	public void GetStore() throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//获得网店的ID
		String id = request.getParameter("shop_id");
		//判断ID是否为空
		if(id == null || id.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No shopID\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		//获得网店实体
		StoreInfoData shopInfo = ServiceContext.get(StoreService.class).getStoreInfoByStoreId(id);
		//对实体进行非空判断
		if(shopInfo == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"无法获得商铺信息\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}else{
			Gson gson = new Gson();
			msg = gson.toJson(shopInfo);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + msg + "}";
			ActionUtil.sendJson(responseResult);
			return;
		}
	}
	
	@Action(value = "getshopidbyopenid")
	public void getShopidByOpenid() throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//获得网店的ID
		String id = request.getParameter("openid");
		//判断ID是否为空
		if(id == null || id.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No openid\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		//获得网店实体
		List<StoreInfoData> shopInfoGroup = ServiceContext.get(StoreService.class).getShopGroupByOpenid(id);
		//对实体进行非空判断
		if(shopInfoGroup == null || shopInfoGroup.size() <= 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"openid没有对应的商铺\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}else{
			Gson gson = new Gson();
			msg = gson.toJson(shopInfoGroup);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + msg + "}";
			ActionUtil.sendJson(responseResult);
			return;
		}
	}
	
	@Action(value = "confirmauth")
    public void confirmAuth(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"\"}";
		
		//获取提交的信息
		String openid = request.getParameter("openid");
		String password = request.getParameter("password");
		if(openid == null || password == null){
			responseResult = "{\"SUCCESS\":\"false\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		StoreInfoData storeInfoData = new StoreInfoData();
		storeInfoData.setOpenid(openid);
		storeInfoData.setPassword(password);
		
		StoreInfoData res = ServiceContext.get(StoreService.class).confirmAuth(storeInfoData);
		if(res == null)
		responseResult = "{\"SUCCESS\":\"false\" }";
		else
			responseResult = "{\"SUCCESS\":\"true\" }";
		System.out.println("**********"+responseResult+"**********");
		ActionUtil.sendJson(responseResult);
		
			
	}
	@Action(value = "getShopGroupByOpenid")
    public void getShopGroupByOpenid(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":false,\"ERROMSG\":\"\"}";
		
		//获取提交的信息
		String openid = request.getParameter("openid");
		if(openid == null){
			responseResult =  "{\"SUCCESS\":false,\"ERROMSG\":\"openid不能为空\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		List<StoreInfoData> res = ServiceContext.get(StoreService.class).getShopGroupByOpenid(openid);
		if(res == null)
		responseResult =  "{\"SUCCESS\":false,\"ERROMSG\":\"列表为空\"}";
		else{
			Gson gson = new Gson();
			String msg = gson.toJson(res);
			responseResult = "{\"SUCCESS\":true,\"DATA\":" + msg + "}";
		}
			
		System.out.println("**********"+responseResult+"**********");
		ActionUtil.sendJson(responseResult);
		
			
	}

}
