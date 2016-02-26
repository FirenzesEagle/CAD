package com.siims.szb.order.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.order.data.Order;
import com.siims.szb.order.enums.ResultType;
import com.siims.szb.order.facade.MapFacade;
import com.siims.szb.order.facade.ResultFacade;
import com.siims.szb.order.service.OrderCardService;
import com.siims.szb.order.service.OrderGoodsService;
import com.siims.szb.order.service.OrderService;
import com.siims.szb.order.util.ReplyUtil;
import com.siims.szb.order.wrapper.CardWrapper;
import com.siims.szb.order.wrapper.OrderWrapper;

@Namespace("/siims/szb/order")
public class CreateAction extends StrutsAction {

	private Order order = new Order();
		
	private String content;
	
	private String cards;
	
	@Action(value="create")
	public void create() {
		order.setCreatetime(new Date());
		order.setIsDeleted("0");
		order.setCode(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		Gson gson = new GsonBuilder().serializeNulls().create();

		List<OrderWrapper> list = gson.fromJson(content, new TypeToken<List<OrderWrapper>>(){}.getType());
			
		if (content == null || content.length() == 1) {
			ResultFacade facade = new ResultFacade(ResultType.error, "传入的内容信息无效");
			ReplyUtil.reply(response, gson.toJson(facade));
		}else if(list == null) {
			ResultFacade facade = new ResultFacade(ResultType.error, "传入的内容信息解析失败");
			ReplyUtil.reply(response, gson.toJson(facade));
		}else if (list.size() == 0) {
			ResultFacade facade = new ResultFacade(ResultType.error, "传入的内容信息为空");
			ReplyUtil.reply(response, gson.toJson(facade));
		}else {
			String result = ServiceContext.get(OrderService.class).save(order);
			if (result == null || result.equals("")) {
				ResultFacade facade = new ResultFacade(ResultType.error, "订单生成失败");
				ReplyUtil.reply(response, gson.toJson(facade));
			} else {
				for (int i = 0; i < list.size(); i++) {
					ServiceContext.get(OrderGoodsService.class).create(result, list.get(i).getGoodsIdString(), list.get(i).getGoodsconfigIdString(), list.get(i).getShopidString(), list.get(i).getPriceString(), list.get(i).getNumString(),list.get(i).getPaytypeString());
				}
				if (cards != null && !cards.equals("")) {
					List<CardWrapper> cardWrappers = gson.fromJson(cards, new TypeToken<List<CardWrapper>>(){}.getType());
					if (cardWrappers != null && cardWrappers.size() >= 1) {
						for (int i = 0; i < cardWrappers.size(); i++) {
							CardWrapper cardWrapper = cardWrappers.get(i);
							System.out.println(cardWrapper.getCardid() +"  "+cardWrapper.getMoney()+" "+ cardWrapper.getPassword()+" "+cardWrapper.getType());
							ServiceContext.get(OrderCardService.class).create(result, cardWrapper.getCardid(), cardWrapper.getPassword(), cardWrapper.getMoney(), cardWrapper.getType());
						}
					}
				}
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("orderid", result);
				MapFacade facade = new MapFacade(ResultType.success, "订单生成成功", map);
				ReplyUtil.reply(response, gson.toJson(facade));
			}
		}
		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}
		
}
