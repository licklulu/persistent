package com.lick.config;

import com.lick.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/6 14:40
 */
public class BoundSql {
    /**
     * 转换后的sql
     */
    private String parseSql;

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql(String parseSql, List<ParameterMapping> parameterMappings) {
        this.parseSql = parseSql;
        this.parameterMappings = parameterMappings;
    }

    public String getParseSql() {
        return parseSql;
    }

    public void setParseSql(String parseSql) {
        this.parseSql = parseSql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
