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
    <form method="POST" action="${pageContext.request.contextPath}/Cars">
        <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
            <a href="${pageContext.request.contextPath}/AddCar" class="btn btn-primary btn-lg">Add Car</a>
            <button class="btn btn-danger" type="submit">Delete Cars</button>
        </c:if>
        <div class="container text-center">
            <c:forEach var="car" items="${cars}">
                <div class="row">
                    <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                        <div class="col">
                            <input type="checkbox" name="car_ids" value="${car.id}"/>
                        </div>
                    </c:if>
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
                        <img src="${pageContext.request.contextPath}/CarPhotos?id=${car.id}" width="48"/>
                    </div>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                        <div class="col">
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddCarPhoto?id=${car.id}">Add Photo</a>
                        </div>
                    </c:if>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                        <div class="col">
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditCar?id=${car.id}">Edit
                                Car</a>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </form>
    <h5>Max parking spots: ${maxParkingSpots}</h5>
    <h5>Remaining parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>