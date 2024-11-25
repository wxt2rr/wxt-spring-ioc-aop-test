package wxt.spring.framework.beans.support;

import wxt.spring.framework.beans.config.WBeanDefinition;
import wxt.spring.framework.context.support.WAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangxt
 * @date 2024/11/24 18:12
 * @desc
 */
public abstract class WDefaultListableBeanFactory extends WAbstractApplicationContext {
    protected final Map<String, WBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}
