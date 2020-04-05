/**
 * 初始化租车管理详情对话框
 */
var RentCarInfoDlg = {
    rentCarInfoData : {}
};

/**
 * 清除数据
 */
RentCarInfoDlg.clearData = function() {
    this.rentCarInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RentCarInfoDlg.set = function(key, val) {
    this.rentCarInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RentCarInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RentCarInfoDlg.close = function() {
    parent.layer.close(window.parent.RentCar.layerIndex);
}

/**
 * 收集数据
 */
RentCarInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('seatCount')
    .set('carBrandId')
    .set('gearBox')
    .set('rent')
    .set('productTypeId')
    .set('content',RentCarInfoDlg.editor1.code());
}

/**
 * 提交添加
 */
RentCarInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rentCar/add", function(data){
        Feng.success("添加成功!");
        window.parent.RentCar.table.refresh();
        RentCarInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rentCarInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RentCarInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rentCar/update", function(data){
        Feng.success("修改成功!");
        window.parent.RentCar.table.refresh();
        RentCarInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rentCarInfoData);
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
RentCarInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#carBrandName").attr("value", RentCarInfoDlg.zTreeInstance.getSelectedVal());     //改
    $("#carBrandId").attr("value", treeNode.id);                                              //改
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
RentCarInfoDlg.showDeptSelectTree = function () {
    var pName = $("#carBrandName");                     //此处改成页面input_id
    var pNameOffset = $("#carBrandName").offset();      //此处改成页面input_id
    $("#parentDeptMenu").css({
        left: pNameOffset.left - 24 + "px",
        top: pNameOffset.top + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
RentCarInfoDlg.hideDeptSelectTree = function () {
    $("#parentDeptMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu" || $(event.target).parents("#parentDeptMenu").length > 0)) {
        RentCarInfoDlg.hideDeptSelectTree();
    }
}

$(function() {


    //创建ztree实例
    var ztree = new $ZTree("parentDeptMenuTree", "/carBrand/tree");

    //ztree绑定对象fn
    ztree.bindOnClick(RentCarInfoDlg.onClickDept);

    ztree.init();

    RentCarInfoDlg.zTreeInstance = ztree;

    /**
     * -- 初始化summernote富文本 --
     */
    RentCarInfoDlg.editor1 = $("#content").summernote({lang:"zh-CN",focus:true,height:400});
    $("#content").code($("#contentVal").val());
    $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});


});
