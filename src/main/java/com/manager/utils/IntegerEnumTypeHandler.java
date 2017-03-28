package com.manager.utils;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通用枚举处理
 */
public class IntegerEnumTypeHandler extends BaseTypeHandler<Enum<?>> {

    private Method getValue;
    private Method create;

    public IntegerEnumTypeHandler(Class<Enum<?>> enumType) {

        String className = enumType.getName();
        String simpleName = enumType.getSimpleName();

        try {
            getValue = enumType.getDeclaredMethod("getValue");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method " + className + "#getValue():int required.'");
        }

        try {
            create = enumType.getDeclaredMethod("create", Integer.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Static method " + className + "#create(int code):" + simpleName + " required.");
        }

        if (!Modifier.isStatic(create.getModifiers())) {
            throw new RuntimeException("Static method " + className + "#create(int code):" + simpleName + " required.");
        }
    }

    public Enum<?> getResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs, columnName);
    }

    public Enum<?> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getNullableResult(rs, columnIndex);
    }

    public Enum<?> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getNullableResult(cs, columnIndex);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Enum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, getValue(parameter));
    }

    @Override
    public Enum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return create(rs.getInt(columnName));
    }

    @Override
    public Enum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return create(rs.getInt(columnIndex));
    }

    @Override
    public Enum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return create(cs.getInt(columnIndex));
    }

    private Integer getValue(Enum object) {
        try {
            return (Integer) getValue.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Enum create(Integer value) {
        if(value==null) value = 0;  //默认0
        try {
            return (Enum) create.invoke(null, value); // invoke static method
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
