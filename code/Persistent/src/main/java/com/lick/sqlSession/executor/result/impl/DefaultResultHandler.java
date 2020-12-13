package com.lick.sqlSession.executor.result.impl;

import com.lick.exceptions.PersistenceException;
import com.lick.pojo.MappedStatement;
import com.lick.sqlSession.executor.result.ResultHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 18:37
 */
public class DefaultResultHandler implements ResultHandler {
    private MappedStatement mappedStatement;


    @Override
    public List<Object> handleResultSets(Statement stmt, MappedStatement mappedStatement) throws SQLException {
        ResultSet resultSet = stmt.getResultSet();
        try {
            Class<?> resultClass = Class.forName(mappedStatement.getResultType());
            ArrayList<Object> objects = new ArrayList<>();
            while (resultSet.next()){
                Object o = resultClass.getDeclaredConstructor().newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
                    propertyDescriptor.getWriteMethod().invoke(o, resultSet.getObject(columnName));
                }
                objects.add(o);
            }
            return objects;
        }catch (ClassNotFoundException | NoSuchMethodException | IntrospectionException | IllegalAccessException | InstantiationException | InvocationTargetException e){
            throw new PersistenceException("Handle result failed, mappedStatement: " +mappedStatement);
        }
    }
}
