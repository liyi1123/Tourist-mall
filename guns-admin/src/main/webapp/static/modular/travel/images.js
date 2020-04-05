/**
 * 图片管理管理初始化
 */
var Images = {
    id: "ImagesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Images.initColumn = function () {
    return [
            {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'prefix', visible: true, align: 'center', valign: 'middle'},
            {title: '缩略图', field: 'name', visible: true, align: 'center', valign: 'middle',
                formatter: function (value){
                    var s = '<a class = "view"  href="javascript:void(0)">' +
                        '<img style="width:70px;height:70px;"  src="'+Feng.ctxPath+"/kaptcha/"+value+'" />' +
                        '</a>';
                    return s;
            }}

    ];
};

/**
 * 检查是否选中
 */
Images.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Images.seItem = ids;
        return true;
    }
};

/**
 * 点击添加图片管理
 */
Images.openAddImages = function () {
    var index = layer.open({
        type: 2,
        title: '添加图片管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/images/images_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看图片管理详情
 */
Images.openImagesDetail = function () {
    if (this.check()) {
        if(Images.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '图片管理详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/images/images_update/' + Images.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除图片管理
 */
Images.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/images/delete", function (data) {
            Feng.success("删除成功!");
            Images.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("imagesIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询图片管理列表
 */
Images.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Images.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Images.initColumn();
    var table = new BSTable(Images.id, "/images/list", defaultColunms);
    table.setPaginationType("client");
    Images.table = table.init();
});
