let vm = new Vue({
    el:'#main-container',
    data:{
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        params:{
            pageNum: '',
            pageSize: '',
            type:''
        },
        statute:{

        },
        myConfig:{
            UEDITOR_HOME_URL:'static/ueditor/',  //默认ueditor加载资源的前缀路径
            charset:"utf-8",
            serverUrl:'doExec',//  后端统一接口路径   /ueditor/doExce
            initialFrameHeight:400
        }
    },
    methods:{
        selectByCondition:function (pageNum,pageSize) {
            this.params.pageNum=pageNum;
            this.params.pageSize=pageSize;

            axios({
                url:'manager/statute/toList',
                data:this.params,
                method:'post'
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                layer.msg(error);
            })
        },
        insert:function () {
            //提交信息到后端
            axios({
                url:'manager/statute/insert',
                method:'post',
                data:this.statute
            }).then(response =>{
                //返回结果如果是成功则显示提示、切换到列表页、清空新增表单信息
                if(response.data.success){
                    this.selectByCondition(1,5);
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
        toUpdate: function (id) {
            axios({
                url:'manager/statute/toUpdate',
                params: {id:id}
            }).then(response => {

                layer.obj = response.data;//返回数据，绑定到layer上，传递给子窗口
                // console.log(layer)
                let index = layer.open({
                    type:2,
                    title:'修改法规',
                    content:'manager/statute/toUpdatePage',
                    area:['90%','90%'],
                    end: () => {//将then函数中的this传递到end的回调函数中
                        // console.log(".....")
                        //刷新页面数据    1.直接查询selectAll实现    2.获取layer.appVersion更新当前pageInfo的该数据
                        this.selectByCondition(this.pageInfo.pageNum,this.pageInfo.pageSize);
                    }
                });
            }).catch(function (error) {
                console.log(error);
            })
        },
        delete2:function (id) {
            var _this = this;
            layer.confirm('您确定要删除吗', {
                btn: ['确定','取消'] //按钮
            }, function(){
                axios({
                    url:'manager/statute/delete',
                    params:{id:id}
                }).then(response => {
                    if (response.data.success){
                        layer.msg(response.data.msg);
                        _this.selectByCondition(1,5);
                        return;
                    }
                    layer.msg(response.data.msg);
                })
            }, function(){
                layer.msg('已取消', {icon: 1});
            });
        },

        select:function(){
            this.clear();
            this.selectByCondition(1,5)
        },
        clear:function () {
            this.statute = {
            }
        }
    },
    created:function () {
        this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },

    components:{//引入vue的富文本编辑器组件
        VueUeditorWrap
    }
})