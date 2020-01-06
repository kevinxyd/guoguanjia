
import com.xyd.config.MybatisConfig;
import com.xyd.entity.SysUser;
import com.xyd.mapper.SysAreaMapper;
import com.xyd.mapper.SysUserMapper;
import com.xyd.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class TestConfig {

    @Autowired
    SysUserMapper statuteService;

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> sysUsers = statuteService.selectByCondition(null);
        for (SysUser s:sysUsers) {
            System.out.println(s.getId() + "" + s.getName() + " " + s.getRoleName());
        }
    }

}
