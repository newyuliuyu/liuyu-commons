package com.liuyu.common.mybatis;

import com.google.common.collect.Maps;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

/**
 * ClassName: LiuyuEnumCodeTypeHandler <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-18 下午6:08 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class LiuyuEnumStringTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private final Class<E> type;
    private final Map<String, E> enumMap;

    public LiuyuEnumStringTypeHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        E[] enums = type.getEnumConstants();

        if (enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
        Map<String, E> enumMap = Maps.newHashMap();
        for (E e : enums) {
            enumMap.put(e.toString(), e);
        }
        this.enumMap = Collections.unmodifiableMap(enumMap);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, t.toString());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return getValue(value);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return getValue(value);
        }
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return getValue(value);
        }
    }

    private E getValue(String value) {
        try {
            return enumMap.get(value);
        } catch (Exception var5) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + this.type.getSimpleName() + " by ordinal value.", var5);
        }
    }
}
