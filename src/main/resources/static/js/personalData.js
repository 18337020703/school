
  layui.use(['rate'], function(){
    var rate = layui.rate;
    rate.render({
      elem: '#test9'
      ,value: 2.5
      ,half: true
      ,readonly: true
    });
  });


  layui.use(['laytpl',"element","laypage"], function(){
    laytpl=layui.laytpl;
    var element=layui.element;
    var laypage=layui.laypage
      $.ajax({
          type:'GET',
          dataType:'JSON',
          url: '../parttimework/totalCounts',
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
                      var data = {"start":curr,"size":limit}
                      $.ajax({
                          type:'GET',
                          data:data,
                          url:'../parttimework/merchantsIssue',
                          dataType:'json',
                          success:function (data) {
                              console.log("值"+data.data)
                              var data = data.data;
                              var getTpl=document.getElementById('demo').innerHTML
                                  ,view = document.getElementById('view');
                              laytpl(getTpl).render(data, function(html){
                                  view.innerHTML = html;
                              });
                              /**
                               * 踩坑，模板引擎点击事件一定要放在success 下面才生效
                               */
                              $('.layui-col-space15 #edit').on('click', function(){
                                  var title=$(this).attr("title");
                                  var content=$(this).attr("content");
                                  var id = $(this).attr("value");
                                  console.log(title)
                                  console.log(content)
                                  console.log(id)
                                  window.location.href=encodeURI("../merchants/editIssue.html?id="+id+'&title='+title+'&content='+content);
                              });
                          }
                      })
                  }
              })
          }
      })

      $('.choice-type').on('click',function () {
          $('.choice-type').css({"background-color":"white","color":"#000"})
          $(this).addClass("class").siblings().removeClass('class');
          $('.class').css({"background-color":"#009688","color":"white"})
          var status = $(this).attr("value");
          if (status==1){
              var objData = {'workStatus':true,'workRequest':true}
          }else if (status==2){
              var objData = {'workStatus':false,'workRequest':true}
          }else if (status==3){
              var objData = {'workRequest':false}
          }
          console.log(objData)
          $.ajax({
              type:'GET',
              data: objData,
              dataType:'JSON',
              url: '../parttimework/totalCountsByCondition',
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
                          var url = '../parttimework/condition/'+curr+'/'+limit
                          $.ajax({
                              type:'GET',
                              data: objData,
                              dataType:'JSON',
                              url:url,
                              success: function (data) {
                                  var data = data.data;
                                  var getTpl = document.getElementById('demo').innerHTML
                                      , view = document.getElementById('view');
                                  laytpl(getTpl).render(data, function (html) {
                                      view.innerHTML = html;
                                  });
                              }
                          })
                      }
                  })
              }
          })
      })
   $('.layui-icon-group').on('click', function(){
    var self= $(this).attr("value");
     var that = this;
     layer.tips("<span style='color:#3e3d3d'>tel:"+self+"</span>", that,{
       tips: [1, '#ffffff']
      , area: [ '120px', '50px' ] 
     });
   });
  });
  $.ajax({
      type:'GET',
      dataType:'json',
      url:'../session/merchants',
      success:function (data) {
          console.log("haaaaaaa")
        //var data = JSON.stringify(data.session)
          var data = data.session
          console.log("merchantsSession"+data)
          $('#companyName').html(data.companyName);
          $('#companyPhone').html(data.companyPhone);
          $('#companyAddress').html(data.companyAddress);
          $('#companyIntroduction').html(data.companyIntroduction);
      }
  })

  function f(id) {
      console.log("value"+id)
      var url = "../merchants/personalDataList.html?id="+id
      console.log(url)
     window.open("../merchants/personalDataList.html?id="+id)
  }
function f1(id) {
      var o = {"workStatus":false,"id":id}
    $.ajax({
        type: "POST",
        data:o,
        dataType:'json',
        url: '../parttimework/edit',
        success:function (data) {
            console.log(data)
        }

    })
}
function f2(id) {
    var url = '../parttimework/'+id
    $.ajax({
        type: "DELETE",
        dataType:'json',
        url: url,
        success:function (data) {
            console.log(data)
            layer.msg(data.msg)
        }
    })
}