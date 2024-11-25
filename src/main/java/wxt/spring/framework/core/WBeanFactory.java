package wxt.spring.framework.core;

/**
 * @author wangxt
 * @date 2024/11/24 18:01
 * @desc
 */
public interface WBeanFactory {
    Object getBean(String beanName) throws Exception;

    Object getBean(Class<?> beanClass) throws Exception;
}
