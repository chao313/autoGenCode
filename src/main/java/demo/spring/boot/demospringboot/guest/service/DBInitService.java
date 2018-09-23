package demo.spring.boot.demospringboot.guest.service;

import com.jolbox.bonecp.BoneCPDataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;

import demo.spring.boot.demospringboot.guest.dao.DBDAO;
import demo.spring.boot.demospringboot.guest.service.DBInitService;
import demo.spring.boot.demospringboot.guest.service.DBService;
import demo.spring.boot.demospringboot.util.SpringContextUtil;

@Service
public class DBInitService {

    private BoneCPDataSource dataSource;
    private SqlSessionFactoryBean sqlSessionFactoryBean;

//    public void xx(){ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) SpringContextUtil.getApplicationContext();
//
//        // 获取bean工厂并转换为DefaultListableBeanFactory
//        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
//
//        // 通过BeanDefinitionBuilder创建bean定义
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserController.class);
//
//        // 设置属性userService,此属性引用已经定义的bean:userService,这里userService已经被spring容器管理了.
////        beanDefinitionBuilder.addPropertyReference("userService", "userService");
//
//        // 注册bean
//        defaultListableBeanFactory.registerBeanDefinition("userController", beanDefinitionBuilder.getRawBeanDefinition());
//
//    }


    private BoneCPDataSource generBoneCPDataSource(String jdbcDriverClassName, String jdbcUrl, String userName, String password) {
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(jdbcDriverClassName);// 数据库驱动
        boneCPDataSource.setJdbcUrl(jdbcUrl);// 相应驱动的jdbcUrl
        boneCPDataSource.setUsername(userName);// 数据库的用户名
        boneCPDataSource.setPassword(password);// 数据库的密码
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);// 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
        boneCPDataSource.setIdleMaxAgeInMinutes(30);// 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
        boneCPDataSource.setMaxConnectionsPerPartition(100);// 每个分区最大的连接数
        boneCPDataSource.setMinConnectionsPerPartition(5);// 每个分区最小的连接数
        return boneCPDataSource;
    }


    public void registerSqlSessionFactory(String jdbcDriverClassName, String jdbcUrl, String userName, String password) throws IOException {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) SpringContextUtil.getApplicationContext();

        // 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();

        // 通过BeanDefinitionBuilder创建SqlSessionFactoryBean定义
        BeanDefinitionBuilder sqlSessionFactoryBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryBean.class);
        sqlSessionFactoryBeanDefinitionBuilder.addPropertyValue("dataSource", this.generBoneCPDataSource(jdbcDriverClassName, jdbcUrl, userName, password));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBeanDefinitionBuilder.addPropertyValue("configLocation", resolver.getResource("classpath:/guest-mybatis/config/mybatis-config.xml"));
        sqlSessionFactoryBeanDefinitionBuilder.addPropertyValue("mapperLocations", resolver.getResources("classpath:/guest-mybatis/mapper/*Mapper.xml"));
        defaultListableBeanFactory.registerBeanDefinition("guestSqlSessionFactoryBean", sqlSessionFactoryBeanDefinitionBuilder.getRawBeanDefinition());// 注册bean


        // 通过BeanDefinitionBuilder创建MapperScannerConfigurer定义
        BeanDefinitionBuilder mapperScannerConfigurerBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
        sqlSessionFactoryBeanDefinitionBuilder.addPropertyValue("basePackage", "demo.spring.boot.demospringboot.guest.dao");
        sqlSessionFactoryBeanDefinitionBuilder.addPropertyValue("sqlSessionFactoryBeanName", "guestSqlSessionFactoryBean");
        defaultListableBeanFactory.registerBeanDefinition("guestMapperScannerConfigurer", mapperScannerConfigurerBeanDefinitionBuilder.getRawBeanDefinition());// 注册bean



 // 通过BeanDefinitionBuilder创建 DBService 定义
        BeanDefinitionBuilder DBServiceBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DBService.class);
        defaultListableBeanFactory.registerBeanDefinition("", DBServiceBeanDefinitionBuilder.getRawBeanDefinition());// 注册bean






    }

    /**
     * 连接数据库
     * @param jdbcDriverClassName
     * @param jdbcUrl
     * @param userName
     * @param password
     * @throws IOException
     */
    public void connect(String jdbcDriverClassName, String jdbcUrl, String userName, String password) throws IOException {
        this.registerSqlSessionFactory(jdbcDriverClassName, jdbcUrl, userName, password);
    }

    /**
     * 判断是否连接
     * @return
     */
    public boolean isconnect() {
        return this.sqlSessionFactoryBean == null ? false : true;
    }

//    @Bean
    public MapperScannerConfigurer createMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("demo.spring.boot.demospringboot.guest.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("guestSqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}
