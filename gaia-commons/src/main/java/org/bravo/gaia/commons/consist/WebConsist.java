package org.bravo.gaia.commons.consist;

/**
 *
 * @author fengqian.lj
 * @version $Id: WebConsist.java, v 0.1 2018年04月10日 19:11 fengqian.lj Exp $
 */
public interface WebConsist {

    String PAGE_PATH_500 = "/500";
    String PAGE_PATH_403 = "/403";
    String PAGE_PATH_404 = "/404";
    String PAGE_PATH_405 = "/405";
    String AJAX_REQUEST_IDENTIFIER = "X-Requested-With";
    String CONTENT_TYPE = "Content-type";
    String TEXT_JSON = "text/json;charset=UTF-8";
    String TEXT_html = "text/html;charset=UTF-8";

}