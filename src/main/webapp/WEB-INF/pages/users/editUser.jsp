<%--
  Created by IntelliJ IDEA.
  User: pallo
  Date: 1/9/2024
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Add Car">
    <h1>Add Car</h1>
    <form class="needs-validation" method="POST" action="${pageContext.request.contextPath}/EditUser">
        <div class="row">
            <div class="col-md-6 mb-3 ">
                <div class="p-5">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username"
                           placeholder="" value="${user.username}" required>
                    <div class="invalid-feedback">
                       Username is required
                    </div>
                </div>

                <div class="p-5">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" name="email"
                           placeholder=""
                           value="${user.email}" required>
                    <div class="invalid-feedback">
                        Email is required
                    </div>
                </div>

                <div class="p-5">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder=""
                           value="">
                </div>

            </div>
            <hr class="mb-4">
            <input  type="hidden" name="user_id" value="${user.id}"/>
            <button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
        </div>
    </form>
</t:pageTemplate>
