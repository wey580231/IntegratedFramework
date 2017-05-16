/**
 * Created by XY on 2017/5/11.
 */
var SelectFalse = false; //用于判断是否被选择条件
function Submit() {
    var chboxValue = [];
    var CheckBox = $('input[name = order]');//得到所的复选框

    for (var i = 0; i < CheckBox.length; i++) {

//jquery1.6以上可以if(CheckBox[i].prop('checked') == true)去判断checkbox是否被选中
        if (CheckBox[i].checked)//如果有1个被选中时
        {
            SelectFalse = true;
            chboxValue.push(CheckBox[i].value)//将被选择的值追加到
        }
    }

    if (!SelectFalse) {
        /*alert("对不起：至少要选一项");*/
        UIkit.notification({
            message: '对不起：至少要选一项！',
            status: 'danger',
            pos: 'top-right',
            timeout: 500
        });
        return false;
    } else window.location.href = "step2.jsp";

}
