/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package org.bravo.gaia.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fengqian.lj
 * @version $Id: TestController.java, v 0.1 2018年04月09日 21:19 fengqian.lj Exp $
 */
@Controller
public class TestController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        System.out.println(RequestContextUtils.getLocale(request));
        return "index";
    }

}