package com.lick.sqlSession.executor.statement;

import com.lick.sqlSession.executor.result.ResultHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 17:27
 */
public interface StatementHandler {
    public <T> List<T> query(PreparedStatement statement) throws SQLException;

    public int update(PreparedStatement statement) throws SQLException;

    public PreparedStatement prepareStatement() throws SQLException;

    public void statementParams(PreparedStatement statement) throws SQLException;
}
