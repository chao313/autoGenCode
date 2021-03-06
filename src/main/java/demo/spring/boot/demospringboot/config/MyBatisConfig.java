package demo.spring.boot.demospringboot.config;

import com.jolbox.bonecp.BoneCPDataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)//表示在DataSource之后才自动装配
public class MyBatisConfig {

    @Bean("sqlSessionFactoryBean")
    @ConditionalOnMissingBean //当容器里没有指定的Bean的情况下创建该对象
    public SqlSessionFactoryBean generateSqlSession(BoneCPDataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);//设置数据源

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setConfigLocation(
                resolver.getResource("classpath:/mybatis/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(
                resolver.getResources("classpath:/mybatis/mapper/*Mapper.xml"));

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer createMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("demo.spring.boot.demospringboot.mybatis.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}
