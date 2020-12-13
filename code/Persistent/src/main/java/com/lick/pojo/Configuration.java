package com.lick.pojo;

import com.lick.config.BoundSql;
import com.lick.sqlSession.executor.impl.DefaultExecutor;
import com.lick.sqlSession.executor.parameter.ParameterHandler;
import com.lick.sqlSession.executor.parameter.impl.DefaultParameterHandler;
import com.lick.sqlSession.executor.result.ResultHandler;
import com.lick.sqlSession.executor.result.impl.DefaultResultHandler;
import com.lick.sqlSession.executor.statement.StatementHandler;
import com.lick.sqlSession.executor.statement.impl.DefaultStatementHandler;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/4 23:15
 */
public class Configuration {
    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MappedStatement getMappedStatement(String statementId){
        return this.mappedStatementMap.get(statementId);
    }
    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatementMap.put(statementId, mappedStatement);
    }

    public DefaultExecutor newDefaultExecutor(){
        return new DefaultExecutor();
    }

    public DefaultExecutor newDefaultExecutor(ResultHandler resultHandler){
        return new DefaultExecutor(resultHandler);
    }

    public StatementHandler newStatementHandler(MappedStatement mappedStatement, ResultHandler resultHandler, Object... params){
        return new DefaultStatementHandler(mappedStatement, resultHandler, params);
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, BoundSql boundSql, Object... params){
        return new DefaultParameterHandler(mappedStatement, boundSql, params);
    }
}
