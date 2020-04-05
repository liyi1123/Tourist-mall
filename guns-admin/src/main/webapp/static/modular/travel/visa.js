/**
 * 签证管理管理初始化
 */
var Visa = {
    id: "VisaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Visa.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '签证国家', field: 'visaNationName', visible: true, align: 'center', valign: 'middle'},
            {title: '签证类型', field: 'visaTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '签发城市', field: 'regionName', visible: true, align: 'center', valign: 'middle'},
            {title: '有效期', field: 'usefulLife', visible: true, align: 'center', valign: 'middle'},
            {title: '办理用时', field: 'needTime', visible: true, align: 'center', valign: 'middle'},
            {title: '最长停留时间', field: 'maxDurationOfStay', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
Visa.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Visa.seItem = ids;
        return true;
    }
};

/**
 * 点击添加签证管理
 */
Visa.openAddVisa = function () {
    var index = layer.open({
        type: 2,
        title: '添加签证管理',
        area: ['1175px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/visa/visa_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看签证管理详情
 */
Visa.openVisaDetail = function () {
    if (this.check()) {
        if(Visa.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '签证管理详情',
                    area: ['1175px', '600px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/visa/visa_update/' + Visa.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除签证管理
 */
Visa.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/visa/delete", function (data) {
            Feng.success("删除成功!");
            Visa.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("visaIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询签证管理列表
 */
Visa.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Visa.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Visa.initColumn();
    var table = new BSTable(Visa.id, "/visa/list", defaultColunms);
    table.setPaginationType("client");
    Visa.table = table.init();
});
