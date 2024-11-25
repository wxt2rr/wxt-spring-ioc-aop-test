package wxt.spring.framework.context;

import wxt.spring.framework.beans.WBeanWrapper;
import wxt.spring.framework.beans.config.WBeanDefinition;
import wxt.spring.framework.beans.support.WBeanDefinitionReader;
import wxt.spring.framework.beans.support.WDefaultListableBeanFactory;
import wxt.spring.framework.context.support.WAbstractApplicationContext;
import wxt.spring.framework.core.WBeanFactory;
import wxt.spring.framework.core.WFactoryBean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangxt
 * @date 2024/11/24 18:14
 * @desc
 */
public class WApplicationContext extends WDefaultListableBeanFactory implements WBeanFactory {
    private String[] configLoactions;
    private WBeanDefinitionReader reader;
    // 存放单例的Bean
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    // 存放所有被代理过的Bean
    private Map<String, WBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public WApplicationContext(String... configLoactions) {
        this.configLoactions = configLoactions;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        // 1. 定位，定位配置文件
        reader = new WBeanDefinitionReader(this.configLoactions);

        // 2. 加载配置文件，扫描指定包下的类，封装BeanDefinition
        List<WBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        // 3. 注册，把BeanDefinition注册到容器
        doRegisterBeanDefinition(beanDefinitions);

        // 4. 把不是延迟加载的类提前初始化
        doAutowrited();
    }

    private void doAutowrited() {
        for (Map.Entry<String, WBeanDefinition> entry : super.beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            if (!entry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegisterBeanDefinition(List<WBeanDefinition> beanDefinitions) {
        for (WBeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new RuntimeException(beanDefinition.getFactoryBeanName() + ", bean is exists");
            }

            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    public Object getBean(String beanName) throws Exception {
        return null;
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[0]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    private Properties getConfig() {
        return this.reader.getConfig();
    }
}
