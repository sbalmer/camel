/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.yammer;

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
public class YammerComponentConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    private org.apache.camel.component.yammer.YammerConfiguration getOrCreateConfiguration(YammerComponent target) {
        if (target.getConfiguration() == null) {
            target.setConfiguration(new org.apache.camel.component.yammer.YammerConfiguration());
        }
        return target.getConfiguration();
    }

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        YammerComponent target = (YammerComponent) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "accesstoken":
        case "accessToken": getOrCreateConfiguration(target).setAccessToken(property(camelContext, java.lang.String.class, value)); return true;
        case "autowiredenabled":
        case "autowiredEnabled": target.setAutowiredEnabled(property(camelContext, boolean.class, value)); return true;
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": target.setBridgeErrorHandler(property(camelContext, boolean.class, value)); return true;
        case "configuration": target.setConfiguration(property(camelContext, org.apache.camel.component.yammer.YammerConfiguration.class, value)); return true;
        case "consumerkey":
        case "consumerKey": getOrCreateConfiguration(target).setConsumerKey(property(camelContext, java.lang.String.class, value)); return true;
        case "consumersecret":
        case "consumerSecret": getOrCreateConfiguration(target).setConsumerSecret(property(camelContext, java.lang.String.class, value)); return true;
        case "delay": getOrCreateConfiguration(target).setDelay(property(camelContext, java.time.Duration.class, value).toMillis()); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        case "limit": getOrCreateConfiguration(target).setLimit(property(camelContext, int.class, value)); return true;
        case "newerthan":
        case "newerThan": getOrCreateConfiguration(target).setNewerThan(property(camelContext, long.class, value)); return true;
        case "olderthan":
        case "olderThan": getOrCreateConfiguration(target).setOlderThan(property(camelContext, long.class, value)); return true;
        case "requestor": getOrCreateConfiguration(target).setRequestor(property(camelContext, org.apache.camel.component.yammer.ApiRequestor.class, value)); return true;
        case "threaded": getOrCreateConfiguration(target).setThreaded(property(camelContext, java.lang.String.class, value)); return true;
        case "usejson":
        case "useJson": getOrCreateConfiguration(target).setUseJson(property(camelContext, boolean.class, value)); return true;
        case "userid":
        case "userId": getOrCreateConfiguration(target).setUserId(property(camelContext, java.lang.String.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "accesstoken":
        case "accessToken": return java.lang.String.class;
        case "autowiredenabled":
        case "autowiredEnabled": return boolean.class;
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return boolean.class;
        case "configuration": return org.apache.camel.component.yammer.YammerConfiguration.class;
        case "consumerkey":
        case "consumerKey": return java.lang.String.class;
        case "consumersecret":
        case "consumerSecret": return java.lang.String.class;
        case "delay": return long.class;
        case "lazystartproducer":
        case "lazyStartProducer": return boolean.class;
        case "limit": return int.class;
        case "newerthan":
        case "newerThan": return long.class;
        case "olderthan":
        case "olderThan": return long.class;
        case "requestor": return org.apache.camel.component.yammer.ApiRequestor.class;
        case "threaded": return java.lang.String.class;
        case "usejson":
        case "useJson": return boolean.class;
        case "userid":
        case "userId": return java.lang.String.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        YammerComponent target = (YammerComponent) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "accesstoken":
        case "accessToken": return getOrCreateConfiguration(target).getAccessToken();
        case "autowiredenabled":
        case "autowiredEnabled": return target.isAutowiredEnabled();
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return target.isBridgeErrorHandler();
        case "configuration": return target.getConfiguration();
        case "consumerkey":
        case "consumerKey": return getOrCreateConfiguration(target).getConsumerKey();
        case "consumersecret":
        case "consumerSecret": return getOrCreateConfiguration(target).getConsumerSecret();
        case "delay": return getOrCreateConfiguration(target).getDelay();
        case "lazystartproducer":
        case "lazyStartProducer": return target.isLazyStartProducer();
        case "limit": return getOrCreateConfiguration(target).getLimit();
        case "newerthan":
        case "newerThan": return getOrCreateConfiguration(target).getNewerThan();
        case "olderthan":
        case "olderThan": return getOrCreateConfiguration(target).getOlderThan();
        case "requestor": return getOrCreateConfiguration(target).getRequestor();
        case "threaded": return getOrCreateConfiguration(target).getThreaded();
        case "usejson":
        case "useJson": return getOrCreateConfiguration(target).isUseJson();
        case "userid":
        case "userId": return getOrCreateConfiguration(target).getUserId();
        default: return null;
        }
    }
}
