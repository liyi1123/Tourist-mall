/**
 * 订单管理初始化
 */
var Orders = {
    id: "OrdersTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Orders.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '父级编号', field: 'parentId', visible: true, align: 'center', valign: 'middle'},
            {title: '订单类型', field: 'orderType', visible: true, align: 'center', valign: 'middle'},
            {title: '产品ID', field: 'productId', visible: true, align: 'center', valign: 'middle'},
            {title: '买家ID', field: 'buyerId', visible: true, align: 'center', valign: 'middle'},
            {title: '收货地址ID', field: 'addressId', visible: true, align: 'center', valign: 'middle'},
            {title: '订单编号', field: 'orderNum', visible: true, align: 'center', valign: 'middle'},
            {title: '数量', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '最晚到店时间', field: 'lastTime', visible: true, align: 'center', valign: 'middle'},
            {title: '入住时间', field: 'checkInTime', visible: true, align: 'center', valign: 'middle'},
            {title: '离店时间', field: 'checkOutTime', visible: true, align: 'center', valign: 'middle'},
            {title: '总金额', field: 'totalMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '使用积分', field: 'usingIntegral', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'desc', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Orders.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Orders.seItem = ids;
        return true;
    }
};

/**
 * 点击添加订单
 */
Orders.openAddOrders = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/orders/orders_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单详情
 */
Orders.openOrdersDetail = function () {
    if (this.check()) {
        if(Orders.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '订单详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/orders/orders_update/' + Orders.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除订单
 */
Orders.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/orders/delete", function (data) {
            Feng.success("删除成功!");
            Orders.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ordersIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询订单列表
 */
Orders.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Orders.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Orders.initColumn();
    var table = new BSTable(Orders.id, "/orders/list", defaultColunms);
    table.setPaginationType("client");
    Orders.table = table.init();
});
