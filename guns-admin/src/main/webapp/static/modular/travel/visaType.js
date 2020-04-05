/**
 * 护照类型管理初始化
 */
var VisaType = {
    id: "VisaTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VisaType.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '签证类型', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VisaType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        VisaType.seItem = ids;
        return true;
    }
};

/**
 * 点击添加护照类型
 */
VisaType.openAddVisaType = function () {
    var index = layer.open({
        type: 2,
        title: '添加护照类型',
        area: ['1175px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/visaType/visaType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看护照类型详情
 */
VisaType.openVisaTypeDetail = function () {
    if (this.check()) {
        if(VisaType.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '护照类型详情',
                    area: ['1175px', '600px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/visaType/visaType_update/' + VisaType.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除护照类型
 */
VisaType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/visaType/delete", function (data) {
            Feng.success("删除成功!");
            VisaType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("visaTypeIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询护照类型列表
 */
VisaType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VisaType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VisaType.initColumn();
    var table = new BSTable(VisaType.id, "/visaType/list", defaultColunms);
    table.setPaginationType("client");
    VisaType.table = table.init();
});
