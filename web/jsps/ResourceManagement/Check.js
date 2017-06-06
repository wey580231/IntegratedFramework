/**
 * Created by zhaoqi on 2017/5/23.
 */
function formValidate() {
    var number = $("#number"),
        name = $("#name"),
        type = $("#type"),
        groups = $("#groups"),
        speed = $("#speed"),
        power = $("#power"),
        lower = $("#lower"),
        upper = $("#upper"),
        recover = $("#recover"),
        formal = $("#formal"),
        state = $("#state");
    if (checkName(name) && checkNumber(number)) {
        $("#order tbody").append("<tr>" +
            "<td>" + "</td>" +
            "<td>" + number.val() + "</td>" +
            "<td>" + name.val() + "</td>" + +
                "</tr>");
        UIkit.modal.confirm('确定添加吗？', function () {
            UIkit.modal.alert('添加成功！');
        });
        return true;
    } else {
        UIkit.modal.alert('请输入完整信息！');
        return false;
    }

}

function checkName(name) {
    var NameRegexp = /^[a-z]([0-9a-z_])+$/;
    if (name == "") {
        $("input#name").addClass("uk-form-danger");
        return false;
    }
    if (!NameRegexp.test(name)) {
        $("input#name").addClass("uk-form-danger");
        return false;
    }
    $("input#name").addClass("uk-form-success");
    return true;
}

function checkNumber(number) {
    var NumberRegexp = /^([0-9a-zA-Z])+$/;
    if (number == "") {
        $("input#number").addClass("uk-form-danger");
        return false;
    }
    if (!passwordRegExp.test(password)) {
        $("input#number").addClass("uk-form-danger");
        return false;
    }
    $("input#number").addClass("uk-form-success");
    return true;
}


function checkLength(number) {
    var max = 16;
    var min = 8;
    if (number == "") {
        $("input#number").addClass("uk-form-danger");
        return false;
    }
    if (number.val().length > max || number.val().length < min) {
        $("input#number").addClass("uk-form-danger");
        return false;
    }
    $("input#number").addClass("uk-form-success");
    return true;
}
