/**
 * 
 */
package com.manager.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @ClassName JsonMapper
 * @author Fermi
 * @date 2013-1-9上午11:36:36
 * @Description Jackson封装工具类
 */
public class JsonMapper {

	private static final Log logger = LogFactory.getLog(JsonMapper.class.getName());

	private ObjectMapper mapper;

	public JsonMapper(JsonInclude.Include inclusion) {
		mapper = new ObjectMapper();
		//允许json格式是单引号
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		//设置输出包含的属性
        mapper.setSerializationInclusion(inclusion);
		//设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
		mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	}

	/**
	 * 创建输出全部属性到Json字符串的Binder.
	 */
	public static JsonMapper buildNormalBinder() {
		return new JsonMapper(JsonInclude.Include.ALWAYS);
	}

	/**
	 * 创建只输出非空属性到Json字符串的Binder.
	 */                     
	public static JsonMapper buildNonNullBinder() {
		return new JsonMapper(JsonInclude.Include.NON_NULL);
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Binder.
	 */
	public static JsonMapper buildNonDefaultBinder() {
		return new JsonMapper(JsonInclude.Include.NON_DEFAULT);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串,返回Null.
	 * 如果JSON字符串为"[]",返回空集合.
	 * 
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句:
	 * List<MyBean> beanList = binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {});
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}

    public <T> T fromJson(String jsonString, TypeReference typeReference) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, typeReference);
        } catch (Exception e) {
            logger.error("parse json string error:" + jsonString, e);
            return null;
        }
    }
	
	/**
	 * 如果JSON字符串为Null或"null"字符串, 返回Null.
	 * 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需读取集合如List/Map, 且不是List<String>時,
	 * 先用constructParametricType(List.class,MyBean.class)構造出JavaTeype,再調用本函數.
	 */
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	/**
	 * 构造泛型的Type如List<MyBean>, Map<String,MyBean>
	 */
	public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
		return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}

	/**
	 * 如果对象为Null,返回"null".
	 * 如果集合为空集合,返回"[]".
	 */
	public String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("write to json string error:" + object, e);
			return null;
		}
	}
	
	/**
	 * 如果对象为Null, 返回"null".
	 * 如果集合为空集合, 返回"[]".
	 */
	public String toJson(Object object, String[] includeFields) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}
	
	/**
	 * 当JSON里只含有Bean的部分属性时，更新一个已存在Bean，只覆盖该部分的属性.
	 */
	public <T> T update(T object, String jsonString) {
		try {
            return mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("parse json string" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("parse json string" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}
	
	/**
	 * 输出JSONP格式数据.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}
	
	/**
	 * 设定是否使用Enum的toString函数来读写Enum,
	 * 为False时使用Enum的name()函数来读写Enum, 默认为False.
	 * 注意本函数一定要在Mapper创建后, 所有的读写动作之前调用.
	 */
	public void setEnumUseToString(boolean value) {
        mapper.getSerializationConfig().with(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.getDeserializationConfig().with(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}
	
	/**
	 * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
	 */
	public void setDateFormat(String pattern) {
		if (StringUtils.isNotBlank(pattern)) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			mapper.getSerializationConfig().with(df);
			mapper.getDeserializationConfig().with(df);
			mapper.setDateFormat(df);
		}
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}