package com.rengu.actions.aps;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.aps.ApsDao;
import com.rengu.actions.SuperAction;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.entity.RG_State3DEntity;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

/**
 * APS回调框架更新计算状态
 * Created by wey580231 on 2017/6/13.
 */
public class FeedBackStateAction extends SuperAction {

    private ApsDao apsDao = new ApsDao();

    //根据返回的id号更新对应schedule的状态
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
                Session session = MySessionFactory.getSessionFactory().getCurrentSession();
                session.beginTransaction();

                Query query = session.createQuery("from RG_ScheduleEntity entity where entity.apsFlag =:flag");
                query.setParameter("flag", id[0]);
                List<RG_ScheduleEntity> list = query.list();
                //TODO 通过消息告诉前端
                if (list.size() == 1 && list.get(0) instanceof RG_ScheduleEntity) {
                    RG_ScheduleEntity entity = (RG_ScheduleEntity) list.get(0);

                    if (state[0].equals("1")) {
                        entity.setState(RG_ScheduleEntity.APS_SUCCESS);
                    }
                    //计算失败
                    else if (state[0].equals("0")) {
                        entity.setState(RG_ScheduleEntity.APS_FAIL);
                    }
                    session.update(entity);
                }

                session.getTransaction().commit();
                session.close();
            }
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }

    //查询APS状态,在对APS数据库操作之前，都要先执行状态查询操作

    public void queryApsState() {

        StringBuilder result = new StringBuilder();

        if (apsDao.queryCurrState(result)) {
            Tools.jsonPrint(result.toString(), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }
}
