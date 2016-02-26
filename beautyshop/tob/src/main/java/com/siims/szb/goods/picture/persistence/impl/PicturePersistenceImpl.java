package com.siims.szb.goods.picture.persistence.impl;

import java.util.List;




import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;
import com.siims.szb.goods.picture.data.PictureData;
import com.siims.szb.goods.picture.persistence.PicturePersistence;
import com.siims.szb.goods.shelf.data.ShelfInfoData;


@Singleton
@AutoBind(bindClass = PicturePersistence.class)
public class PicturePersistenceImpl implements PicturePersistence{

	
	private final BaseDao hibernateDao;

	
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	
	@Inject
	public PicturePersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	

	public String addPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, pictureData);
		return pictureData.getId();
	}

	public boolean delPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean editPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, pictureData);
		return true;
	}

	public PictureData searchPicture(String goodsId) {
		// TODO Auto-generated method stub
		String sql = "from PictureData p where p.goodsId = '" + goodsId +"'  ";
		List<PictureData> list = hibernateDao.queryList(sql, null);
		if(list.size()!=0)
			return list.get(0);
		else
			return null;
		
	}

	public List<PictureData> searchAllPicture(String pageID) {
		// TODO Auto-generated method stub
		return null;
	}

}
