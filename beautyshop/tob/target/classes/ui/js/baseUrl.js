/*李东奎服务主机地址*/
//var Host = 'localhost:8580';
var Host = '182.92.4.200:8838';
/*商品、服务列表展示地址刘峰*/
var goodlists='http://'+Host+'/siims/szb/goods/goodsListByStoreId.jspx';
var servicelists='http://'+Host+'/siims/szb/service/serviceListByStoreId.jspx';
/*商圈商铺展示地址李东奎*/
var districtshops='http://'+Host+'/siims/szb/StoreInfoAction/getallstore.jspx';
/*添加购物车*/
var shoppingTrollyAdd = 'http://'+Host+'/siims/szb/ShoppingTrollyAction/AddShoppingTrolly.jspx';
/*通过openid获取某个用户所有的购物车条目*/
var shoppingTrollyAllShoppingTrollyData = 'http://'+Host+'/siims/szb/ShoppingTrollyAction/getAllShoppingTrollyDataByOpenid.jspx';
/*删除购物车的某个条目*/
var shoppingTrollyDelete = 'http://'+Host+'/siims/szb/ShoppingTrollyAction/deleteShoppingTrolly.jspx';
/*获取店铺下添加购物车的条目*/
var getShoppingTrollyByStore='http://'+Host+'/siims/szb/ShoppingTrollyAction/getShoppingTrollyByStore.jspx';
/*获取某个用户所有购物车的总和*/
var shoppingTrollySum = 'http://'+Host+'/siims/szb/ShoppingTrollyAction/getShoppingTrollySum.jspx';
/*预约表单获取数据*/
var yyGetData='http://'+Host+'/siims/szb/bespeak/getbespeakbydate.jspx';
/*添加预约的url*/
var addbespeak='http://'+Host+'/siims/szb/bespeak/addbespeak.jspx';
/*C类用户的服务订单uri*/
var GetbespeakorderforC='http://'+Host+'/siims/szb/bespeakorder/getbespeakorderforc.jspx';
/*取消预约订单*/
var cancelbespeak='http://'+Host+'/siims/szb/bespeak/cancelbespeak.jspx';
/*添加收货信息*/
var addReceivingInfo='http://'+Host+'/siims/szb/personal/addreceivinginfo.jspx';
/*删除收货信息*/
var delReceivingInfo='http://'+Host+'/siims/szb/personal/delreceivinginfo.jspx';
/*编辑收货信息*/
var editReceivingInfo='http://'+Host+'/siims/szb/personal/editreceivinginfo.jspx';
/*获取对应openID用户所有收货信息*/
var searchAllReceivingInfo='http://'+Host+'/siims/szb/personal/searchallreceivinginfo.jspx';
/*获取个人信息*/
var searchPersonalInfo='http://'+Host+'/siims/szb/personal/searchpersonalinfo.jspx';
/*获取单条收货信息*/
var searchReceivingInfo='http://'+Host+'/siims/szb/personal/searchreceivinginfo.jspx';
/*获取默认收货信息*/
var searchDefaultReceivingInfo='http://'+Host+'/siims/szb/personal/searchdefaultreceivinginfo.jspx';
/*生成商品订单信息*/
var create='http://'+Host+'/siims/szb/order/create.jspx';
var querybasicByCustomerId='http://'+Host+'/siims/szb/order/querybasicByCustomerId.jspx';
/*通过商品订单生成订单商品信息*/
var queryOrderGoodsByOrderId='http://'+Host+'/siims/szb/order/queryOrderGoodsByOrderId.jspx';
/*删除商品订单url*/
var deleteGoodsByOrderId='http://'+Host+'/siims/szb/order/delete.jspx';
/*更新商品订单状态*/
var updatestatus='http://'+Host+'/siims/szb/order/updateStatus.jspx';
/*会员卡部分*/
/*查询我的会员卡列表*/
var querymycardsmethod='http://'+Host+'/vipcard/b2c/querymycardsmethod.jspx';
/*获取可领取会员卡列表*/
var validlistmethod='http://'+Host+'/vipcard/b2c/validlistmethod.jspx';
/*获取已领取会员卡详情*/
var collareddetailmethod='http://'+Host+'/vipcard/b2c/collareddetailmethod.jspx';
/*获取未领取会员卡详情*/
var notcollareddetailmethod='http://'+Host+'/vipcard/b2c/notcollareddetailmethod.jspx';
/*获取我的账单详情*/
var mycardbillmethod='http://'+Host+'/vipcard/b2c/mycardbillmethod.jspx';
/*会员卡规格查询*/
var queryconfig='http://'+Host+'/vipcard/b2c/queryconfig.jspx';
/* 增加会员卡*/
var addconsumervipcard='http://'+Host+'/vipcard/b2c/addconsumervipcard.jspx';
/*会员信息确认*/
var queryconsumercard='http://'+Host+'/vipcard/b2c/queryconsumercard.jspx';
/*会员卡支付查询会员卡信息*/
var queryvipcardpay='http://'+Host+'/vipcard/b2c/queryvipcardpay.jspx';
/* 会员卡支付确认付款*/
var vipcardpay2='http://'+Host+'/vipcard/b2c/vipcardpay2.jspx';
/*获取店铺会员卡*/
var querymycardoftheshop='http://'+Host+'/vipcard/b2c/querymycardoftheshop.jspx';