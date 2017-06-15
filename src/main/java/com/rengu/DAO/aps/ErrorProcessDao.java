package com.rengu.DAO.aps;

import com.rengu.entity.ErrorState;
import com.rengu.entity.RG_AdjustDeviceEntity;
import com.rengu.util.ApsTools;
import com.rengu.util.MySessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于aps进行异常的处理
 * Created by wey580231 on 2017/6/15.
 */
public class ErrorProcessDao {

    public Integer processDeviceError(String id) {

        org.hibernate.Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Integer result = ApsTools.UNKNOWN;

        Query query = session.createQuery("from RG_AdjustDeviceEntity entity where entity.id=:id");
        query.setParameter("id", id);
        List list = query.list();
        if (list.size() == 1 && list.get(0) instanceof RG_AdjustDeviceEntity) {
            RG_AdjustDeviceEntity entity = (RG_AdjustDeviceEntity) list.get(0);

            //撤销资源
            if (entity.getCancelTime() != null && entity.getLatestCancelTime() != null) {
                result = ApsTools.instance().executeCommand(getCancelDeviceURL(entity));
            }
            //设置时段不可用
            else if (entity.getUnavailableStartDate() != null && entity.getUnavailableEndDate() != null) {
                result = ApsTools.instance().executeCommand(getUnavailableDeviceURL(entity));
            }

            //更新故障的状态
            if (result == ApsTools.STARTED) {
                entity.setState(ErrorState.ERROR_APS_PROCESS);
                session.update(entity);
            }
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }

    private String getCancelDeviceURL(RG_AdjustDeviceEntity entity) {
        String result = "/NCL:RUN?Program=./Model/Interaction/Rescheduling/Resource/RejectResource.n" +
                "&" +
                "BUFFER=1\\n2\\n" + entity.getResoureId() + "\\n001\\n2000-01-01\\t06:00\\n120\\n"
                + convertSpaceWithTab(entity.getCancelTime()) + "\\n" + convertSpaceWithTab(entity.getLatestCancelTime()) +
                "&" +
                "REPLY=" + ApsTools.instance().getReplyAddress() +
                "&" +
                "ID=001" +
                "&" +
                "DELAY=1000";
        return result;
    }

    private String getUnavailableDeviceURL(RG_AdjustDeviceEntity entity) {
        String result = "/NCL:RUN?Program=./Model/Interaction/Rescheduling/Resource/ModifyResourceTimeGantt.n" +
                "&" +
                "BUFFER=1\\n2\\n" + entity.getResoureId() + "\\n001\\n2000-01-01\\t06:00\\n120\\n" + entity.getUnavailableStartTime()
                + "\\n" + entity.getUnavailableEndTime() + "\\n1\\n2\\n" + convertSpaceWithTab(entity.getUnavailableStartDate()) + "\\n" + convertSpaceWithTab(entity.getUnavailableEndDate()) +
                "&" +
                "REPLY=" + ApsTools.instance().getReplyAddress() +
                "&" +
                "ID=001" +
                "&" +
                "DELAY=1000\n";
        return result;
    }

    //将字符转中包含的\s替换成\t
    private String convertSpaceWithTab(String source) {
        source = source.trim();

        Pattern pattern = Pattern.compile("(\\s)+|\t|\r|\n");
        Matcher match = pattern.matcher(source);

        return match.replaceAll("\\\\t");
    }
}
