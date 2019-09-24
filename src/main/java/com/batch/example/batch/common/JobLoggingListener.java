package com.batch.example.batch.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Log4j2
@Configuration
public class JobLoggingListener
		implements JobExecutionListener, InitializingBean {

	private SimpleDateFormat sdf;

	private String dateFormat = "z yyyy-MM-dd HH:mm:ss.SSS";

	private String timeZone = "GMT";

	@Override
	public void afterPropertiesSet() throws Exception {
		sdf = new SimpleDateFormat(dateFormat, Locale.UK);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
	}

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		if ( log.isInfoEnabled() ) {
			String jobName = jobExecution.getJobInstance().getJobName();
			Date startDate = jobExecution.getStartTime();
			log.info("######################## JOB START ########################");
			log.info("# Job name : " + jobName);
			log.info("# Timestamp");
			log.info("# - Start : " + sdf.format(startDate));
			log.info("###########################################################");
			log.info("# JOB parameters");
			JobParameters jobParameters = jobExecution.getJobParameters();
			Map<String, JobParameter> jobParamsMap = jobParameters.getParameters();
			for (Entry<String, JobParameter> entry : jobParamsMap.entrySet()) {
				String paramName = entry.getKey();
				JobParameter jobParam = entry.getValue();

				StringBuilder logMsg = new StringBuilder();
				logMsg.append("# - ");
				logMsg.append(paramName);
				logMsg.append(" : ");
				if ( JobParameter.ParameterType.DATE.equals(jobParam.getType().getDeclaringClass()) ) {
					logMsg.append(jobParam.getValue());
				} else if ( JobParameter.ParameterType.DOUBLE.equals(jobParam.getType().getDeclaringClass()) ) {
					logMsg.append(jobParam.getValue());
				} else if ( JobParameter.ParameterType.LONG.equals(jobParam.getType().getDeclaringClass()) ) {
					logMsg.append(jobParam.getValue());
				} else {
					logMsg.append(jobParam.getValue());
				}

				log.info(logMsg.toString());
			}
			log.info("###########################################################");
		}
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if ( log.isInfoEnabled() ) {
			String jobName = jobExecution.getJobInstance().getJobName();
			Date startDate = jobExecution.getStartTime();
			Date endDate = jobExecution.getEndTime();
			log.info("######################### JOB END #########################");
			log.info("# Job name : " + jobName);
			log.info("# Timestamp");
			log.info("# - Start : " + sdf.format(startDate));
			log.info("# - End   : " + sdf.format(endDate));
			StepLoggingListener.logFailureExcept(jobExecution.getFailureExceptions());
			log.info("###########################################################");
		}
	}

}
