package com.batch.example;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class ExampleBatchLauncher {

  private final JobLauncher jobLauncher;

  @Autowired
  public ExampleBatchLauncher(JobLauncher jobLauncher) {
    this.jobLauncher = jobLauncher;
  }

  public void start(Job job, JobParameters jobParameters) {
    log.info(">>>>> ExampleBatchLauncher.start");
    try {
      jobLauncher.run(job, jobParameters);
    } catch (JobInstanceAlreadyCompleteException e) {
      log.error(e);
    } catch (JobRestartException e) {
      log.error(e);
    } catch (JobParametersInvalidException e) {
      log.error(e);
    } catch (JobExecutionAlreadyRunningException e) {
      log.error(e);
    }
  }
}
