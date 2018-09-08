package org.bravo.gaia.test.example;

import org.bravo.gaia.app.boot.AppStarter;
import org.bravo.gaia.commons.commoninterface.ErrorCodeLevel;
import org.bravo.gaia.commons.commoninterface.ErrorCodeType;
import org.bravo.gaia.commons.domain.ErrorCode;
import org.bravo.gaia.commons.system.SystemNameEnum;

/**
 *
 * @author lijian
 * @version $Id: StarterTest.java, v 0.1 2018年04月09日 21:18 lijian Exp $
 */
public class StarterTest {

    public static void main(String[] args) throws InterruptedException {
        AppStarter.start(args);
        //AppStarter.blockingStart(args);
    }


}