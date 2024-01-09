<%@tag description="base page template" pageEncoding="UTF-8" %>
<%@attribute name="pageTitle" %>
<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</head>
<body>
<main class="container-fluid mt-5">
    <jsp:include page="/WEB-INF/pages/menu.jsp"/>
    <jsp:doBody/>
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</main>
<script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</body>
</html>