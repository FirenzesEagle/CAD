package com.siims.szb.wxopenid.action;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.utils.Sha1Util;

//@Namespace("/siims/szb/openid")
public class getOpenIdAction extends StrutsAction{

	/**
	 * 
	 */
	private static String DOMIN = "www.91dzsw.cn";
	
	private static final long serialVersionUID = 1L;
	//商户相关资料 
	private static String appid = "wxbf649da2ec1bd6e9";
	private static String appsecret = "ac55516b3a4b31912d437c63584aafd5";
	private static String backUri = "http://" + DOMIN + "/siims/szb/openid/returnopenid.jspx";
	
	//@Action(value = "getopenid")
	public void getOpenId() throws IOException{
		System.out.println("getopenid");
		
		backUri = URLEncoder.encode(backUri);
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid +
				"&redirect_uri=" +
				 backUri+
				"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}

}
