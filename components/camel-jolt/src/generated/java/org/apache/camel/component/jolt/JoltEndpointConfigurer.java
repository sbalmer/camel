/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.jolt;

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
public class JoltEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        JoltEndpoint target = (JoltEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "allowcontextmapall":
        case "allowContextMapAll": target.setAllowContextMapAll(property(camelContext, boolean.class, value)); return true;
        case "allowtemplatefromheader":
        case "allowTemplateFromHeader": target.setAllowTemplateFromHeader(property(camelContext, boolean.class, value)); return true;
        case "contentcache":
        case "contentCache": target.setContentCache(property(camelContext, boolean.class, value)); return true;
        case "inputtype":
        case "inputType": target.setInputType(property(camelContext, org.apache.camel.component.jolt.JoltInputOutputType.class, value)); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        case "outputtype":
        case "outputType": target.setOutputType(property(camelContext, org.apache.camel.component.jolt.JoltInputOutputType.class, value)); return true;
        case "synchronous": target.setSynchronous(property(camelContext, boolean.class, value)); return true;
        case "transformdsl":
        case "transformDsl": target.setTransformDsl(property(camelContext, org.apache.camel.component.jolt.JoltTransformType.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "allowcontextmapall":
        case "allowContextMapAll": return boolean.class;
        case "allowtemplatefromheader":
        case "allowTemplateFromHeader": return boolean.class;
        case "contentcache":
        case "contentCache": return boolean.class;
        case "inputtype":
        case "inputType": return org.apache.camel.component.jolt.JoltInputOutputType.class;
        case "lazystartproducer":
        case "lazyStartProducer": return boolean.class;
        case "outputtype":
        case "outputType": return org.apache.camel.component.jolt.JoltInputOutputType.class;
        case "synchronous": return boolean.class;
        case "transformdsl":
        case "transformDsl": return org.apache.camel.component.jolt.JoltTransformType.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        JoltEndpoint target = (JoltEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "allowcontextmapall":
        case "allowContextMapAll": return target.isAllowContextMapAll();
        case "allowtemplatefromheader":
        case "allowTemplateFromHeader": return target.isAllowTemplateFromHeader();
        case "contentcache":
        case "contentCache": return target.isContentCache();
        case "inputtype":
        case "inputType": return target.getInputType();
        case "lazystartproducer":
        case "lazyStartProducer": return target.isLazyStartProducer();
        case "outputtype":
        case "outputType": return target.getOutputType();
        case "synchronous": return target.isSynchronous();
        case "transformdsl":
        case "transformDsl": return target.getTransformDsl();
        default: return null;
        }
    }
}
