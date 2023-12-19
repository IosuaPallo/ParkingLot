package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarPhotoDto;
import com.parking.parkinglot.ejb.CarsBean;
import com.parking.parkinglot.entities.CarPhoto;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CarPhotos", value = "/CarPhotos")
public class CarPhotos extends HttpServlet {
    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer carId = Integer.parseInt(request.getParameter("id"));
        CarPhotoDto photoDto = carsBean.findPhotoByCarId(carId);
        if(photoDto!=null){
            response.setContentType(photoDto.getFileType());
            response.setContentLength(photoDto.getFileContent().length);
            response.getOutputStream().write(photoDto.getFileContent());
        }
        else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}