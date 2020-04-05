/**
 * 选配服务管理初始化
 */
var Services = {
    id: "ServicesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Services.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '属性内容', field: 'val', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Services.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Services.seItem = ids;
        return true;
    }
};

/**
 * 点击添加选配服务
 */
Services.openAddServices = function () {
    var index = layer.open({
        type: 2,
        title: '添加选配服务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/services/services_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看选配服务详情
 */
Services.openServicesDetail = function () {
    if (this.check()) {
        if(Services.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '选配服务详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/services/services_update/' + Services.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除选配服务
 */
Services.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/services/delete", function (data) {
            Feng.success("删除成功!");
            Services.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("servicesIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询选配服务列表
 */
Services.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Services.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Services.initColumn();
    var table = new BSTable(Services.id, "/services/list", defaultColunms);
    table.setPaginationType("client");
    Services.table = table.init();
});
