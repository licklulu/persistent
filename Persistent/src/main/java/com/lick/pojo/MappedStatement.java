package com.lick.pojo;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/4 23:13
 */
public class MappedStatement {
    public static final String NODEOPTION_SELECT = "select";
    public static final String NODEOPTION_UPDATE = "update";
    public static final String NODEOPTION_INDSERT = "insert";
    public static final String NODEOPTION_DELETE = "delete";
    public static String[] nodeOptionNames = {NODEOPTION_SELECT, NODEOPTION_UPDATE, NODEOPTION_INDSERT, NODEOPTION_DELETE};
    public MappedStatement(String id, String resultType, String paramterType, String sql, Configuration configuration, String nodeType) {
        this.id = id;
        this.resultType = resultType;
        this.paramterType = paramterType;
        this.sql = sql;
        this.configuration = configuration;
        this.nodeType = nodeType;
    }

    private String id;

    private String resultType;

    private String paramterType;

    private String sql;

    private Configuration configuration;

    private String nodeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", paramterType='" + paramterType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
