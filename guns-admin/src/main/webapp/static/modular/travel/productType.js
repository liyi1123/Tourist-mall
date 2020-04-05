/**
 * 产品类型管理初始化
 */
var ProductType = {
    id: "ProductTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProductType.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '产品类型名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'desc', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ProductType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        ProductType.seItem = ids;
        return true;
    }
};

/**
 * 点击添加产品类型
 */
ProductType.openAddProductType = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品类型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/productType/productType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看产品类型详情
 */
ProductType.openProductTypeDetail = function () {
    if (this.check()) {
        if(ProductType.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '产品类型详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/productType/productType_update/' + ProductType.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除产品类型
 */
ProductType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/productType/delete", function (data) {
            Feng.success("删除成功!");
            ProductType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("productTypeIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询产品类型列表
 */
ProductType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ProductType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ProductType.initColumn();
    var table = new BSTable(ProductType.id, "/productType/list", defaultColunms);
    table.setPaginationType("client");
    ProductType.table = table.init();
});


$("#condition").keypress(function(e){
    if(e.keyCode==13){
        $("#btnSearch").click();
    }
});

