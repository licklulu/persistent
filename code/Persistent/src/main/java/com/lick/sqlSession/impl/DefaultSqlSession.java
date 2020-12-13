package com.lick.sqlSession.impl;

import com.lick.exceptions.PersistenceException;
import com.lick.pojo.Configuration;
import com.lick.pojo.MappedStatement;
import com.lick.sqlSession.SqlSession;
import com.lick.sqlSession.executor.Executor;
import com.lick.sqlSession.executor.impl.DefaultExecutor;
import com.lick.sqlSession.executor.result.ResultHandler;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 22:28
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object... params){
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        try {
            return executor.query(mappedStatement, params);
        } catch (Exception e) {
            throw new PersistenceException("Select statementId: " + statementId+ ",mappedStatement: " + mappedStatement + ",params: " + Arrays.toString(params) + "failed", e);
        }
    }

    @Override
    public <T> List<T> selectList(String statementId, ResultHandler resultHandler, Object... params) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        try {
            return executor.query(mappedStatement, params);
        } catch (Exception e) {
            throw new PersistenceException("Select statementId: " + statementId + ",mappedStatement: " + mappedStatement + ",params: " + Arrays.toString(params) + "failed", e);
        }
    }

    @Override
    public <T> T selectOne(String statementId, Object... params){
        List<T> objects = selectList(statementId, params);
        if(objects.size() == 1){
            return objects.get(0);
        }else {
            throw new RuntimeException("Result is empty or more than one");
        }
    }

    @Override
    public int update(String statementId, Object... params) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        try {
            return executor.update(mappedStatement, params);
        }catch (Exception e){
            throw new PersistenceException("Update statementId: " + statementId + ",mappedStatement: " + mappedStatement + ",params: " + Arrays.toString(params) + "failed", e);
        }
    }
    @Override
    public int insert(String statementId, Object... params) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        try {
            return executor.update(mappedStatement, params);
        }catch (Exception e){
            throw new PersistenceException("Insert statementId: " + statementId + ",mappedStatement: " + mappedStatement + ",params: " + Arrays.toString(params) + "failed", e);
        }
    }
    @Override
    public void delete(String statementId, Object... params) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        try {
            executor.update(mappedStatement, params);
        }catch (Exception e){
            throw new PersistenceException("Delete statementId: " + statementId + ",mappedStatement: " + mappedStatement + ",params: " + Arrays.toString(params) + "failed", e);
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass){
        Object proxyInstance = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperClass}, (proxy, method, args) -> {
            String statementId = method.getDeclaringClass().getName() + "." + method.getName();
            MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
            if(mappedStatement == null){
                throw new PersistenceException("Cant find mappedStatement, statementId: " + statementId);
            }
            switch (mappedStatement.getNodeType()){
                case MappedStatement.NODEOPTION_SELECT:
                    Type genericReturnType = method.getGenericReturnType();
                    if(genericReturnType instanceof ParameterizedType){
                        return selectList(statementId, args);
                    }
                    return selectOne(statementId, args);
                case MappedStatement.NODEOPTION_UPDATE:
                    return update(statementId, args);
                case MappedStatement.NODEOPTION_INDSERT:
                    return insert(statementId, args);
                case MappedStatement.NODEOPTION_DELETE:
                    delete(statementId, args);
                    break;
                default:
                    throw new PersistenceException("Node type is illegal, statementId: " + statementId + ",nodeType: " + mappedStatement.getNodeType());
            }
            return null;
        });
        return (T) proxyInstance;
    }

}
