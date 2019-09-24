package com.batch.example.batch.base.data.entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * @classname : BatchBaseInfoVO.java
 * @description : BatchBaseInfoVO
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.16
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */

@Setter
@Getter
@ToString
@Alias("BatchBaseInfoVO")
public class BatchBaseInfoVO {

	private String jobName;

	private String batchStartDttm;

	private String batchEndDttm;

	private String batchStsCd;

	private String batchRsltCd;

	private String batchErrMsg;
}
