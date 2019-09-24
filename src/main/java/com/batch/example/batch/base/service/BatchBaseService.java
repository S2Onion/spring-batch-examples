package com.batch.example.batch.base.service;

import com.batch.example.batch.base.data.entity.BatchBaseInfoVO;

public interface BatchBaseService {

	BatchBaseInfoVO selectBatchBaseInfo(BatchBaseInfoVO batchBaseInfoVO);

	int updateInprogress(BatchBaseInfoVO batchBaseInfoVO);

	void updateBatchResult(BatchBaseInfoVO batchBaseInfoVO);

}
