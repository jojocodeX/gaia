package org.bravo.gaia.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
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
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String sql = "INSERT INTO T_ORDER(order_id, user_id) VALUES (?,?)";
        jdbcTemplate.update(sql, 1, 1);
        return "index";
    }

    @RequestMapping("/ajax")
    public @ResponseBody String ajax() {
        return "ok";
    }

}