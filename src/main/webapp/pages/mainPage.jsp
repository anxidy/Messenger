<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Messenger</title>
</head>
<body>
<h1>Hello there</h1>

<c:if test="${empty sessionScope['accountId']}">
<form method="post" action="${pageContext.request.contextPath}/login">
    <p>Login:</p>
    <p><label>
        <input type="text" name="login" value="${param['login']}">
    </label></p>
    <p>Password:</p>
    <p><label>
        <input type="password" name="password">
    </label></p>
    <p><input type="submit" name="submit"></p>
</form>
</c:if>

<c:if test="${not empty sessionScope['accountId']}">
<p><a href="${pageContext.request.contextPath}/messenger"></a></p>
</c:if>

<c:if test="${not empty param['login']}">
    <p class="error" style="color: crimson">
        Wrong login or password!
    </p>
</c:if>

</body>
</html>