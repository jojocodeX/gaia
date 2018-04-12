package org.bravo.gaia.mybatis.config;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@MapperScan(MybatisConfig.MYBATIS_SCAN_PACKAGE)
public class MybatisConfig {
	
	public static final String CUSTOM_GENERAL_MAPPER = "org.bravo.gaia.mybatis.general.CustomPageMapper";
    public static final String MYBATIS_SCAN_PACKAGE = "**.dao";

    @Value("${mybatis.general.mapper.scan}")
    private String mybatis_scan_package;

	//通用mapper配置
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		Config config = new Config();
		mapperScannerConfigurer.getMapperHelper().setConfig(config);
        mybatis_scan_package = StringUtils.isEmpty(mybatis_scan_package) ? MYBATIS_SCAN_PACKAGE : mybatis_scan_package;
		mapperScannerConfigurer.setBasePackage(mybatis_scan_package);
		Properties prop = new Properties();
		prop.put("mappers", CUSTOM_GENERAL_MAPPER);
		mapperScannerConfigurer.setProperties(prop);
		return  mapperScannerConfigurer;
	}
	
}
