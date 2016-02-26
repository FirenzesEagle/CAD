package com.siims.szb.alipay.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.alipay.config.AlipayConfig;
import com.siims.szb.alipay.util.AlipaySubmit;

import com.siims.szb.utils.ReplyUtil;
import com.siims.szb.utils.ResultFacade;
import com.siims.szb.utils.ResultType;


@Namespace("/siims/szb/alipay")
public class AlipayAction extends StrutsAction{

	private String payment_type = "1";
	
	private String notify_url = "http://www.baidu.com";
	
	private String return_url = "http://www.baidu.com";
	
	private String out_trade_no;
	
	private String subject;
	
	private String total_fee;
	
	private String body;
	
	private String show_url;
	
	private String anti_phishing_key;
	
	private String exter_invoke_ip;
	
	@Action(value="alipay")
	public void pay() throws IOException {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (notify_url == null || notify_url.equals("") || out_trade_no == null|| out_trade_no.equals("") || total_fee == null || total_fee.equals("")) {
			ReplyUtil.reply(response, gson.toJson(new ResultFacade(ResultType.error, "传入的关键参数不能为空")));
		} else {
			Map<String, String> sParaTemp = new HashMap<String, String>();
			System.out.println(subject);
			sParaTemp.put("service", "create_direct_pay_by_user");
	        sParaTemp.put("partner", AlipayConfig.partner);
	        sParaTemp.put("seller_email", AlipayConfig.seller_email);
	        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			
			String content = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>支付宝即时到账交易接口</title></head>TEST<body></body></html>";
			
			//建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(content.replace("TEST", sHtmlText));

		}
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getAnti_phishing_key() {
		return anti_phishing_key;
	}

	public void setAnti_phishing_key(String anti_phishing_key) {
		this.anti_phishing_key = anti_phishing_key;
	}

	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}
	
	
	
}
