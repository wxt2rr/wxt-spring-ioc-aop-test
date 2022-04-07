package core.dao.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import core.dao.RoleDao;
import core.pojo.RoleDto;
import core.util.ConnUtil;
import core.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

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

    @Override
    public int updateById(int id, String name) {
        try {
            //DruidPooledConnection connection = DataSourceUtil.getSource().getConnection();
            Connection connection = ConnUtil.getInstance().get();

            String sql = "update role set role_name = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2,id);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            //connection.close();

            return result;
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
}
