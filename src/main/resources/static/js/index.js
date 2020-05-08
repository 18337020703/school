
// $(document).ready(function(){
//     $.ajax({
//         type:'GET',
//         dataType:'JSON',
//         url:'http://localhost:8080/session',
//         success: function (data) {
//             var data = data.session;
//             console.log("cookie"+data.nickname.toString())
//             // $.cookie('cookieUsername', data.nickname, { expires: 7, path: '/' });
//             $.cookie('cookieNickname', data.nickname);
//         }
//     })
// });
$('#showHead').on('click',function () {
    window.open('../public/index.html')
})
  //JavaScript代码区域
  layui.use(['layer', 'form', 'element', 'jquery'],function () {
      var element = layui.element;
      var $ = layui.jquery;

      element.on('nav(leftNav)', function(elem){
                  var navA = $(elem);
                 console.log(navA.attr('href'))
                  var id = navA.attr('data-id');
                  var url = navA.attr('data-url');
                  var text = navA.attr('data-text');
                  console.log(id,url,text)
                  $('.layui-body').find('iframe').attr('src',url)
                  //$('#showHead').text(text)
                   if(!url){
                      return;
                  }
      });

      element.on('nav(test)', function(elem){
          var navA = $(elem);
          //console.log(navA.attr('href'))
         // var id = navA.attr('data-id');
          var url = navA.attr('data-url');
         // var text = navA.attr('data-text');
          console.log(url)
          $('.layui-body').find('iframe').attr('src',url)
          //$('#showHead').text(text)
          if(!url){
              return;
          }
      });
      element.on('nav(rightNav)', function(elem){
        var navA = $(elem);
       console.log(navA.attr('href'))
        var id = navA.attr('data-id');
        var url = navA.attr('data-url');
        var text = navA.attr('data-text');
        console.log(id,url,text)
        $('.layui-body').find('iframe').attr('src',url)
       // $('#showHead').text(text)
         if(!url){
            return;
        }
});


element.on('nav(headNav)', function(elem){
  var navA = $(elem);
 console.log(navA.attr('href'))
  var id = navA.attr('data-id');
  var url = navA.attr('data-url');
  var text = navA.attr('data-text');
  console.log(id,url,text)
  $('.layui-body').find('iframe').attr('src',url)
 // $('#showHead').text(text)
   if(!url){
      return;
  }
});
      /**
       * 注销登录
       */
    $('#logout').on('click',function () {
        console.log("注销登录")
        $.ajax({
            type:'POST',
            url:'../logout',
            success:function () {
                layer.msg("注销成功")
            }
        })
    })
      /**
       * 学生个人资料跳转
       */
      $('#login').on('click',function () {
          window.open('../page/index.html')
      })
      /**
       * 商家个人资料跳转
       */
      $('#issue').on('click',function () {
          window.open('../merchants/merchantsIndex.html')
      })
      $.ajax({
          type:'GET',
          dataType:'JSON',
          url:'../session',
          success: function (data) {
              var data = data.session;
              if (data != null){
                  console.log(data.nickname)
                  $('#nickname').html(data.nickname)
                  $('#img').attr("src",data.path)
              }
          }
      })
      $.ajax({
          type:'GET',
          dataType:'JSON',
          url:'../session/merchants',
          success: function (data) {
              var data = data.session;
              console.log("1111111111"+data)
              if (data != null){
                  console.log(data.nickname)
                  $('#nickname').html(data.nickname)
              }
          }
      })
  });


  //隐藏左侧栏
//   layui.use('element', function(){
//     var element = layui.element;
// });
var isShow=1;
function iconHide(){
  if(isShow===1)
    hide();
  else
    show();
  isShow*=-1;
}
function hide(){
    $('.layui-side span').hide();  
    $('.layui-header span').hide(); 
    $('.layui-side').animate({width:'55px'});
    $('.layui-body').animate({left:'65px'});
    $('#showHead').animate({left:'-100px'});
    $('.hide-icon').animate({left:'-105px'});
    document.getElementById('hide').className="layui-color layui-icon layui-icon-spread-left";    		
}
function show(){
    $('.layui-side span').show();  
    $('.layui-header span').show(); 
    $('.layui-side').animate({width:'150px'});
    $('.layui-body').animate({left:'150px'});
    $('#showHead').animate({left:'15px'});
    $('.hide-icon').animate({left:'0px'});
    document.getElementById('hide').className="layui-color layui-icon layui-icon-shrink-right";      	
}
function ulHide(){
  if(isShow===-1)
    show();
  isShow=1;
} 

