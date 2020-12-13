package com.lick.sqlSession;

import com.lick.config.XMLConfigBuilder;
import com.lick.pojo.Configuration;
import com.lick.sqlSession.impl.DefaultSqlSessionFactory;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 21:01
 */
public class SqlSessionFactoryBuilder {
        public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
            return new DefaultSqlSessionFactory(new XMLConfigBuilder().parseConfig(in));
        }
}
