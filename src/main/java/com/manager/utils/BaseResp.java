/**
 * 
 */
package com.manager.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @ClassName BaseResp
 * @author Fermi
 * @date 2013-1-9上午12:26:17
 * @Description 返回基类
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class BaseResp implements Serializable {
	private static final long serialVersionUID = 1L;

	public String toJsonStr() {
		return JSON.toJSONString(this);
	}

    @Override
	public String toString()  {
		return ToStringBuilder.reflectionToString(this, ObjectToStringStyle.getInstance());
	}
	
}
