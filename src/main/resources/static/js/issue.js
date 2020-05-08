

layui.use(['upload','laydate','form', 'layedit','layer'], function(){
    var $ = layui.jquery
    ,upload = layui.upload
        ,laydate=layui.laydate
        ,layer = layui.layer;
    $.ajax({
        type:'GET',
        dataType:'JSON',
        url:'../session/merchants',
        success: function (data) {
            console.log("hahha")
            var data = data.session;
            console.log("data"+data.companyName)
            if (data.companyName=='' || data.companyPhone=='' || data.companyIntroduction=='' || data.companyAddress==''){
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,resize: false
                    ,btn: ['完善资料', '残忍拒绝']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
                        '你好，"'+data.nickname+'"同学<br>'+'根据商丘师范学院学院规定<br>必须完善信息才可以发布兼职</div>'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').on('click',function () {
                            window.location.href=("../merchants/editMerchantsData.html")
                        });
                        btn.find('.layui-layer-btn1').on('click',function () {
                            window.location.href=("../merchants/personalData.html")
                        });
                    }
                });
            }
        }
    })

    laydate.render({
        elem: '#test1'
    });
    var url = location.search; //获取url中"?"符后的字串 ('?endId=.....')
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1); //substr()方法返回从参数值开始到结束的字符串；
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
    //给企业一览表赋值(这里要解码)
    console.log(decodeURI("yyy"+theRequest.id))
    console.log(decodeURI("yyy"+theRequest.content))
    $('#title').val(decodeURI(theRequest.title))
    $('#hidden').val(decodeURI(theRequest.id))
    $('#content').text(decodeURI(theRequest.content));
    $('#edit').on('click',function () {
        var o = {};
        var data = $('form').serializeArray();
        $.each(data, function(index) {
            if (o[this['name']]) {
                o[this['name']] = o[this['name']] + "," + this['value'];
            } else {
                o[this['name']] = this['value'];
            }
        });
        console.log(o)
        $.ajax({
            type: "POST",
            data:o,
            dataType:'json',
            url: '../parttimework/edit',
            success:function (data) {
                console.log(data)
            }
        })
    })






    upload.render({
        elem: '#test2'
        ,url: '/upload/'
        ,multiple: true
        ,before: function(obj){
          //预读本地文件示例，不支持ie8
          obj.preview(function(index, file, result){
            $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" style="width:200px">')
          });
        }
        ,done: function(res){
          //上传完毕
        }
      });
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

    //自定义验证规则
    form.verify({
        title: function(value){
            if(value.length < 5){
                return '标题至少得5个字符啊';
            }
        }
        ,pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
        ,content: function(value){
            layedit.sync(editIndex);
        }
    });

    //监听指定开关
    form.on('switch(switchTest)', function(data){
        layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
            offset: '6px'
        });
        layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    });

    //监听提交
    form.on('submit(demo1)', function(data){
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
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
    layui.$('#LAY-component-form-getval').on('click', function(){
        var data = form.val('example');
        alert(JSON.stringify(data));
    });
    $('#tijiao').on('click',function () {
        var o = {};
        var data = $('form').serializeArray();
           $.each(data, function(index) {
                  if (o[this['name']]) {
                      o[this['name']] = o[this['name']] + "," + this['value'];
                    } else {
                          o[this['name']] = this['value'];
                       }
                });
        console.log(o)
        $.ajax({
            type: "POST",
            data:o,
            dataType:'json',
            url: '../parttimework',
            success:function (data) {
                console.log(data)
            }
        })
    })


})



var sheng=["商丘市","三门峡市","郑州市","周口市"];
var city=[["柘城县","睢县","永城"],["卢氏","灵宝","渑池"],["二七区","金水区","管城区"],["太康县","西华县"]]
var ar=[[["城关镇","申桥乡","岗王乡","安平镇","柏岗乡","慈圣镇","绍园乡","牛城乡"],
         ["夷陵区","城关镇","申桥乡","岗王乡","城关镇","申桥乡","岗王乡"],
         ["咸安区","城关镇","申桥乡","岗王乡","城关镇","申桥乡","岗王乡"]],
         [["芙蓉区","城关镇","申桥乡","岗王乡","城关镇","申桥乡","岗王乡"],
             ["鼎城区","城关镇","申桥乡","岗王乡","城关镇","申桥乡","岗王乡"],
             ["双清区","城关镇","申桥乡","岗王乡"]],
              [["荔湾区","城关镇","申桥乡","岗王乡"],
                  ["福田区","城关镇","申桥乡","岗王乡"],
                  ["惠阳区","城关镇","申桥乡","岗王乡"]]]
window.onload=start;
var s=document.getElementById("pro");  //设置初始的省份选项
function start(){
    for(var i=0;i<sheng.length;i++){
        var op=document.createElement("option");
        op.innerHTML=sheng[i];
        s.appendChild(op);      //添加几个可选择的省份到第一个选项下拉栏
    }
}
var c=document.getElementById("city")
function getcity(){
    c.length=1;
    var sw=s.selectedIndex;//找到省份位置，从而好使后面的城市与省份对应
    var citys=city[sw-1];
    for(var j=0;j<citys.length;j++){
        var op1= document.createElement("option");
        op1.innerHTML=citys[j];
        c.appendChild(op1);
    }
}
var a=document.getElementById("area")
function getarea(){
    a.length=1;
    var sw=s.selectedIndex;//省份位置，与上一步中的sw一样
    var cw=c.selectedIndex;//城市位置
    var citys=ar[sw-1];//先把三维数组中的区域找出来，确定是哪个省里的几个区
    var ars=citys[cw-1];//再通过之前定位的城市位置，把对应的区对应给相应的城市
    for(var k=0;k<ars.length;k++){
        var op2=document.createElement("option");
        op2.innerHTML=ars[k];
        a.appendChild(op2);
    }
}

var d = document.getElementById("type")
$.ajax({
    type:'GET',
    url:'../parttimework/typeofwork',
    dataType:'JSON',
    success:function (data) {
        var typeOfWork = data.data;
        //console.log(typeOfWork[0])
        for(var i=0;i<typeOfWork.length;i++){
           // console.log(typeOfWork[i].name)
            var op3=document.createElement("option");
            op3.innerHTML=typeOfWork[i].name;
            d.appendChild(op3)
        }
    }
})