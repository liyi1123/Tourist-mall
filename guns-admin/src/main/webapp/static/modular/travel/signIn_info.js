/**
 * 初始化详情对话框
 */
var SignInInfoDlg = {
    signInInfoData : {}
};

/**
 * 清除数据
 */
SignInInfoDlg.clearData = function() {
    this.signInInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SignInInfoDlg.set = function(key, val) {
    if(key == 'beginTime' || key == 'endTime'){
        if(typeof val == "undefined"){
            var v = $("#" + key).val(),date;
            if(v == '' || v == 'undefined'){
                date = new Date();
            }else {
                date = new Date(v);
            }

            this.signInInfoData[key] = Math.round(date.getTime()/1000);
        }else {
            var date = new Date(val);
            this.signInInfoData[key] = Math.round(date.getTime()/1000);
        }
    }else {
        this.signInInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    }
    return this;
}

/**设置签到任务状态
 * 开启:1,关闭:0
 */
SignInInfoDlg.changeStatus = function (me) {
    if(me.checked){
        $(me).val(1);
    }else {
        $(me).val(0);
    }
}



/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SignInInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SignInInfoDlg.close = function() {
    parent.layer.close(window.parent.SignIn.layerIndex);
}

/**
 * 收集数据
 */
SignInInfoDlg.collectData = function() {
    this
    .set('id')
    .set('parentId')
    .set('subjectName')
    .set('teacherId')
    .set('beginTime')
    .set('endTime')
    .set('status')
    .set('deptId')
    .set('desc2');
}

/**
 * 提交添加
 */
SignInInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/signIn/add", function(data){
        Feng.success("添加成功!");
        window.parent.SignIn.table.refresh();
        SignInInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.signInInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SignInInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/signIn/update", function(data){
        Feng.success("修改成功!");
        window.parent.SignIn.table.refresh();
        SignInInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.signInInfoData);
    ajax.start();
}


/**
 * 点击部门input框时
 */
SignInInfoDlg.onClickDept = function (e, treeId, treeNode){
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#deptId").attr("value", treeNode.id);
}

/**
 *显示部门的树
 */
SignInInfoDlg.showDeptSelectTree =function (){
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        SignInInfoDlg.hideDeptSelectTree();
    }
}
/**
 * 隐藏部门选择的树
 */
SignInInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};




$(function() {
    var ztree = new $ZTree("treeDemo", "/dept/tree");
    ztree.bindOnClick(SignInInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

    var elem = $(".js-switch").get(0);
    new Switchery(elem,{color: "#1AB394"});

});
