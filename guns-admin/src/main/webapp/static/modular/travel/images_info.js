/**
 * 初始化图片管理详情对话框
 */
var ImagesInfoDlg = {
    imagesInfoData : {}
};

/**
 * 清除数据
 */
ImagesInfoDlg.clearData = function() {
    this.imagesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ImagesInfoDlg.set = function(key, val) {
    this.imagesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ImagesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ImagesInfoDlg.close = function() {
    parent.layer.close(window.parent.Images.layerIndex);
}

/**
 * 收集数据
 */
ImagesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('prefix')
    .set('name')
    .set('sysUserId')
    .set('status')
    .set('desc');
}

/**
 * 提交添加
 */
ImagesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/images/add", function(data){
        Feng.success("添加成功!");
        window.parent.Images.table.refresh();
        ImagesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.imagesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ImagesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/images/update", function(data){
        Feng.success("修改成功!");
        window.parent.Images.table.refresh();
        ImagesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.imagesInfoData);
    ajax.start();
}

$(function() {

});
