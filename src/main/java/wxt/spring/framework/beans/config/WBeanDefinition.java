package wxt.spring.framework.beans.config;

import lombok.Data;

/**
 * @author wangxt
 * @date 2024/11/24 18:03
 * @desc
 */
@Data
public class WBeanDefinition {
    // Bean的全类名
    private String beanClassName;
    // 是否开启懒加载
    private boolean lazyInit = false;
    // BeanId, Bean在Ioc容器中存储的key
    private String factoryBeanName;
}
