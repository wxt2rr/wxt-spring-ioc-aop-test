package simple.service;

import simple.pojo.ResultVo;

public interface RoleService {

    ResultVo queryRole();

    ResultVo update(String id, String name);
}
