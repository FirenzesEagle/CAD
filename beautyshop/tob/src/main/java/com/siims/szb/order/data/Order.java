package com.siims.szb.order.data;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.siims.framework.base.BaseData;

@Entity
@Table(name="szb_order")
@Where(clause = "isDeleted = '0'")
public class Order implements BaseData {

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ORDER_ID", length = 32, nullable = false)
	private String id;
	
	@Column(name="order_price")
	private String price;
	
	@Column(name="order_status")
	private String status;
	
	@Column(name="order_code")
	private String code;
	
	@Column(name="order_createtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	
	@Column(name="order_paytime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date paytime;
	
	@Column(name="order_finishtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishtime;
	
	@Column(name="order_deletetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletetime;
	
	@Column(name="customerid")
	private String customerId;
		
	@Column(name="isdeleted")
	private String isDeleted;
	
	@Column(name="address")
	private String address;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="ordercomment")
	private String ordercomment;
	
	public Order() {
		this.status = "0";
	}
	
	public Order(String price, String status) {
		this.price = price;
		this.status = status;
		this.createtime = new Date();
		this.isDeleted = "0";
		this.code = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		this.ordercomment = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}
	
	public Date getDeletetime() {
		return deletetime;
	}

	public void setDeletetime(Date deletetime) {
		this.deletetime = deletetime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrdercomment() {
		return ordercomment;
	}

	public void setOrdercomment(String ordercomment) {
		this.ordercomment = ordercomment;
	}

}











