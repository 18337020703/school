layui.use(['table','layer'], function(){
    var table = layui.table;
  /**
   * 获取路径？后面传的参数
   * @type {string}
   */
  var url = location.search; //获取url中"?"符后的字串 ('?endId=.....')
  var theRequest = new Object();
  if (url.indexOf("?") != -1) {
    var str = url.substr(1); //substr()方法返回从参数值开始到结束的字符串；
    var strs = str.split("&");
    for (var i = 0; i < strs.length; i++) {
      theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
    }
  }
  console.log("111"+decodeURI(theRequest.id))
  var pid = decodeURI(theRequest.id)
  var url = '../parttimework/studentList/'+pid
    //方法级渲染
    table.render({
      elem: '#LAY_table_user'
      ,url: url
      ,cols: [[
        {field:'nickname', title: '姓名', width:100, sort: true}
        ,{field:'sex', title: '性别', width:80}
        ,{field:'phone', title: '手机号码',width:120}
        ,{field:'bir', title: '生日', sort: true, width:180}
        ,{field:'university', title: '学校', sort: true, width:180}
        ,{field:'department', title: '学院', width:180}
        ,{field:'profession', title: '专业', sort: true, width:185}
        ,{fixed:'right',width:178,title:'操作', align:'center', toolbar: '#barDemo'}
      ]]
      ,id: 'testReload'
      ,page: true
      ,height: 580
      ,skin:'line'
      ,size:'sm'
      ,even:false
      ,loading:false
      ,done:function () {
        $('#loading').hide()
      }
    });
    
    var $ = layui.$, active = {
      reload: function(){
        var demoReload = $('#demoReload');
        
        //执行重载
        table.reload('testReload', {
          page: {
            curr: 1 //重新从第 1 页开始
          }
          ,where: {
            key: {
              id: demoReload.val()
            }
          }
        }, 'data');
      }
    };
    
    $('.demoTable .layui-btn').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
      // layer.msg('ID：'+ data.id + ' 的查看操作');
      var data = {"sid":data.id,"pid":pid,"status":true}
      /**
       * 踩坑，data需要JSON.stringify(data)
       */
        $.ajax({
          type:'POST',
          data:JSON.stringify(data),
          url:'../parttimework/editDeal',
          dataType:'json',
          contentType:'application/json',
          success:function (data) {
            layer.msg(data.msg)
            table.reload("testReload")
          }
        })
    } else if(obj.event === 'edit'){
      var data = {"sid":data.id,"pid":pid,"status":false}
      /**
       * 踩坑，data需要JSON.stringify(data)
       */
      $.ajax({
        type:'POST',
        data:JSON.stringify(data),
        url:'../parttimework/editDeal',
        dataType:'json',
        contentType:'application/json',
        success:function (data) {
          layer.msg(data.msg)
          table.reload("testReload")
        }
      })
     // layer.alert('编辑行：<br>'+ JSON.stringify(data))
    }else if(obj.event === 'over'){
      var data = {"sid":data.id,"pid":pid,"status":true}
      /**
       * 踩坑，data需要JSON.stringify(data)
       */
      $.ajax({
        type:'POST',
        data:JSON.stringify(data),
        url:'../parttimework/editDealFinish',
        dataType:'json',
        contentType:'application/json',
        success:function (data) {
          layer.msg(data.msg)
          table.reload("testReload")
        }
      })
    }
  });
  });