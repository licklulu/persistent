package com.lick.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.lick.exceptions.PersistenceException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 23:00
 */
public class DataSourceBuilder {
    public DataSource build(Properties properties) {
        try {
            String dataSourceClass = properties.getProperty("dataSource");
            if(dataSourceClass == null){
                throw new PersistenceException("dataSource config cant be null");
            }
            DataSource dataSource = (DataSource) Class.forName(dataSourceClass).getDeclaredConstructor().newInstance();
            if (DruidDataSource.class.equals(dataSource.getClass())) {
                ((DruidDataSource)dataSource).setDriverClassName(properties.getProperty("driverClass"));
                ((DruidDataSource)dataSource).setUrl(properties.getProperty("jdbcUrl"));
                ((DruidDataSource)dataSource).setUsername(properties.getProperty("user"));
                ((DruidDataSource)dataSource).setPassword(properties.getProperty("password"));
            } else if (ComboPooledDataSource.class.equals(dataSource.getClass())) {
                ((ComboPooledDataSource)dataSource).setDriverClass(properties.getProperty("driverClass"));
                ((ComboPooledDataSource)dataSource).setJdbcUrl(properties.getProperty("jdbcUrl"));
                ((ComboPooledDataSource)dataSource).setUser(properties.getProperty("user"));
                ((ComboPooledDataSource)dataSource).setPassword(properties.getProperty("password"));
            }else {
                throw new PersistenceException("Not support the dataSourceClass: " + dataSourceClass);
            }
            return dataSource;
        }catch (ClassNotFoundException | PropertyVetoException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new PersistenceException("Build datasource failed, properties: " + properties);
        }
    }
}
