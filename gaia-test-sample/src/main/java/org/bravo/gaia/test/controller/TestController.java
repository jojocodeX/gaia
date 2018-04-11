package org.bravo.gaia.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fengqian.lj
 * @version $Id: TestController.java, v 0.1 2018年04月09日 21:19 fengqian.lj Exp $
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping("/ajax")
    public @ResponseBody String ajax() {
        return "ok";
    }

}