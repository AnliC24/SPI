package com.hitqz.sjtc.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = {"com.hitqz.sjtc.mapper.mysql",
        "com.hitqz.dict.starter.mapper","com.hitqz.device.mapper"}, sqlSessionTemplateRef  = "mysqlSqlSessionTemplate")
public class MysqlDataSourceConfig {

    public static String MYSQL_LOCATION_PATTERN ="classpath*:/mapper/mysql/*.xml";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MYSQL_LOCATION_PATTERN));

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        mybatisConfiguration.addInterceptor(interceptor);
        mybatisConfiguration.setJdbcTypeForNull(JdbcType.NULL);
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        mybatisConfiguration.setCallSettersOnNulls(true);
        mybatisConfiguration.setLogImpl(StdOutImpl.class);

        bean.setConfiguration(mybatisConfiguration);
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    @Primary
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
