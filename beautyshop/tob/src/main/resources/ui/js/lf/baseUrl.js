//var domin = "182.92.4.200:8838";
//var domin = "localhost:8580";
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

var test = "localhost:8580";

/*获取某个用户所有购物车的总和*/
var shoppingTrollySum = '/siims/szb/ShoppingTrollyAction/getShoppingTrollySum.jspx';
/*预约表单获取数据*/
var yyGetData='/siims/szb/bespeakB/getbespeakbydateB.jspx';
/*添加预约的url*/
//var addbespeak='/siims/szb/bespeak/addbespeak.jspx';
/*C类用户的服务订单uri*/
//var GetbespeakorderforC='/siims/szb/bespeakorder/getbespeakorderforc.jspx';
/*取消预约订单*/
//var cancelbespeak='/siims/szb/bespeak/cancelbespeak.jspx';
/*收货地址*/


var goodlists='/siims/szb/goods/goodsListToB.jspx';//lf
var deletegoods='/siims/szb/goods/deleteGoods.jspx';//lf
var changesaleornot='/siims/szb/goods/changeSaleOrNot.jspx';//lf
var changeupornot='/siims/szb/goods/changeUpOrNot.jspx';//lf
var addgoods='/siims/szb/goods/addGoodsByStoreId.jspx';//lf
var editgoods='/siims/szb/goods/goodsShow.jspx';//lf
var changegoods='/siims/szb/goods/editGoodsById.jspx';//lf
var servicelists='/siims/szb/service/serviceListToBByStoreId.jspx';//lf
var addservices='/siims/szb/service/addService.jspx';//lf
var changeservices='/siims/szb/service/editServiceByDetailId.jspx';//lf
var editservices='/siims/szb/service/serviceShow.jspx';//lf
var getdetailid='/siims/szb/service/getDetailIdByServiceId.jspx';//lf
var deleteservice='/siims/szb/service/deleteServiceById.jspx'//lf
var uploadpic='/siims/szb/picture/uploadImage.jspx';//lf
var lidongkuiIP='localhost';
var loginPC='/siims/szb/StoreInfoAction/storeLoginpc.jspx';//ldk
var userlists='/siims/szb/StoreInfoAction/getshopinfo.jspx';//ldk
