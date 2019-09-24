package com.batch.example.batch;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.batch.example.batch.base.BatchBaseConstants;
import com.batch.example.batch.base.BatchContext;
import com.batch.example.batch.base.MyBatchException;
import com.batch.example.batch.base.data.entity.BatchBaseInfoVO;
import com.batch.example.batch.base.service.BatchBaseService;

import lombok.extern.log4j.Log4j2;

/*
 * @classname : ExampleBatchLauncher.java
 * @description : ExampleBatchLauncher
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.16
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */

@Log4j2 // log 사용을 위한 lombok 어노테이션
@Configuration
public class ExampleBatchLauncher {

	private final JobLauncher jobLauncher;
	private final BatchBaseService batchBaseService;

	@Autowired
	public ExampleBatchLauncher(JobLauncher jobLauncher, @Qualifier("batchBaseService") BatchBaseService batchBaseService) {
		this.jobLauncher = jobLauncher;
		this.batchBaseService = batchBaseService;
	}

	public void start(ConfigurableApplicationContext ctx, BatchContext batchContext) {
		log.info(">>>>> ExampleBatchLauncher.start");

		final String jobName = batchContext.getJobName();

		BatchBaseInfoVO baseInfoVO = new BatchBaseInfoVO();
		baseInfoVO.setJobName(jobName);
		String batchResult = BatchBaseConstants.FAIL;
		String batchErrMsg = "";
		try {
			BatchBaseInfoVO batchStatusVO = batchBaseService.selectBatchBaseInfo(baseInfoVO);

			if ( !StringUtils.equals(batchStatusVO.getBatchStsCd(), BatchBaseConstants.TERMINATED) ) {
				throw new MyBatchException(batchContext, "Batch Status is not " + BatchBaseConstants.TERMINATED + ".");
			}

			baseInfoVO.setBatchStsCd(BatchBaseConstants.INPROGRESS);
			int updateResult = batchBaseService.updateInprogress(baseInfoVO);

			if(updateResult < 1){
				throw new MyBatchException(batchContext, "updateInprogress fail.");
			}

			JobParametersBuilder jobParametersBuilder = new JobParametersBuilder().addString("jobName", jobName);
			jobLauncher.run(ctx.getBean(jobName, Job.class), jobParametersBuilder.toJobParameters());
			batchResult = BatchBaseConstants.SUCCESS;
		} catch (Exception e) {
			log.error("[ExampleBatchLauncher.start] Batch Error, ", e);
			batchErrMsg = e.getMessage();
		} finally {
			BatchBaseInfoVO resultSetVO = new BatchBaseInfoVO();
			resultSetVO.setJobName(jobName);
			resultSetVO.setBatchStsCd(BatchBaseConstants.TERMINATED);
			resultSetVO.setBatchRsltCd(batchResult);
			resultSetVO.setBatchErrMsg(batchErrMsg);
			batchBaseService.updateBatchResult(resultSetVO);
		}
	}
}
