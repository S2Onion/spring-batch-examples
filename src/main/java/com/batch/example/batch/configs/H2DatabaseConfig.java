package com.batch.example.batch.configs;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Log4j2
@Configuration
public class H2DatabaseConfig {

	private static final String TYPE_ALIAS_PAKAGE = "com.batch.example.batch";
	private static final String MAPPER_LOCATION_PATTERN = "classpath:/mapper/**/*.xml";
	private static final String H2_INIT_SQL_FILE_PATH = "'" + "classpath:init-sql/h2/psnlDataMask-work-schema.sql" + "'";

	@Bean
	public DataSource h2DataSource() {
		String driver = "org.h2.Driver";
		String username = "sa";
		String password = "";
		String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL";

		String h2Url = url + ";INIT=RUNSCRIPT FROM " + H2_INIT_SQL_FILE_PATH;

		return DataSourceBuilder.create().driverClassName(driver).url(h2Url).username(username).password(password).build();
	}

	@Bean
	public SqlSessionFactory h2SqlSessionFactory(@Qualifier("h2DataSource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource);
		sqlSessionFactory.setTypeAliasesPackage(TYPE_ALIAS_PAKAGE);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION_PATTERN));

		log.info("[H2DatabaseConfig] h2SqlSessionFactory complete.");

		return sqlSessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate h2SqlSessionTemplate(@Qualifier("h2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		log.info("[H2DatabaseConfig] h2SqlSession complete.");
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
