package org.bravo.gaia.viewsolver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * 系统默认的错误页面配置controller
 *
 * @author lijian
 * @version $Id: ContainerErrorController.java, v 0.1 2018年04月10日 20:22 lijian Exp $
 */
@Controller
@RequestMapping(value = "/")
public class ContainerErrorController {

    @Value("${web.errorPage._400:/400}")
    private String errorPage_400;
    @Value("${web.errorPage._403:/403}")
    private String errorPage_403;
    @Value("${web.errorPage._404:/404}")
    private String errorPage_404;
    @Value("${web.errorPage._405:/405}")
    private String errorPage_405;
    @Value("${web.errorPage._500:/500}")
    private String errorPage_500;

    @RequestMapping(value="400")
    public String _400()throws Exception{
        return removePrefixSlash(errorPage_400);
    }

    @RequestMapping(value="403")
    public String _403()throws Exception{
        return removePrefixSlash(errorPage_403);
    }

    @RequestMapping(value="404")
    public String _404()throws Exception{
        return removePrefixSlash(errorPage_404);
    }

    @RequestMapping(value="405")
    public String _405()throws Exception{
        return removePrefixSlash(errorPage_405);
    }

    @RequestMapping(value="500")
    public String _500()throws Exception{
        return removePrefixSlash(errorPage_500);
    }

    private String removePrefixSlash(String str) {
        return str.substring(1, str.length());
    }

}