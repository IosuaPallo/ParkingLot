package com.parking.parkinglot.servlets.users;

import com.parking.parkinglot.common.UserDTO;
import com.parking.parkinglot.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {

    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        Long userId = Long.parseLong(request.getParameter("id"));
        UserDTO userDTO = usersBean.findById(userId);
        request.setAttribute("user",userDTO);
        request.getRequestDispatcher("/WEB-INF/pages/users/editUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("user_id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        usersBean.updateUser(userId,username,email,password);
        response.sendRedirect(request.getContextPath()+"/Users");
    }
}