layui.use(['form', 'layedit', 'laydate','layer'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });

    //创建一个编辑器
    var editIndex = layedit.build('LAY_demo_editor');
    $.ajax({
        type:'GET',
        dataType:'JSON',
        url:'../session/merchants',
        success: function (data) {
            var data = data.session;
            console.log(data.username)
            console.log(data.phone)
            console.log(data.id)
            $('#nickname').val(data.nickname)
            $('#hidden').val(data.id)
            $('#phone').val(data.phone)
            $('#companyName').val(data.companyName)
            $('#companyAddress').val(data.companyAddress)
            $('#companyPhone').val(data.companyPhone)
            $('#companyIntroduction').val(data.companyIntroduction)

        }
    })
    //监听提交
    form.on('submit(demo1)', function(data){
        var data = JSON.stringify(data.field);
        console.log(data)
        var o = JSON.parse(data)
        console.log(o)
        $.ajax({
            type:'POST',
            data: o,
            dataType: 'JSON',
            url:'../user/merchants',
            beforeSend: function () {    //请求开始时
                $("#loading").show()
            },
            success:function (data) {
                $("#loading").hide()
                layer.msg(data.res)
            }

        })
        return false;
    });

    //表单赋值
    layui.$('#LAY-component-form-setval').on('click', function(){
        form.val('example', {
            "username": "贤心" // "name": "value"
            ,"password": "123456"
            ,"interest": 1
            ,"like[write]": true //复选框选中状态
            ,"close": true //开关状态
            ,"sex": "女"
            ,"desc": "我爱 layui"
        });
    });

    //表单取值
    // layui.$('#LAY-component-form-getval').on('click', function(){
    //     var data = form.val('example');
    //     alert(JSON.stringify(data));
    // });

});