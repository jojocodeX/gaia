package org.bravo.gaia.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

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
        if (true) {
            throw new MyException(MyErrorCodeEnum.NOT_FOUND.getCode());
        }
        return "ok";
    }

}