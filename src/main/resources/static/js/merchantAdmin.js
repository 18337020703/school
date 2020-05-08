layui.use('table', function(){
    var table = layui.table;
    
    //方法级渲染
    table.render({
      elem: '#LAY_table_user'
      ,type:'GET'
      ,url: '../user/merchants'
      ,cols: [[
        {field:'id', title: 'ID', width:140, fixed: true}
        ,{field:'username', title: '用户名', width:120, align:'center'}
        ,{field:'nickname', title: '昵称', width:100, align:'center'}
        ,{field:'phone', title: '联系方式', width:120, align:'center'}
        ,{field:'companyName', title: '公司名称',width:170, align:'center'}
        ,{field:'companyPhone', title: '公司电话',width:120, align:'center'}
        ,{field:'companyAddress', title: '公司地址', width:180, align:'center'}
        ,{field:'companyIntroduction', title: '公司简介', width:180, align:'center'}
        ,{fixed:'right',width:178,title:'操作', align:'center', width:111,toolbar: '#barDemo'}
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
          //data:JSON.stringify(data0),
          data:JSON.stringify(data0),
          url:'../user/locked',
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
          url:'../user/locked',
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