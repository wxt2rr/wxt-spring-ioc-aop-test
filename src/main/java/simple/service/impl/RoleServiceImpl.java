package simple.service.impl;

import simple.dao.RoleDao;
import simple.pojo.ResultVo;
import simple.pojo.RoleDto;
import simple.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    // 最原始方式，代码耦合严重
    //private RoleDao roleDao = new RoleDaoImpl();

    // 通过beanFactory获取指定的bean,不够优雅,想直接通过声明的方式
    //private RoleDao roleDao = (RoleDao) BeanFactory.getBean("roleDao");

    // 直接声明依赖对象，怎么将对象初始化呢，暂时通过代码块
    private RoleDao roleDao;

    //{
    //    roleDao = (RoleDao) BeanFactory.getBean("roleDao");
    //}

    // 通过代码块方式还是需要根据对应的名称或者id获取对应的对象，能不能不将id写死到代码中，我们改为通过set或者构造方法对依赖对象赋值

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public ResultVo queryRole() {
        List<RoleDto> list = roleDao.queryRoleList();

        return new ResultVo(200, "success", list);
    }

    @Override
    public ResultVo update(String id, String name) {
        Boolean result = false;
        try{
            // 开启事务
            //ConnUtil.getInstance().get().setAutoCommit(false);
           // TransactionUtil.open();

            int i = roleDao.updateById(Integer.parseInt(id), name);

            // 模拟异常，这里两个update应该在同一个事务里边，怎么办？所以需要将数据库层面的事务上升到service层来控制
            // 数据库执行sql时，每个conn是一个事务，所以我们需要将两个update的conn使用同一个，怎么使用同一个？可以将获取conn的代码提取出来，
            // 每个service请求（线程）对应一个conn
            int temp = 1 / 0;

            int i1 = roleDao.updateById(Integer.parseInt(id), name + "v2");

            result = i == i1 && i == 1;

            // 提交事务
            //ConnUtil.getInstance().get().commit();
          //  TransactionUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            // 回滚事务
            try {
                //ConnUtil.getInstance().get().rollback();
            //    TransactionUtil.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }finally {
            // 关闭链接
            try {
                //ConnUtil.getInstance().get().close();
            //    TransactionUtil.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ResultVo(200, "success", result);
    }
}
