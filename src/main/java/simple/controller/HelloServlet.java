package simple.controller;

import simple.service.HelloService;
import simple.service.impl.HelloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    private HelloService helloService = new HelloServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get execute");

        String result = helloService.ping();

        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.close();

        System.out.println("get over");
    }
}
