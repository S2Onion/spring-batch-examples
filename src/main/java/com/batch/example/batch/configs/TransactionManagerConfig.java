package com.batch.example.batch.configs;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Log4j2
@Configuration
public class TransactionManagerConfig {

    @Bean
    public PlatformTransactionManager resorcelessTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setRollbackOnCommitFailure(true);
        return new DataSourceTransactionManager();
    }
}
