layui.use(['form', 'layedit', 'laydate','jquery'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;
    $ = layui.$;

    //日期
    laydate.render({
        elem: '#date'
    });
    $.ajax({
        type:'GET',
        dataType:'JSON',
        url:'../session',
        success: function (data) {
            console.log(data.session)
            var data = data.session;
            console.log(data.username)
            console.log(data.phone)
            console.log(data.sex)
            $('#nickName').val(data.nickname)
            $('#sex').val(data.sex)
            $('#phone').val(data.phone)
            $('#hidden').val(data.id)
            $('#pro').val(data.university);
            $('#city').val(data.department);
            $('#area').val(data.profession)
        }
    })
    $('#editButton').on('click',function () {
        var o = {};
        var data = $('#editData').serializeArray();
        $.each(data, function(index) {
            if (o[this['name']]) {
                o[this['name']] = o[this['name']] + "," + this['value'];
            } else {
                o[this['name']] = this['value'];
            }
        });
        console.log(o)
        $.ajax({
            type:'POST',
            dataType:'JSON',
            data:o,
            url:'../user/student',
            success:function (data){
                console.log(data)
            }
        })
    })
});



//三级联动
var sheng=["商丘师范学院","商丘工学院","商丘学院","商丘医专"];
var city=[["信息技术学院","法学院","外语学院","美术学院"],["卢氏","灵宝","渑池"],["二七区","金水区","管城区"],["太康县","西华县"]]
var ar=[[["计算机科学与技术","物联网","大数据","网络通信"],
    ["法学","社会工作学","思想政治学","岗王乡","城关镇","申桥乡","岗王乡"],
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