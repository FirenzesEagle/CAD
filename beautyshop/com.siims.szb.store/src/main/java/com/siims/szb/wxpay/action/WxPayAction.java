package com.siims.szb.wxpay.action;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.utils.CommonUtil;
import com.siims.szb.utils.GetWxOrderno;
import com.siims.szb.utils.RequestHandler;
import com.siims.szb.utils.Sha1Util;
import com.siims.szb.utils.TenpayUtil;

@Namespace("/siims/szb/wxpay")
public class WxPayAction extends StrutsAction {

	private String domin = "";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//微信商家平台id
	private String partner= "1236424202";
	//微信商家平台密钥
	private String partnerkey = "f611838e48a69b120148aabe0edd01da";
	//支付完成后返回的连接地址
	private String notifyurl2 = "http://www.baidu.com";
	//商品订单支付完成后返回的连接地址
	private String notifyurl1 = "/siims/szb/order/GoodsOrderWXNotify.jspx?status=payed";
	//微信公众平台id
	private String appid = "wxbf649da2ec1bd6e9";
	//微信公众平台密钥
	private String appsecret = "ac55516b3a4b31912d437c63584aafd5";
	//微信支付统一请求地址
	private String createorderurl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
     * 微信支付接口 〈功能详细描述〉
     * 
     * @author 凌豪然
     * @param describe 商品描述信息
     * @param openId 微信openid
     * @param userId 购买商品的用户id
     * @param orderNo 订单编号
     * @throws UnsupportedEncodingException 
     * @see WxPayAction#WxPay()
     * @since vmaque2.4
     */
	@Action(value = "weixinpay")
	public void WxPay() throws ServletException,IOException {
		String describe = new String(request.getParameter("describe").getBytes("ISO8859-1"), "UTF-8");
		describe.replaceAll(" ", "");
		if(describe == null || describe.equals("")){
            describe = "商品名称";
        }
//		String openId = request.getParameter("openId");
        String userId = request.getParameter("userId");
        String orderNo = request.getParameter("orderNo");
        String money = request.getParameter("money");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        
        String openId = userId;
//		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
//		
//		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
//		if (null != jsonObject) {
//			openId = jsonObject.getString("openid");
//		}
		
		String notify_url = "";
		
        // 金额转化为分为单位
        float sessionmoney = Float.parseFloat(money);
        String finalmoney = String.valueOf((int) (sessionmoney * 100));
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 商户号
        String mch_id = partner;
        // 随机数
        String nonce_str = strReq;
        // 商品描述
        String body = describe;
        // 附加数据
        String attach = userId;
        // 商户订单号
        String out_trade_no = orderNo;
        // 总金额以分为单位，不带小数点
        int intMoney = Integer.parseInt(finalmoney);
        // 订单生成的机器IP
        String spbill_create_ip = "127.0.0.1";
        //String spbill_create_ip = request.getRemoteAddr();
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        if(type.equals("1"))//1代表商品订单，2代表会员卡
        	notify_url = domin + notifyurl1;
        else
        	notify_url = domin + notifyurl2;
        // 请求方式
        String trade_type = "JSAPI";
        String openid = openId;
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);
        // 这里写的金额为1 分到时修改
        //packageParams.put("total_fee", "1");
        packageParams.put("total_fee", String.valueOf(intMoney));
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        packageParams.put("openid", openid);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(appid, appsecret, partnerkey);
        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>"
                + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>"
                + "<attach>" + attach + "</attach>"
                + "<out_trade_no>" + out_trade_no + "</out_trade_no>"
                +
                // 金额，这里写的1 分到时修改
                //"<total_fee>" + 1 + "</total_fee>" +
                "<total_fee>" + finalmoney + "</total_fee>" +
                 "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                 + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>" + trade_type
                + "</trade_type>" + "<openid>" + openid + "</openid>" + "</xml>";
        String allParameters = "";
        try {
            allParameters = reqHandler.genPackage(packageParams);
        } catch (Exception e) {
            json = "";
            return;
        }
        String prepay_id = "";
        try {
            prepay_id = new GetWxOrderno().getPayNo(createorderurl, xml);
            if (prepay_id.equals("")) {
                json = "";
                return;
            }
        } catch (Exception e1) {
            json = "";
            return; 
        }
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String appid2 = appid;
        String timestamp = Sha1Util.getTimeStamp();
        String nonceStr2 = nonce_str;
        String prepay_id2 = "prepay_id=" + prepay_id;
        String packages = prepay_id2;
        finalpackage.put("appId", appid2);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonceStr2);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        String finalsign = reqHandler.createSign(finalpackage);
        String url = "http://linglaodie.imwork.net:21098/pay.jsp?appid=" + appid2 + "&timeStamp=" + timestamp + "&nonceStr=" + nonceStr2
                + "&package=" + packages + "&sign=" + finalsign;
        System.out.println("http://linglaodie.imwork.net:21098/pay.jsp?appid=" + appid2 + "&timeStamp=" + timestamp + "&nonceStr=" + nonceStr2
                + "&package=" + packages + "&sign=" + finalsign);
        String returnJsonStr = "{\"appid\": \"" + appid2 + "\", \"timeStamp\": \"" + timestamp + "\", \"nonceStr\": \""
                + nonceStr2 + "\", \"package_\": \"" + packages + "\", \"sign\": \"" + finalsign + "\"}";
        
        json = returnJsonStr;
        //ActionUtil.sendJson((String) json);
        response.sendRedirect(url);
	}
	
	protected Object json = null;

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		if (json instanceof String) {
			json = json.toString().replaceAll("\r\n", "");
		}
		this.json = json;
	}

}
