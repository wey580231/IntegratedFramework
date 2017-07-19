package com.rengu.actions;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.SnapshotDao;
import com.rengu.DAO.impl.SnapshotDaoImpl;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;
import java.util.Map;

/**
 * 快照树管理操作，用于查看快照节点的3D模拟/用于将选中快照下发给MES
 * Created by wey580231 on 2017/6/27.
 */
public class SnapshotAction extends SuperAction {

    SnapshotDao snapshotDao = new SnapshotDao();

    //查看当前快照对应结果在3的车间中的运行状态
    public void viewCurrentSnapshotEmulateData() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean opresult = false;
        StringBuilder jsonString = new StringBuilder();

        if (parameterMap.size() == 1) {
            String[] ids = (String[]) parameterMap.get("id");
            if (ids.length == 1) {
                opresult = snapshotDao.switchToEmulateData("1", ids[0]);
            }
        }

        if (opresult) {
            Tools.jsonPrint(Tools.resultCode("ok", "Execute operation"), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }

    //将当前选中的快照节点对应的结果下发给MES
    public void dispatcherResultToMess() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean opresult = false;
        StringBuilder jsonString = new StringBuilder();

        if (parameterMap.size() == 1) {
            String[] ids = (String[]) parameterMap.get("id");
            if (ids.length == 1) {
                opresult = snapshotDao.switchResultToMess("1", ids[0]);
            }
        }

        if (opresult) {
            Tools.jsonPrint(Tools.resultCode("ok", "Execute operation"), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }

    public void getAllByLevel() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        String level = jsonNode.get("level").asText();
        SnapshotDaoImpl snapshotDao = DAOFactory.getSnapshotDaoImplInstance();
        List<RG_SnapshotNodeEntity> rg_snapshotNodeEntityList = snapshotDao.findAllByLevel(level);
        if (rg_snapshotNodeEntityList.size() > 0) {
            //生成根节点树
            JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
            JsonFactory jsonFactory = new JsonFactory();
            //根节点
            ArrayNode arrayNode = jsonNodeFactory.arrayNode();
            for (RG_SnapshotNodeEntity rg_snapshotNodeEntity : rg_snapshotNodeEntityList) {
                ObjectNode snapshotNode = jsonNodeFactory.objectNode();
                snapshotNode.put("id", rg_snapshotNodeEntity.getId());
                snapshotNode.put("name", rg_snapshotNodeEntity.getName());
                arrayNode.add(snapshotNode);
            }
            String jsonString = new ObjectMapper().writeValueAsString(arrayNode);
            Tools.jsonPrint(jsonString, this.httpServletResponse);
        } else {
            Tools.jsonPrint(null, this.httpServletResponse);
        }
    }

    public void getAllById() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        String snapshotId = jsonNode.get("id").asText();
        SnapshotDaoImpl snapshotDao = DAOFactory.getSnapshotDaoImplInstance();
        RG_SnapshotNodeEntity rg_snapshotNodeEntity = snapshotDao.findAllById(snapshotId);
        String jsonString = Tools.entityConvertToJsonString(rg_snapshotNodeEntity);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }
}
