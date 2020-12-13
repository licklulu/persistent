package com.lick.sqlSession.executor.impl;

import com.lick.config.BoundSql;
import com.lick.pojo.MappedStatement;
import com.lick.sqlSession.executor.Executor;
import com.lick.sqlSession.executor.result.ResultHandler;
import com.lick.sqlSession.executor.result.impl.DefaultResultHandler;
import com.lick.sqlSession.executor.statement.StatementHandler;
import com.lick.sqlSession.executor.statement.impl.DefaultStatementHandler;
import com.lick.utils.GenericTokenParser;
import com.lick.utils.ParameterMapping;
import com.lick.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 22:53
 */
public class DefaultExecutor implements Executor {
    private ResultHandler resultHandler;
    public DefaultExecutor() {
        this.resultHandler = new DefaultResultHandler();
    }

    public DefaultExecutor(ResultHandler resultHandler) {
        this.resultHandler = resultHandler;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Object... params) throws SQLException {
        StatementHandler statementHandler = mappedStatement.getConfiguration().newStatementHandler(mappedStatement, resultHandler, params);
        PreparedStatement statement = prepareStatement(statementHandler);
        return (List<T>) statementHandler.query(statement);
    }

    @Override
    public int update(MappedStatement mappedStatement, Object... params) throws SQLException {
        StatementHandler statementHandler = mappedStatement.getConfiguration().newStatementHandler(mappedStatement, resultHandler, params);
        PreparedStatement statement = prepareStatement(statementHandler);
        return statementHandler.update(statement);
    }

    private PreparedStatement prepareStatement(StatementHandler statementHandler) throws SQLException {
        PreparedStatement statement = statementHandler.prepareStatement();
        statementHandler.statementParams(statement);
        return statement;
    }
}
