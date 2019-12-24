let vm = new Vue({
    el:'#main-container',
    data:{
       /* appList:'',*/
        pageInfo:'',
        app:{
            platform:0,
            forceUpdate:1//模拟默认值
        }//新增页面使用的对象
    },
    methods:{
        //分页查询
        selectAll:function(pageNum,pageSize){
            //发送ajax请求查询分页数据，并返回给userList接收  ，通过  vue接管遍历显示数据
            axios({
                url:'manager/app/toList',
                params:{pageNum:pageNum,pageSize:pageSize}
            }).then(response =>{
                this.pageInfo = response.data;
            }).catch(error =>{
                layer.msg(error);
            })
        },
        toUpdate:function (id) {
            axios({
                url:'manager/app/toUpdate',
                params:{id:id}
            }).then(response =>{
                console.log(response.data);
                layer.obj = response.data;//给layer对象绑定临时属性user
                //弹出子窗口
                layer.open({
                    type:2,//弹框类型是一个iframe
                    content:'manager/app/toUpdatePage',//弹出的子窗口需要加载的页面
                    area:['80%','80%'],//按比例占据父窗口的宽高
                    end:() =>{//关闭子窗口的时候回调函数
                        // console.log(111);
                        this.selectAll();//重新更新列表
                    }
                })
            }).catch(function (error) {
                console.log(error);
            })
        },
        insert:function () {
            //提交信息到后端
            axios({
                url:'manager/app/insert',
                method:'post',
                data:this.app
            }).then(response =>{
                //返回结果如果是成功则显示提示、切换到列表页、清空新增表单信息
                if(response.data.success){
                    this.selectAll(1,5);
                    this.clear();
                    console.log($("#myTab").find("li[class='active']"));
                    $("#myTab").find("li[class='active']").attr("class",'').siblings().attr("class",'active');//切换选项卡的激活状态
                    $("#home").addClass("active");
                    $("#profile").removeClass("active");
                }
                layer.msg(response.data.msg);
            }).catch( error => {

            })

        },
        clear:function () {
            this.app = {
            }
        },
        delete2:function (id) {
            var _this = this;
            layer.confirm('您确定要删除吗', {
                btn: ['确定','取消'] //按钮
            }, function(){
                axios({
                    url:'manager/app/delete',
                    params:{id:id}
                }).then(response => {
                    if (response.data.success){
                        layer.msg(response.data.msg);
                        _this.selectAll(1,5);
                        return;
                    }
                    layer.msg(response.data.msg);
                })
            }, function(){
                layer.msg('已取消', {icon: 1});
            });
        },
        //详情
        selectByDetail:function (id) {
            axios({
                url:'manager/app/toUpdate',
                params:{id:id}
            }).then(response =>{
                console.log(response.data);
                layer.detail = response.data;//给layer对象绑定临时属性user
                console.log(response.data)
                //弹出子窗口
                layer.open({
                    type:2,//弹框类型是一个iframe
                    content:'manager/app/toDetailPage',//弹出的子窗口需要加载的页面
                    area:['80%','80%'],//按比例占据父窗口的宽高
                })
            }).catch(function (error) {
                console.log(error);
            })
        }
    },
    created:function () {
        this.selectAll(1,5);
    }
})