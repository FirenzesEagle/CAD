package com.siims.szb.order.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name="szb_ordergoods")
public class OrderGoods implements BaseData{

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ORDER_GOODS_ID", length = 32, nullable = false)
	private String id;
	
	@Column(name="ordeid")
	private String orderId;
	
	@Column(name="goodsid")
	private String goodsId;
	
	@Column(name="goodsconfigid")
	private String goodsconfigId;
	
	@Column(name="shopid")
	private String shopId;
	
	@Column(name="price")
	private String price;
	
	@Column(name="num")
	private String num;
	
	@Column(name="paytype")
	private String paytype;
	
	public OrderGoods() {
	}

	public OrderGoods(String orderId, String goodsId, String goodsconfigId, String shopid, String price, String num, String paytype) {
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.goodsconfigId = goodsconfigId;
		this.shopId = shopid;
		this.price = price;
		this.num = num;
		this.paytype = paytype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsconfigId() {
		return goodsconfigId;
	}

	public void setGoodsconfigId(String goodsconfigId) {
		this.goodsconfigId = goodsconfigId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	
}
