package com.rengu.actions;

import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.entity.FullCalendarEvent;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.*;

/**
 * Created by hanchangming on 2017/6/20.
 */
public class FullCalendarAction extends SuperAction {

    public String getAllFullCalendarEvents() throws Exception {
        OrdersDAOImpl ordersDAO = DAOFactory.getOrdersDAOInstance();
        List<RG_OrderEntity> rg_orderEntityList = ordersDAO.findAll();
        List<FullCalendarEvent> fullCalendarEventList = new ArrayList<>();
        for (RG_OrderEntity tempRG_OrderEntity : rg_orderEntityList) {
            FullCalendarEvent fullCalendarEvent = new FullCalendarEvent();
            fullCalendarEvent.setId(UUID.randomUUID().toString());
            fullCalendarEvent.setAllDay(true);
            if (!tempRG_OrderEntity.getName().isEmpty()) {
                fullCalendarEvent.setTitle(tempRG_OrderEntity.getName());
            } else {
                continue;
            }
            if (!tempRG_OrderEntity.getT0().isEmpty()) {
                fullCalendarEvent.setStart(Tools.stringConvertToDate(tempRG_OrderEntity.getT0()));
            } else {
                continue;
            }
            if (!tempRG_OrderEntity.getT2().isEmpty()) {
                Date realDate = Tools.stringConvertToDate(tempRG_OrderEntity.getT2());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(realDate);
                calendar.add(Calendar.DATE, 1);
                fullCalendarEvent.setEnd(calendar.getTime());
            } else {
                continue;
            }
            fullCalendarEventList.add(fullCalendarEvent);
        }
        String jsonString = Tools.entityConvertToJsonString(fullCalendarEventList);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
        System.out.println(jsonString);
        return "success";
    }
}
