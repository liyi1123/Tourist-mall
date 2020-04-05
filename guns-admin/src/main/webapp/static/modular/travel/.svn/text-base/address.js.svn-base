/**
 * 会员账户管理初始化
 */
var Address = {
    id: "AddressTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Address.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '买家收货地址表', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'sys_user的ID', field: 'sysUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '收货人姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '地址信息', field: 'addressInfo', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Address.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{

        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        Address.seItem = ids;
        return true;
    }
};

/**
 * 点击添加会员账户
 */
Address.openAddAddress = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员账户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/address/address_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员账户详情
 */
Address.openAddressDetail = function () {
    if (this.check()) {
        if(Address.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '会员账户详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/address/address_update/' + Address.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除会员账户
 */
Address.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/address/delete", function (data) {
            Feng.success("删除成功!");
            Address.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("addressIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询会员账户列表
 */
Address.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Address.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Address.initColumn();
    var table = new BSTable(Address.id, "/address/list", defaultColunms);
    table.setPaginationType("client");
    Address.table = table.init();
});
