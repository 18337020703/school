layui.use(['element','jquery','form'], function(){
     $ = layui.$;
    var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    var num = 1;
    var form = layui.form;
    //自定义验证规则
    // form.verify({
    //     phone: function(value){
    //         if(value.length != 11){
    //             return '您输入的手机号码有误';
    //         }
    //     }
    //     ,pass: [
    //         /^[\S]{6,12}$/
    //         ,'密码必须6到12位，且不能出现空格'
    //     ]
    //     // ,content: function(value){
    //     //     layedit.sync(editIndex);
    //     // }
    // });
    /**
     * 提交登录数据
     */
    $('#loginbutton').on('click',function(){
      var data=$('#loginform').serialize();
      console.log(data)
      console.log("1111111111")
      $.ajax({
        type:'POST',
        data:data,
        dataType:'json',
        url:'../login',
        success:function(msg){
          console.log(msg)
        }
      })
    })
    $('#register').on('click',function () {
        $('#right').hide();
        $("#left").show();
        console.log("i am here")
    })
    $('.gologin').on('click',function () {
        $('#right').show();
        $("#left1").hide();
        console.log("i am here")
    })
    /**
     * 验证码方法包含发送手机验证码方法
     */
    window.onload=aa();
    function aa(){
        var one = Math.floor(Math.random()*10+1);
        var two = Math.floor(Math.random()*10);
        var three = Math.floor(Math.random()*10);
        var four = Math.floor(Math.random()*10);
        if (one>=10){one=9}
        var code = one*1000+two*100+three*10+four
        console.log(code)
        if (num === 1){
            $('#span1').css('font-size','10px');
            $('#span2').css('font-size','20px')
            $('#span3').css('font-size','15px')
            $('#span4').css('font-size','10px')
        }else {
            $('#span1').css('font-size','20px');
            $('#span2').css('font-size','15px')
            $('#span3').css('font-size','10px')
            $('#span4').css('font-size','20px')
        }
        num*=-1;
        $('#span1').html(one)
        $('#span2').html(two)
        $('#span3').html(three)
        $('#span4').html(four)
        /**
         * 发送验证码
         */
        $('#messageCode').on('click',function () {




            var phone = $('#phoneNumber').val();
            var imgCode = $('#codeValue').val();
            console.log("phone:"+phone+"code:"+imgCode)
            if (phone!=''&&imgCode==code){
                console.log("进来啦"+phone+code)
                /**
                 * 验证码倒计时
                 * @type {number}
                 */
                var second = 60;
                var timer = null;
                timer = setInterval(function(){
                    second -= 1;
                    if(second >0){
                        $('#messageCode').attr('disabled',true);
                        $('#messageCode').text(second + "秒后重发");
                    }else{
                        clearInterval(timer);
                        $('#messageCode').attr('disabled',false);
                        $('#messageCode').text("发送验证码");
                    }
                },1000);
                $.ajax({
                    type:'POST',
                    data: phone,
                    url: '../register/sendMessageCode',
                    contentType: "application/json",
                    dataType:'JSON',
                    success: function (data) {
                        console.log(data.msg)
                        layer.msg(data.msg)
                    }
                })

            }else if (phone=='') {
                layer.msg("手机号码为空")
            }else if (imgCode!=code){
                layer.msg("验证码不正确")
            }else {
                layer.msg("验证码为空")
            }

        })
    }

    /**
     * 点击更换图片验证码方法
     */
    $('#imgCode').on('click',function () {
            aa()
    })
    //if ($('#phoneNumber').val()!=''){
        console.log("nexttt")
        $('#sendMessageToSetPassword').on('click',function () {
            console.log("next")
            $('#left1').show();
            $("#left").hide();
        })
   // }


    /**
     *发送手机验证码给后台
     */
    $('#sendMessageToController').on('click',function () {
        var code = $('#MessageValue').val();
        var password1 = $('#password1').val();
        var password2 = $('#password2').val();
        var phone = $('#phoneNumber').val();
        var radio =$('#radio input[name="sex"]:checked').val();
        var Obj = {'code':code,'password':password1,'password2':password2,'phone':phone,'sex':radio}
        //var ObjJson = JSON.stringify(Obj)
       // console.log(ObjJson)
        console.log(Obj)
        if (password1==password2){
            $.ajax({
                type:'POST',
                data:Obj,
                dataType:'JSON',
               // contentType:'application/json',
                url:'../register/receiveMessageCode',
                success:function (data) {
                    layer.msg(data.msg)
                }
            })
        }else if (password1=='' | password2 == ''){
            layer.msg("密码为空")
        }else {
            layer.msg("两次密码输入不一致")
        };

    })

    //触发事件
    var active = {
    tabChange: function(){
        //切换到指定Tab项
        element.tabChange('demo', '22'); //切换到：用户管理
      }
    };
    
    $('.site-demo-active').on('click', function(){
      var othis = $(this), type = othis.data('type');
      active[type] ? active[type].call(this, othis) : '';
    });
    
    //Hash地址的定位
    // var layid = location.hash.replace(/^#test=/, '');
    // element.tabChange('test', layid);
    
    // element.on('tab(test)', function(elem){
    //   location.hash = 'test='+ $(this).attr('lay-id');
    // });
    
  });