package com.batch.example.batch.configs;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Log4j2
@Configuration
public class JobRepositoryConfig extends DefaultBatchConfigurer {

	private PlatformTransactionManager platformTransactionManager;

	public JobRepositoryConfig(@Qualifier("resorcelessTransactionManager") PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}

	@Override
	protected JobRepository createJobRepository() throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
		factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
		factory.setTransactionManager(platformTransactionManager);
		return factory.getObject();
	}
}
