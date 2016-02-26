package com.siims.szb.szbldk.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;


@Entity
@Table(name = "SZB_SHOPPING_TROLLY")
public class ShoppingTrollyData implements BaseData {
	/**
	 * author:storm
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "SHOPPING_TROLLY_ID", length = 32, nullable = false)
	private String id;
	
	/**
	 * 商铺ID
	 */
	@Column(name = "SHOP_ID", nullable = false)
	private String shop_id;
	/**
	 *  商铺名称
	 */
	@Column(name = "SHOP_NAME", nullable = false)
	private String shop_name;
	/**
	 * 商品ID
	 */
	@Column(name = "ITEM_ID", nullable = false)
	private String item_id;
	/**
	 * 商品名称
	 */
	@Column(name = "ITEM_NAME", nullable = false)
	private String item_name;
	/**
	 * 商品图片地址
	 */
	@Column(name = "ITEM_IMG_URL", nullable = false)
	private String item_img_url;
	/**
	 * 商品规格
	 */
	@Column(name = "ITEM_SPEC", nullable = false)
	private String item_spec;
	/**
	 * 用户ID
	 */
	@Column(name = "USER_ID", nullable = true)
	private String user_id;
	/**
	 * openid
	 */
	@Column(name = "OPENID", nullable = true)
	private String openid;
	/**
	 * 商品数量
	 */
	@Column(name = "ITEM_COUNT", nullable = false)
	private Integer item_count;
	/**
	 * 商品单价
	 */
	@Column(name = "ITEM_PRICE", nullable = false)
	private float item_price;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME", nullable = false)
	private Date create_time;
	
	/**
	 * 是否删除
	 */
	@Column(name = "STATUS", nullable = false)
	private Integer status;



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

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getItem_count() {
		return item_count;
	}

	
	public float getItem_price() {
		return item_price;
	}

	public void setItem_price(float item_price) {
		this.item_price = item_price;
	}

	public void setItem_count(Integer item_count) {
		this.item_count = item_count;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_img_url() {
		return item_img_url;
	}

	public void setItem_img_url(String item_img_url) {
		this.item_img_url = item_img_url;
	}

	public String getItem_spec() {
		return item_spec;
	}

	public void setItem_spec(String item_spec) {
		this.item_spec = item_spec;
	}
	
	
	
	
}
