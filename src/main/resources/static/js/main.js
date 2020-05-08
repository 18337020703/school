


layui.use(['laytpl', "element", "jquery", "carousel"], function () {
  laytpl = layui.laytpl;
  var element = layui.element;
  $ = layui.$;
  var carousel = layui.carousel;

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
    url:'../parttimework/1/3',
    dataType:'JSON',
    success: function (data) {
      var data = data.data
      var getTpl = document.getElementById('demo').innerHTML
          , view = document.getElementById('view');
      laytpl(getTpl).render(data, function (html) {
        view.innerHTML = html;
      });
    }
  })

  element.init();
  $.ajax({
    type:'GET',
    url:'../parttimework/1/3',
    dataType:'JSON',
    success: function (data) {
      var data1 = data.data
      var getTpl = document.getElementById('demo1').innerHTML
          , view = document.getElementById('view1');
      laytpl(getTpl).render(data1, function (html) {
        view.innerHTML = html;
      });
    }
  })





  $('.layui-icon-location').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    // layer.tips("<span style='color:#3e3d3d'>tel:"+self+"</span>", that,{
    layer.tips('<div id="container" style="width:300px; height: 180px;"></div> ', that, {
      tips: [1, '#fff']
      , area: ['320px', '320px']
    });

  });

  $('.layui-icon-star').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    layer.tips("<span style='color:#3e3d3d'>tel:" + self + "</span>", that, {
      tips: [1, '#ffffff']
      , area: ['120px', '50px']
    });
  });

  $('.layui-icon-dialogue').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    layer.tips("<span style='color:#3e3d3d'>tel:" + self + "</span>", that, {
      tips: [1, '#ffffff']
      , area: ['120px', '50px']
    });
  });

  $('.layui-icon-login-wechat').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    layer.tips("<img src='../img/QRcode/" + self + "'style='width:200px;height:200px'>", that, {
      tips: [1, '#fff']
      , area: ['230px', '217px']
    });
  });

  $('.layui-icon-login-qq').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    layer.tips("<img src='../img/QRcode/" + self + "'style='width:200px;height:200px'>", that, {
      tips: [1, '#ffffff']
      , area: ['220px', '220px']
    });
  });

  $('.layui-icon-cellphone').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    layer.tips("<span style='color:#3e3d3d'>tel:" + self + "</span>", that, {
      tips: [1, '#ffffff']
      , area: ['120px', '50px']
    });
  });

  $('.layui-icon-group').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    layer.tips("<span style='color:#3e3d3d'>tel:" + self + "</span>", that, {
      tips: [1, '#ffffff']
      , area: ['120px', '50px']
    });
  });

  $('.layui-icon-release').on('click', function () {
    var self = $(this).attr("value");
    var that = this;
    layer.tips("<span style='color:#3e3d3d'>tel:" + self + "</span>", that, {
      tips: [1, '#ffffff']
      , area: ['120px', '50px']
    });
  });
  // }
  //})



});


// var map = new AMap.Map('container', {
//   resizeEnable: true, //是否监控地图容器尺寸变化
//   zoom:10, //初始化地图层级
//   center: [116.397428, 39.90923] //初始化地图中心点
// });












