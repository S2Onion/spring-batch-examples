CREATE TABLE tbl_batch_base_info (
    job_name VARCHAR(32) NOT NULL
  , batch_start_dttm DATETIME NOT NULL
  , batch_end_dttm DATETIME
  , batch_sts_cd VARCHAR(20) NOT NULL
  , batch_rslt_cd VARCHAR(20)
  , batch_err_msg VARCHAR(500)
  , PRIMARY KEY (job_name)
);

INSERT INTO tbl_batch_base_info (
       job_name
	 , batch_start_dttm
     , batch_sts_cd
     , batch_rslt_cd
	 ) VALUES (
       'simpleJob'
	 , NOW()
     , 'TERMINATED'
     , 'SUCCESS'
     );