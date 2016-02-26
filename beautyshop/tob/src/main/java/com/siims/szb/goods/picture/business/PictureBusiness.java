package com.siims.szb.goods.picture.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.picture.data.PictureData;

public interface PictureBusiness extends BaseBusiness{
	
	public String addPicture(PictureData pictureData);
	
	
	public boolean delPicture(PictureData pictureData);
	
	
	public boolean editPicture(PictureData pictureData);
	
	
	public PictureData searchPicture(String pictureId);
	
	//pageId
	public List<PictureData> searchAllPicture(String pageID);
}
