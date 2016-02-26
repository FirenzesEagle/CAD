package com.siims.szb.vipcard.sbp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;


/**
 * 会员卡类型表实体类
 * @author libo
 * 2015-09-12
 */


@Entity
@Table(name = "SZB_VIPCARDTYPE")
public class VipCardTypeData implements BaseData{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID作为唯一标识
	 */
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
	/**
	 * 商家用户ID
	 */
	@Column(name = "STOREUSERID", length = 32, nullable = false)
	private String storeUserId;
	
	/**
	 * 卡的类型
	 */
	@Column(name = "TYPE")
	private int type;
	
	/**
	 * 是否删除
	 * 0是未删除，1是已删除
	 */
	@Column(name = "ISDELETE")
	private int isdelete;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreUserId() {
		return storeUserId;
	}

	public void setStoreUserId(String storeUserId) {
		this.storeUserId = storeUserId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	
	
	
	

}
