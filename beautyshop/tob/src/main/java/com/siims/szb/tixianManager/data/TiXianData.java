package com.siims.szb.tixianManager.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;


@Entity
@Table(name = "SZB_TIXIANTABLE")
public class TiXianData implements BaseData {
	
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
	
	@Column(name = "TYPE", length = 2)
	private int type;
	
	@Column(name = "SHOPERNAME", length = 32)
	private String shoperName;
	
	@Column(name = "ZHIFUBAOACCOUNT", length = 32)
	private String zhiFuBaoAccount;
	
	@Column(name = "SZBSECRET", length = 32)
	private String sZBSecret;
	
	@Column(name = "MONEY", length = 10)
	private float money;
	
	@Column(name= "CARDTYPE", length = 32)
	private String cardType;
	
	@Column(name = "CARDNUMBER", length = 32)
	private String cardNumber;
	
	@Column(name = "TIME", length = 7)
	private Date time;
	
	@Column(name = "IS_DEAL", length = 2)
	private int isDeal;
	
	
	public TiXianData() {
		// TODO Auto-generated constructor stub
	}
	
	public TiXianData(String shoperId) {
		super();
		this.shoperId = shoperId;
		this.type = 0;
		this.shoperName = "";
		this.zhiFuBaoAccount = "";
		this.sZBSecret = "";
		this.money = 0;
		this.cardType = "";
		this.cardNumber = "";
		this.setTime(new Date());
		this.setIsDeal(0);
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getShoperName() {
		return shoperName;
	}

	public void setShoperName(String shoperName) {
		this.shoperName = shoperName;
	}

	public String getZhiFuBaoAccount() {
		return zhiFuBaoAccount;
	}

	public void setZhiFuBaoAccount(String zhiFuBaoAccount) {
		this.zhiFuBaoAccount = zhiFuBaoAccount;
	}

	public String getSZBSecret() {
		return sZBSecret;
	}

	public void setSZBSecret(String sZBSecret) {
		this.sZBSecret = sZBSecret;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(int isDeal) {
		this.isDeal = isDeal;
	}
	
}
