package com.siims.szb.goods.shelf.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.shelf.data.ShelfInfoData;

public interface ShelfInfoService extends BaseService{
	/**添加货架信息
	 * @author liufeng
	 * @param shelfInfoData
	 * @return 货架的ID
	 * @since 2015年9月7日16:38:34
	 */
	public String addShelfInfo(ShelfInfoData shelfInfoData);
	
	/**将货架信息进行软删除
	 * @author liufeng
	 * @param shelfInfoData
	 * @since 2015年9月7日16:39:40
	 */
	public boolean delShelfInfo(ShelfInfoData shelfInfoData);
	
	/**修改货架信息
	 * @author liufeng
	 * @param shelfInfoData
	 * @since 2015年9月7日16:40:29
	 */
	public boolean editShelfInfo(ShelfInfoData shelfInfoData);
	
	/**查找出对应的货架信息
	 * @author liufeng
	 * @param 货架信息的id
	 * @return 货架实体
	 * @since 2015年9月7日16:41:28
	 */
	public ShelfInfoData searchShelfInfo(String id);
	
	/**查找出对应的货架信息
	 * @author liufeng
	 * @param 根据商品信息的规格id
	 * @return 货架实体
	 * @since 2015年9月7日16:41:28
	 */
	public List<ShelfInfoData> searchShelfInfoByConfigId(String configId);
	
	/**返回商品对应的货架
	 * @author liufeng
	 * @param 商品的id
	 * @return 商品对应的所有规格信息实体
	 * @since 2015年9月7日16:44:12
	 */
	public List<ShelfInfoData> searchAllShelfInfo(String id);
	
}
