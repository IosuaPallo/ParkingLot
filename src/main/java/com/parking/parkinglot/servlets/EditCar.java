package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarDTO;
import com.parking.parkinglot.common.UserDTO;
import com.parking.parkinglot.ejb.CarsBean;
import com.parking.parkinglot.ejb.UsersBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@DeclareRoles({"WRITE_CARS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_CARS"}))
@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get users
        List<UserDTO> users = usersBean.findAllUsers();
        request.setAttribute("users",users);

        //get car id - parameter

        Long carId = Long.parseLong(request.getParameter("id"));
        CarDTO car = carsBean.findById(carId);
        request.setAttribute("car",car);

        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long userId = Long.parseLong(request.getParameter("owner_id"));
        Long carId = Long.parseLong(request.getParameter("car_id"));

        carsBean.updateCar(carId,licensePlate,parkingSpot,userId);
        response.sendRedirect(request.getContextPath()+"/Cars");
    }
}