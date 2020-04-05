/**
 * 初始化热门主题详情对话框
 */
var ThemeInfoDlg = {
    themeInfoData : {}
};

/**
 * 清除数据
 */
ThemeInfoDlg.clearData = function() {
    this.themeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ThemeInfoDlg.set = function(key, val) {
    this.themeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ThemeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ThemeInfoDlg.close = function() {
    parent.layer.close(window.parent.Theme.layerIndex);
}

/**
 * 收集数据
 */
ThemeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
ThemeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/theme/add", function(data){
        Feng.success("添加成功!");
        window.parent.Theme.table.refresh();
        ThemeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.themeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ThemeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/theme/update", function(data){
        Feng.success("修改成功!");
        window.parent.Theme.table.refresh();
        ThemeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.themeInfoData);
    ajax.start();
}

$("#name").keypress(function(e){
    if(e.keyCode==13){
        $("#btnSearch").click();
    }
});

