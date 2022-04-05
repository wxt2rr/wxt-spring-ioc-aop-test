import com.alibaba.druid.pool.DruidPooledConnection;
import core.util.DataSourceUtil;
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
}
