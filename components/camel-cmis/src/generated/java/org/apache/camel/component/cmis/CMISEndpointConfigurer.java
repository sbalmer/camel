/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.cmis;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@SuppressWarnings("unchecked")
public class CMISEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        CMISEndpoint target = (CMISEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": target.setBridgeErrorHandler(property(camelContext, boolean.class, value)); return true;
        case "exceptionhandler":
        case "exceptionHandler": target.setExceptionHandler(property(camelContext, org.apache.camel.spi.ExceptionHandler.class, value)); return true;
        case "exchangepattern":
        case "exchangePattern": target.setExchangePattern(property(camelContext, org.apache.camel.ExchangePattern.class, value)); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        case "pagesize":
        case "pageSize": target.getSessionFacade().setPageSize(property(camelContext, int.class, value)); return true;
        case "password": target.getSessionFacade().setPassword(property(camelContext, java.lang.String.class, value)); return true;
        case "query": target.getSessionFacade().setQuery(property(camelContext, java.lang.String.class, value)); return true;
        case "querymode":
        case "queryMode": target.setQueryMode(property(camelContext, boolean.class, value)); return true;
        case "readcontent":
        case "readContent": target.getSessionFacade().setReadContent(property(camelContext, boolean.class, value)); return true;
        case "readcount":
        case "readCount": target.getSessionFacade().setReadCount(property(camelContext, int.class, value)); return true;
        case "repositoryid":
        case "repositoryId": target.getSessionFacade().setRepositoryId(property(camelContext, java.lang.String.class, value)); return true;
        case "sessionfacadefactory":
        case "sessionFacadeFactory": target.setSessionFacadeFactory(property(camelContext, org.apache.camel.component.cmis.CMISSessionFacadeFactory.class, value)); return true;
        case "synchronous": target.setSynchronous(property(camelContext, boolean.class, value)); return true;
        case "username": target.getSessionFacade().setUsername(property(camelContext, java.lang.String.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return boolean.class;
        case "exceptionhandler":
        case "exceptionHandler": return org.apache.camel.spi.ExceptionHandler.class;
        case "exchangepattern":
        case "exchangePattern": return org.apache.camel.ExchangePattern.class;
        case "lazystartproducer":
        case "lazyStartProducer": return boolean.class;
        case "pagesize":
        case "pageSize": return int.class;
        case "password": return java.lang.String.class;
        case "query": return java.lang.String.class;
        case "querymode":
        case "queryMode": return boolean.class;
        case "readcontent":
        case "readContent": return boolean.class;
        case "readcount":
        case "readCount": return int.class;
        case "repositoryid":
        case "repositoryId": return java.lang.String.class;
        case "sessionfacadefactory":
        case "sessionFacadeFactory": return org.apache.camel.component.cmis.CMISSessionFacadeFactory.class;
        case "synchronous": return boolean.class;
        case "username": return java.lang.String.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        CMISEndpoint target = (CMISEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return target.isBridgeErrorHandler();
        case "exceptionhandler":
        case "exceptionHandler": return target.getExceptionHandler();
        case "exchangepattern":
        case "exchangePattern": return target.getExchangePattern();
        case "lazystartproducer":
        case "lazyStartProducer": return target.isLazyStartProducer();
        case "pagesize":
        case "pageSize": return target.getSessionFacade().getPageSize();
        case "password": return target.getSessionFacade().getPassword();
        case "query": return target.getSessionFacade().getQuery();
        case "querymode":
        case "queryMode": return target.isQueryMode();
        case "readcontent":
        case "readContent": return target.getSessionFacade().isReadContent();
        case "readcount":
        case "readCount": return target.getSessionFacade().getReadCount();
        case "repositoryid":
        case "repositoryId": return target.getSessionFacade().getRepositoryId();
        case "sessionfacadefactory":
        case "sessionFacadeFactory": return target.getSessionFacadeFactory();
        case "synchronous": return target.isSynchronous();
        case "username": return target.getSessionFacade().getUsername();
        default: return null;
        }
    }
}
