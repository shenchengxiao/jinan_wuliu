package com.manager.utils;

import com.manager.exception.ValidationException;
import com.manager.service.IBusinessStatusEnum;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class Validator {

    /**
     * 验证参数是不是空的.
     * 实现中会根据object的实际类型做校验.
     * 目前支持String、Collection、Map
     * 默认错误消息BusinessStatusEnum.PARAM_EMPTY
     *
     * @param object 验证对象
     * @throws ValidationException 如果是空的会抛出异常.
     */
    public static final void isEmpty(Object object) throws ValidationException {
        isEmpty(object, BusinessStatusEnum.PARAM_EMPTY);
    }

    /**
     * 验证参数是不是空的.
     * 实现中会根据object的实际类型做校验.
     * 目前支持String、Collection、Map
     *
     * @param object 验证对象
     * @param errorMessage 自定义的错误消息
     * @throws ValidationException
     */
    public static final void isEmpty(Object object, String errorMessage) throws ValidationException {
        isEmpty(object, BusinessStatusEnum.PARAM_EMPTY, errorMessage);
    }

    /**
     * 验证参数是不是空的.
     * 实现中会根据object的实际类型做校验.
     * 目前支持String、Collection、Map
     *
     * @param object 验证对象
     * @param businessStatusEnum 错误枚举
     * @throws ValidationException
     */
    public static final void isEmpty(Object object, IBusinessStatusEnum businessStatusEnum) throws ValidationException {
        isEmpty(object, businessStatusEnum, businessStatusEnum.getDesc());
    }

    /**
     * 验证参数是不是空的.
     * 实现中会根据object的实际类型做校验.
     * 目前支持String、Collection、Map
     *
     * @param object 验证对象
     * @param businessStatusEnum 错误枚举
     * @param errorMessage 自定义错误消息
     * @throws ValidationException
     */
    public static final void isEmpty(Object object, IBusinessStatusEnum businessStatusEnum, String errorMessage) throws ValidationException {
        if(null == object) {
            throw new ValidationException(businessStatusEnum, errorMessage);
        }
        if(object instanceof String && !StringUtils.hasText(object.toString())) {
            throw new ValidationException(businessStatusEnum, errorMessage);
        }
        if(object instanceof Collection && CollectionUtils.isEmpty((Collection)object)) {
            throw new ValidationException(businessStatusEnum, errorMessage);
        }
        if(object instanceof Map && CollectionUtils.isEmpty((Map)object)) {
            throw new ValidationException(businessStatusEnum, errorMessage);
        }
    }

    /**
     * 验证参数是不是数字.
     *
     * @param validateString 验证对象
     * @throws ValidationException 当传入参数不是数字时
     */
    public static final void isNumber(String validateString) throws ValidationException {
        isNumber(validateString, BusinessStatusEnum.PARAM_ILLEGAL);
    }

    /**
     * 验证参数是不是数字.
     *
     * @param validateString 验证对象
     * @param errorMessage 自定义错误消息
     * @throws ValidationException 当传入参数不是数字时
     */
    public static final void isNumber(String validateString, String errorMessage) throws ValidationException {
        isNumber(validateString, BusinessStatusEnum.PARAM_ILLEGAL, errorMessage);
    }

    /**
     * 验证参数是不是数字.
     *
     * @param validateString 验证对象
     * @param businessStatusEnum 错误枚举
     * @throws ValidationException 当传入参数不是数字时
     */
    public static final void isNumber(String validateString, IBusinessStatusEnum businessStatusEnum) throws ValidationException {
        isNumber(validateString, businessStatusEnum, businessStatusEnum.getDesc());
    }

    /**
     * 验证参数是不是数字.
     *
     * @param validateString 验证对象
     * @param businessStatusEnum 错误枚举
     * @param errorMessage 自定义错误消息
     * @throws ValidationException 当传入参数不是数字时
     */
    public static final void isNumber(String validateString, IBusinessStatusEnum businessStatusEnum, String errorMessage) throws ValidationException {
        //StringUtils.isNumeric 比正则\\d+$ 效率高
        if(!org.apache.commons.lang3.StringUtils.isNumeric(validateString)) {
            throw new ValidationException(businessStatusEnum, errorMessage);
        }
    }

    /**
     * 验证参数是不是电话号码.
     *
     * @param phoneNumber 验证对象
     * @throws ValidationException 当传入参数不是电话号码时
     */
    public static final void isPhoneNumber(String phoneNumber) throws ValidationException {
        isPhoneNumber(phoneNumber, BusinessStatusEnum.PARAM_ILLEGAL);
    }

    /**
     * 验证参数是不是电话号码.
     *
     * @param phoneNumber 验证对象
     * @param errorMessage 自定义错误消息
     * @throws ValidationException 当传入参数不是电话号码时
     */
    public static final void isPhoneNumber(String phoneNumber, String errorMessage) throws ValidationException {
        isPhoneNumber(phoneNumber, BusinessStatusEnum.PARAM_ILLEGAL, errorMessage);
    }

    /**
     * 验证参数是不是电话号码.
     *
     * @param phoneNumber 验证对象
     * @param businessStatusEnum 错误枚举
     * @throws ValidationException 当传入参数不是电话号码时
     */
    public static final void isPhoneNumber(String phoneNumber, IBusinessStatusEnum businessStatusEnum) throws ValidationException {
        isPhoneNumber(phoneNumber, businessStatusEnum, businessStatusEnum.getDesc());
    }

    /**
     * 验证参数是不是电话号码.
     *
     * @param phoneNumber 验证对象
     * @param businessStatusEnum 错误枚举
     * @param errorMessage 自定义错误消息
     * @throws ValidationException 当传入参数不是电话号码时
     */
    public static final void isPhoneNumber(String phoneNumber, IBusinessStatusEnum businessStatusEnum, String errorMessage) throws ValidationException {
        if(!Pattern.matches("^1[0-9]{10}$", phoneNumber)) {
            throw new ValidationException(businessStatusEnum, errorMessage);
        }
    }

    /**
     * 验证传入的表达式是否为真.
     *
     * @param expression 逻辑表达式
     * @throws ValidationException 当表达式不为真时
     */
    public static final void isTrue(boolean expression) throws ValidationException {
        isTrue(expression, BusinessStatusEnum.VALIDATE_ERROR);
    }

    /**
     * 验证传入的表达式是否为真.
     *
     * @param expression 逻辑表达式
     * @param message 自定义错误消息
     * @throws ValidationException 当表达式不为真时
     */
    public static final void isTrue(boolean expression, String message) throws ValidationException {
        isTrue(expression, BusinessStatusEnum.VALIDATE_ERROR, message);
    }

    /**
     * 验证传入的表达式是否为真.
     *
     * @param expression 逻辑表达式
     * @param businessStatusEnum 错误枚举
     * @throws ValidationException 当表达式不为真时
     */
    public static final void isTrue(boolean expression, IBusinessStatusEnum businessStatusEnum) throws ValidationException {
        isTrue(expression, businessStatusEnum, businessStatusEnum.getDesc());
    }

    /**
     * 验证传入的表达式是否为真.
     *
     * @param expression 逻辑表达式
     * @param businessStatusEnum 错误枚举
     * @param errorMessage 自定义错误消息
     * @throws ValidationException 当表达式不为真时
     */
    public static final void isTrue(boolean expression, IBusinessStatusEnum businessStatusEnum, String errorMessage) throws ValidationException {
        if(!expression) {
            throw new ValidationException(businessStatusEnum, errorMessage);
        }
    }
}
