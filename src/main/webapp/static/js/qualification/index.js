let vm = new Vue({
    el:'#main-container',
    data:{
       /* appList:'',*/
        pageInfo:'',
        params:{
            type:'',
            check:''
        }
    },
    methods:{
        //分页查询
        selectAll:function(pageNum,pageSize){
            this.params.pageNum=pageNum;
            this.params.pageSize=pageSize;
            //发送ajax请求查询分页数据，并返回给userList接收  ，通过  vue接管遍历显示数据
            axios({
                url:'manager/qualification/toList',
                data:this.params,
                method:'post'
            }).then(response =>{
                this.pageInfo = response.data;
            }).catch(error =>{
                layer.msg(error);
            })
        },
        toUpdate:function (id) {
            axios({
                url:'manager/qualification/toUpdate',
                params:{id:id}
            }).then(response =>{
                console.log(response.data);
                layer.obj = response.data.qu;//给layer对象绑定临时属性user
                layer.oid = response.data.oid;
                console.log(response.data)
                //弹出子窗口
                layer.open({
                    type:2,//弹框类型是一个iframe
                    content:'manager/qualification/toUpdatePage',//弹出的子窗口需要加载的页面
                    area:['80%','80%'],//按比例占据父窗口的宽高
                    end:() =>{//关闭子窗口的时候回调函数
                        // console.log(111);
                        this.selectAll();//重新更新列表
                    }
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