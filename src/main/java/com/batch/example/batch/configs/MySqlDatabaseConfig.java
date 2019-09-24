package com.batch.example.batch.configs;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Log4j2
@Configuration
public class MySqlDatabaseConfig {

    private static final String TYPE_ALIAS_PAKAGE = "com.batch.example.batch";
    private static final String MAPPER_LOCATION_PATTERN = "classpath:/mapper/**/*.xml";

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    @Primary
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().driverClassName(driver).url(url).username(username).password(password).build();
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(datasource);
        sqlSessionFactory.setTypeAliasesPackage(TYPE_ALIAS_PAKAGE);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION_PATTERN));

        log.info("[MySqlDatabaseConfig] sqlSessionFactory complete.");

        return sqlSessionFactory.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        log.info("[MySqlDatabaseConfig] sqlSession complete.");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
