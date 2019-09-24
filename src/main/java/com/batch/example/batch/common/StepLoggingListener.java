package com.batch.example.batch.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Log4j2
@Configuration
public class StepLoggingListener
		implements StepExecutionListener, InitializingBean {

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
	public void beforeStep(StepExecution stepExecution) {
		if ( log.isInfoEnabled() ) {
			String stepName = stepExecution.getStepName();
			Date startDate = stepExecution.getStartTime();
			log.info("####################### STEP START ########################");
			log.info("# Step name : " + stepName);
			log.info("# Timestamp");
			log.info("# - Start : " + sdf.format(startDate));
			log.info("###########################################################");
		}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getWriteCount();
		if ( log.isInfoEnabled() ) {
			String stepName = stepExecution.getStepName();
			Date startDate = stepExecution.getStartTime();
			Date endDate = new Date(System.currentTimeMillis());
			log.info("######################## STEP END #########################");
			log.info("# Step name : " + stepName);
			log.info("# Timestamp");
			log.info("# - Start       : " + sdf.format(startDate));
			log.info("# - End         : " + sdf.format(endDate));
			log.info("# - Read count  : " + stepExecution.getReadCount());
			log.info("# - Write count : " + stepExecution.getWriteCount());
			logFailureExcept(stepExecution.getFailureExceptions());
			log.info("###########################################################");
		}
		return stepExecution.getExitStatus();
	}

	static void logFailureExcept(List<Throwable> failureExecpts) {
		if ( failureExecpts != null && !failureExecpts.isEmpty() ) {
			log.info("# Exception");
			for (Throwable throwable : failureExecpts) {
				log.info("# - " + throwable.getMessage(), throwable);
			}
		}

	}
}
