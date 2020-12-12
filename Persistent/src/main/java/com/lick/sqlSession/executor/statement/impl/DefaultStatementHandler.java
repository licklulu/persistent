package com.lick.sqlSession.executor.statement.impl;

import com.lick.config.BoundSql;
import com.lick.pojo.Configuration;
import com.lick.pojo.MappedStatement;
import com.lick.sqlSession.executor.parameter.ParameterHandler;
import com.lick.sqlSession.executor.parameter.impl.DefaultParameterHandler;
import com.lick.sqlSession.executor.result.ResultHandler;
import com.lick.sqlSession.executor.statement.StatementHandler;
import com.lick.utils.GenericTokenParser;
import com.lick.utils.ParameterMappingTokenHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 17:29
 */
public class DefaultStatementHandler implements StatementHandler {
    private Object[] params;
    private Configuration configuration;
    private MappedStatement mappedStatement;
    private ParameterHandler parameterHandler;
    private BoundSql boundSql;
    private ResultHandler resultHandler;

    public DefaultStatementHandler(MappedStatement mappedStatement, ResultHandler resultHandler, Object... params) {
        this.params = params;
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.boundSql = getBoundSql(mappedStatement.getSql());
        this.parameterHandler = configuration.newParameterHandler(mappedStatement, this.boundSql, params);
        this.resultHandler = resultHandler;
    }

    @Override
    public <T> List<T> query(PreparedStatement statement) throws SQLException {
        statement.executeQuery();
        return (List<T>) this.resultHandler.handleResultSets(statement, mappedStatement);
    }

    @Override
    public int update(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
        return statement.getUpdateCount();
    }

    @Override
    public PreparedStatement prepareStatement() throws SQLException {
        return configuration.getDataSource().getConnection().prepareStatement(boundSql.getParseSql());
    }

    @Override
    public void statementParams(PreparedStatement statement) throws SQLException {
        this.parameterHandler.setParams(statement);
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    //1.完成对#{}的解析，将它换成?  2.将#{}内的值进行存储
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", handler);
        return new BoundSql(genericTokenParser.parse(sql), handler.getParameterMappings());
    }
}
