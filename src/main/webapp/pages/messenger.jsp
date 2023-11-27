<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:useBean id="chats" scope="request" type="java.util.List<ru.anxidy.entities.Chat>"/>
<html>
<head>
    <title>Messenger</title>
</head>
<h1>Welcome</h1>
<body>
<p>Your last chats:</p>

<c:forEach items="${chats}" var="chat">
    <form method="get" action="${pageContext.request.contextPath}/chat">
        <p><button type="submit" name="ch" value="${chat.id}">${chat.name}</button></p>
    </form>
</c:forEach>

</body>
</html>
