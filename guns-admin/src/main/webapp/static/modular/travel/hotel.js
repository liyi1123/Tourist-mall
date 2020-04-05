/**
 * 酒店业务管理初始化
 */
var Hotel = {
    id: "HotelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Hotel.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '酒店', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '酒店星级', field: 'grade', visible: true, align: 'center', valign: 'middle'},
            {title: '所属地区', field: 'regionName', visible: true, align: 'center', valign: 'middle'},
            {title: '酒店类型', field: 'productTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '折扣', field: 'discount', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'tel', visible: true, align: 'center', valign: 'middle'},
            {title: '营业时间', field: 'openTime', visible: true, align: 'center', valign: 'middle'},
            {title: '床型', field: 'bedType', visible: true, align: 'center', valign: 'middle'},
            {title: '热点指数', field: 'hot', visible: true, align: 'center', valign: 'middle'},
            {title: '推荐指数', field: 'recommend', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
Hotel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Hotel.seItem = ids;
        return true;
    }
};

/**
 * 点击添加酒店业务
 */
Hotel.openAddHotel = function () {
    var index = layer.open({
        type: 2,
        title: '添加酒店业务',
        area: ['1175px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotel/hotel_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看酒店业务详情
 */
Hotel.openHotelDetail = function () {
    if (this.check()) {
        if(Hotel.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '酒店业务详情',
                    area: ['1175px', '600px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/hotel/hotel_update/' + Hotel.seItem[0]
            });
            this.layerIndex = index;
            layer.full(index);
        }
    }
};

/**
 * 删除酒店业务
 */
Hotel.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotel/delete", function (data) {
            Feng.success("删除成功!");
            Hotel.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询酒店业务列表
 */
Hotel.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Hotel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Hotel.initColumn();
    var table = new BSTable(Hotel.id, "/hotel/list", defaultColunms);
    table.setPaginationType("client");
    Hotel.table = table.init();
});
