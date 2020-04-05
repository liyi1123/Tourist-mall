/**
 * 管理初始化
 */
var SignIn = {
    id: "SignInTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SignIn.initColumn = function () {
    return [
            {field: 'selectItem', checkbox: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            /*{title: '父级ID', field: 'parentId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '课目名称', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
            /*{title: '学生ID', field: 'studentId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '老师姓名', field: 'teacherName', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '班级', field: 'deptName', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index){
                    var che=value == 1? 'checked' : '',id = row.id;
                    return "<div class='switch'>\n" +
                        "    <div class='onoffswitch'>\n" +
                        "        <input type='checkbox' "+che+" class='onoffswitch-checkbox' id='example"+id+"' value='"+id+"' onclick='SignIn.changeStatus(this);'>\n" +
                        "        <label class='onoffswitch-label' for='example"+id+"'>\n" +
                        "            <span class='onoffswitch-inner'></span>\n" +
                        "            <span class='onoffswitch-switch'></span>\n" +
                        "        </label>\n" +
                        "    </div>\n" +
                        "</div>";
                }
            },
            {title: '备注', field: 'desc2', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SignIn.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        var ids=new Array();
        for(var i in selected){
            ids.push(selected[i].id);
        }
        SignIn.seItem = ids;
        return true;
    }
};

/**
 * 点击添加
 */
SignIn.openAddSignIn = function () {
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
SignIn.openSignInDetail = function () {
    if (this.check()) {
        if(SignIn.seItem.length > 1){
            Feng.alert("亲,每次只能修改一个",0);
        }else{
            var index = layer.open({
                    type: 2,
                    title: '详情',
                    area: ['966px', '500px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/signIn/signIn_update/' + SignIn.seItem[0]
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除
 */
SignIn.delete = function () {
    var me = this;
    if (this.check()) {
        Feng.confirm("确定要删除",3,function (){
            var ajax = new $ax(Feng.ctxPath + "/signIn/delete", function (data) {
                Feng.success("删除成功!");
                SignIn.table.refresh();
            }, function (data) {
                Feng.error("删除失败!");
            });
            ajax.set("signInIds",me.seItem);
            ajax.start();
        });

    }
};

/**
 * 查询列表
 */
SignIn.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SignIn.table.refresh({query: queryData});
};
/**
*ajax 修改签到任务开启/关闭状态
*/
SignIn.changeStatus = function (me){
    var ajax = new $ax(Feng.ctxPath +"/signIn/changeStatus",function (data){
        Feng.success("签到任务状态成功!");
    },function (data){
        Feng.error("签到任务状态失败!");
    });

    ajax.set({"id":me.value,"status":me.checked ? 1 : 0});
    ajax.start();
}

/**
 * 打开当前选中任务的学生签到详情
 */
SignIn.goHasSignInList = function (){
    if(this.check()){
        if(this.seItem.length > 1){
            Feng.alert("查看详情只能选择一个",1);
        }else {
            var index = layer.open({
                type: 2,
                title: '添加',
                area: ['1198px', '600px'], //宽高
                fixed: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/signIn/goHasSignInList'
            });
            this.layerIndex= index;
        }

    }
    



}



$(function () {
    var defaultColunms = SignIn.initColumn();
    var table = new BSTable(SignIn.id, "/signIn/list", defaultColunms);
    table.setPaginationType("client");
    SignIn.table = table.init();



});
