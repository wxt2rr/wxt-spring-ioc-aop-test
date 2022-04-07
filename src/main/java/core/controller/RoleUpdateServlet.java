package core.controller;

import core.factory.BeanFactory;
import core.pojo.ResultVo;
import core.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "role", urlPatterns = "/update")
public class RoleUpdateServlet extends HttpServlet {

    private RoleService roleService;

    {
        roleService = (RoleService) BeanFactory.getBean("roleService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get execute");

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        ResultVo resultVo = roleService.update(id , name);

        PrintWriter writer = resp.getWriter();
        writer.write(resultVo.toString());
        writer.close();

        System.out.println("get over");
    }
}
