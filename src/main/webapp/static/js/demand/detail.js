let vs = new Vue({
    el:'.main-container',
    data:{
        demand:''
    },
    created:function () {
        this.demand = parent.layer.detail;
    }
});