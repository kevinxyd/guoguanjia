new Vue({
    el:'.main-container',
    data:{
        app:''
    },
    methods:{
        //修改方法
        doUpdate:function () {
            axios({
                url:'manager/app/doUpdate',
                method:'post',
                data:this.app
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
        //从父窗口对象中获取到需要传递的对象数据，绑定到当前vue对象的user属性上

        // console.log(layer);
        //parent  是layer封装的父窗口对象
        // console.log(parent.layer.user);
        this.app = parent.layer.obj;
    }
});

//取消相当于关闭窗口
function cancle() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}