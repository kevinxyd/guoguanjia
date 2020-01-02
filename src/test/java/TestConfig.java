
import com.xyd.config.MybatisConfig;
import com.xyd.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class TestConfig {

    @Autowired
    AppVersionService statuteService;

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum",1);
        map.put("pageSize",5);
        map.put("type",1);
        System.out.println(statuteService.selectPage(1,2));

    }

}
