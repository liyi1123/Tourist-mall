/**
 * 初始化会员账户详情对话框
 */
var AddressInfoDlg = {
    addressInfoData : {}
};

/**
 * 清除数据
 */
AddressInfoDlg.clearData = function() {
    this.addressInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AddressInfoDlg.set = function(key, val) {
    this.addressInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AddressInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AddressInfoDlg.close = function() {
    parent.layer.close(window.parent.Address.layerIndex);
}

/**
 * 收集数据
 */
AddressInfoDlg.collectData = function() {
    this
    .set('id')
    .set('sysUserId')
    .set('userName')
    .set('phone')
    .set('addressInfo');
}

/**
 * 提交添加
 */
AddressInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/address/add", function(data){
        Feng.success("添加成功!");
        window.parent.Address.table.refresh();
        AddressInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.addressInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AddressInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/address/update", function(data){
        Feng.success("修改成功!");
        window.parent.Address.table.refresh();
        AddressInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.addressInfoData);
    ajax.start();
}

$(function() {

});
