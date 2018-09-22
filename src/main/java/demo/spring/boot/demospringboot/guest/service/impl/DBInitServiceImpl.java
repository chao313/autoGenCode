package demo.spring.boot.demospringboot.guest.service.impl;

import com.jolbox.bonecp.BoneCPDataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;

import demo.spring.boot.demospringboot.guest.service.DBInitService;

@Service
@Configuration
@MapperScan(value = "demo.spring.boot.demospringboot.guest.dao")
public class DBInitServiceImpl implements DBInitService {

    private BoneCPDataSource dataSource;
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    public SqlSessionFactoryBean getSqlSessionFactoryBean() {
        return sqlSessionFactoryBean;
    }

    /**
     * 获取连接池信息
     *
     * @param jdbcDriverClassName
     * @param jdbcUrl
     * @param userName
     * @param password
     * @return
     */
    public void generateDataSource(String jdbcDriverClassName, String jdbcUrl, String userName, String password) {

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
//    @Bean(name = "guestSqlSessionFactoryBean")
    public SqlSessionFactoryBean generateSqlSession() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);//设置数据源

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setConfigLocation(
                resolver.getResource("classpath:/guest-mybatis/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(
                resolver.getResources("classpath:/guest-mybatis/mappers/mysql/**/*.xml"));

        this.sqlSessionFactoryBean = sqlSessionFactoryBean;
        return this.sqlSessionFactoryBean;
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
        return this.sqlSessionFactoryBean == null ? false : true;
    }

//    @Bean
    public MapperScannerConfigurer createMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("demo.spring.boot.demospringboot.guest.dao");
        return mapperScannerConfigurer;
    }
}
