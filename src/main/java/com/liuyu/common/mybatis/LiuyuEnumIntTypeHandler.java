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
public class LiuyuEnumIntTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private final Class<E> type;
    private final Map<Integer, E> enumMap;

    public LiuyuEnumIntTypeHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        E[] enums = type.getEnumConstants();

        if (enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
        Map<Integer, E> enumMap = Maps.newHashMap();
        for (E e : enums) {
            enumMap.put(Integer.parseInt(e.toString()), e);
        }
        this.enumMap = Collections.unmodifiableMap(enumMap);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E t, JdbcType jdbcType) throws SQLException {
        int value = Integer.parseInt(t.toString());
        preparedStatement.setInt(i, value);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int value = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return getValue(value);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int value = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return getValue(value);
        }
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int value = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return getValue(value);
        }
    }

    private E getValue(int value){
        try {
            return enumMap.get(value);
        } catch (Exception var5) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + this.type.getSimpleName() + " by ordinal value.", var5);
        }
    }
}
