
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.config.MybatisConfig;
import com.xyd.entity.AppVersion;
import com.xyd.entity.User;
import com.xyd.mapper.AppVersionMapper;
import com.xyd.service.AppVersionService;
import com.xyd.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class TestConfig {

    @Autowired
    DruidDataSource druidDataSource;
    @Autowired
    AppVersionMapper mapper;
    @Autowired
    SqlSessionFactoryBean sqlSessionFactoryBean;
    @Autowired
    AppVersionService appVersionService;

    @Autowired
    UserService userService;
    @Test
    public void testDruidDataSource(){
        try {
            System.out.println(sqlSessionFactoryBean.getObject().openSession().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testMapper(){

        AppVersion appVersion = mapper.selectByPrimaryKey((long) 1);
        System.out.println(appVersion);
    }
    @Test
    public void testMapperInsert(){
        AppVersion appVersion = new AppVersion();
        appVersion.setDelFlag("0");
        appVersion.setUpdateDate(new Date());
        appVersion.setCreateDate(new Date());
        appVersion.setCreateBy("admin");
        appVersion.setDownPath("http://127.0.0.1:8080/guguanjia/manager/#/ajax/manager/app/index");
        appVersion.setVersionNo("1.5.8");
        appVersion.setPlatform(0);
        appVersion.setForceUpdate(0);
        int i = mapper.insertSelective(appVersion);//动态更新
//        int insert = mapper.insert(appVersion);
        System.out.println(appVersion);
    }



    @Test
    public void testServiceInsert(){
        AppVersion appVersion = new AppVersion();
        appVersion.setDelFlag("0");
        appVersion.setUpdateDate(new Date());
        appVersion.setCreateDate(new Date());
        appVersion.setCreateBy("admin");
        appVersion.setDownPath("http://127.0.0.1:8080/guguanjia/manager/#/ajax/manager/app/index");
        appVersion.setVersionNo("1.5.8");
        appVersion.setPlatform(0);
        appVersion.setForceUpdate(0);
        int i = appVersionService.insertSelective(appVersion);//动态更新
//        int insert = mapper.insert(appVersion);
        System.out.println(appVersion);
    }


    @Test
    public void testPageInfo(){
        PageHelper.startPage(1,3);
        List<AppVersion> list = mapper.selectAll();//当前方法返回值已经被替换成Page对象类型
        PageInfo<AppVersion> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);

    }
    @Test
    public void testUser(){
        PageHelper.startPage(1,10);
        List<User> list = userService.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        System.out.println(userPageInfo);
    }
}
