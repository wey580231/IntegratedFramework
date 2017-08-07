import com.rengu.entity.RG_UserConfigEntity;
import com.rengu.util.UserConfigTools;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wey580231 on 2017/7/18.
 */
public class TestUserConfig {

    @Test
    public void testUserConfig(){
        RG_UserConfigEntity userconfig = UserConfigTools.getUserConfig("1");
    }

    @Test
    public void testDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        System.out.println(dateString);
    }

}
