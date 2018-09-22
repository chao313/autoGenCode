package demo.spring.boot.demospringboot.guest.service;

import com.jolbox.bonecp.BoneCPDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface DBService {
    /**
     * 获取连接池信息
     *
     * @param jdbcDriverClassName
     * @param jdbcUrl
     * @param userName
     * @param password
     * @return
     */
     void generateDataSource(String jdbcDriverClassName, String jdbcUrl, String userName, String password) ;


    /**
     * 获取Session
     *
     * @return
     * @throws IOException
     */
     void generateSqlSession() throws IOException;

    /**
     * 连接数据库
     * @param jdbcDriverClassName
     * @param jdbcUrl
     * @param userName
     * @param password
     * @throws IOException
     */
     void connect(String jdbcDriverClassName, String jdbcUrl, String userName, String password) throws IOException;

    /**
     * 判断是否连接
     * @return
     */
     boolean isconnect();
}
