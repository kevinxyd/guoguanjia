let vm = new Vue({
    el:'.row',
    data:{
       /* appList:'',*/
        pageInfo:'',
    },
    methods:{
        //分页查询
        selectAll:function(pageNum,pageSize){
            //发送ajax请求查询分页数据，并返回给userList接收  ，通过  vue接管遍历显示数据
            axios({
                url:'manager/demand/toList',
                params:{pageNum:pageNum,pageSize:pageSize}
            }).then(response =>{
                this.pageInfo = response.data;
                console.log(response.data)
            }).catch(error =>{
                layer.msg(error);
            })
        },
        toUpdate:function (id) {
            axios({
                url:'manager/demand/toDetail',//修改详情公用
                params:{id:id}
            }).then(response =>{
                console.log(response.data);
                layer.detail = response.data;//给layer对象绑定临时属性user
                //弹出子窗口
                layer.open({
                    type:2,//弹框类型是一个iframe
                    content:'manager/demand/toUpdatePage',//弹出的子窗口需要加载的页面
                    area:['80%','80%'],//按比例占据父窗口的宽高
                    end:() =>{//关闭子窗口的时候回调函数
                        console.log(111);
                        this.selectAll();//重新更新列表
                    }
                })
            }).catch(function (error) {
                console.log(error);
            })
        },
        selectByDetail:function (id) {
            axios({
                url:'manager/demand/toDetail',
                params:{id:id}
            }).then(response =>{
                console.log(response.data);
                layer.detail = response.data;//给layer对象绑定临时属性user
                console.log(response.data)
                //弹出子窗口
                layer.open({
                    type:2,//弹框类型是一个iframe
                    content:'manager/demand/toDetailPage',//弹出的子窗口需要加载的页面
                    area:['80%','80%'],//按比例占据父窗口的宽高

            })
        }).catch(function (error) {
                console.log(error);
            })
        }
    },
    created:function () {
        this.selectAll(1,8);
    }
})