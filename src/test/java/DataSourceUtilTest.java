import com.alibaba.druid.pool.DruidPooledConnection;
import simple.dao.RoleDao;
import simple.dao.impl.RoleDaoImpl;
import simple.util.DataSourceUtil;
import org.junit.Assert;
import org.junit.Test;

public class DataSourceUtilTest {

    @Test
    public void getSourceTest(){
        try (DruidPooledConnection connection = DataSourceUtil.getSource().getConnection()) {
            Assert.assertNotNull(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateTest(){
        RoleDao roleDao = new RoleDaoImpl();
        int i = roleDao.updateById(1, "100");
        System.out.println(i);
    }
}
