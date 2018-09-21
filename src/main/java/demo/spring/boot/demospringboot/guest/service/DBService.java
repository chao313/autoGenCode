package demo.spring.boot.demospringboot.guest.service;

import com.jolbox.bonecp.BoneCPDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DBService {

    private BoneCPDataSource dataSource;
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    /**
     * 获取连接池信息
     *
     * @param jdbcDriverClassName
     * @param jdbcUrl
     * @param userName
     * @param password
     * @return
     */
    private void generateDataSource(String jdbcDriverClassName, String jdbcUrl, String userName, String password) {

        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(jdbcDriverClassName);// 数据库驱动
        boneCPDataSource.setJdbcUrl(jdbcUrl);// 相应驱动的jdbcUrl
        boneCPDataSource.setUsername(userName); // 数据库的用户名
        boneCPDataSource.setPassword(password);// 数据库的密码
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60); // 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
        boneCPDataSource.setIdleMaxAgeInMinutes(30);// 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
        boneCPDataSource.setMaxConnectionsPerPartition(100);// 每个分区最大的连接数
        boneCPDataSource.setMinConnectionsPerPartition(5); // 每个分区最小的连接数
        this.dataSource = boneCPDataSource;
    }


    /**
     * 获取Session
     *
     * @return
     * @throws IOException
     */
    private void generateSqlSession() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);//设置数据源

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setConfigLocation(
                resolver.getResource("classpath:/guest-mybatis/config/mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(
//                resolver.getResources("classpath:/guest-mybatis/mappers/mysql/**/*.xml"));

        this.sqlSessionFactoryBean = sqlSessionFactoryBean;
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
        this.generateDataSource(jdbcDriverClassName,jdbcUrl,userName,password);
        this.generateSqlSession();
    }

    /**
     * 判断是否连接
     * @return
     */
    public boolean isconnect() {
        return this.sqlSessionFactoryBean == null ? true : false;
    }
}
