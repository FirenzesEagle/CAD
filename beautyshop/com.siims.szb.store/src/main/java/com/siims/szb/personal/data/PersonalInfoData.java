package com.siims.szb.personal.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.siims.framework.base.BaseData;

 	
/** 
 * @Description 个人信息实体类
 * @author liumingbo
 * @date 2015年9月12日 下午7:18:42 
 * @version V0.1
 */ 
  	
@Entity
@Table(name = "SZB_PERSONAL_INFO")
public class PersonalInfoData implements BaseData {

	/** @Fields serialVersionUID: */
	
	private static final long serialVersionUID = 3570582411255162327L;

	
	/** @Fields openID: 用户OPENID 主键*/
	  	
	@Id
	@Column(name = "OPENID", length = 32, nullable = false)
	private String openID;
	
	
	/** @Fields user_wx_name: 用户微信昵称*/
	  	
	@Column(name = "USER_WX_NAME", length = 32)
	private String user_wx_name;
	
	
	/** @Fields user_pic: 用户头像URL*/
	  	
	@Column(name = "USER_PIC", length = 255)
	private String user_pic;

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getUser_wx_name() {
		return user_wx_name;
	}

	public void setUser_wx_name(String user_wx_name) {
		this.user_wx_name = user_wx_name;
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
		
	public String getId() {
		// TODO Auto-generated method stub
		return openID;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		this.openID = id;
	}

}
