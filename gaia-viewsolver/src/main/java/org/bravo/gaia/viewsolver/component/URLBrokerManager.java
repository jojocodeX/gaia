/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package org.bravo.gaia.viewsolver.component;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bravo.gaia.utils.AssertUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * uribroker管理类，注册成一个spring bean用于加载当前类路径下所有的uri资源文件
 * @author lijian
 * @version @Id: URIBrokerManager.java, v 0.1 2018年11月19日 22:57 lijian Exp $
 */
@Component
public class URLBrokerManager implements InitializingBean {

    /** uri资源文件名称 */
    private static final String    CONFIG_FILE_NAME = "url-broker.xml";

    /** 根节点xpath路径 */
    private static final String    PARENT_XPATH     = "//urls/url-broker";

    /** 子节点xpath路径 */
    private static final String    CHILD_XPATH      = "children/child-url-broker";

    /** 节点name属性xpath路径 */
    private static final String    NAME_ATTR_XPATH  = "@name";

    /** 节点path属性xpath路径 */
    private static final String    PATH_ATTR_XPATH  = "@path";

    /** xml变量占位符正则表达式 */
    public static final String     PLACEHODER_REGEX = "\\$\\{[a-zA-Z_$]+[a-zA-Z0-9_$.-]+\\}";

    /** 校验日志提示信息 */
    public static final String     CHECK_MSG        = "URL Broker xml属性值校验失败,请检查";

    @Autowired
    private Environment            environment;

    /** 当前加载的所有的uribroker对象 */
    private Map<String, URLBroker> uriBrokers       = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource(CONFIG_FILE_NAME);

        try (InputStream inputStream = classPathResource.getInputStream();
             StringWriter originalWriter = new StringWriter()) {

            //替换占位符
            String urlBrokerXmlStr = replacePlaceholder(inputStream, originalWriter);

            //解析xml文件
            parseURLBrokerXML(urlBrokerXmlStr);
        }
    }



    //~~~ 内部方法

    /**
     * 解析xml
     */
    private void parseURLBrokerXML(String urlBrokerXmlStr) throws DocumentException {
        //使用dom4j解析xml
        SAXReader saxReader = new SAXReader();
        StringReader domReader = new StringReader(urlBrokerXmlStr);
        Document document = saxReader.read(domReader);

        List<Node> parentURLBrokerNodes = document.selectNodes(PARENT_XPATH);
        for (Node parentURLBrokerNode : parentURLBrokerNodes) {
            //check 节点属性值
            checkNodeValue(
                    parentURLBrokerNode.valueOf(NAME_ATTR_XPATH),
                    parentURLBrokerNode.valueOf(PATH_ATTR_XPATH)
            );

            //装载父URL Broker对象
            assembleParentURLBroker(parentURLBrokerNode);

            List<Node> childrenURLBrokerNodes = parentURLBrokerNode.selectNodes(CHILD_XPATH);
            for (Node childURLBrokerNode : childrenURLBrokerNodes) {
                //check 节点属性值
                checkNodeValue(
                        childURLBrokerNode.valueOf(NAME_ATTR_XPATH),
                        StringUtils.trim(childURLBrokerNode.getText())
                );

                //装载子URL Broker对象
                assembleChildURLBroker(parentURLBrokerNode, childURLBrokerNode);
            }
        }
    }

    /**
     * 填充子URLbroker
     */
    private void assembleChildURLBroker(Node parentURLBrokerNode, Node childURLBrokerNode) {
        URLBroker childURLBroker = new DefaultURLBroker(
                childURLBrokerNode.valueOf(NAME_ATTR_XPATH),
                assenbleChildPath(
                        parentURLBrokerNode.valueOf(PATH_ATTR_XPATH),
                        StringUtils.trim(childURLBrokerNode.getText())
                )
        );
        uriBrokers.put(childURLBrokerNode.valueOf(NAME_ATTR_XPATH), childURLBroker);
    }

    /**
     * 填充父URLbroker
     */
    private void assembleParentURLBroker(Node parentURLBrokerNode) {
        DefaultURLBroker parentURLBroker = new DefaultURLBroker(
                parentURLBrokerNode.valueOf(NAME_ATTR_XPATH),
                parentURLBrokerNode.valueOf(PATH_ATTR_XPATH)
        );
        uriBrokers.put(parentURLBrokerNode.valueOf(NAME_ATTR_XPATH), parentURLBroker);
    }

    /**
     * 使用正则表达式替换占位符
     * 使用正则表达式的原因： freemarker模板替换不支持变量名中带.
     * e.g: ${app.name}，该变量名在freemarker中解析要报错
     */
    private String replacePlaceholder(InputStream inputStream, StringWriter originalWriter) throws IOException {
        StringBuffer eventualTemplate = new StringBuffer(StringUtils.EMPTY);

        IOUtils.copy(inputStream, originalWriter, StandardCharsets.UTF_8);
        Pattern compile = Pattern.compile(PLACEHODER_REGEX);
        Matcher matcher = compile.matcher(originalWriter.toString());

        while (matcher.find()) {
            String prop = matcher.group().substring(2, matcher.group().length() - 1);
            if (StringUtils.isNotBlank(environment.getProperty(prop))) {
                matcher.appendReplacement(eventualTemplate, environment.getProperty(prop));
            }
        }
        matcher.appendTail(eventualTemplate);

        return eventualTemplate.toString();
    }

    /**
     * 校验节点数据
     */
    private void checkNodeValue(String name, String path) {
        AssertUtil.isNotBlank(name, CHECK_MSG);
        AssertUtil.isNotBlank(path, CHECK_MSG);
    }

    /**
     * 拼装子节点path
     */
    private String assenbleChildPath(String path, String text) {
        if (StringUtils.isBlank(path))
            return text;

        return path.charAt(path.length() - 1) == '/' ? path + text : path + "/" + text;
    }

}
