import core.factory.BeanFactory;
import org.junit.Assert;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void getBean(){
        Object bean = BeanFactory.getBean("roleService");
        Assert.assertNotNull(bean);
    }
}
