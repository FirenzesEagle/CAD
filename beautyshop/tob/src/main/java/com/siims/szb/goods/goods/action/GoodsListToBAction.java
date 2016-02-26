package com.siims.szb.goods.goods.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
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
public class GoodsListToBAction extends StrutsAction{

	/**
	 * @author liufeng
	 * 
	 * @since 2015.9.7
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String storeId;
	private String isOnShelf;
	private String type;
	private String configId;
	
	
	@Action(value = "test")
	public void test() throws IOException{
		DetailInfoData detailInfoData = new DetailInfoData("12345678");
		detailInfoData.setGoodsDesImg("12345678");
		detailInfoData.setGoodsDesDes("12345678");
		System.out.println(77);
		ServiceContext.get(GoodsDetailService.class).addDetailInfo(detailInfoData);
	}
	
	//展示configId下的所有数据
	@Action(value = "goodsShow")
	public void goodsShow() throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("1233445566677");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		configId = request.getParameter("goodsId");
		CommodityConfigData configData = ServiceContext.get(CommodityConfigService.class).getCommodityConfig(configId);
		
		List<DetailInfoData> listDetail = ServiceContext.get(GoodsDetailService.class).searchDetailInfoByGoodsConfigId(configData.getId());
		//System.out.println(listDetail);
		//System.out.println(listDetail.get(0).getGoodsDesDes());
		String tmp="" ;
		for(int i=0;i<listDetail.size();i++){
			
			//String fileterDetail = listDetail.get(i).getGoodsDesImg();
			listDetail.get(i).getGoodsDesImg().replace('\"',' ');
			listDetail.get(i).getGoodsDesImg().replace('\\', ' ');
			
		}
		
		for(int i=0;i<listDetail.size();i++){
			tmp +="{"+"\""+"detailid"+"\""+":"+"\""+listDetail.get(i).getId()+"\""+","+"\""+"url"+"\""+":"+"\""+listDetail.get(i).getGoodsDesImg()+"\""+","+"\""+"des"+"\""+":"+"\""+listDetail.get(i).getGoodsDesDes()+"\""+"}"+",";
		}
		//System.out.println(listDetail.size());
		if(tmp.length()>0)
			tmp = tmp.substring(0, tmp.length()-1);
		//System.out.println(tmp);
		String detailContent = "["+tmp+"]";
		//System.out.println(detailContent);
		String goodsId = configData.getGoodsId();
		GoodsInfoData goodsInfo = ServiceContext.get(GoodsService.class).searchGoodsInfo(goodsId);
		
		List<ShelfInfoData> listShelf = ServiceContext.get(ShelfInfoService.class).searchShelfInfoByConfigId(configId);
		
		//System.out.println(listShelf.get(0).getShelfTime());
		
		String temp = "{"+"\""+"goodsId"+"\""+":"+"\""+configId+"\""+","
				+"\""+"goodsShowImg"+"\""+":"+"\""+goodsInfo.getGoodsShowImg()+"\""+","
				+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
				+"\""+"goodsName"+"\""+":"+"\""+goodsInfo.getGoodsName()+"\""+","
				+"\""+"configName"+"\""+":"+"\""+configData.getConfigName()+"\""+","
				+"\""+"configPrice"+"\""+":"+"\""+configData.getConfigPrice()+"\""+","
				+"\""+"goodsDistribution"+"\""+":"+"\""+goodsInfo.getGoodsDistribution()+"\""+","
				+"\""+"detailInfoData"+"\""+":"+detailContent+","
				//+"\""+"detailInfoData"+"\""+":"+"\""+listDetail+"\""+","
				+"\""+"goodsType"+"\""+":"+"\""+goodsInfo.getGoodsType()+"\""+","
				+"\""+"onShelf"+"\""+":"+"\""+listShelf.get(0).getOnShelf()+"\""+"}";
		msg += temp+",";
		
		
		if(msg.length()==0){
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"\"}";
		}
		else responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "],\"ERROMSG\":\"\"}";
		ActionUtil.sendJson(responseResult);
		System.out.println(responseResult+"=====================");
		
	}
	
	@Action(value = "goodslisttob", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/goodsManage.ftl")})
	public String goodslisttob() {
		return SUCCESS;
	}
	
	
	//展示出storeId下的所有商品
	@Action(value = "goodsListToB")
	public void goodsListToB() throws IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//根据商铺id获取该商铺的所有的商品
		List<GoodsInfoData> listGoods;
		List<CommodityConfigData> listConfig;
		List<ShelfInfoData> listShelf;
		ShelfInfoData shelfInfoData;
		
		storeId = request.getParameter("storeId");
		isOnShelf = request.getParameter("isOnShelf");
		type =request.getParameter("type");
		
		if(type.equals("sale")) type="现货";
		if(type.equals("book")) type="预定";
		
		listGoods = ServiceContext.get(GoodsService.class).searchAllGoodsInfo(storeId);
		
		//System.out.println(storeId+isOnShelf+type);
		
		if(listGoods == null || listGoods.size() == 0)
		{
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			for(int i = 0; i < listGoods.size(); i++)
			{
				//展示的服务只有商品的isdelete字段为"0"
				if(listGoods.get(i).getIsDelete().equals("0"))
				{
					//System.out.println(listGoods.get(i).getIsDelete());
					String goodsId = listGoods.get(i).getId();
					//System.out.println("1");
					listConfig = ServiceContext.get(CommodityConfigService.class).getAllCommodityConfig(goodsId);
					//System.out.println(listGoods.get(i).getGoodsName());
					for(int j = 0; j<listConfig.size();j++)
					{
						//根据规格id获取该规格货架信息
						String configId = listConfig.get(j).getId();
						//System.out.println("2");
						listShelf = ServiceContext.get(ShelfInfoService.class).searchShelfInfoByConfigId(configId);
						//System.out.println("3");
						//System.out.println(listShelf.get(0).getOnShelf());
						//if(listShelf.get(0)==null) System.out.println("lalala");
						//System.out.println("shelf::"+isOnShelf);
						
						if(listConfig.get(j).getIsDelete().equals("0")){
						if(isOnShelf.equals("2")){
							//判断分类
							//System.out.println("type1");
							//System.out.println(listGoods.get(i).getGoodsType());
							
							if(type.equals("all")){
								String temp = "{"+"\""+"goodsId"+"\""+":"+"\""+configId+"\""+","
										+"\""+"goodsShowImg"+"\""+":"+"\""+listGoods.get(i).getGoodsShowImg()+"\""+","
										+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
										+"\""+"goodsName"+"\""+":"+"\""+listGoods.get(i).getGoodsName()+"\""+","
										+"\""+"configName"+"\""+":"+"\""+listConfig.get(j).getConfigName()+"\""+","
										+"\""+"configPrice"+"\""+":"+"\""+listConfig.get(j).getConfigPrice()+"\""+","
										+"\""+"goodsDistribution"+"\""+":"+"\""+listGoods.get(i).getGoodsDistribution()+"\""+","
										+"\""+"goodsShowDes"+"\""+":"+"\""+listGoods.get(i).getGoodsShowDes()+"\""+","
										+"\""+"goodsType"+"\""+":"+"\""+listGoods.get(i).getGoodsType()+"\""+","
										+"\""+"onShelf"+"\""+":"+"\""+listShelf.get(0).getOnShelf()+"\""+"}";
								msg += temp+",";
							}
							
							if(listGoods.get(i).getGoodsType().equals(type)){
							String temp = "{"+"\""+"goodsId"+"\""+":"+"\""+configId+"\""+","
									+"\""+"goodsShowImg"+"\""+":"+"\""+listGoods.get(i).getGoodsShowImg()+"\""+","
									+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
									+"\""+"goodsName"+"\""+":"+"\""+listGoods.get(i).getGoodsName()+"\""+","
									+"\""+"configName"+"\""+":"+"\""+listConfig.get(j).getConfigName()+"\""+","
									+"\""+"configPrice"+"\""+":"+"\""+listConfig.get(j).getConfigPrice()+"\""+","
									+"\""+"goodsDistribution"+"\""+":"+"\""+listGoods.get(i).getGoodsDistribution()+"\""+","
									+"\""+"goodsShowDes"+"\""+":"+"\""+listGoods.get(i).getGoodsShowDes()+"\""+","
									+"\""+"goodsType"+"\""+":"+"\""+listGoods.get(i).getGoodsType()+"\""+","
									+"\""+"onShelf"+"\""+":"+"\""+listShelf.get(0).getOnShelf()+"\""+"}";
							msg += temp+",";
							}
						}
						
						if(listShelf.get(0).getOnShelf().equals(isOnShelf))
						{
							//System.out.println("type2");
							//System.out.println(type);
							if(type.equals("all")){
								String temp = "{"+"\""+"goodsId"+"\""+":"+"\""+configId+"\""+","
										+"\""+"goodsShowImg"+"\""+":"+"\""+listGoods.get(i).getGoodsShowImg()+"\""+","
										+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
										+"\""+"goodsName"+"\""+":"+"\""+listGoods.get(i).getGoodsName()+"\""+","
										+"\""+"configName"+"\""+":"+"\""+listConfig.get(j).getConfigName()+"\""+","
										+"\""+"configPrice"+"\""+":"+"\""+listConfig.get(j).getConfigPrice()+"\""+","
										+"\""+"goodsDistribution"+"\""+":"+"\""+listGoods.get(i).getGoodsDistribution()+"\""+","
										+"\""+"goodsShowDes"+"\""+":"+"\""+listGoods.get(i).getGoodsShowDes()+"\""+","
										+"\""+"goodsType"+"\""+":"+"\""+listGoods.get(i).getGoodsType()+"\""+","
										+"\""+"onShelf"+"\""+":"+"\""+listShelf.get(0).getOnShelf()+"\""+"}";
								msg += temp+",";
							}
							//System.out.println(listGoods.get(i).getGoodsType());
							if(listGoods.get(i).getGoodsType().equals(type)){
								//System.out.println("66"+type);
							String temp = "{"+"\""+"goodsId"+"\""+":"+"\""+configId+"\""+","
									+"\""+"goodsShowImg"+"\""+":"+"\""+listGoods.get(i).getGoodsShowImg()+"\""+","
									+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
									+"\""+"goodsName"+"\""+":"+"\""+listGoods.get(i).getGoodsName()+"\""+","
									+"\""+"configName"+"\""+":"+"\""+listConfig.get(j).getConfigName()+"\""+","
									+"\""+"configPrice"+"\""+":"+"\""+listConfig.get(j).getConfigPrice()+"\""+","
									+"\""+"goodsDistribution"+"\""+":"+"\""+listGoods.get(i).getGoodsDistribution()+"\""+","
									+"\""+"goodsShowDes"+"\""+":"+"\""+listGoods.get(i).getGoodsShowDes()+"\""+","
									+"\""+"goodsType"+"\""+":"+"\""+listGoods.get(i).getGoodsType()+"\""+","
									+"\""+"onShelf"+"\""+":"+"\""+listShelf.get(0).getOnShelf()+"\""+"}";
							msg += temp+",";
						
							}
							
						  }
						}
						
						
						//System.out.println("type "+listGoods.get(i).getGoodsType());
					}
					//System.out.println(listGoods.get(i).getIsDelete());
				}
			}
			System.out.println(msg);
			if(msg.length()==0){
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"\"}";
			}
			else responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
	
}
