
// $(document).ready(function(){
//   var name2 = $.cookie('cookieNickname');
//   $("#type2").text(name2);
//   console.log("111555"+name2)
// });

layui.use(['laytpl', "element", "jquery", "carousel",'laypage', 'layer'], function () {
    laytpl = layui.laytpl;
    var element = layui.element;
    $ = layui.$;
    var carousel = layui.carousel;
  var laypage = layui.laypage
      ,layer = layui.layer;
  
    carousel.render({
      elem: '#test2'
      , interval: 1800
      , anim: 'fade'
      , height: '120px'
    });
    carousel.render({
      elem: '#test3'
      , interval: 3000
      , anim: 'fade'
      , height: '200px'
      , width: '399px'
      , indicator: 'none'
      , backgroundcolor: 'red'
    });
    carousel.render({
      elem: '#test4'
      , interval: 3000
      , anim: 'fade'
      , height: '520px'
      , width: '242px'
      , indicator: 'none'
    });
    $.ajax({
      type:'GET',
      dataType:'JSON',
      url:'../parttimework/totalCountsByCondition',
      success:function (data) {
        var counts = data;
        console.log(data)
        laypage.render({
          elem: 'demo2-1'
          ,count: counts
          ,theme: '#FF5722'
          ,limit: 5
          ,jump:function (obj) {
            console.log(obj.curr)
            console.log(obj.limit)
            var curr = obj.curr
            var limit = obj.limit
            var url = '../parttimework/'+curr+'/'+limit
            console.log(url)
            $.ajax({
              type:'GET',
              url:url,
              dataType:'JSON',
              success: function (data) {

                $('#loading').hide();
                console.log("session"+data.session)
                var data = data.data
                console.log(data)
                var getTpl = document.getElementById('demo').innerHTML
                    , view = document.getElementById('view');
                laytpl(getTpl).render(data, function (html) {
                  view.innerHTML = html;
                });
              }
            })
          }
        });
      }
    })

  /**
   * 分类查询
   */
  $('.choice-type').on('click',function () {
    $('.choice-type').css({"background-color":"white","color":"#000"})
    $(this).addClass("class").siblings().removeClass('class');
    $('.class').css({"background-color":"#009688","color":"white"})
    var type = $(this).attr("value");
    var objData = {'typeOfWork':type,'workStatus':'true','worRequest':'true'}
    console.log(type)
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
});

/**
 * 踩坑，以后遇到这种直接传过来一个对象
 *  onclick="abc($(this))"
 * @param obj
 */
function abc(obj){
  var self = obj.val();
  console.log("BUTTON is work"+obj.attr("id"))

  $.ajax({
    type:'POST',
    data:self,
    url:'../parttimework/deal',
    dataType:'JSON',
    contentType:'application/json',
    beforeSend:function () {
      $('#loading').show()
    },
    success:function (data) {
      $('#loading').hide()
      //var cookie = $.cookie('cookieNickname')
      //console.log("88888"+cookie)
      if(data.res=="报名成功") {
        obj.css({"background-color": "#fff", "border": "1px #5b5b5b solid", "color": "#5c5c5c"})
        obj.removeAttr("onclick")
        obj.html("已经报名")
      }
      layer.msg(data.res)
    }
  })
}