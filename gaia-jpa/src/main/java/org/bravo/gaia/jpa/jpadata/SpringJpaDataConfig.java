package org.bravo.gaia.jpa.jpadata;

import org.bravo.gaia.jpa.commondao.CommonRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * spring jpa data配置
 * @author lijian
 * @version $Id: JpaConfig.java, v 0.1 2018年04月12日 15:25 lijian Exp $
 */
@Configuration
@EnableJpaRepositories(basePackages = {"org.**.dao", "com.**.dao"},
							repositoryBaseClass = CommonRepositoryImpl.class)
public class SpringJpaDataConfig {
	
	
}
