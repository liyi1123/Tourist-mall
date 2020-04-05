/**
 * 管理初始化
 */
var StuSignIn = {
    id: "StuSignInTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StuSignIn.initColumn = function () {
    return [
            {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            /*{title: '父级ID', field: 'parentId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '课目名称', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
            {title: '老师姓名', field: 'teacherName', visible: true, align: 'center', valign: 'middle'},
            {title: '班级', field: 'deptName', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
                formatter: function (value,row,index) {
                    var text,iclass,able,btn,onClick;
                    if(1 == value){
                         text ="签到",iclass = "btn-primary",able = "",onClick="onclick='StuSignIn.signIn("+row.id+")'";
                    }else if(3 == value){
                        text ="已签到",iclass = "btn-info btn-outline",able = "",onClick="";
                    } else {
                        text ="已关闭",iclass = "btn-default",able = "disable",onClick="";
                    }
                     btn = "<a class='btn "+iclass+" btn-rounded btn-xs' href='javascript:;' "+able+" "+onClick+">"+text+"</a>";
                    return btn;
                }
            }

    ];
};

/**
 * 检查是否选中
 */
StuSignIn.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{


        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        StuSignIn.seItem = ids;
        return true;
    }
};

/**
 * 点击添加
 */
StuSignIn.openAddSignIn = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1198px', '546px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/signIn/signIn_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
StuSignIn.openSignInDetail = function () {
    if (this.check()) {
        if(StuSignIn.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '详情',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/signIn/signIn_update/' + StuSignIn.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除
 */
StuSignIn.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/signIn/delete", function (data) {
            Feng.success("删除成功!");
            StuSignIn.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("signInIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询列表
 */
StuSignIn.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    StuSignIn.table.refresh({query: queryData});
};
/**
 * 学生签到
 */
StuSignIn.signIn = function (signInParentId){
    var ajax = new $ax(Feng.ctxPath+"/signIn/addSignIn",function (data){
            Feng.success("签到成功!");
            window.StuSignIn.table.refresh();
        },function (data){
            Feng.error("签到失败!" );
        }
    );
    ajax.set("parentId",signInParentId);
    ajax.start();
}

$(function () {
    var defaultColunms = StuSignIn.initColumn();
    var table = new BSTable(StuSignIn.id, "/signIn/stuSignInlist", defaultColunms);
    table.setPaginationType("client");
    StuSignIn.table = table.init();


});
