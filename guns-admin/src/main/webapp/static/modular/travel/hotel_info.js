/**
 * 初始化酒店业务详情对话框
 */
var HotelInfoDlg = {
    hotelInfoData : {}
};

/**
 * 清除数据
 */
HotelInfoDlg.clearData = function() {
    this.hotelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelInfoDlg.set = function(key, val) {
    this.hotelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelInfoDlg.close = function() {
    parent.layer.close(window.parent.Hotel.layerIndex);
}

/**
 * 收集数据
 */
HotelInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('title')
    .set('grade')
    .set('regionId')
    .set('productTypeId')
    .set('address')
    .set('mapX')
    .set('mapY')
    .set('price')
    .set('discount')
    .set('tel')
    .set('openTime')
    .set('bedType')
    .set('img1')
    .set('img2')
    .set('img3')
    .set('img4')
    .set('img5')
    .set('hot')
    .set('recommend')
    .set('sales')
    .set('brandId')
    .set('introduce',HotelInfoDlg.editor1.code());
}

/**
 * 提交添加
 */
HotelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotel/add", function(data){
        Feng.success("添加成功!");
        window.parent.Hotel.table.refresh();
        HotelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HotelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotel/update", function(data){
        Feng.success("修改成功!");
        window.parent.Hotel.table.refresh();
        HotelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelInfoData);
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
HotelInfoDlg.onClickDept = function(e, treeId, treeNode) {
    $("#regionName").attr("value", HotelInfoDlg.zTreeInstance.getSelectedVal());     //改
    $("#regionId").attr("value", treeNode.id);                                              //改
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
HotelInfoDlg.showDeptSelectTree = function() {
    var pName = $("#regionName");                     //此处改成页面input_id
    var pNameOffset = $("#regionName").offset();      //此处改成页面input_id
    $("#parentDeptMenu").css({
        left : pNameOffset.left - 24 + "px",
        top : pNameOffset.top + "px"
    }).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
HotelInfoDlg.hideDeptSelectTree = function() {
    $("#parentDeptMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDeptMenu" || $(event.target).parents("#parentDeptMenu").length > 0)) {
        HotelInfoDlg.hideDeptSelectTree();
    }

}




$(function() {
//验证输入框
    // Feng.initValidator("regionInfoForm", ScenicSpotInfoDlg.validateFields);

//创建ztree实例
    var ztree = new $ZTree("parentDeptMenuTree", "/region/tree");

//ztree绑定对象fn
    ztree.bindOnClick(HotelInfoDlg.onClickDept);

    ztree.init();

    HotelInfoDlg.zTreeInstance = ztree;



    /**
     * -- 初始化summernote富文本 --
     */
    HotelInfoDlg.editor1 = $("#introduce").summernote({lang:"zh-CN",focus:true,height:400});
    $("#introduce").code($("#introduceVal").val());
    $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});


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




HotelInfoDlg.openModal = function (obj){

    $('#image').val(obj.id);
    $('#myModal').modal("show");
}

HotelInfoDlg.setImage = function () {
    var radio_a = $("input[name='radio_a']");
    radio_a.each(function (){
        if(this.checked){
            var input_ = $("#image").val();
            $("#"+input_).val($(this).val()).attr("disabled","disabled");
            $('#myModal').modal("hide");
        }
    })
}

HotelInfoDlg.clearImage = function (obj){
    $(obj).parent().prev().val("").removeAttr("disabled");

}


