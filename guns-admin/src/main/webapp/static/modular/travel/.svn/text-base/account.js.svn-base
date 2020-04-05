/**
 * 会员账户管理初始化
 */
var Account = {
    id: "AccountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Account.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '帐户', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'sys_user的ID', field: 'sysUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '余额', field: 'money', visible: true, align: 'center', valign: 'middle'},
            {title: '积分', field: 'integral', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Account.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Account.seItem = ids;
        return true;
    }
};

/**
 * 点击添加会员账户
 */
Account.openAddAccount = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员账户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/account/account_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员账户详情
 */
Account.openAccountDetail = function () {
    if (this.check()) {
        if(Account.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '会员账户详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/account/account_update/' + Account.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除会员账户
 */
Account.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/account/delete", function (data) {
            Feng.success("删除成功!");
            Account.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("accountIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询会员账户列表
 */
Account.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Account.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Account.initColumn();
    var table = new BSTable(Account.id, "/account/list", defaultColunms);
    table.setPaginationType("client");
    Account.table = table.init();
});
