package com.rengu.DAO.aps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.util.ApsTools;

/**
 * Created by wey580231 on 2017/6/14.
 */
public class ApsDao {

    public boolean queryCurrState(StringBuilder result) {
        boolean flag = false;
        int state = ApsTools.instance().queryExecuteState();

        ObjectMapper mapper = new ObjectMapper();  //定义转换类
        ObjectNode root = mapper.createObjectNode();//创建根节点
        root.put("result", "0");

        ObjectNode data = mapper.createObjectNode();
        data.put("state", state);

        root.put("data", data);

        try {
            result.append(mapper.writeValueAsString(root));
            flag = true;
        } catch (JsonProcessingException e) {

        }
        return flag;
    }
}
