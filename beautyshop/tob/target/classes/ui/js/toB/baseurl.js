/*
 * 存放所有连接地址
 */

var domin = "182.92.4.200:8838";
var test = "localhost:8580"

/*通过shopId获取所有的订单本身信息*/
var getOrderIdByShopId="http://"+domin+"/siims/szb/order/queryAllInfoByShopId.jspx";
/*通过orderId获取订单中商品信息*/
var getGoodListByOrderId="http://"+domin+"/siims/szb/order/queryOrderGoodsByOrderId.jspx";
/*我要接单按钮*/
var getGoodOrder="http://"+domin+"/siims/szb/order/updateStatus.jspx";

