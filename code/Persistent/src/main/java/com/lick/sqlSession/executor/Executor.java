package com.lick.sqlSession.executor;

import com.lick.pojo.Configuration;
import com.lick.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 22:53
 */
public interface Executor {
    public <T>List<T> query(MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, Exception;

    public int update(MappedStatement mappedStatement, Object... params) throws SQLException;
}
