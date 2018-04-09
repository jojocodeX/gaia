/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package org.bravo.gaia.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fengqian.lj
 * @version $Id: TestController.java, v 0.1 2018年04月09日 21:19 fengqian.lj Exp $
 */
@Controller
public class TestController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}