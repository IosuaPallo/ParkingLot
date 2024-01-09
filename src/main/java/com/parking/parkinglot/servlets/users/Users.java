package com.parking.parkinglot.servlets.users;

import com.parking.parkinglot.common.UserDTO;
import com.parking.parkinglot.ejb.InvoiceBean;
import com.parking.parkinglot.ejb.UserGroupsBean;
import com.parking.parkinglot.ejb.UsersBean;
import com.parking.parkinglot.entities.User;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DeclareRoles({"READ_USERS","WRITE_USERS", "INVOICING"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
    httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS", "INVOICING"})})
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    UserGroupsBean userGroupsBean;
    @Inject
    InvoiceBean invoiceBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<UserDTO> users = usersBean.findAllUsers();
        request.setAttribute("users",users);
        request.setAttribute("numberOfUsers",users.size());


        Principal principal = request.getUserPrincipal();
        boolean hasInvoicingUserGroup = userGroupsBean.hasInvoicingGroup(principal.getName());
        if(hasInvoicingUserGroup) {
            if (!invoiceBean.getUserIds().isEmpty()) {
                Collection<String> usernames = usersBean.findUsernamesByUserIds(invoiceBean.getUserIds());
                request.setAttribute("invoices", usernames);
            }
        }
        else{
            request.setAttribute("invoices","");
        }

        request.getRequestDispatcher("/WEB-INF/pages/users/users.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String[] userIdsAsString = request.getParameterValues("user_ids");
        if(userIdsAsString!=null){
            List<Long> userIds = new ArrayList<>();
            for (String userIdAsString:userIdsAsString){
                userIds.add(Long.parseLong(userIdAsString));
            }
            invoiceBean.getUserIds().addAll(userIds);
        }
        response.sendRedirect(request.getContextPath()+"/Users");
    }
}