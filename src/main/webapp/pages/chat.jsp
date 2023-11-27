<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:useBean id="messages" scope="request" type="java.util.List<ru.anxidy.entities.Message>"/>
<jsp:useBean id="chat" scope="request" type="ru.anxidy.entities.Chat"/>
<html>
<head>
    <title>Messenger</title>
</head>
<body>
<p>Chat ${chat.name}:</p>

<c:forEach items="${messages}" var="message">
    <p>${message.sender.login} ${message.time}</p>
    <p>${message.messageBody}</p>
</c:forEach>

<form method="post" action="${pageContext.request.contextPath}/chat">
    <label>
        <input type="text" name="messageBody">
        <input type="hidden" name="">
        <input type="hidden" name="ch" value=${chat.id}>
        <button type="submit">Send</button>
    </label>
</form>

</body>
</html>
