package org.bravo.gaia.test.example.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bravo.gaia.log.GaiaLogUtil;
import org.bravo.gaia.test.example.dao.MyMapper;
import org.bravo.gaia.test.example.dao.MyMapper2;
import org.bravo.gaia.test.example.dataobject.User;
import org.bravo.gaia.test.example.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lijian
 * @version $Id: TestController.java, v 0.1 2018年04月09日 21:19 lijian Exp $
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate      jdbcTemplate;
    @Autowired
    private MyMapper          myMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private MyService         myService;

    private static final Logger GLOBAL_LOG = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        GaiaLogUtil.getTraceMsgSubLogger().info("消息来了");
        GaiaLogUtil.getTraceRpcSendLogger().info("rpc同步调用来了");
        GLOBAL_LOG.info("全局记录");
        GaiaLogUtil.getGlobalErrorLogger().info("错误appender info记录");
        GaiaLogUtil.getGlobalErrorLogger().error(ExceptionUtils.getStackTrace(new RuntimeException("错了")));
        return "index";
    }

    @RequestMapping("/ajax")
    public @ResponseBody String ajax() {
        myService.test1();
        myService.test2();
        return "ok";
    }

}