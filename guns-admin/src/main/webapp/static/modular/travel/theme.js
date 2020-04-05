/**
 * 热门主题管理初始化
 */
var Theme = {
    id: "ThemeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Theme.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '热门主题', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Theme.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Theme.seItem = ids;
        return true;
    }
};

/**
 * 点击添加热门主题
 */
Theme.openAddTheme = function () {
    var index = layer.open({
        type: 2,
        title: '添加热门主题',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/theme/theme_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看热门主题详情
 */
Theme.openThemeDetail = function () {
    if (this.check()) {
        if(Theme.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '热门主题详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/theme/theme_update/' + Theme.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除热门主题
 */
Theme.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/theme/delete", function (data) {
            Feng.success("删除成功!");
            Theme.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("themeIds",this.seItem);
        ajax.start();
    }
};


/**
 * 查询热门主题列表
 */
Theme.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Theme.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Theme.initColumn();
    var table = new BSTable(Theme.id, "/theme/list", defaultColunms);
    table.setPaginationType("client");
    Theme.table = table.init();
});



$("#condition").keypress(function(e){
    if(e.keyCode==13){
        $("#btnSearch").click();
    }
});
