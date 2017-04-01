package com.manager.annotations;

import com.manager.enums.UserRoleEnum;

import java.lang.annotation.*;

/**
 * Created by shencx on 17/2/27.
 * Api Method 注解.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authentication {
    UserRoleEnum[] allow() default {};
    UserRoleEnum[] deny() default {};
}
