package core.service;

import core.pojo.ResultVo;

public interface RoleService {

    ResultVo queryRole();

    ResultVo update(String id, String name);
}
