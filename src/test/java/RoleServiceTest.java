import core.dao.RoleDao;
import core.factory.BeanFactory;
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
}
