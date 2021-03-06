package com.eason.web.servlet;

import com.eason.dao.impl.UserDaoImpl;
import com.eason.domain.User;
import com.eason.service.UserService;
import com.eason.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String verifycode = req.getParameter("verifycode");
        HttpSession session = req.getSession();
        String generatedVerifyCode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");


        if(!generatedVerifyCode.equalsIgnoreCase(verifycode)){
            req.setAttribute("login_msg","VerifyCodeWrong!");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }

        Map<String, String[]> map = req.getParameterMap();
        User loginUser = new User();


        try {
            BeanUtils.populate(loginUser,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        User user = userService.login(loginUser);

        if(user == null){
            req.setAttribute("login_msg","Username/Password Wrong");
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else{

            session.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
