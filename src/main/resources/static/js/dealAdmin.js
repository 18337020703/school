layui.use('table', function(){
    var table = layui.table;

    //方法级渲染
    table.render({
        elem: '#LAY_table_user'
        ,url: '../parttimework/findAllDeal'
        ,cols: [[
            {field:'id', title: 'ID', width:220, sort: true, fixed: true}
            ,{field:'sid', title: '学生ID', width:230,align:'center'}
            ,{field:'pid', title: '工作ID', width:180,align:'center'}
            ,{field:'status', title: '审核状态', width:200,align:'center',templet:function (val) {
                    if (val.status == 0){
                        return "发布者未确认"
                    }else if (val.status == 1){
                        return "发布者已确认"
                    }
                }}
            ,{field:'finish', title: '交易状态',width:200,align:'center',templet:function (val) {
                    if (val.finish == 0){
                        return "交易正在进行"
                    }else if (val.finish == 1){
                        return "交易已经完成"
                    }
                }}
            ,{field:'date', title: '交易时间', width:200}
        ]]
        ,id: 'testReload'
        ,page: true
        ,height: 580
        ,skin:'line'
        ,size:'sm'
        ,even:false
        ,loading:false
        ,done:function () {
            $('#loading').hide()
        }
    });

    var $ = layui.$, active = {
        reload: function(){
            var demoReload = $('#demoReload');

            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        id: demoReload.val()
                    }
                }
            }, 'data');
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});