<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title><fmt:message key="activity.view.title"/></title>
</head>
<body>
<c:choose>
    <c:when test="${authUser.isAdmin()}">
        <h2>Select user whose activity log you want to view:</h2>
        <form:form action="/activity/log" method="post" modelAttribute="userInfo">
            <c:forEach items="${userInfo.userList}" var="user">
                <div class="input-group">
                    <span class="input-group-addon">
                        <form:radiobutton value="${user.id}" path="userId" label=""/>
                    </span>
                    <label class="form-control" aria-label="..."><c:out value="${user.username}"/></label>
                </div>
            </c:forEach>
            <br>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </c:when>
    <c:otherwise>
        <h2>Here are your activities: </h2>

        <div class="pre-scrollable set-activity-height">
            <ul class="list-group">
                <c:forEach items="${activityList}" var="activity">
                    <li class="list-group-item list-group-item-warning"><c:out
                            value="${activity.getDateAndTime()}"/></li>
                    <li class="list-group-item"><c:out value="${activity.summary}"/></li>
                </c:forEach>
            </ul>
        </div>
    </c:otherwise>
</c:choose>
</body>