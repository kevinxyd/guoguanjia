new Vue({
    el:'.main-container',
    data:{
        qu:'',
        oid:''
    },
    methods:{
        //修改方法
        doUpdate:function (check) {
            axios({
                url:'manager/qualification/doUpdate',
                data:{id:this.qu.id,check:check,description:this.qu.description},
                method:'post'
            }).then(response => {
                if (response.data.success){
                    let index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    parent.layer.msg(response.data.msg);
                    return;
                }
                layer.msg(response.data.msg)
            }).catch(function (error) {
                layer.msg(error);
            })
        }
    },
    created:function () {
        this.qu = parent.layer.obj;
        this.oid = parent.layer.oid;
    }
});