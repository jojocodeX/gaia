package org.bravo.gaia.test.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.bravo.gaia.test.example.dataobject.User;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 *
 * @author lijian
 * @version $Id: MyMapper2.java, v 0.1 2018年04月12日 18:46 lijian Exp $
 */
@Mapper
public interface MyMapper2 {

    User findById(String id);


}