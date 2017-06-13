package com.rengu.actions.aps;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.actions.SuperAction;
import com.rengu.util.Tools;

import java.util.Map;

/**APS回调框架更新计算状态
 * Created by wey580231 on 2017/6/13.
 */
public class FeedBackStateAction extends SuperAction {

    public void update() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;
        StringBuilder jsonString = new StringBuilder();

        if (parameterMap.size() == 3) {
            String[] id = (String[]) parameterMap.get("id");
            String[] state = (String[]) parameterMap.get("STATE");
            String[] message = (String[]) parameterMap.get("MESSAGE");
            if (id.length > 0 && state.length > 0 && message.length > 0) {
                //计算成功
                if(state[0]=="1")
                {

                }
                //计算失败
                else if(state[0]=="0")
                {

                }
            }
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }
}
