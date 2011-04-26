//编辑者：周永丰
Ext.namespace("JsHelper");

// 操作确认框  msg: 提示消息；fn:事件;animEl:从什么DOM飞出
JsHelper.OK = function(msg, fn, animEl) {
    Ext.Msg.show({
        title: '<font size=2>系统确认</font>',
        msg: '<font size=2>' + msg + '</font>',
        buttons: Ext.Msg.OK,
        fn: fn,
        animEl: animEl,
        icon: Ext.MessageBox.INFO
    });
}
// 系统出错提示信息 msg:消息内容;fn:事件;animEl:从什么DOM飞出
JsHelper.ShowError = function(msg, fn, animEl) {
    Ext.Msg.show({
        title: '<font size=2>系统错误</font>',
        msg: '<font size=2>' + msg + '</font>',
        buttons: Ext.Msg.OK,
        fn: fn,
        animEl: animEl,
        icon: Ext.MessageBox.ERROR
    });
}
// 一般性的错误提示信息 msg:消息内容;fn:事件;animEl:从什么DOM飞出
JsHelper.ShowWarning = function(msg, fn,animEl ) {
    Ext.Msg.show({
        title: '系统消息',
        msg: msg,
        buttons: Ext.Msg.OK,
        fn: fn,
        animEl: animEl,
        icon: Ext.MessageBox.WARNING
    });
}
//打印
JsHelper.Print = function(url, data, method, store, openurl) {
    Ext.Ajax.request({
        url: url,
        params: { data: data },
        method: method,
        success: function() {
            //window.location.target = "_blank";
            //window.location.href = openurl;
            window.open(openurl, "_blank");
            store.reload();
        },
        failure: function(response) {
            JsHelper.ShowWarning("数据打印失败！");
        }
    })
}
