package core.service.impl;

import core.dao.RoleDao;
import core.dao.impl.RoleDaoImpl;
import core.factory.BeanFactory;
import core.pojo.ResultVo;
import core.pojo.RoleDto;
import core.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    // 最原始方式，代码耦合严重
    //private RoleDao roleDao = new RoleDaoImpl();

    // 通过beanFactory获取指定的bean,不够优雅,想直接通过声明的方式
    //private RoleDao roleDao = (RoleDao) BeanFactory.getBean("roleDao");

    // 直接声明依赖对象，怎么将对象初始化呢，暂时通过代码块
    private RoleDao roleDao;

    {
        roleDao = (RoleDao) BeanFactory.getBean("roleDao");
    }

    @Override
    public ResultVo queryRole() {
        List<RoleDto> list = roleDao.queryRoleList();

        return new ResultVo(200, "success", list);
    }
}
