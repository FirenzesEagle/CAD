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
 * 用户会员卡实体类，即会员领取的会员卡的信息
 * @author libo
 * 2015-09-13
 */
@Entity
@Table(name = "SZB_CONSUMERVIPCARD")
public class ConsumerVipCardData implements BaseData {

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
	 * 领取的会员卡类型ID
	 */
	@Column(name = "VIPCARDTYPEID")
	private String vipCardTypeId;
	
	/**
	 * 领取的会员卡规格ID
	 */
	@Column(name = "VIPCARDCONFIGID")
	private String vipCardConfigId;
	
	/**
	 * 会员卡卡号，同一个Type内唯一
	 */
	@Column(name = "CARDNUMBER")
	private int cardNumber;
	
	/**
	 * 会员的WEIXINOPENID，保留字段，备用
	 */
	@Column(name = "CONSUMERWEIXINOPENID")
	private String consumerWeixinOpenId;
	
	/**
	 * 用户姓名
	 */
	@Column(name = "CONSUMERNAME")
	private String consumerName;
	
	/**
	 * 用户电话
	 */
	@Column(name = "CONSUMERPHONE")
	private String consumerPhone;
	
	/**
	 * 密码
	 */
	@Column(name = "PASSWORD")
	private String password;
	
	/**
	 * 会员卡的领取时间，即创建时间
	 */
	@Column(name = "CREATETIME")
	private Date createTime;
	
	/**
	 * 是否删除，0是未删除，1是删除
	 */
	@Column(name = "ISDELETE")
	private int isDelete;
	
	/**
	 * 会员卡余额
	 */
	@Column(name = "LASTMONEY")
	private double lastMoney;
	
	/**
	 * 是否支付
	 * 0是未支付，1是已支付
	 */
	@Column(name = "ISPAY")
	private int ispay;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVipCardTypeId() {
		return vipCardTypeId;
	}

	public void setVipCardTypeId(String vipCardTypeId) {
		this.vipCardTypeId = vipCardTypeId;
	}

	public String getVipCardConfigId() {
		return vipCardConfigId;
	}

	public void setVipCardConfigId(String vipCardConfigId) {
		this.vipCardConfigId = vipCardConfigId;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getConsumerWeixinOpenId() {
		return consumerWeixinOpenId;
	}

	public void setConsumerWeixinOpenId(String consumerWeixinOpenId) {
		this.consumerWeixinOpenId = consumerWeixinOpenId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerPhone() {
		return consumerPhone;
	}

	public void setConsumerPhone(String consumerPhone) {
		this.consumerPhone = consumerPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public double getLastMoney() {
		return lastMoney;
	}

	public void setLastMoney(double lastMoney) {
		this.lastMoney = lastMoney;
	}

	public int getIspay() {
		return ispay;
	}

	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	
	
	
}
