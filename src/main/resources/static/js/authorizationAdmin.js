layui.use('table', function(){
    var table = layui.table;
    
    //方法级渲染
    table.render({
      elem: '#LAY_table_user'
      ,type:'get'
      ,url: '../user/student'
      ,cols: [[
         {field:'id', title: 'ID', width:155, fixed: true}
        ,{field:'username', title: '用户名', width:120, align:'center'}
        ,{field:'sex', title: '性别', width:80, align:'center'}
        ,{field:'phone', title: '电话', width:120, align:'center'}
        ,{field:'bir', title: '生日',width:120, align:'center'}
        ,{field:'university', title: '大学', width:150, align:'center'}
        ,{field:'department', title: '学院', width:160, align:'center'}
        ,{field:'profession', title: '专业', width:160, align:'center'}
        ,{fixed:'right',width:178,title:'操作', align:'center', toolbar: '#barDemo'}
      ]]
      ,id: 'testReload'
      ,page:true
      ,height: 568
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
    layui.use('jquery',function () {
      var $ = layui.jquery;
      table.on('tool(demo)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
          // layer.msg('ID：'+ data.id + ' 的查看操作');
          /**
           * 踩坑，data需要JSON.stringify(data)
           */
          data0 = {"locked":true,"id":data.id};

          console.log("locked")
          $.ajax({
            type:'POST',
            //data:{"locked":true,"id":data.id},
            data:JSON.stringify(data0),
            url:'../user/setLocked',
            dataType:'json',
            contentType:'application/json',
            success: function (data) {
              layer.msg(data.msg)
              table.reload('testReload')
            }
          })
        } else if(obj.event === 'edit'){
          // var data = {"sid":data.id,"pid":pid,"status":false}
          data1 = {"locked":false,"id":data.id};
          /**
           * 踩坑，data需要JSON.stringify(data)
           */
          $.ajax({
            type:'POST',
            //data:{"locked":false},
            data:JSON.stringify(data1),
            url:'../user/setLocked',
            dataType:'json',
            contentType:'application/json',
            success: function (data) {
              layer.msg(data.msg)
              table.reload('testReload')
            }
          })
          // layer.alert('编辑行：<br>'+ JSON.stringify(data))
        }
      });
    })

  });