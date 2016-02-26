package com.siims.szb.goods.commodityconfig.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;

public interface CommodityConfigService extends BaseService{
	/**添加商品规格信息
	 * @author ling
	 * @param commodityConfigData
	 * @return 规格的ID
	 * @since 2015年9月7日16:38:34
	 */
	public String addCommodityConfig(CommodityConfigData commodityConfigData);
	
	/**将规格信息进行软删除
	 * @author ling
	 * @param commodityConfigData
	 * @since 2015年9月7日16:39:40
	 */
	public boolean delCommodityConfig(CommodityConfigData commodityConfigData);
	
	/**修改规格信息
	 * @author ling
	 * @param commodityConfigData
	 * @since 2015年9月7日16:40:29
	 */
	public boolean editCommodityConfig(CommodityConfigData commodityConfigData);
	
	/**查找出对应的规格信息
	 * @author ling
	 * @param 规格信息的id
	 * @return 规格实体
	 * @since 2015年9月7日16:41:28
	 */
	public CommodityConfigData getCommodityConfig(String id);
	
	/**返回商品对应的规格信息
	 * @author ling
	 * @param 商品的id
	 * @return 商品对应的所有规格信息实体
	 * @since 2015年9月7日16:44:12
	 */
	public List<CommodityConfigData> getAllCommodityConfig(String id);
	
	/**通过网店ID找出该网店的种类的实体
	 * @author ling
	 * @param 网店id
	 * @return 该网店的所有种类信息
	 */
	public List<CommodityConfigData> getCommodityType(String id);
}
