package com.lick.sqlSession.executor.parameter;

import com.lick.pojo.MappedStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 17:19
 */
public interface ParameterHandler {
    public void setParams(PreparedStatement statement) throws SQLException;
}
