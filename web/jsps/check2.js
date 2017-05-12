/**
 * Created by hanchangming on 2017/5/11.
 */

function check() {
    var ipaddress = document.myform.ip.value;
    var orderno = document.myform.myorder.value;
    var radios = document.getElementsByTagName("radio1");
    var result = false;

    //正则表达式
    var EMailRegExp = /^((?:(?:25[0-5]|2[0-4]\d|[01]?\d?\d)\.){3}(?:25[0-5]|2[0-4]\d|[01]?\d?\d))+$/;
    var ordernoRegExp = /^\d{5,10}$/;

    if (!EMailRegExp.test(ipaddress)) {     //判断ip
        UIkit.notification({
            message: '请输入合法的ip地址！',
            status: 'danger',
            pos: 'top-right',
            timeout: 500
        });
        return false;
    } else if (!ordernoRegExp.test(orderno)) {     //判断订单号
        UIkit.notification({
            message: '请输入合法的订单号！',
            status: 'danger',
            pos: 'top-right',
            timeout: 500
        });
        return false;
    } else window.location.href = "step3.jsp";

}

