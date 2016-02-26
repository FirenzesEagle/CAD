package com.siims.szb.goods.goods.action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.goods.goods.util.ZingUtil;


@Namespace("/siims/szb/goods")
public class CreateGoodsTwoCodeAction extends StrutsAction{
	
	
	/**
	 * 生成商品预览二维码
	 */
	private static final long serialVersionUID = 3536230677850507708L;
	private String goodsId;
	//private String serviceUrl = ResouceUtil.getProperty("")
	@Action(value = "createTwoCode")
	public void createTwoCode() throws IOException{
		String responseResult;
		goodsId = request.getParameter("goodsId");
		if (goodsId.equals("")) {
			
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Goods\"}";
			ActionUtil.sendJson(responseResult);
			
			
		}
		else{
			//Gson gson = new Gson();
			Calendar cal = Calendar.getInstance();// 使用日历类
			int year = cal.get(Calendar.YEAR);// 得到年
			int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
			int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
			//String filePath = ServletActionContext.getServletContext().getRealPath("/axisCode")+ "/"+ year+ "/"+ month+ "/" + day + "/";
			String filePath = ServletActionContext.getServletContext().getRealPath("/serviceCode")+ "/"+ year+ "/"+ month+ "/" + day + "/";
			File file = new File(filePath);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			
			
			GoodsInfoData goodsInfoData = ServiceContext.get(GoodsService.class).searchGoodsInfo(goodsId);
			String pathurl = "http://localhost:8580/siims/szb/service/getServiceById.jspx?serviceId="+goodsInfoData.getId();
			if(ZingUtil.zing(pathurl,filePath + "/" + "service_"+goodsInfoData.getId()+".gif",500,500)){responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"Yes GOODS\"}";
			ActionUtil.sendJson(responseResult);}
			
			goodsInfoData.setGoodsEWCode(filePath+ "/" + "goods_"+goodsInfoData.getId()+".gif");
			ServiceContext.get(GoodsService.class).editGoodsInfo(goodsInfoData);
		}
			
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
	
	
}

