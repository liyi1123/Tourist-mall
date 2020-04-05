/**
 * 租车管理管理初始化
 */
var RentCar = {
    id: "RentCarTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RentCar.initColumn = function () {
    return [
        {fieldasderew: 'selectItem', checkbox: true},
            {title: '租车', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '座位数', field: 'seatCount', visible: true, align: 'center', valign: 'middle'},
            {title: '车辆品牌', field: 'carBrandName', visible: true, align: 'center', valign: 'middle'},
            {title: '变速箱', field: 'gearBox', visible: true, align: 'center', valign: 'middle'},
            {title: '日租金', field: 'rent', visible: true, align: 'center', valign: 'middle'},
            {title: '租车类型', field: 'productTypeName', visible: true, align: 'center', valign: 'middle'},
            /*{title: '介绍', field: 'content', visible: true, align: 'center', valign: 'middle'}*/
            {title: '图片', field: 'img1', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
RentCar.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        RentCar.seItem = ids;
        return true;
    }
};

/**
 * 点击添加租车管理
 */
RentCar.openAddRentCar = function () {
    var index = layer.open({
        type: 2,
        title: '添加租车管理',
        area: ['1175px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rentCar/rentCar_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看租车管理详情
 */
RentCar.openRentCarDetail = function () {
    if (this.check()) {
        if(RentCar.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '租车管理详情',
                    area: ['1175px', '600px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/rentCar/rentCar_update/' + RentCar.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除租车管理
 */
RentCar.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/rentCar/delete", function (data) {
            Feng.success("删除成功!");
            RentCar.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("rentCarIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询租车管理列表
 */
RentCar.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    RentCar.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RentCar.initColumn();
    var table = new BSTable(RentCar.id, "/rentCar/list", defaultColunms);
    table.setPaginationType("client");
    RentCar.table = table.init();
});
