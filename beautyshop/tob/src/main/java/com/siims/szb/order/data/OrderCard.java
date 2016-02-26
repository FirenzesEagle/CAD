package com.siims.szb.order.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name="szb_ordercard")
public class OrderCard implements BaseData{

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ORDER_CARD_ID", length = 32, nullable = false)
	private String id;
	
	@Column(name="orderid")
	private String orderid;
	
	@Column(name="cardid")
	private String cardid;
	
	@Column(name="password")
	private String password;
	
	@Column(name="money")
	private String money;
	
	@Column(name="type")
	private String type;

	public OrderCard() {
		
	}
	
	public OrderCard(String orderid, String cardid, String password, String money, String type) {
		this.orderid = orderid;
		this.cardid = cardid;
		this.password = password;
		this.money = money;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}












