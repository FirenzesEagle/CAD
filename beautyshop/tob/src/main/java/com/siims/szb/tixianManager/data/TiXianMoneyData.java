package com.siims.szb.tixianManager.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_TIXIANMONEY")
public class TiXianMoneyData implements BaseData{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
	@Column(name = "SHOPERID", length = 32)
	private String shoperId;
	
	@Column(name = "MONEY", length = 10)
	private float money;
	
	public TiXianMoneyData() {
		// TODO Auto-generated constructor stub
	}
	
	public TiXianMoneyData(String shoperId) {
		// TODO Auto-generated constructor stub
		super();
		this.shoperId = shoperId;
		this.money = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShoperId() {
		return shoperId;
	}

	public void setShoperId(String shoperId) {
		this.shoperId = shoperId;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}
}
