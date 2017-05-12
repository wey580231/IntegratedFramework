/**
 * Created by hanchangming on 2017/5/11.
 */

function formValidate() {
    //用户输入
    var EMailAddress = $("input#EMailAddress").val();
    var password = $("input#password").val();

    if (checkEMailAddress(EMailAddress) && checkPassword(password)) {
        return true;
    } else {
        UIkit.notification({
            message: '请输入合法的用户名和密码！',
            status: 'danger',
            pos: 'top-right',
            timeout: 500
        });
        return false;
    }
}

function checkEMailAddress(EMailAddress) {
    //邮箱地址正则表达式
    var EMailRegExp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
    if (EMailAddress == "") {
        $("input#EMailAddress").addClass("uk-form-danger");
        return false;
    }
    if (!EMailRegExp.test(EMailAddress)) {
        $("input#EMailAddress").addClass("uk-form-danger");
        return false;
    }
    $("input#EMailAddress").addClass("uk-form-success");
    return true;
}

function checkPassword(password) {
    var passwordRegExp = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
    if (password == "") {
        $("input#password").addClass("uk-form-danger");
        return false;
    }
    if (!passwordRegExp.test(password)) {
        $("input#password").addClass("uk-form-danger");
        return false;
    }
    $("input#password").addClass("uk-form-success");
    return true;
}
