/**
 * 租车服务管理初始化
 */
var CarBrand = {
    id: "CarBrandTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CarBrand.initColumn = function () {
    return [
            {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '车辆品牌', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '信息', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CarBrand.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        CarBrand.seItem = ids;
        return true;
    }
};

/**
 * 点击添加租车服务
 */
CarBrand.openAddCarBrand = function () {
    var index = layer.open({
        type: 2,
        title: '添加租车服务',
        area: ['1175px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/carBrand/carBrand_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看租车服务详情
 */
CarBrand.openCarBrandDetail = function () {
    if (this.check()) {
        if(CarBrand.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '租车服务详情',
                    area: ['1175px', '600px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/carBrand/carBrand_update/' + CarBrand.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除租车服务
 */
CarBrand.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/carBrand/delete", function (data) {
            Feng.success("删除成功!");
            CarBrand.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("carBrandIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询租车服务列表
 */
CarBrand.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CarBrand.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CarBrand.initColumn();
    var table = new BSTreeTable(CarBrand.id, "/carBrand/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    // table.setPaginationType("client");
    CarBrand.table = table.init();
});
