<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <title><fmt:message key="dashboard.view.title"/></title>
    <script>
        function refreshList() {
            $("#buzzList").load("/buzz/buzzList")
        }

        $(document).ready(function () {
            $("#buzzList").load("/buzz/buzzList")
            setInterval(refreshList, 5000);
        });
    </script>
</head>

<body>
<div class="container-fluid">
    <div id="buzzMain" class="panel panel-primary">
        <div class="panel-heading">
            <h3><b><i><fmt:message key="buzz.view.label.title"/></i></b></h3>
        </div>

        <div class="panel-body">
            <div id="buzzList"></div>
            <br>
            <form:form id="buzzMessageForm" action="/buzz/sendBuzz" method="POST" modelAttribute="newBuzz">
            <form:input path="message" placeholder="Enter your message..." cssStyle="width: 94%"/>
            <input type="submit" class="btn btn-primary" value="Send"/>
        </div>
        <div class="panel-footer clearfix">
            <form:errors path="message" cssClass="alert-danger"/>
            <c:if test="${authUser.isAdmin()}">
                <a href="/buzz/buzzHistory?prev=0&next=20" class="btn btn-info" role="button" style="float:right">
                    <span class="glyphicon glyphicon glyphicon-time"/>
                </a>
            </c:if>
            </form:form>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${topStickyNote}" var="item">
            <div class="container-fluid col-lg-3">
                <div class="panel panel-warning" style="height: 150px">
                    <div class="panel-heading clearfix">
                        <small><i><cite>${item.noteType} ${item.getRemindDateFormatted()}</cite></i></small>
                    </div>

                    <div class="panel-body">
                        <strong>${item.title}</strong>
                        <c:choose>
                            <c:when test="${fn:length(item.description) gt 102}">
                                <p>${fn:substring(item.description, 0, 102)}...
                                    <a href="/notes#${item.id}"><strong><fmt:message key="dashboard.view.note.readMore"/></strong></a>
                                </p>
                             </c:when>
                            <c:when test="${fn:length(item.description) lt 103}">
                                <p>${item.description}</p>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="row">
        <c:forEach items="${latestReminders}" var="item">
            <div class="container-fluid col-lg-3">
                <div class="panel panel-warning" style="height: 150px">
                    <div class="panel-heading clearfix">
                        <small><i><cite>${item.noteType} ${item.getRemindDateFormatted()}</cite></i></small>
                    </div>

                    <div class="panel-body">
                        <strong>${item.title}</strong>
                        <c:choose>
                            <c:when test="${fn:length(item.description) gt 102}">
                                <p>${fn:substring(item.description, 0, 102)}...
                                    <a href="/notes"><strong><fmt:message key="dashboard.view.note.readMore"/></strong></a>
                                </p>
                             </c:when>
                            <c:when test="${fn:length(item.description) lt 103}">
                                <p>${item.description}</p>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>


