import core.dao.RoleDao;
import core.factory.BeanFactory;
import core.factory.ProxyFactory;
import core.pojo.ResultVo;
import core.pojo.RoleDto;
import core.service.RoleService;
import org.junit.Test;

import java.util.List;

public class RoleServiceTest {

    @Test
    public void test(){
        RoleService roleService = (RoleService) BeanFactory.getBean("roleService");
        ResultVo resultVo = roleService.queryRole();
        System.out.println(resultVo);
    }

    @Test
    public void update(){
        RoleService roleService = (RoleService) BeanFactory.getBean("roleService");
        ResultVo tz = roleService.update("1", "ceshi");
        System.out.println(tz);
    }

    @Test
    public void TranUpdate(){
        RoleService roleService = (RoleService) ProxyFactory.getInstance().getProxyObj(BeanFactory.getBean("roleService"));
        ResultVo tz = roleService.update("1", "haha");
        System.out.println(tz);
    }
}
