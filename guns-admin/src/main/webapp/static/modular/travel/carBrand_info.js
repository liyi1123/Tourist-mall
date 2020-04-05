/**
 * 初始化租车服务详情对话框
 */
var CarBrandInfoDlg = {
    carBrandInfoData : {},
    zTreeInstance : null,
};

/**
 * 清除数据
 */
CarBrandInfoDlg.clearData = function() {
    this.carBrandInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CarBrandInfoDlg.set = function(key, val) {
    this.carBrandInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CarBrandInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CarBrandInfoDlg.close = function() {
    parent.layer.close(window.parent.CarBrand.layerIndex);
}

/**
 * 收集数据
 */
CarBrandInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('parentId')
    .set('comment');
}

/**
 * 提交添加
 */
CarBrandInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/carBrand/add", function(data){
        Feng.success("添加成功!");
        window.parent.CarBrand.table.refresh();
        CarBrandInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.carBrandInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CarBrandInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/carBrand/update", function(data){
        Feng.success("修改成功!");
        window.parent.CarBrand.table.refresh();
        CarBrandInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.carBrandInfoData);
    ajax.start();
}



/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
CarBrandInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#carBrandName").attr("value", CarBrandInfoDlg.zTreeInstance.getSelectedVal());     //改
    $("#carBrandId").attr("value", treeNode.id);                                              //改
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
CarBrandInfoDlg.showDeptSelectTree = function () {
    var pName = $("#carBrandName");                     //此处改成页面input_id
    var pNameOffset = $("#carBrandName").offset();      //此处改成页面input_id
    $("#parentDeptMenu").css({
        left: pNameOffset.left + "px",
        top: pNameOffset.top * 2 + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
CarBrandInfoDlg.hideDeptSelectTree = function () {
    $("#parentDeptMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu" || $(event.target).parents("#parentDeptMenu").length > 0)) {
        CarBrandInfoDlg.hideDeptSelectTree();
    }
}

$(function() {

    //创建ztree实例
    var ztree = new $ZTree("parentDeptMenuTree", "/carBrand/tree");
    //ztree绑定对象fn
    ztree.bindOnClick(CarBrandInfoDlg.onClickDept);
    ztree.init();
    CarBrandInfoDlg.zTreeInstance = ztree;
    /**
     * -- 初始化summernote富文本 --
     */
    /*CarBrandInfoDlg.editor1 = $("#comment").summernote({lang:"zh-CN",focus:true,height:400});
    $("#comment").code($("#commentVal").val());*/

});
