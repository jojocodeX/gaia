package org.bravo.gaia.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring jpa entity的扫描路径配置
 * @author lijian
 * @version $Id: JpaConfig.java, v 0.1 2018年04月12日 15:25 lijian Exp $
 */
@Configuration
@EntityScan(basePackages = {"org.**.dataobject", "com.**.dataobject"})
public class JpaConfig {
}