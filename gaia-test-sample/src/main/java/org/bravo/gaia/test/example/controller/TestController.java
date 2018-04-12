package org.bravo.gaia.test.example.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bravo.gaia.test.example.dao.MyMapper;
import org.bravo.gaia.test.example.dao.MyMapper2;
import org.bravo.gaia.test.example.dataobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lijian
 * @version $Id: TestController.java, v 0.1 2018年04月09日 21:19 lijian Exp $
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate   jdbcTemplate;
    @Autowired
    private MyMapper myMapper;
    @Autowired
    private MyMapper2 myMapper2;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        //User user = new User();
        //user.setId("1");
        //User one = myMapper.selectOne(user);
        //System.out.println(one);
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //Object findById = sqlSession.selectOne("findById");
        User param = new User();
        param.setId("1");

        User user = new User();
        user.setId("1");
        user.setName("alex");
        myMapper.insert(user);
        if (true) {
            throw new RuntimeException("错了");
        }
        //jdbcTemplate.update("insert into t_user(id, name) VALUES (?,?)", "9" , "9");
        return "index";
    }

    @RequestMapping("/ajax")
    public @ResponseBody String ajax() {
        return "ok";
    }

}