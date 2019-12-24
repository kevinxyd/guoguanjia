let vs = new Vue({
    el:'.main-container',
    data:{
        app:''
    },
    created:function () {
        this.app = parent.layer.detail;
    }
});