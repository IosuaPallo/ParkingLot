<%--
  Created by IntelliJ IDEA.
  User: pallo
  Date: 11/28/2023
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><%--
  Created by IntelliJ IDEA.
  User: pallo
  Date: 11/28/2023
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Add Car">
    <h1>Add Car</h1>
    <form class="needs-validation" method="POST" action="${pageContext.request.contextPath}/EditCar">
        <div class="row">
            <div class="col-md-6 mb-3 ">
                <div class="p-5">
                    <label for="license_plate">License Plate</label>
                    <input type="text" class="form-control" id="license_plate" name="license_plate"
                           placeholder="" value="${car.licensePlate}" required>
                    <div class="invalid-feedback">
                        License Plate is required
                    </div>
                </div>

                <div class="p-5">
                    <label for="parking_spot">Parking Spot</label>
                    <input type="text" class="form-control" id="parking_spot" name="parking_spot"
                           placeholder=""
                           value="${car.parkingSpot}" required>
                    <div class="invalid-feedback">
                        Parking Spot is required
                    </div>
                </div>


                <div class="p-5">
                    <label for="owner_id">Owner Id</label>
                    <select class="form-select" name="owner_id" id="owner_id" required>
                        <option value="">Choose...</option>
                        <c:forEach var="user" items="${users}" varStatus="status">
                            <option value="${user.id}" ${car.ownerName eq user.username ? 'selected' : ''}>${user.username}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                        Please select an owner
                    </div>
                </div>
            </div>
            <hr class="mb-4">
            <input  type="hidden" name="car_id" value="${car.id}"/>
            <button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
        </div>
    </form>
</t:pageTemplate>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
