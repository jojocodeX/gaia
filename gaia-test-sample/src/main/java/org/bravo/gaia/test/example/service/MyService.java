package org.bravo.gaia.test.example.service;

import org.bravo.gaia.biz.mybatis.infrastructure.service.BizCommonService;
import org.bravo.gaia.test.example.dataobject.User;

/**
 *
 * @author lijian
 * @version $Id: MyService.java, v 0.1 2018年04月12日 21:19 lijian Exp $
 */
public interface MyService extends BizCommonService<User, String> {
}