package com.lick.sqlSession.executor.parameter.impl;

import com.lick.config.BoundSql;
import com.lick.exceptions.PersistenceException;
import com.lick.pojo.MappedStatement;
import com.lick.sqlSession.executor.parameter.ParameterHandler;
import com.lick.utils.ParameterMapping;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 17:28
 */
public class DefaultParameterHandler implements ParameterHandler {
    private MappedStatement mappedStatement;
    private BoundSql boundSql;
    private Object[] params;
    public DefaultParameterHandler(MappedStatement mappedStatement, BoundSql boundSql, Object... params) {
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
        this.params = params;
    }

    public MappedStatement getMappedStatement() {
        return mappedStatement;
    }

    public void setMappedStatement(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }

    public void setBoundSql(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public void setParams(PreparedStatement statement) throws SQLException {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (mappedStatement.getParamterType() != null) {
            try {
                Class<?> parameterClass = Class.forName(mappedStatement.getParamterType());
                if(isBaseClass(parameterClass)){
                    statement.setObject(1, params[0]);
                }else {
                    for (int i = 0; i < parameterMappings.size(); i++) {
                        try {
                            Field field = parameterClass.getDeclaredField(parameterMappings.get(i).getContent());
                            field.setAccessible(true);
                            Object o = field.get(params[0]);
                            statement.setObject(i + 1, o);
                        }catch (NoSuchFieldException e){
                            statement.setObject(i + 1, params[0]);
                        }
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException e) {
                throw new PersistenceException("Set params failed, mappedStatement: " + mappedStatement, e);
            }
        }
    }
    private Class[] baseClasses = {Integer.class, int.class, Short.class, short.class, Float.class, float.class, Double.class, double.class, Double.class, double.class, Long.class, long.class, String.class};
    private boolean isBaseClass(Class c){
        return Arrays.stream(baseClasses).anyMatch(aClass -> aClass == c);
    };
}
