package com.lick.config;

import com.lick.pojo.Configuration;
import com.lick.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 22:00
 */
public class XMLMapperBuilder {

    private Configuration configuration;
    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        for (String nodeName : MappedStatement.nodeOptionNames){
            List<Element> selectNodes = rootElement.selectNodes("//" + nodeName);
            selectNodes.forEach(element -> {
                String id = element.attributeValue("id");
                String resultType = element.attributeValue("resultType");
                String parameterType = element.attributeValue("parameterType");
                String sql = element.getTextTrim();
                configuration.addMappedStatement(namespace + "." + id, new MappedStatement(id, resultType, parameterType, sql, configuration, nodeName));
            });
        }

    }
}
