/** 
 * FileName GroupDetailService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.CodeDetail;

/**
 * FileName GroupDetailService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 共有Code相关业务处理接口
 */
public interface GroupDetailService {

	/**
	 * 根据共有Code组ID取得详细一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param codeGroupId
	 *            共有Code组ID
	 * @return List<CodeDetail> 详细一览
	 * @throws 无
	 */
	public List<CodeDetail> getCodeDetailList(String codeGroupId);

	/**
	 * 根据共有Code组ID和详细ID取得名称
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param codeGroupId
	 *            共有Code组ID
	 * @param codeId
	 *            共有Code详细ID
	 * @return String code名称
	 * @throws 无
	 */
	public String getCodeDetailName(String codeGroupId, String codeId);
}
