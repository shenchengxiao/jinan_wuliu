package com.manager.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.manager.service.IEnum;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 实现描述：枚举工具
 */
public class EnumUtils {

    public static <E extends Enum<?>> E getEnum(E[] enums, Object enumVal) {
        for (E e : enums) {
            IEnum en = (IEnum) e;
            if (en.getValue().equals(enumVal)) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<?>> E getEnum(E[] enums, String title) {
        for (E e : enums) {
            IEnum en = (IEnum) e;
            if (en.getTitle().equals(title)) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<E>> E valueOf(Class<E> enumType, String name){
        try {
            if (StringUtils.isBlank(name)) {
                return null;
            }
            return Enum.valueOf(enumType, name);
        } catch (RuntimeException re) { // ignore exception
            //logger.error("枚举值不合法: " + name);
        }
        return null;
    }

    public static <T> List<T> enumToValList(List<? extends IEnum<T>> enums) {
        List<T> result = Lists.newArrayList();
        for (IEnum<T> e : enums) {
            result.add(e.getValue());
        }
        return result;
    }

    public static final String toJSONString(Object object, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();

        Map<String, Object> jsonMap = Maps.newHashMap();
        if(object instanceof Enum) {
            Field[] fields = ((Enum)object).getClass().getDeclaredFields();
            for(Field f : fields) {
                if(!(f.getType().isEnum()) && !"$VALUES".equals(f.getName())) {
                    f.setAccessible(true);
                    Object val = null;
                    try {
                        val = f.get(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    jsonMap.put(f.getName(), val);
                }
            }
        }
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (com.alibaba.fastjson.serializer.SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.write(jsonMap);
            return out.toString();
        } finally {
            out.close();
        }
    }
}
