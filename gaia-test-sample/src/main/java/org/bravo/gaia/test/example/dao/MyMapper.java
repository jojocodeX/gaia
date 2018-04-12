package org.bravo.gaia.test.example.dao;

import org.bravo.gaia.mybatis.general.CustomPageMapper;
import org.bravo.gaia.test.example.dataobject.User;

/**
 *
 * @author lijian
 * @version $Id: MyMapper.java, v 0.1 2018年04月12日 18:30 lijian Exp $
 */
public interface MyMapper extends CustomPageMapper<User> {

    User findById(String id);

}