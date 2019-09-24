package com.batch.example.batch.base;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

/*
 * @classname : BatchContext.java
 * @description : BatchContext
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.17
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */

@Getter
@ToString
public class BatchContext {

	private final String jobName;
	private final LocalDateTime batchStartDttm;

	public BatchContext(String jobName, LocalDateTime batchStartDttm) {
		super();
		this.jobName = jobName;
		this.batchStartDttm = batchStartDttm;
	}
}
