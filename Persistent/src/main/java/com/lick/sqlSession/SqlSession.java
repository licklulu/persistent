package com.lick.sqlSession;

import com.lick.pojo.MappedStatement;
import com.lick.sqlSession.executor.result.ResultHandler;

import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 22:27
 */
public interface SqlSession {
    public <T>List<T> selectList(String statementId, Object... params);

    public <T>List<T> selectList(String statementId, ResultHandler resultHandler, Object... params);

    public <T>T selectOne(String statementId, Object... params);

    public int update(String statementId, Object... params);

    public int insert(String statementId, Object... params);

    public void delete(String statementId, Object... params);

    public <T>T getMapper(Class<?> mapperClass);
}
