/**
 * 初始化产品类型详情对话框
 */
var ProductTypeInfoDlg = {
    productTypeInfoData : {}
};

/**
 * 清除数据
 */
ProductTypeInfoDlg.clearData = function() {
    this.productTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductTypeInfoDlg.set = function(key, val) {
    this.productTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProductTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.ProductType.layerIndex);
}

/**
 * 收集数据
 */
ProductTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('desc');
}

/**
 * 提交添加
 */
ProductTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/productType/add", function(data){
        Feng.success("添加成功!");
        window.parent.ProductType.table.refresh();
        ProductTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProductTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/productType/update", function(data){
        Feng.success("修改成功!");
        window.parent.ProductType.table.refresh();
        ProductTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productTypeInfoData);
    ajax.start();
}

$("#name").keypress(function(e){
    if(e.keyCode==13){
        $("#ensure").click();
    }
});
