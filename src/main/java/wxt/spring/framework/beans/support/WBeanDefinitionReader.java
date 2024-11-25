package wxt.spring.framework.beans.support;

import wxt.spring.framework.beans.config.WBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wangxt
 * @date 2024/11/24 18:16
 * @desc 读取配置文件信息，扫描包路径，并将其下的所有类的配置信息解析成BeanDefinition
 */
public class WBeanDefinitionReader {
    private List<String> registryBeanClass = new ArrayList<>();
    private Properties config = new Properties();
    private final String SCAN_PACKAGE = "scan.package";

    public WBeanDefinitionReader(String... locations) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = scanPackage + file.getName().replace(".class", "");
                registryBeanClass.add(className);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    // 把从配置文件中扫描到的所有配置信息转换成BeanDefinition对象
    public List<WBeanDefinition> loadBeanDefinitions() {
        List<WBeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registryBeanClass) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // 把每个配置信息解析成一个BeanDefinition
    private WBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        WBeanDefinition beanDefinition = new WBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    // 把类名首字母转成小写
    private String toLowerFirstCase(String simpleName) {
        char[] charArray = simpleName.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
    }
}
