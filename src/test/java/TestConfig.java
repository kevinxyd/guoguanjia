
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.config.MybatisConfig;
import com.xyd.entity.AppVersion;
import com.xyd.entity.Qualification;
import com.xyd.entity.User;
import com.xyd.mapper.AppVersionMapper;
import com.xyd.mapper.QualificationMapper;
import com.xyd.service.AppVersionService;
import com.xyd.service.QualificationService;
import com.xyd.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class TestConfig {

    @Autowired
    QualificationService qualificationService;

    @Test
    public void test1(){
        HashMap<String, Object> map = new HashMap<>();
        //map.put("pageNum",1);
        map.put("check",0);
        map.put("type",0);
        map.put("start","2019-02-01");
        map.put("end","2019-10-01");
        PageInfo<Qualification> qualifications = qualificationService.selectByCondition(map);
        System.out.println(qualifications);
    }

}
