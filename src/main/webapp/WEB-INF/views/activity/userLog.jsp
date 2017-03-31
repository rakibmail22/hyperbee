<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title><fmt:message key="activity.view.title"/></title>
</head>
<body>
<h2>Activity log for <c:out value="${user.username}"/></h2>

<div class="pre-scrollable set-activity-height">
    <ul class="list-group">
        <c:forEach items="${activityList}" var="activity">
            <li class="list-group-item list-group-item-warning"><c:out value="${activity.getDateAndTime()}"/></li>
            <li class="list-group-item"><c:out value="${activity.summary}"/></li>
        </c:forEach>
    </ul>
</div>
</body>
