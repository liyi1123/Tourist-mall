/**
 * 初始化签证管理详情对话框
 */
var VisaInfoDlg = {
    visaInfoData : {}
};

/**
 * 清除数据
 */
VisaInfoDlg.clearData = function() {
    this.visaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VisaInfoDlg.set = function(key, val) {
    this.visaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VisaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VisaInfoDlg.close = function() {
    parent.layer.close(window.parent.Visa.layerIndex);
}

/**
 * 收集数据
 */
VisaInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('visaNationId')
    .set('visaTypeId')
    .set('price')
    .set('issueAt')
    .set('usefulLife')
    .set('needTime')
    .set('maxDurationOfStay')
    .set('needDatum',VisaInfoDlg.editor1.code())
    .set('recommend',VisaInfoDlg.editor2.code());
}

/**
 * 提交添加
 */
VisaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/visa/add", function(data){
        Feng.success("添加成功!");
        window.parent.Visa.table.refresh();
        VisaInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.visaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VisaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/visa/update", function(data){
        Feng.success("修改成功!");
        window.parent.Visa.table.refresh();
        VisaInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.visaInfoData);
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
VisaInfoDlg.onClickDept1 = function (e, treeId, treeNode) {
    $("#visaNationName").attr("value", VisaInfoDlg.zTreeInstance1.getSelectedVal());     //改
    $("#visaNationId").attr("value", treeNode.id);

}

VisaInfoDlg.onClickDept2=function(e,treeId,treeNode){
    $("#regionName").attr("value",VisaInfoDlg.zTreeInstance2.getSelectedVal());
    $("#issueAt").attr("value",treeNode.id);
}

/**
 * 显示签证国家选择的树
 *
 * @returns
 */
VisaInfoDlg.showDeptSelectTree1 = function () {
    var pName = $("#visaNationName");                     //此处改成页面input_id
    var pNameOffset = $("#visaNationName").offset();      //此处改成页面input_id
    $("#parentDeptMenu1").css({
        left: pNameOffset.left - 24 + "px",
        top: pNameOffset.top + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown1);
}

/**
 * 显示签发城市选择的树
 *
 * @returns
 */
VisaInfoDlg.showDeptSelectTree2 = function () {
    var pName = $("#regionName");                     //此处改成页面input_id
    var pNameOffset = $("#regionName").offset();      //此处改成页面input_id
    $("#parentDeptMenu2").css({
        left: pNameOffset.left - 24 + "px",
        top: pNameOffset.top + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown2);
}
/**
 * 隐藏部门选择的树
 */
VisaInfoDlg.hideDeptSelectTree1 = function () {
    $("#parentDeptMenu1").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown1);// mousedown当鼠标按下就可以触发，不用弹起
}
VisaInfoDlg.hideDeptSelectTree2 = function () {
    $("#parentDeptMenu2").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown2);// mousedown当鼠标按下就可以触发，不用弹起
}


function onBodyDown1(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu1" || $(event.target).parents("#parentDeptMenu1").length > 0)) {
        VisaInfoDlg.hideDeptSelectTree1();
    }
}

function onBodyDown2(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu2" || $(event.target).parents("#parentDeptMenu2").length > 0)) {
        VisaInfoDlg.hideDeptSelectTree2();
    }
}

$(function() {

    //创建签证国家ztree实例
    var ztree1 = new $ZTree("parentDeptMenuTree1", "/visaNation/tree");

    //创建签发城市ztree实例
    var ztree2 = new $ZTree("parentDeptMenuTree2", "/region/tree");

    //ztree绑定对象fn
    ztree1.bindOnClick(VisaInfoDlg.onClickDept1);
    ztree2.bindOnClick(VisaInfoDlg.onClickDept2);

    ztree1.init();
    ztree2.init();

    VisaInfoDlg.zTreeInstance1 = ztree1;
    VisaInfoDlg.zTreeInstance2= ztree2;

    /**
     * -- 初始化summernote富文本 --
     */
    VisaInfoDlg.editor1 = $("#needDatum").summernote({lang:"zh-CN",focus:true,height:400});
    $("#needDatum").code($("#needDatumVal").val());

    VisaInfoDlg.editor2 = $("#recommend").summernote({lang:"zh-CN",focus:true,height:400});
    $("#recommend").code($("#recommendVal").val());
    $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});



});

