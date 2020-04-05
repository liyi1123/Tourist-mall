/**
 * 酒店品牌管理初始化
 */
var Brand = {
    id: "BrandTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Brand.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '酒店品牌', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '信息', field: 'desc', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Brand.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Brand.seItem = ids;
        return true;
    }
};

/**
 * 点击添加酒店品牌
 */
Brand.openAddBrand = function () {
    var index = layer.open({
        type: 2,
        title: '添加酒店品牌',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/brand/brand_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看酒店品牌详情
 */
Brand.openBrandDetail = function () {
    if (this.check()) {
        if(Brand.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '酒店品牌详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/brand/brand_update/' + Brand.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除酒店品牌
 */
Brand.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/brand/delete", function (data) {
            Feng.success("删除成功!");
            Brand.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("brandIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询酒店品牌列表
 */
Brand.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Brand.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Brand.initColumn();
    var table = new BSTable(Brand.id, "/brand/list", defaultColunms);
    table.setPaginationType("client");
    Brand.table = table.init();
});
