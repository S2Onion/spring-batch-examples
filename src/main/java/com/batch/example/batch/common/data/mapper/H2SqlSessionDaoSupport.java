package com.batch.example.batch.common.data.mapper;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Log4j2
@Configuration
public class H2SqlSessionDaoSupport
		extends SqlSessionDaoSupport {

	@Override
	@Resource(name = "h2SqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
}
