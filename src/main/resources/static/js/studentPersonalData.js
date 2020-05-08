layui.use('jquery', function () {
  var $ = layui.$;
  $.ajax({
    type:'GET',
    dataType:'JSON',
    url:'../session',
    success: function (data) {
      var data = data.session;
      console.log("path"+data.path)
      $('#nickName').html(data.nickname)
      $('#phoneNumber').html(data.phone)
      $('#birthDay').html(data.bir)
      $('#university').html(data.university);
      $('#department').html(data.department);
      $('#profession').html(data.profession);
      if (data.path!=null){
        $('#photo').attr("src",data.path);
      } else {
        $('#photo').attr("src","../upload/sx744a7dd8264296b8551c6a78b47ae4.png");
      }

      if (data.sex=='男'){
          $('#man').show()
      }else if (data.sex=='女'){
        $('#woman').show()
      }
    }
  })
});
layui.use(['rate','upload'], function () {
//   var rate = layui.rate;
   var upload = layui.upload;
//   rate.render({
//     elem: '#test9'
//     , value: 2.5
//     , half: true
//     , readonly: true
//   });
  var uploadInst = upload.render({
    elem: '#personalDataIcon'
    ,method: 'POST'
    ,exts:'png|jpg'
    ,size:10240
    ,url: '../user/upload'
    ,done: function(data){
      //如果上传失败
        layer.msg(data.msg);
        console.log("url"+data.url)
      $('#photo').attr("src",data.url);
    }
  });
});

//饼状图
// var myChartPie = echarts.init(document.getElementById('pie'));
// option = {
//   title:{
//     text:'我的收入总览'
//     ,subtext:'总收入'
//     ,x:'center'
//   },
//   tooltip: {
//     trigger: 'item',
//     formatter: "{a} <br/>{b} : {c} ({d}%)"
//   },
//   legend: {
//     orient: 'vertical',
//     left: '0px',
//     top:'80px',
//     data: ['期望总收入','总额', 'ee']
//   },
//   series: [
//     {
//       name: '访问来源',
//       type: 'pie',
//       radius: '65%',
//       center: ['50%', '65%'],
//       data: [
//         { value: 335, name: '总额' ,itemStyle:{color:'#33d6c2'}}
//         , { value: 310, name: 'ee' ,itemStyle:{color:'#2fc16c'}}
//         , { value: 310, name: 'eq' ,itemStyle:{color:'#000'}}
//       ]
//       // data:(function () {
//       //   var income = [];
//       // })
//       , itemStyle: {
//         emphasis: {
//           shadowBlur: 10,
//           shadowOffsetX: 0,
//           shadowColor: 'rgba(0, 0, 0, 0.5)'
//         }
//       }
//     }
//   ]
// };

// 使用刚指定的配置项和数据显示图表。
//myChartPie.setOption(option);

// $.ajax({
//   type:'GET',
//   dataType:'JSON',
//   url:'http://localhost:8080/parttimework/studentWork',
//   success:function (data) {
//     console.log("7777777"+data.work)
//     console.log(data.merchants)
//   }
// })
//数据模板
layui.use(['laytpl',"element","jquery","laypage"], function(){

  laytpl=layui.laytpl;
  var element=layui.element;
  var $ = layui.$;
  var laypage = layui.laypage;
  $.get({
    url:"../parttimework/findDealTotal",
    success:function (data) {
      console.log("size"+data)
      var counts = data;
      laypage.render({
        elem: 'demo2-1'
        ,count: counts
        ,theme: '#FF5722'
        ,limit: 5
        ,jump:function (obj) {
          var curr = obj.curr
          var limit = obj.limit
          console.log("页码"+curr)
          console.log(limit)
          //var objData = {"start":curr,"size":limit}
          var url = '../parttimework/studentWork/'+curr+'/'+limit
          $.ajax({
            type:'GET',
            dataType:'JSON',
            url:url,
            success:function (data) {
              console.log("salary"+data.sum)
              $('#salary').html(data.sum)
              $('#times').html(data.times)
              $('#date').html(new Date().toLocaleDateString())
              $('#loading').hide()
              console.log(JSON.stringify(data.data))
              var item = data.data
              var getTpl=document.getElementById('demo').innerHTML
                  ,view = document.getElementById('view');
              laytpl(getTpl).render(item, function(html){
                view.innerHTML = html;
              });
            }
          })
        }})
    }
  })

  $('.choice-type').on('click',function () {
    $('.choice-type').css({"background-color":"white","color":"#000"})
    $(this).addClass("class").siblings().removeClass('class');
    $('.class').css({"background-color":"#009688","color":"white"})
    var status = $(this).attr("value");
    if (status==1){
      var objData = {'workStatus':true,'finish':false}
    }else if (status==2){
      var objData = {'finish':true}
    }else if (status==3){
      var objData = {'workStatus':false}
    }
    console.log(objData)
    $.ajax({
      type:'GET',
      data: objData,
      dataType:'JSON',
      url: '../parttimework/findDealTotal',
      success:function (data) {
        var counts = data
        laypage.render({
          elem: 'demo2-1'
          ,count: counts
          ,theme: '#FF5722'
          ,limit: 5
          ,jump: function (obj) {
            var curr = obj.curr;
            var limit = obj.limit;
            var url = '../parttimework/studentWork/'+curr+'/'+limit
            $.ajax({
              type:'GET',
              data: objData,
              dataType:'JSON',
              url:url,
              success: function (data) {
                // var data = data.data;
                // var getTpl = document.getElementById('demo').innerHTML
                //     , view = document.getElementById('view');
                // laytpl(getTpl).render(data, function (html) {
                //   view.innerHTML = html;
                // });
               // console.log(JSON.stringify(data.data))
                var item = data.data
                var getTpl=document.getElementById('demo').innerHTML
                    ,view = document.getElementById('view');
                laytpl(getTpl).render(item, function(html){
                  view.innerHTML = html;
                });
              }
            })
          }
        })
      }
    })
  })
});
var a = -1;
$('#switch').on('click',function () {

   if (a===-1){
     console.log("switch")
    $('#show').hide();
     a = a*-1;
  }else if (a===1){
    $('#show').show();
    a = a*-1;
  }
 // $('#show').hide();
})