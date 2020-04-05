/**
 * 初始化签证国家详情对话框
 */
var VisaNationInfoDlg = {
    visaNationInfoData : {}
};

/**
 * 清除数据
 */
VisaNationInfoDlg.clearData = function() {
    this.visaNationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VisaNationInfoDlg.set = function(key, val) {
    this.visaNationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VisaNationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VisaNationInfoDlg.close = function() {
    parent.layer.close(window.parent.VisaNation.layerIndex);
}

/**
 * 收集数据
 */
VisaNationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('parentId')
    .set('comment');
}

/**
 * 提交添加
 */
VisaNationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/visaNation/add", function(data){
        Feng.success("添加成功!");
        window.parent.VisaNation.table.refresh();
        VisaNationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.visaNationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VisaNationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/visaNation/update", function(data){
        Feng.success("修改成功!");
        window.parent.VisaNation.table.refresh();
        VisaNationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.visaNationInfoData);
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
VisaNationInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#visaNationName").attr("value", VisaNationInfoDlg.zTreeInstance.getSelectedVal());     //改
    $("#parentId").attr("value", treeNode.id);                                              //改
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
VisaNationInfoDlg.showDeptSelectTree = function () {
    var pName = $("#visaNationName");                     //此处改成页面input_id
    var pNameOffset = $("#visaNationName").offset();      //此处改成页面input_id
    $("#parentDeptMenu").css({
        left: pNameOffset.left + "px",
        top: pNameOffset.top * 2 + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
VisaNationInfoDlg.hideDeptSelectTree = function () {
    $("#parentDeptMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu" || $(event.target).parents("#parentDeptMenu").length > 0)) {
        VisaNationInfoDlg.hideDeptSelectTree();
    }
}

$(function() {

    //创建ztree实例
    var ztree = new $ZTree("parentDeptMenuTree", "/visaNation/tree");
    //ztree绑定对象fn
    ztree.bindOnClick(VisaNationInfoDlg.onClickDept);
    ztree.init();
    VisaNationInfoDlg.zTreeInstance = ztree;

});
