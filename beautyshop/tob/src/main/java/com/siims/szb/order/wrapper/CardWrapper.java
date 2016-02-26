package com.siims.szb.order.wrapper;

public class CardWrapper {

	private String cardid;
	
	private String password;
	
	private String money;
	
	private String type;
	
	public CardWrapper() {
		// TODO Auto-generated constructor stub
	}

	public CardWrapper(String cardid, String password, String money, String type) {
		this.cardid = cardid;
		this.password = password;
		this.money = money;
		this.type = type;
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
