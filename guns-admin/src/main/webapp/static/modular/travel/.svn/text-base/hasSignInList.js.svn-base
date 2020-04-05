/**
 * 管理初始化
 */
var HasSignInList = {
    id: "HasSignInTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HasSignInList.initColumn = function () {
    return [
            {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            /*{title: '父级ID', field: 'parentId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '课目名称', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
            {title: '签到时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle',
                formatter: function (value) {
                    return new Date(parseInt(value) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
                }},
            {title: '备注', field: 'desc2', visible: true, align: 'center', valign: 'middle'}


    ];
};

/**
 * 检查是否选中
 */
HasSignInList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{


        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        HasSignInList.seItem = ids;
        return true;
    }
};

/**
 * 点击添加
 */
HasSignInList.openAddSignIn = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1198px', '546px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/signIn/signIn_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
HasSignInList.openSignInDetail = function () {
    if (this.check()) {
        if(HasSignInList.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/signIn/signIn_update/' + HasSignInList.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除
 */
HasSignInList.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/signIn/delete", function (data) {
            Feng.success("删除成功!");
            HasSignInList.table.refresh();
        }, function (data) {
            Feng.error("删除失败!");
        });
        ajax.set("signInIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询列表
 */
HasSignInList.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    HasSignInList.table.refresh({query: queryData});
};



$(function () {
    var defaultColunms = HasSignInList.initColumn();
    var table = new BSTable(HasSignInList.id, "/signIn/hasSignInList", defaultColunms);
    table.setPaginationType("client");
    var id = window.parent.SignIn.seItem[0];
    table.setQueryParams({"parentId" : id,"condition" : $("#condition").val()});        //ajax传入参数,用set()和setDate()方法无效,所以用setQueryParams()发现有效
    HasSignInList.table = table.init();


});
