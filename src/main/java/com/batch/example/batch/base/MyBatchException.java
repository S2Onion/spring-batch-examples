package com.batch.example.batch.base;

import org.apache.commons.lang3.StringUtils;

/*
 * @classname : MyBatchException.java
 * @description : MyBatchException
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.17
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */
public class MyBatchException
		extends RuntimeException {

	private BatchContext batchContext;

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new My batch exception.
	 */
	public MyBatchException() {
		super();
	}

	/**
	 * Instantiates a new My batch exception.
	 *
	 * @param message the message
	 */
	public MyBatchException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new My batch exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public MyBatchException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new My batch exception.
	 *
	 * @param cause the cause
	 */
	public MyBatchException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new My batch exception.
	 *
	 * @param batchContext the batch context
	 */
	public MyBatchException(BatchContext batchContext) {
		super(createBatchContextMessage(batchContext));
		this.batchContext = batchContext;
	}

	/**
	 * Instantiates a new My batch exception.
	 *
	 * @param batchContext the batch context
	 * @param message the message
	 */
	public MyBatchException(BatchContext batchContext, String message) {
		super(createBatchContextMessage(batchContext, message));
		this.batchContext = batchContext;
	}

	/**
	 * Instantiates a new My batch exception.
	 *
	 * @param batchContext the batch context
	 * @param message the message
	 * @param cause the cause
	 */
	public MyBatchException(BatchContext batchContext, String message, Throwable cause) {
		super(createBatchContextMessage(batchContext, message), cause);
		this.batchContext = batchContext;
	}

	/**
	 * Instantiates a new My batch exception.
	 *
	 * @param batchContext the batch context
	 * @param cause the cause
	 */
	public MyBatchException(BatchContext batchContext, Throwable cause) {
		super(createBatchContextMessage(batchContext), cause);
		this.batchContext = batchContext;
	}

	private static String createBatchContextMessage(BatchContext batchContext, String... messages) {
		String bctxBase = batchContext != null ? batchContext.toString() : null;
		StringBuilder sb = new StringBuilder();
		if ( StringUtils.isNotBlank(bctxBase) ) {
			sb.append("[Batch Context Info : ");
			sb.append(bctxBase);
			sb.append("]");
		}
		if ( messages != null && messages.length > 0 ) {
			sb.append("[Batch Exception Message : ");
			sb.append(StringUtils.join(messages, ", "));
			sb.append("]");
		}
		return sb.toString();
	}

	/**
	 * Gets batch context.
	 *
	 * @return the batch context
	 */
	public BatchContext getBatchContext() {
		return batchContext;
	}

}
