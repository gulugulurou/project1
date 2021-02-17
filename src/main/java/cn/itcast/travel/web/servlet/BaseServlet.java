package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("baseServlet的service办法被执行了。。。");
        //完成方法的分发
        //1.获取请求路径
        String uri = req.getRequestURI();
        System.out.println("请求的uri:" + uri);  //travel/user/add
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("方法名称:" + methodName);
        //3.获取方法对象的method
        try {
            //忽略访问权限修饰符的方式获取类的方法对象
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //设置暴力反射
//            method.setAccessible(true);
            //执行类的方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            System.out.println("我是NoSuchMethodException:" + methodName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("我是IllegalAccessException:" + methodName);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("我是InvocationTargetException:" + methodName);
            e.printStackTrace();
        }
        //4.执行方法
    }

    /**
     * writeValue（参数，obj）：直接将传入的对象序列化为json，并且返回给客户端
     *
     * @param obj
     * @param response
     */
    public void writeValue(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        //输出流getoutputstream,用于向socket端发送数据,就是发送消息用的
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * writeValueAsString（obj）：将传入的对象序列化为json，返回给调用者
     *
     * @param obj
     * @return
     */
    public String writeAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
