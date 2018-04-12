package org.bravo.gaia.test.example.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bravo.gaia.test.example.dao.MyMapper;
import org.bravo.gaia.test.example.dao.MyMapper2;
import org.bravo.gaia.test.example.dataobject.User;
import org.bravo.gaia.test.example.service.MyService;
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

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        User user = new User();
        user.setId("2");
        user.setName("alex2222");
        myService.add(user);
        return "index";
    }

    @RequestMapping("/ajax")
    public @ResponseBody
    String ajax() {
        return "ok";
    }

}