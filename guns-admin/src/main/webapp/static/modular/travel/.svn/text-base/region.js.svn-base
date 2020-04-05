/**
 * 所属区域管理初始化
 */
var Region = {
    id: "RegionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Region.initColumn = function () {
    return [
            {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '父级ID', field: 'parentId', visible: true, align: 'center', valign: 'middle'}/*,
            {title: '父级ID列表', field: 'parentIdList', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
Region.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Region.seItem = ids;
        return true;
    }
};

/**
 * 点击添加所属区域
 */
Region.openAddRegion = function () {
    var index = layer.open({
        type: 2,
        title: '添加所属区域',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/region/region_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看所属区域详情
 */
Region.openRegionDetail = function () {
    if (this.check()) {
        if(Region.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '所属区域详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/region/region_update/' + Region.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除所属区域
 */
Region.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/region/delete", function (data) {
                Feng.success("删除成功!");
                Region.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("regionIds",Region.seItem);
            ajax.start();
        };
        Feng.confirm("确定刪除该区域?", operation);
    }
};

/**
 * 查询所属区域列表
 */
Region.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Region.table.refresh({query: queryData});
};

/* 页面加载完成后,加载region List数据 */
$(function () {
    var defaultColunms = Region.initColumn();
    var table = new BSTreeTable(Region.id, "/region/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.init();
    Region.table = table;

});

$("#condition").keypress(function(e){
    if(e.keyCode==13){
        $("#btnSearch").click();
    }
});

