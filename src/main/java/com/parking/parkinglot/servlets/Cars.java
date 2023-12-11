package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarDTO;
import com.parking.parkinglot.ejb.CarsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Cars", value = "/Cars")
public class Cars extends HttpServlet {
    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<CarDTO> cars = carsBean.findAllCars();
        System.out.println(cars);
        request.setAttribute("cars", cars);
        request.setAttribute("numberOfFreeParkingSpots",10-cars.size());
        request.setAttribute("activePage","Cars");
        request.getRequestDispatcher("/WEB-INF/pages/cars.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] carIdsString = request.getParameterValues("car_ids");
        if(carIdsString!=null){
            List<Long> carIds = new ArrayList<>();
            for(String carIdString :carIdsString){
                carIds.add(Long.parseLong(carIdString));
            }
            carsBean.deleteCarsByIds(carIds);
        }
        response.sendRedirect(request.getContextPath()+"/Cars");
    }
}