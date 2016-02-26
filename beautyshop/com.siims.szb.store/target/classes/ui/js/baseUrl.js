/*李东奎服务主机地址*/
//var Host = 'localhost:8580';
//var Host = 'http://182.92.4.200:8838';
/*商品、服务列表展示地址刘峰*/
var goodlists='/siims/szb/goods/goodsListByStoreId.jspx';
var servicelists='/siims/szb/service/serviceListByStoreId.jspx';
/*商圈商铺展示地址李东奎*/
var districtshops='/siims/szb/StoreInfoAction/getallstore.jspx';
/*添加购物车*/
var shoppingTrollyAdd = '/siims/szb/ShoppingTrollyAction/AddShoppingTrolly.jspx';
/*通过openid获取某个用户所有的购物车条目*/
var shoppingTrollyAllShoppingTrollyData = '/siims/szb/ShoppingTrollyAction/getAllShoppingTrollyDataByOpenid.jspx';
/*删除购物车的某个条目*/
var shoppingTrollyDelete = '/siims/szb/ShoppingTrollyAction/deleteShoppingTrolly.jspx';
/*获取店铺下添加购物车的条目*/
var getShoppingTrollyByStore='/siims/szb/ShoppingTrollyAction/getShoppingTrollyByStore.jspx';
/*获取某个用户所有购物车的总和*/
var shoppingTrollySum = '/siims/szb/ShoppingTrollyAction/getShoppingTrollySum.jspx';
/*预约表单获取数据*/
var yyGetData='/siims/szb/bespeak/getbespeakbydate.jspx';
/*添加预约的url*/
var addbespeak='/siims/szb/bespeak/addbespeak.jspx';
/*C类用户的服务订单uri*/
var GetbespeakorderforC='/siims/szb/bespeakorder/getbespeakorderforc.jspx';
/*取消预约订单*/
var cancelbespeak='/siims/szb/bespeak/cancelbespeak.jspx';
/*添加收货信息*/
var addReceivingInfo='/siims/szb/personal/addreceivinginfo.jspx';
/*删除收货信息*/
var delReceivingInfo='/siims/szb/personal/delreceivinginfo.jspx';
/*编辑收货信息*/
var editReceivingInfo='/siims/szb/personal/editreceivinginfo.jspx';
/*获取对应openID用户所有收货信息*/
var searchAllReceivingInfo='/siims/szb/personal/searchallreceivinginfo.jspx';
/*获取个人信息*/
var searchPersonalInfo='/siims/szb/personal/searchpersonalinfo.jspx';
/*获取单条收货信息*/
var searchReceivingInfo='/siims/szb/personal/searchreceivinginfo.jspx';
/*获取默认收货信息*/
var searchDefaultReceivingInfo='/siims/szb/personal/searchdefaultreceivinginfo.jspx';
/*生成商品订单信息*/
var create='/siims/szb/order/create.jspx';
var querybasicByCustomerId='/siims/szb/order/querybasicByCustomerId.jspx';
/*通过商品订单生成订单商品信息*/
var queryOrderGoodsByOrderId='/siims/szb/order/queryOrderGoodsByOrderId.jspx';
/*删除商品订单url*/
var deleteGoodsByOrderId='/siims/szb/order/delete.jspx';
/*更新商品订单状态*/
var updatestatus='/siims/szb/order/updateStatus.jspx';
/*会员卡部分*/
/*查询我的会员卡列表*/
var querymycardsmethod='/vipcard/b2c/querymycardsmethod.jspx';
/*获取可领取会员卡列表*/
var validlistmethod='/vipcard/b2c/validlistmethod.jspx';
/*获取已领取会员卡详情*/
var collareddetailmethod='/vipcard/b2c/collareddetailmethod.jspx';
/*获取未领取会员卡详情*/
var notcollareddetailmethod='/vipcard/b2c/notcollareddetailmethod.jspx';
/*获取我的账单详情*/
var mycardbillmethod='/vipcard/b2c/mycardbillmethod.jspx';
/*会员卡规格查询*/
var queryconfig='/vipcard/b2c/queryconfig.jspx';
/* 增加会员卡*/
var addconsumervipcard='/vipcard/b2c/addconsumervipcard.jspx';
/*会员信息确认*/
var queryconsumercard='/vipcard/b2c/queryconsumercard.jspx';
/*会员卡支付查询会员卡信息*/
var queryvipcardpay='/vipcard/b2c/queryvipcardpay.jspx';
/* 会员卡支付确认付款*/
var vipcardpay2='/vipcard/b2c/vipcardpay2.jspx';
/*获取店铺会员卡*/
var querymycardoftheshop='/vipcard/b2c/querymycardoftheshop.jspx';
/*检测会员卡支付的密码是否正确*/
var vipcardcheckpassword='/vipcard/b2c/vipcardcheckpassword.jspx';

var alipayaction='/siims/szb/alipay/alipay.jspx';