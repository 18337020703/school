layui.use(['form','jquery','layer'], function(){
    var form = layui.form;
    $ =layui.$;
    var layer = layui.layer;
    //监听提交
    form.on('submit(formDemo)', function(data){
        $('#registerForm').reset();
        $('#un').val('');
        console.log("hahaha")
      
        var formdata = JSON.parse(JSON.stringify(data.field))
        $.ajax({
            type: 'POST',
            data: formdata,
             dataType:'JSON',
            url: '../register',
            success:function(msg){
                console.log("200")
                layer.msg("注册成功")
            }
        })
      
      console.log(JSON.stringify(data.field))
      layer.msg(JSON.stringify(data.field));
      return false;
    });
  });