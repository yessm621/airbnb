package com.airbnb.common;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;

public class ErrorConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/4xx");
        ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error-page/403");
        ErrorPage errorPage5xx = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/5xx");
        ErrorPage errorPageEx = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/4xx");

        factory.addErrorPages(errorPage404);
        factory.addErrorPages(errorPage403);
        factory.addErrorPages(errorPage5xx);
        factory.addErrorPages(errorPageEx);
    }
}
