package org.bravo.gaia.viewsolver.webconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class EmbeddedServletContainerCustomizerBean implements
		WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Value("${web.errorPage._400:/400}")
	private String errorPage_400;
	@Value("${web.errorPage._404:/404}")
	private String errorPage_404;
	@Value("${web.errorPage._500:/500}")
	private String errorPage_500;

	@Override
	public void customize(ConfigurableServletWebServerFactory server) {
        server.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, errorPage_400));
        server.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, errorPage_404));
        server.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, errorPage_500));
	}
}
