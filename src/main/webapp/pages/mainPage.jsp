<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Messenger</title>
</head>
<body>
<h1>Hello there</h1>

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

</body>
</html>