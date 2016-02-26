package com.siims.szb.goods.goodsdetail.service;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.framework.base.BasePersistence;
import com.siims.framework.base.BaseService;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.goodsdetail.data.DetailInfoData;
import com.siims.szb.goods.shelf.data.ShelfInfoData;

public interface GoodsDetailService extends BaseService{
	/**添加商品详细信息
	 * @author liufeng
	 * @param detailInfoData
	 * @return ID
	 * @since 2015年9月7日16:38:34
	 */
	public String addDetailInfo(DetailInfoData detailInfoData);
	
	/**将商品详细进行软删除
	 * @author liufeng
	 * @param detailInfoData
	 * @since 2015年9月7日16:39:40
	 */
	public boolean delDetailInfo(DetailInfoData detailInfoData);
	
	/**修改商品详细
	 * @author liufeng
	 * @param detailInfoData
	 * @since 2015年9月7日16:40:29
	 */
	public boolean editDetailInfo(DetailInfoData detailInfoData);
	
	/**查找出对应的商品详细
	 * @author liufeng
	 * @param 货架信息的id
	 * @return 商品详细
	 * @since 2015年9月7日16:41:28
	 */
	public DetailInfoData searchDetailInfo(String id);
	
	

	/**查找出对应的商品详细
	 * @author liufeng
	 * @param 商品的id
	 * @return 商品详细
	 * @since 2015年9月7日16:41:28
	 */
	public DetailInfoData searchDetailInfoByGoodsId(String goodsId);
	
	/**返回商品对应的商品详细
	 * @author liufeng
	 * @param 商品的id
	 * @return 商品对应的所有商品详细实体
	 * @since 2015年9月7日16:44:12
	 */
	public List<DetailInfoData> searchDetailInfoByGoodsConfigId(String goodsId);
	
	/**通过网店ID找出该网店的种类的实体
	 * @author liufeng
	 * @param 网店id
	 * @return 该网店的所有种类信息
	 */
	//public List<CommodityConfigData> GetCommodityType(String id);
}
