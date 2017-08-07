import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.actions.mes.MesConsumer;
import com.rengu.actions.mes.MesSender;
import com.rengu.entity.*;
import com.rengu.util.MessTable;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wey580231 on 2017/7/6.
 */
public class TestMQ {
    @Test
    public void name() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode root = mapper.createObjectNode();

        root.put("FC", MessTable.MES_PRODUCT);

        ObjectNode data = mapper.createObjectNode();
        data.put("id", "0001");
        data.put("name", "产品A");
        data.put("stock", 20);
        data.put("unit", "块");
        data.put("model", "GJB9001");

        String sha = Tools.getSha(data.toString());
        System.out.println(sha);

        root.put("REVICER", "");
        root.put("SENDER", "");
        root.put("UUID", sha);
        root.put("DATA", data);

        MesSender.instance().emulateData(root.toString());

    }

    @Test
    public void testMe() {
        String ss = "{\"id\":\"asdf\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        try {
            RG_ResourceEntity entity = objectMapper.readValue(ss, RG_ResourceEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoad() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String id = "kqd";
        RG_OrderEntity entity = session.get(RG_OrderEntity.class, id);

        if (entity != null) {
            System.out.println(entity.getId());
        }


        session.getTransaction().commit();
    }
}
