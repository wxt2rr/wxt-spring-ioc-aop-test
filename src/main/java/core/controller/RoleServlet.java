package core.controller;

import core.factory.BeanFactory;
import core.pojo.ResultVo;
import core.service.RoleService;
import core.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "role", urlPatterns = "/role")
public class RoleServlet extends HttpServlet {

    //private RoleService roleService = new RoleServiceImpl();

    //private RoleService roleService = (RoleService) BeanFactory.getBean("roleService");

    private RoleService roleService;

    {
        roleService = (RoleService) BeanFactory.getBean("roleService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get execute");

        ResultVo resultVo = roleService.queryRole();

        PrintWriter writer = resp.getWriter();
        writer.write(resultVo.toString());
        writer.close();

        System.out.println("get over");
    }
}
