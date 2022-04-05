package core.dao;

import com.alibaba.druid.pool.DruidPooledConnection;
import core.pojo.RoleDto;
import core.util.DataSourceUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{

    @Override
    public List<RoleDto> queryRoleList() {
        try {
            DruidPooledConnection connection = DataSourceUtil.getSource().getConnection();

            String sql = "select id, role_name from role";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<RoleDto> result = new ArrayList<>();
            while(resultSet.next()) {
                RoleDto roleDto = new RoleDto();
                roleDto.setId(resultSet.getInt("id"));
                roleDto.setRoleName(resultSet.getString("role_name"));
                result.add(roleDto);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return result;
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>(0);
    }
}
