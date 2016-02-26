package com.siims.szb.szbldk.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "COMPOENT_FOOD_INFO")
public class DemoData implements BaseData{

	/**
	 * @author ling
	 * @since 2015��8��20��14:22:10
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "FOOD_ID", length = 32, nullable = false)
	private String id;
	
	@Column(name = "SHOP_ID", length = 32)
	private String shop_id;
	
	@Column(name = "FOOD_NAME", length = 32)
	private String food_name;
	
	@Column(name = "FOOD_PRICE")
	private float food_price;
	
	@Column(name = "FOOD_RECOMMEND")
	private int food_recommend;
	
	@Column(name = "FOOD_TYPE")
	private String food_type;
	
	/*--------------------------------get��set����--------------------------------*/
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public float getFood_price() {
		return food_price;
	}

	public void setFood_price(float food_price) {
		this.food_price = food_price;
	}

	public int getFood_recommend() {
		return food_recommend;
	}

	public void setFood_recommend(int food_recommend) {
		this.food_recommend = food_recommend;
	}

	public String getFood_type() {
		return food_type;
	}

	public void setFood_type(String food_type) {
		this.food_type = food_type;
	}
}
