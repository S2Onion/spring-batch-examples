package com.batch.example.batch.common.data.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/*
 * @classname : PagingItemReaderVO.java
 * @description : PagingItemReaderVO
 * @modification : modification
 * @author : S2Onion
 * @since :2019.9.17
 * @version : 1.0
 *
 * <pre>
 *
 * </pre>
 */

@Setter
@Getter
public class PagingItemReaderVO {

	private int page;

	private int pageSize;

	private int skipRows;

	private List<String> includeList;

	private List<String> excludeList;
}
