package com.rengu.actions;

import com.rengu.DAO.EventLogDAO;
import com.rengu.DAO.impl.EventLogDAOImpl;
import com.rengu.entity.RG_EventLogEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanch on 2017/7/20.
 */
public class EventLogAction extends SuperAction {
    public void getAllEventLog() throws Exception {
        EventLogDAO eventLogDAO = DAOFactory.getEventLogDAOImplInstance();
        List list = eventLogDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_EventLogEntity rg_eventLogEntity = Tools.jsonConvertToEntity(jsonString, RG_EventLogEntity.class);
        rg_eventLogEntity.setId(Tools.getUUID());
        EventLogDAOImpl eventLogDAOImplInstance = DAOFactory.getEventLogDAOImplInstance();
        if (eventLogDAOImplInstance.save(rg_eventLogEntity)) {
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("EvenLog保存失败", "alert"));
        }
    }
}
