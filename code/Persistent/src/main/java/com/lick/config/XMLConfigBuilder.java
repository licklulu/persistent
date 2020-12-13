package com.lick.config;

import com.lick.io.Resources;
import com.lick.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.SAXParser;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/5 21:08
 */
public class XMLConfigBuilder {
    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    private Configuration configuration;

    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> selectNodes = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        selectNodes.forEach(element -> properties.setProperty(element.attributeValue("name"), element.attributeValue("value")));
        configuration.setDataSource(new DataSourceBuilder().build(properties));

        List<Element> mapperNodes = rootElement.selectNodes("//mapper");
        for (Element element : mapperNodes){
            InputStream resourceAsStream = Resources.getResourceAsStream(element.attributeValue("resource"));
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }
}
