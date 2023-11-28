<%--
  Created by IntelliJ IDEA.
  User: pallo
  Date: 11/14/2023
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cars">

    <h1>Cars</h1>
    <div class="container text-center">
        <c:forEach var="car" items="${cars}">
            <div class="row">
                <div class="col">
                    ${car.licensePlate}
                </div>
                <div class="col">
                    ${car.parkingSpot}
                </div>
                <div class="col">
                    ${car.ownerName}
                </div>
               <div class="col">
                   <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditCar?id=${car.id}">Edit Car</a>
               </div>
            </div>
        </c:forEach>
    </div>
        <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
    <a href="${pageContext.request.contextPath}/AddCar" class="btn btn-primary btn-lg">Add Car</a>
</t:pageTemplate>