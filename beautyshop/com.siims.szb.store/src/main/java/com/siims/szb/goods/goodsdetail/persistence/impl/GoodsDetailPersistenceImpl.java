package com.siims.szb.goods.goodsdetail.persistence.impl;

import java.util.List;









import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.goodsdetail.data.DetailInfoData;
import com.siims.szb.goods.goodsdetail.persistence.GoodsDetailPersistence;


@Singleton
@AutoBind(bindClass = GoodsDetailPersistence.class)
public class GoodsDetailPersistenceImpl implements GoodsDetailPersistence{

	
	private final BaseDao hibernateDao;

	
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	
	@Inject
	public GoodsDetailPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String addDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, detailInfoData);
		return detailInfoData.getId();
	}

	public boolean delDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.delete(null, detailInfoData);
		return true;
	}

	public boolean editDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, detailInfoData);
		return true;
	}

	public DetailInfoData searchDetailInfo(String id) {
		// TODO Auto-generated method stub
		DetailInfoData detailInfoData = (DetailInfoData) hibernateDao.queryData(id, DetailInfoData.class);
		return detailInfoData;
	}

	@SuppressWarnings("unchecked")
	public List<DetailInfoData> searchAllDetailInfo(String storeId) {
		// TODO Auto-generated method stub
		//String sql = "from GoodsInfoData g,PageGoodsInfoData p where g.id=p.goodsinfoid";
		String sql = "from DetailInfoData d where d.storeId = '" + storeId +"' order by d.id desc";
		List<DetailInfoData> list = hibernateDao.queryList(sql, null);
		return list;
	}

	public DetailInfoData searchDetailInfoByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		DetailInfoData detailInfoData = (DetailInfoData) hibernateDao.queryData(goodsId, DetailInfoData.class);
		return detailInfoData;
	}
	
	public List<DetailInfoData> searchDetailInfoByGoodsConfigId(String goodsConfigId){
		String sql = "from DetailInfoData d where d.goodsId = '" + goodsConfigId +"' order by d.createTime";
		List<DetailInfoData> list = hibernateDao.queryList(sql, null);
		return list;
		
	}

	

}
