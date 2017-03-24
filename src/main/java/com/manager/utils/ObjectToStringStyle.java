/**
 * 
 */
package com.manager.utils;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author Fermi(fermi@youleyu.com)
 * @date Dec 8, 2013
 * @desc	Object和String的映射
 */
public class ObjectToStringStyle extends ToStringStyle {
	private static final long serialVersionUID = 1L;
	
	private static ObjectToStringStyle instance = new ObjectToStringStyle();

	private ObjectToStringStyle() {
		setContentEnd("");
		setContentStart("");
		setUseIdentityHashCode(false);
		setUseClassName(false);
		setFieldSeparator("&");

	}

	/**
	 * @return the instance
	 */
	public static ObjectToStringStyle getInstance() {
		return instance;
	}
}
