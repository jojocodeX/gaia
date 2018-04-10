package org.bravo.gaia.viewsolver.freemarker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.ext.jsp.TaglibFactory.ClasspathMetaInfTldSource;

@Configuration
@AutoConfigureAfter(FreeMarkerAutoConfiguration.class)
public class FreeMarkerPropertiesExtension {

	private static final Logger LOG = LoggerFactory.getLogger(FreeMarkerPropertiesExtension.class);

	//让freemarker加载classpath下的所有tld，用于使用jsptaglibs
	@Autowired(required = false)
	public void populateJspTaglib(FreeMarkerConfigurer freeMarkerConfigurer, ServletContext sc){
		if (freeMarkerConfigurer != null && sc != null) {
			LOG.warn("freemarker configure没有配置，请检查!");
			return;
		}

		List<ClasspathMetaInfTldSource> list = new ArrayList<>();
		list.add(new ClasspathMetaInfTldSource(Pattern.compile(".*")));
		if(freeMarkerConfigurer.getTaglibFactory() == null){
			freeMarkerConfigurer.setServletContext(sc);
		}
		freeMarkerConfigurer.getTaglibFactory().setMetaInfTldSources(list);
	}
	
}
