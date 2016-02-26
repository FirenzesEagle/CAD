package com.siims.szb.vipcard.sbp.data;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

/**
 * 会员卡消费交易流水表
 * @author libo
 * 2015-09-13
 */
@Entity
@Table(name = "SZB_VIPCARDBILL")
public class VipCardBillData implements BaseData {

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
	 * 使用的消费者会员卡的ID
	 */
	@Column(name = "VIPCARDID")
	private String vipCardId;
	
	/**
	 * 交易金额，如果是充值或者开卡，则为正，否则为负，如果是计次卡，则代表的是次数
	 */
	@Column(name = "MONEY")
	private Double money;
	
	/**
	 * 这比交易过后的余额
	 */
	@Column(name = "LASTMONEY")
	private Double lastMoney;
	
	/**
	 * 消费类型，0是开卡，1是充值，2是商品扣费，3是现场收款，4是现场扣次
	 */
	@Column(name = "CONSUMETYPE")
	private int consumeType;
	
	/**
	 * 消费的摘要信息
	 */
	@Column(name = "COMMENTS")
	private String comments;
	
	/**
	 * 交易的日期
	 */
	@Column(name = "TIME")
	private Date time;
	
	/**
	 * 订单编号，当为商品消费时，关联订单编号
	 */
	@Column(name = "ORDERID")
	private String orderId;
	
	/**
	 * 支付类型，主要是为充值和开卡所用，0为微信，1为支付宝
	 */
	@Column(name = "PAYTYPE")
	private int payType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVipCardId() {
		return vipCardId;
	}

	public void setVipCardId(String vipCardId) {
		this.vipCardId = vipCardId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getLastMoney() {
		return lastMoney;
	}

	public void setLastMoney(Double lastMoney) {
		this.lastMoney = lastMoney;
	}

	public int getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(int consumeType) {
		this.consumeType = consumeType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	
	

}
