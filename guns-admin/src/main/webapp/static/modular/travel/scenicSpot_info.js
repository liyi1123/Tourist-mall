/**
 * 初始化景点详情对话框
 */
var ScenicSpotInfoDlg = {
    scenicSpotInfoData: {},
    zTreeInstance: null,
    editor1: null,
    editor2: null,
    validateFields:{
        name: {
            validators: {
                notEmpty: {
                    message: '景点名称不能为空'
                }
            }
        },
        regionName: {
            validators: {
                notEmpty: {
                    message: '所属区域不能为空'
                }
            }
        },
    }
};


/**
 * 清除数据
 */
ScenicSpotInfoDlg.clearData = function () {
    this.scenicSpotInfoData = {};
}


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScenicSpotInfoDlg.set = function (key, val) {
    this.scenicSpotInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScenicSpotInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScenicSpotInfoDlg.close = function () {
    parent.layer.close(window.parent.ScenicSpot.layerIndex);
}

/**
 *检查景点主题多选框至少选一个
 */
ScenicSpotInfoDlg.check = function (){
    var checkedTheme = $("input[name=themeList]:checked").length;
    if(checkedTheme == 0){
        Feng.info("'景点主题' 至少选着一个!");
        return false;
    }
    return true;
}

/**
 * 收集数据
 */
ScenicSpotInfoDlg.collectData = function () {
    var themeList = $("input[name=themeList]"),
        themeListIds = new Array(),
        oldThemeIds = $('#oldThemeIds').val();

    themeList.each(function () {
        if (this.checked) {
            themeListIds.push($(this).val());
        }
    });
    var oldThemeIds = eval(oldThemeIds);
    this.set('id')
        .set('name')
        .set('grade')
        .set('regionId')
        .set('productTypeId')
        .set('adultTicket')
        .set('childrenTicket')
        .set('specialTiclet')
        .set('address')
        .set('openTime')
        .set('mapX')
        .set('mapY')
        .set('recommend')
        .set('hot')
        .set('introduce', ScenicSpotInfoDlg.editor1.code())
        .set('commitment', ScenicSpotInfoDlg.editor2.code())
        .set('themeIds', themeListIds)
        .set('oldThemeIds',oldThemeIds);

}

/**
 * 提交添加
 */
ScenicSpotInfoDlg.addSubmit = function () {

    this.clearData();
    if(this.check()){
        this.collectData();
        console.log(this.scenicSpotInfoData);
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/scenicSpot/add", function (data) {
            Feng.success("添加成功!");
            window.parent.ScenicSpot.table.refresh();
            ScenicSpotInfoDlg.close();
        }, function (data) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.scenicSpotInfoData);
        ajax.start();
    }

}

/**
 * 提交修改
 */
ScenicSpotInfoDlg.editSubmit = function () {

    this.clearData();
    if(this.check()){
        this.collectData();

        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/scenicSpot/update", function (data) {
            Feng.success("修改成功!");
            window.parent.ScenicSpot.table.refresh();
            ScenicSpotInfoDlg.close();
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message + "!");
            cosin
        });
        ajax.set(this.scenicSpotInfoData);
        ajax.start();
    }

}



/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
ScenicSpotInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#regionName").attr("value", ScenicSpotInfoDlg.zTreeInstance.getSelectedVal());     //改
    $("#regionId").attr("value", treeNode.id);                                              //改
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
ScenicSpotInfoDlg.showDeptSelectTree = function () {
    var pName = $("#regionName");                     //此处改成页面input_id
    var pNameOffset = $("#regionName").offset();      //此处改成页面input_id
    $("#parentDeptMenu").css({
        left: pNameOffset.left - 24 + "px",
        top: pNameOffset.top + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
ScenicSpotInfoDlg.hideDeptSelectTree = function () {
    $("#parentDeptMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu" || $(event.target).parents("#parentDeptMenu").length > 0)) {
        ScenicSpotInfoDlg.hideDeptSelectTree();
    }
}


$(function () {


//创建ztree实例
    var ztree = new $ZTree("parentDeptMenuTree", "/region/tree");

//ztree绑定对象fn
    ztree.bindOnClick(ScenicSpotInfoDlg.onClickDept);

    ztree.init();

    ScenicSpotInfoDlg.zTreeInstance = ztree;

    /**
     * -- 初始化summernote富文本 --
     */
    ScenicSpotInfoDlg.editor1 = $("#introduce").summernote({lang: "zh-CN", focus: true, height: 400});
    ScenicSpotInfoDlg.editor2 = $("#commitment").summernote({lang: "zh-CN", focus: true, height: 400});
    $("#introduce").code($("#introduceVal").val());
    $("#commitment").code($("#commitmentVal").val());

    $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"});
    /**
     * 双击 打开百度地图,获取经纬度
     */
    $("#mapX").on('dblclick',function (){
        $('#coordinateModal').modal('show');
    });
    $("#mapY").on('dblclick',function (){
        $('#coordinateModal').modal('show');
    });

    //百度地图
    var map = new BMap.Map("mapBox");
    var point = new BMap.Point(108.953138,34.268618);
    map.centerAndZoom(point, 12);
    var marker = new BMap.Marker(point);
    map.addOverlay(marker);

    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());
    map.addControl(new BMap.MapTypeControl());
    map.enableScrollWheelZoom(true);
    /* 标记的事件 */
    marker.addEventListener("click", function(){
        alert("您点击了标注");
    });
    marker.enableDragging();
    marker.addEventListener("dragend", function(e){
        $('#mapX').val(e.point.lng);
        $('#mapY').val(e.point.lat);
    });


});


