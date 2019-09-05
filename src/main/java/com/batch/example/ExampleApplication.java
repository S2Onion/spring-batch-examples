package com.batch.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/** @author : S2Onion */
@Log4j2
@SpringBootApplication
@EnableBatchProcessing
public class ExampleApplication {

  public static void main(String[] args) {

    if (args.length < 1) {
      throw new IllegalArgumentException("Parameter Job is necessary");
    }

    try (ConfigurableApplicationContext ctx =
        SpringApplication.run(ExampleApplication.class, args)) {
      ExampleBatchLauncher exampleBatchLauncher =
          ctx.getBean("exampleBatchLauncher", ExampleBatchLauncher.class);

      String jobName = args[0];
      System.setProperty("job.name", jobName);
      Job job = ctx.getBean(jobName, Job.class);
      JobParametersBuilder jobParametersBuilder =
          new JobParametersBuilder()
              .addString("jobName", jobName)
              .addString("runningTime", LocalDateTime.now(ZoneId.of("GMT")).toString());

      exampleBatchLauncher.start(job, jobParametersBuilder.toJobParameters());
    }
  }
}
