<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:useBean id="chats" scope="request" type="java.util.List<ru.anxidy.entities.Account>"/>
<html>
<head>
    <title>Messenger</title>
</head>
<h1>Your dialogues</h1>
<body>
<table>
    <thead>
    <tr>
        <th>Last dialogues:</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${chats}" var="chat">
        <tr>
            <th>
                <form method="get" action="${pageContext.request.contextPath}/chat_${chat.id}">
                    <input name="${chat.name}" type="button">
                </form>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
