let vm = new Vue({
    el:'#main-container',
    data:{
        user:{},
        roles:[]
    },
    methods:{
        doUpdate:function () {
            //获取所有的wasteType并且由vue生成option

            axios({
                url:'manager/user/doUpdate',
                method:'post',
                data:this.user
            }).then(response => {
                console.log(response.data)
                let index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                parent.layer.msg(response.data.msg);
                layer.msg(response.data.msg);

            }).catch(function (error) {
                layer.msg(error.message);
            })
        },
        selectRoles:function(){
            axios({
                url:'manager/role/selectRoles'
            }).then(response => {
                this.roles = response.data;//   this.setNodes(.....)

            }).catch(function (error) {
                layer.msg(error.message);
            })
        }
    },
    created:function () {
        //在vue对象创建的时候获取父窗口layer对象绑定的数据，放入当前app对象中
        this.user = parent.layer.obj.sysUser;
        this.roles = parent.layer.obj.sysRoles;
        let str = this.user.roleName;
        this.user.roleName = str.split(",");
        console.log(this.user)
        console.log(this.roles)
    }
})