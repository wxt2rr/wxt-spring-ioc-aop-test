package core.factory;

import jdk.internal.util.xml.SAXParser;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {

    private static Map<String,Object> beanMap = new HashMap<>();

    static {
        try(InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml")){
            SAXReader reader = new SAXReader();
            Document read = reader.read(inputStream);
            Element rootElement = read.getRootElement();
            List<Element> list = rootElement.selectNodes("//bean");
            for(Element element : list){
                String id = element.attributeValue("id");
                String aClass = element.attributeValue("class");

                Class<?> aClass1 = Class.forName(aClass);
                Object o = aClass1.newInstance();
                beanMap.put(id, o);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object getBean(String name){
        return beanMap.get(name);
    }
}
