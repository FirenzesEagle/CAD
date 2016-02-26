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
 * 会员卡规则表实体类
 * @author libo
 * 2015-09-12
 */


@Entity
@Table(name = "SZB_VIPCARDCONFIG")
public class VipCardConfigData implements BaseData {

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
	 * 会员卡类型ID，即这个规格是哪个类型的
	 */
	@Column(name = "TYPEID")
	private String typeId;
	
	/**
	 * 规格的类型，0是无优惠，1是打折卡，2是返现卡，3是计次卡
	 */
	@Column(name = "TYPE")
	private int type;
	
	/**
	 * 该规格的充值金额
	 */
	@Column(name = "PRICE")
	private Double price;
	
	/**
	 * 该规格的回报，如果是打折卡即折扣，返现卡即返现金额，计次卡即计次次数
	 */
	@Column(name = "BENEFIT")
	private Double benefit;
	
	/**
	 * 是否删除
	 * 0是未删除，1是已删除
	 */
	@Column(name = "ISDELETE")
	private int isdelete;
	
	@Column(name = "TIME")
	private Date time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getBenefit() {
		return benefit;
	}

	public void setBenefit(Double benefit) {
		this.benefit = benefit;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
