package core.dao;

import core.pojo.RoleDto;

import java.util.List;

public interface RoleDao {

    List<RoleDto> queryRoleList();

    int updateById(int id, String name);
}
