package com.siims.szb.goods.goods.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import java.lang.reflect.Type;
import java.net.URLDecoder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.goods.goodsdetail.data.DetailInfoData;
import com.siims.szb.goods.goodsdetail.service.GoodsDetailService;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.service.ShelfInfoService;


@Namespace("/siims/szb/goods")
public class AddGoodsAction extends StrutsAction{

	private static final long serialVersionUID = 1L;
	/**
	 * @author liufeng
	 * @param storeId goodsName configName configPrice goodsShowImg goodsDesImg goodsDesDes goodsDistribution goodsType onShelf
	 * @since 2015年8月20日16:24:49
	 */

	private GoodsInfoData goodsInfoData ;
	private CommodityConfigData commodityConfigData ;
	private ShelfInfoData shelfInfoData ;
	//private DetailInfoData detailInfoData;
	
	
	
	
	@Action(value = "addgoods", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/addGoods.ftl")})
	public String addgoods() {
		return SUCCESS;
	}
	
	
	@Action(value = "addGoodsByStoreId")
	public void addGoodsByStoreId() throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
				
		System.out.println("1");
		//根据传进来的商铺id创建一个商品对象
		String storeId = request.getParameter("storeId");
		goodsInfoData = new GoodsInfoData(storeId);
		
		//获取提交的信息
		String goodsName = request.getParameter("goodsName");
		//String goodsName = URLDecoder.decode( request.getParameter("goodsName"));
		
		//String configName = URLDecoder.decode( request.getParameter("configName"));
		String configName = request.getParameter("configName");
		
		String configPrice = request.getParameter("configPrice");
		String goodsShowImg = request.getParameter("goodsShowImg");
		System.out.println(goodsShowImg);
				
		//String goodsDesImg =request.getParameter("goodsDesImg");
		//String goodsDesDes = request.getParameter("goodsDesDes");
		//String goodsDistribution = URLDecoder.decode( request.getParameter("goodsDistribution"));
		String goodsDistribution =  request.getParameter("goodsDistribution");
		String goodsType = request.getParameter("goodsType");
				
		String onShelf = request.getParameter("onShelf");
		
		if(goodsType.equals("sale")) goodsType="现货";
		if(goodsType.equals("book")) goodsType="预定";
		
		
		
		
		//将商品信息数据已对象方式存储
				goodsInfoData.setGoodsName(goodsName);
				goodsInfoData.setGoodsShowImg(goodsShowImg);
				//在数据库中showDes和goodsDesDes存储一样的数据
				//goodsInfoData.setGoodsShowDes(goodsDesDes);
				//设置商品分组：饮料和零食
				goodsInfoData.setGoodsDistribution(goodsDistribution);
				//设置商品类型：现货和预定
				goodsInfoData.setGoodsType(goodsType);
				
				//将商品信息提交至数据库进行存储
				String msg3 = ServiceContext.get(GoodsService.class).addGoodsInfo(goodsInfoData);
				
				//根据数据库中商品的id创建一个商品规格对象
				String goodsId = goodsInfoData.getId();
				commodityConfigData =new CommodityConfigData(goodsId);
				
				//将商品规格数据已对象方式存储
				commodityConfigData.setConfigName(configName);
				//将从前台获取的商品价格字符串转换成double类型
				commodityConfigData.setConfigPrice(Double.parseDouble(configPrice));
				
				//将商品规格提交至数据库进行存储
				String msg2 = ServiceContext.get(CommodityConfigService.class).addCommodityConfig(commodityConfigData);
				
				
				//根据商品的规格Id创建一个商品详细信息对象
				//detailInfoData = new DetailInfoData(commodityConfigData.getId());
				//将商品详细信息已对象方式存储
//				detailInfoData.setGoodsDesImg(goodsDesImg);
//				detailInfoData.setGoodsDesDes(goodsDesDes);
				
				//将商品规格提交至数据库进行存储
				String msg1 ;
				//根据商品的规格Id创建一个货架对象
				String configId = commodityConfigData.getId();
				shelfInfoData = new ShelfInfoData(configId);
				
				//设置商品是否立刻上架
				shelfInfoData.setOnShelf(onShelf);
				//将商品规格提交至数据库进行存储
				String msg4 = ServiceContext.get(ShelfInfoService.class).addShelfInfo(shelfInfoData);
				
		
		
		
		
		
		
		
		String json = request.getParameter("DATA");
		System.out.println(json);
		if(request.getParameter("DATA") == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"无数据\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		int i = 0;//有多少个Data
		int j = 0;//每个评论有多少个pic
		
		Type type = new TypeToken<C>() {
		}.getType();
		System.out.println(11);
		Gson gson = new Gson();
		System.out.println(22);
		C c = gson.fromJson(json, type);
		System.out.println(33);
		System.out.println(c.Data.size());
		while(i < c.Data.size())
		{
			
			System.out.println(44);
			//获得描述信息和
			String goodsDesDes = c.Data.listIterator(i).next().getGoodDes();
			//String goodsDesDes = URLDecoder.decode( c.Data.listIterator(i).next().getGoodDes());
			String goodsDesImg = c.Data.listIterator(i).next().getGoodDesPicUrl();
			System.out.println(goodsDesDes);
			System.out.println(goodsDesImg);
			System.out.println(55);
			System.out.println(commodityConfigData.getId());
			DetailInfoData detailInfoData = new DetailInfoData(commodityConfigData.getId());
			System.out.println(66);
			detailInfoData.setGoodsDesImg(goodsDesImg);
			detailInfoData.setGoodsDesDes(goodsDesDes);
			System.out.println(77);
			ServiceContext.get(GoodsDetailService.class).addDetailInfo(detailInfoData);
			System.out.println(88);
			i++;
		}
		
		
		
		
		
		String msg = "  "+msg2+"  "+msg3+"  "+msg4;
		
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + msg + "\"}";
		ActionUtil.sendJson(responseResult);
	}


	public GoodsInfoData getGoodsInfoData() {
		return goodsInfoData;
	}


	public void setGoodsInfoData(GoodsInfoData goodsInfoData) {
		this.goodsInfoData = goodsInfoData;
	}


	public CommodityConfigData getCommodityConfigData() {
		return commodityConfigData;
	}


	public void setCommodityConfigData(CommodityConfigData commodityConfigData) {
		this.commodityConfigData = commodityConfigData;
	}


	public ShelfInfoData getShelfInfoData() {
		return shelfInfoData;
	}


	public void setShelfInfoData(ShelfInfoData shelfInfoData) {
		this.shelfInfoData = shelfInfoData;
	}


	
	/**
	 * 单个商品详细表
	 * @author liufeng
	 *
	 */
	class B {
		private String detailId;
		private String goodDes;
		private String goodDesPicUrl;
		//private String configId;
		public String getDetailId() {
			return detailId;
		}
		public void setDetailId(String detailId) {
			this.detailId = detailId;
		}
		public String getGoodDes() {
			return goodDes;
		}
		public void setGoodDes(String goodDes) {
			this.goodDes = goodDes;
		}
		public String getGoodDesPicUrl() {
			return goodDesPicUrl;
		}
		public void setGoodDesPicUrl(String goodDesPicUrl) {
			this.goodDesPicUrl = goodDesPicUrl;
		}
//		public String getConfigId() {
//			return configId;
//		}
//		public void setConfigId(String configId) {
//			this.configId = configId;
//		}
		
		
	}
	/**
	 * 商品详细表集合
	 * @author liufeng
	 *
	 */
	class C {
		private List<B> Data;

		public List<B> getData() {
			return Data;
		}
		public void setData(List<B> data) {
			Data = data;
		}
	}
	
	
	
	
	
}







