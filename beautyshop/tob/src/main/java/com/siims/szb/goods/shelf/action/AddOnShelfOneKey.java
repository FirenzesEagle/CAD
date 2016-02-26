package com.siims.szb.goods.shelf.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.service.ShelfInfoService;



/**
 * @author liufeng
 * @since 2015年8月20日16:24:49
 */
@Namespace("/siims/szb/shelf")
public class AddOnShelfOneKey extends StrutsAction{

	
	private static final long serialVersionUID = 1L;

	private ShelfInfoData shelfInfoData;
	/**
	 * 根据shelfInfoData的Id上架商品
	 */
	@Action(value = "addOnShelfByIds")
	public void addOnShelfByIds(){
		
		String id = request.getParameter("id");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		//根据Id获取对象
		shelfInfoData = ServiceContext.get(ShelfInfoService.class).searchShelfInfo(id);
		
		//设置onshelf为1即可以上架
		shelfInfoData.setOnShelf("1");;
		
		//上架
		Boolean msg = ServiceContext.get(ShelfInfoService.class).editShelfInfo(shelfInfoData);
		
		
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + msg.toString() + "\"}";
		ActionUtil.sendJson(responseResult);
	}
		
		
		
		
	}

