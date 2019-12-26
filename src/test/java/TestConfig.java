
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.config.MybatisConfig;
import com.xyd.entity.AppVersion;
import com.xyd.entity.Examine;
import com.xyd.entity.Qualification;
import com.xyd.entity.User;
import com.xyd.mapper.AppVersionMapper;
import com.xyd.mapper.ExamineMapper;
import com.xyd.mapper.QualificationMapper;
import com.xyd.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class TestConfig {

    @Autowired
    SysOfficeService sysOfficeService;

    @Test
    public void test1(){
        System.out.println(sysOfficeService.selectAll());
    }

}
