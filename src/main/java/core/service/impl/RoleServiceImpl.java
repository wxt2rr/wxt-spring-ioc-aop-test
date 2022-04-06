package core.service.impl;

import core.dao.RoleDao;
import core.dao.impl.RoleDaoImpl;
import core.factory.BeanFactory;
import core.pojo.ResultVo;
import core.pojo.RoleDto;
import core.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    //private RoleDao roleDao = new RoleDaoImpl();

    private RoleDao roleDao = (RoleDao) BeanFactory.getBean("roleDao");

    @Override
    public ResultVo queryRole() {
        List<RoleDto> list = roleDao.queryRoleList();

        return new ResultVo(200, "success", list);
    }
}
