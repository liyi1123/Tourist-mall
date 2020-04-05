/**
 * 签证国家管理初始化
 */
var VisaNation = {
    id: "VisaNationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VisaNation.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '五大洲(国名)', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '父级ID', field: 'parentId', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VisaNation.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        VisaNation.seItem = ids;
        return true;
    }
};

/**
 * 点击添加签证国家
 */
VisaNation.openAddVisaNation = function () {
    var index = layer.open({
        type: 2,
        title: '添加签证国家',
        area: ['1175px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/visaNation/visaNation_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看签证国家详情
 */
VisaNation.openVisaNationDetail = function () {
    if (this.check()) {
        if(VisaNation.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '签证国家详情',
                    area: ['1175px', '600px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/visaNation/visaNation_update/' + VisaNation.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除签证国家
 */
VisaNation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/visaNation/delete", function (data) {
            Feng.success("删除成功!");
            VisaNation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("visaNationIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询签证国家列表
 */
VisaNation.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VisaNation.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VisaNation.initColumn();
    var table = new BSTreeTable(VisaNation.id, "/visaNation/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    // table.setPaginationType("client");
    VisaNation.table = table.init();
});
