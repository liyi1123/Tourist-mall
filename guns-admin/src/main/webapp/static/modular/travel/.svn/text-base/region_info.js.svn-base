/**
 * 初始化所属区域详情对话框
 */
var RegionInfoDlg = {
    regionInfoData : {},
    zTreeInstance : null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '区域名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RegionInfoDlg.clearData = function() {
    this.regionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RegionInfoDlg.set = function(key, val) {
    this.regionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RegionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RegionInfoDlg.close = function() {
    parent.layer.close(window.parent.Region.layerIndex);
}

/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RegionInfoDlg.onClickDept = function(e, treeId, treeNode) {
    $("#parentIdList").attr("value", RegionInfoDlg.zTreeInstance.getSelectedVal());     //改
    $("#parentId").attr("value", treeNode.id);                                          //改
}


/**
 * 显示部门选择的树
 *
 * @returns
 */
RegionInfoDlg.showDeptSelectTree = function() {
    var pName = $("#parentIdList");                     //此处改成页面input_id
    var pNameOffset = $("#parentIdList").offset();      //此处改成页面input_id
    $("#parentDeptMenu").css({
        left : pNameOffset.left + "px",
        top : pNameOffset.top + pName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
RegionInfoDlg.hideDeptSelectTree = function() {
    $("#parentDeptMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}


/**
 * 验证数据是否为空
 */
RegionInfoDlg.validate = function () {
    $('#regionInfoForm').data("bootstrapValidator").resetForm();
    $('#regionInfoForm').bootstrapValidator('validate');
    return $("#regionInfoForm").data('bootstrapValidator').isValid();
}



/**
 * 收集数据
 */
RegionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('parentId')
    .set('parentIdList');
}

/**
 * 提交添加
 */
RegionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/region/add", function(data){
        Feng.success("添加成功!");
        window.parent.Region.table.refresh();
        RegionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.regionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RegionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/region/update", function(data){
        Feng.success("修改成功!");
        window.parent.Region.table.refresh();
        RegionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.regionInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu" || $(event.target).parents("#parentDeptMenu").length > 0)) {
        RegionInfoDlg.hideDeptSelectTree();
    }
}

$(function() {
//验证输入框
    Feng.initValidator("regionInfoForm", RegionInfoDlg.validateFields);

//创建ztree实例
    var ztree = new $ZTree("parentDeptMenuTree", "/region/tree");

//ztree绑定对象fn
    ztree.bindOnClick(RegionInfoDlg.onClickDept);

    ztree.init();

    RegionInfoDlg.zTreeInstance = ztree;
});

