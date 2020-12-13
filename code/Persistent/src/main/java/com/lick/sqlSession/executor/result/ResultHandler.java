package com.lick.sqlSession.executor.result;

import com.lick.pojo.MappedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 18:37
 */
public interface ResultHandler {
    public List<Object> handleResultSets(Statement stmt, MappedStatement mappedStatement) throws SQLException;
}
