/**
 * 景点管理初始化
 */
var ScenicSpot = {
    id: "ScenicSpotTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ScenicSpot.initColumn = function () {
    return [
            {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '景点名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '级别', field: 'grade', visible: true, align: 'center', valign: 'middle'},
            {title: '所属地区', field: 'regionName', visible: true, align: 'center', valign: 'middle'},
            {title: '产品类型', field: 'productTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '成人票价', field: 'adultTicket', visible: true, align: 'center', valign: 'middle'},
            {title: '儿童票价', field: 'childrenTicket', visible: true, align: 'center', valign: 'middle'},
            {title: '特殊人群票价', field: 'specialTiclet', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '开放时间', field: 'openTime', visible: true, align: 'center', valign: 'middle'},
            {title: '推荐', field: 'recommend', visible: true, align: 'center', valign: 'middle'},
            {title: '热点', field: 'hot', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ScenicSpot.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        ScenicSpot.seItem = ids;
        return true;
    }
};

/**
* 点击添加景点
*/
ScenicSpot.openAddScenicSpot = function () {
    var index = layer.open({
        type: 2,
        title: '添加景点',
        area: ['1175px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/scenicSpot/scenicSpot_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看景点详情
 */
ScenicSpot.openScenicSpotDetail = function () {
    if (this.check()) {
        if(ScenicSpot.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '景点详情',
                    area: ['1175px', '600px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/scenicSpot/scenicSpot_update/' + ScenicSpot.seItem[0]
            });
            this.layerIndex = index;
            layer.full(index);
        }
    }
};

/**
 * 删除景点
 */
ScenicSpot.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/scenicSpot/delete", function (data) {
            Feng.success("删除成功!");
            ScenicSpot.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("scenicSpotIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询景点列表
 */
ScenicSpot.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ScenicSpot.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ScenicSpot.initColumn();
    var table = new BSTable(ScenicSpot.id, "/scenicSpot/list", defaultColunms);
    table.setPaginationType("client");
    ScenicSpot.table = table.init();
});
