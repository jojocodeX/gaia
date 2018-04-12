package org.bravo.gaia.test.example.controller;

import org.bravo.gaia.test.example.dao.UserRepository;
import org.bravo.gaia.test.example.dataobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    @Transactional
    public String index(HttpServletRequest request) {
        //System.out.println(entityManager);
        //User user = userRepository.findById("1");
        User user = new User();
        user.setId("5");
        user.setName("alex");
        userRepository.save(user);
        jdbcTemplate.update("insert into t_user(id, name) VALUES (?,?)", "9" , "9");
        return "index";
    }

    @RequestMapping("/ajax")
    public @ResponseBody String ajax() {
        return "ok";
    }

}