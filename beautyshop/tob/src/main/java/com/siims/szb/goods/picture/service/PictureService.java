package com.siims.szb.goods.picture.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.picture.data.PictureData;


public interface PictureService extends BaseService{
	

	public String addPicture(PictureData pictureData);
	
	
	public boolean delPicture(PictureData pictureData);
	
	
	public boolean editPicture(PictureData pictureData);
	
	
	public PictureData searchPicture(String pictureId);
	
	//pageId
	public List<PictureData> searchAllPicture(String pageID);
}
