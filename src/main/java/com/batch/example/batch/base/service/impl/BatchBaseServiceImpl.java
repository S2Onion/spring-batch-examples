package com.batch.example.batch.base.service.impl;

import org.springframework.stereotype.Service;

import com.batch.example.batch.base.data.entity.BatchBaseInfoVO;
import com.batch.example.batch.base.mapper.BatchBaseInfoMapper;
import com.batch.example.batch.base.service.BatchBaseService;

/*
 * @classname : BatchBaseServiceImpl.java
 * @description : BatchBaseServiceImpl
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.17
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */

@Service("batchBaseService")
public class BatchBaseServiceImpl
		implements
		BatchBaseService {

	private final BatchBaseInfoMapper batchBaseInfoMapper;

	public BatchBaseServiceImpl(BatchBaseInfoMapper batchBaseInfoMapper) {
		this.batchBaseInfoMapper = batchBaseInfoMapper;
	}

	@Override
	public BatchBaseInfoVO selectBatchBaseInfo(BatchBaseInfoVO batchBaseInfoVO) {
		return batchBaseInfoMapper.selectBatchBaseInfo(batchBaseInfoVO);
	}

	@Override
	public int updateInprogress(BatchBaseInfoVO batchBaseInfoVO) {
		return batchBaseInfoMapper.updateInprogress(batchBaseInfoVO);
	}

	@Override
	public void updateBatchResult(BatchBaseInfoVO batchBaseInfoVO) {
		batchBaseInfoMapper.updateBatchResult(batchBaseInfoVO);
	}

}
