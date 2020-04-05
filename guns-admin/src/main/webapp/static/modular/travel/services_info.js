/**
 * 初始化选配服务详情对话框
 */
var ServicesInfoDlg = {
    servicesInfoData : {}
};

/**
 * 清除数据
 */
ServicesInfoDlg.clearData = function() {
    this.servicesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServicesInfoDlg.set = function(key, val) {
    this.servicesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServicesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServicesInfoDlg.close = function() {
    parent.layer.close(window.parent.Services.layerIndex);
}

/**
 * 收集数据
 */
ServicesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('val');
}

/**
 * 提交添加
 */
ServicesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/services/add", function(data){
        Feng.success("添加成功!");
        window.parent.Services.table.refresh();
        ServicesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.servicesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServicesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/services/update", function(data){
        Feng.success("修改成功!");
        window.parent.Services.table.refresh();
        ServicesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.servicesInfoData);
    ajax.start();
}

$(function() {

});
