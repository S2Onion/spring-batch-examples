package com.batch.example.batch;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.batch.example.batch.base.BatchContext;

/*
 * @classname : ExampleApplication.java
 * @description : ExampleApplication
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.17
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */

@EnableBatchProcessing // 배치기능 활성화
@SpringBootApplication // @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan 세가지를 한번에 묶어놓은것
public class ExampleApplication {

	public static void main(String[] args) {

		if ( args.length < 1 ) {
			throw new IllegalArgumentException("Parameter Job is necessary"); // 실행하고자 하는 Job Parameter 전달여부 확인
		}

		try (ConfigurableApplicationContext ctx = SpringApplication.run(ExampleApplication.class, args)) { // 실행하고자하는 Job 이름을 설정하지 않고 실행하여 Bean 등록
			ExampleBatchLauncher exampleBatchLauncher = ctx.getBean("exampleBatchLauncher", ExampleBatchLauncher.class);
			exampleBatchLauncher.start(ctx, setBatchMgmtData(args));
		}
	}

	private static BatchContext setBatchMgmtData(String[] args) {
		System.setProperty("job.name", args[0]); // Job 명 System Property에 등록
		return new BatchContext(args[0], LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime());
	}
}