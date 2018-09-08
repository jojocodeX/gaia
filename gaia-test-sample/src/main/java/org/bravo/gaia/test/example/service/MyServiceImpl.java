package org.bravo.gaia.test.example.service;

import org.bravo.gaia.biz.mybatis.infrastructure.service.AbstractBizCommonService;
import org.bravo.gaia.log.annotation.NeedLog;
import org.bravo.gaia.test.example.dataobject.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author lijian
 * @version $Id: MyServiceImpl.java, v 0.1 2018年04月12日 21:20 lijian Exp $
 */
@Service
public class MyServiceImpl extends AbstractBizCommonService<User, String> implements MyService {


    @NeedLog(sceneName = "我是test1")
    @Override
    public void test1() {
        System.out.println("invoke test1");
    }

    @Override
    public void test2() {
        System.out.println("invoke test2");
    }
}