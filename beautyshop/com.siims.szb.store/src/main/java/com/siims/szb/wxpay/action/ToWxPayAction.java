package com.siims.szb.wxpay.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.utils.CommonUtil;
import com.siims.szb.utils.GetWxOrderno;
import com.siims.szb.utils.RequestHandler;
import com.siims.szb.utils.Sha1Util;
import com.siims.szb.utils.TenpayUtil;

@Namespace("/siims/szb/pay")
public class ToWxPayAction extends StrutsAction{

	/**微信支付
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//商户相关资料 
	private static String appid = "wxbf649da2ec1bd6e9";
	private static String appsecret = "ac55516b3a4b31912d437c63584aafd5";
	
	@Action(value = "towxpay")
	public void towxpay() throws IOException{
		String backUri = "http://shop.91dzsw.cn:8838/siims/szb/pays/weixinpay.jspx";
		String orderNo = request.getParameter("orderno");
//		String orderNo = appid+Sha1Util.getTimeStamp();
		String money = request.getParameter("money");
		String userId = request.getParameter("userid");
		String describe = request.getParameter("describe");
		String type = request.getParameter("type");
		
		backUri = backUri+"?userId="+ userId +"&orderNo="+ orderNo +"&describe="+ describe +"&money=" + money +"&type=" + type;
		
		backUri = URLEncoder.encode(backUri);
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid +
				"&redirect_uri=" +
				 backUri+
				"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
		
	}

}
