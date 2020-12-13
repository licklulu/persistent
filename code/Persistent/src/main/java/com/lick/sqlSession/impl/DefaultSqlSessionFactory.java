package com.lick.sqlSession.impl;

import com.lick.pojo.Configuration;
import com.lick.sqlSession.SqlSession;
import com.lick.sqlSession.SqlSessionFactory;
import com.lick.sqlSession.executor.impl.DefaultExecutor;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 22:26
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration, configuration.newDefaultExecutor());
    }
}
