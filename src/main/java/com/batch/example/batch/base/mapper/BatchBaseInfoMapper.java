package com.batch.example.batch.base.mapper;

import org.springframework.stereotype.Repository;

import com.batch.example.batch.base.data.entity.BatchBaseInfoVO;
import com.batch.example.batch.common.data.mapper.MySqlSessionDaoSupport;

/*
 * @classname : BatchBaseInfoMapper.java
 * @description : BatchBaseInfoMapper
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.16
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */

@Repository("batchBaseInfoMapper")
public class BatchBaseInfoMapper
		extends
		MySqlSessionDaoSupport {

	/**
	 * Select batch base info batch base info vo.
	 *
	 * @param batchBaseInfoVO the batch base info vo
	 * @return the batch base info vo
	 */
	public BatchBaseInfoVO selectBatchBaseInfo(BatchBaseInfoVO batchBaseInfoVO) {
		return getSqlSession().selectOne("selectBatchBaseInfo", batchBaseInfoVO);
	}

	/**
	 * Update inprogress int.
	 *
	 * @param batchBaseInfoVO the batch base info vo
	 * @return the int
	 */
	public int updateInprogress(BatchBaseInfoVO batchBaseInfoVO) {
		return getSqlSession().update("updateInprogress", batchBaseInfoVO);
	}

	/**
	 * Update batch result.
	 *
	 * @param batchBaseInfoVO the batch base info vo
	 */
	public void updateBatchResult(BatchBaseInfoVO batchBaseInfoVO) {
		getSqlSession().update("updateBatchResult", batchBaseInfoVO);
	}
}
