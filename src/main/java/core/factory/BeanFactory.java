package core.factory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BeanFactory {

    private static Map<String,Object> singletonMap = new HashMap<>();

    private static Map<String,Object> earlyMap = new HashMap<>();

    private static Map<String,Object> singletonFactoryMap = new HashMap<>();

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

                singletonMap.put(id, o);
            }

            List<Element> property = rootElement.selectNodes("//property");
            for(Element element : property){
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");
                String id = element.getParent().attributeValue("id");

                Class<?> aClass = singletonMap.get(id).getClass();
                Method[] methods = aClass.getMethods();
                for (Method method : methods){
                    if(method.getName().equalsIgnoreCase("set" + name)){
                        Object o = singletonMap.get(id);
                        method.invoke(o, singletonMap.get(ref));
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object getBean(String name){
        return singletonMap.get(name);
    }
}
