layui.use('table', function(){
    var table = layui.table;
    
    //方法级渲染
    table.render({
      elem: '#LAY_table_user'
      ,url: '../parttimework/condition/1/50'
      ,cols: [[
        {field:'id', title: 'ID', width:50,fixed: true}
        ,{field:'title', title: '标题', width:120,align:'center'}
        ,{field:'content', title: '内容', width:220,align:'center'}
        ,{field:'workAddress', title: '地址', width:220,align:'center'}
        ,{field:'workDate', title: '工作日期',width:120,sort: true,align:'center'}
        ,{field:'typeOfWork', title: '工作类型', width:120,align:'center'}
        ,{field:'companyId', title: '公司ID', width:140,align:'center'}
        ,{field:'releaseDate', title: '发布日期', width:160,sort: true,align:'center'}
        ,{fixed:'right',width:178,title:'操作', width:95,align:'center', toolbar: '#barDemo'}
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
        data0 = {"workRequest":true,"id":data.id};

        console.log("locked")
        $.ajax({
          type:'PATCH',
          //data:{"locked":true,"id":data.id},
          data:JSON.stringify(data0),
          url:'../parttimework/status',
          dataType:'json',
          contentType:'application/json',
          success: function (data) {
            layer.msg(data.msg)
            table.reload('testReload')
          }
        })
      } else if(obj.event === 'edit'){
        // var data = {"sid":data.id,"pid":pid,"status":false}
        data1 = {"workRequest":false,"id":data.id};
        /**
         * 踩坑，data需要JSON.stringify(data)
         */
        $.ajax({
          type:'PATCH',
          //data:{"locked":false},
          data:JSON.stringify(data1),
          url:'../parttimework/status',
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