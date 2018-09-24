package demo.spring.boot.demospringboot.guest.service;

import com.jolbox.bonecp.BoneCPDataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;

@Service
@SessionScope
public class DBInitService {

    private SqlSessionFactoryBean guestSqlSessionFactoryBean;

    public SqlSessionFactoryBean getGuestSqlSessionFactoryBean() {
        return guestSqlSessionFactoryBean;
    }

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


    public SqlSessionFactoryBean generateGuestSqlSessionFactory(String jdbcDriverClassName, String jdbcUrl, String userName, String password) throws IOException {
        SqlSessionFactoryBean guestSqlSessionFactoryBean = new SqlSessionFactoryBean();
        guestSqlSessionFactoryBean.setDataSource(this.generBoneCPDataSource(jdbcDriverClassName, jdbcUrl, userName, password));//设置数据源
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        guestSqlSessionFactoryBean.setConfigLocation(
                resolver.getResource("classpath:/guest-mybatis/config/mybatis-config.xml"));
        guestSqlSessionFactoryBean.setMapperLocations(
                resolver.getResources("classpath:/guest-mybatis/mapper/*Mapper.xml"));

        this.guestSqlSessionFactoryBean = guestSqlSessionFactoryBean;
        return guestSqlSessionFactoryBean;
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
        this.generateGuestSqlSessionFactory(jdbcDriverClassName, jdbcUrl, userName, password);
    }

    /**
     * 判断是否连接
     * @return
     */
    public boolean isconnect() {
        return this.guestSqlSessionFactoryBean == null ? false : true;
    }

}
