<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
 * @author rumman
 * @since 11/22/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<head>
    <title><fmt:message key="conference.html.title"/></title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form:form class="form-signin" method="post" action="${pageContext.request.contextPath}${action}"
                       modelAttribute="conferenceRoom">
                <h2 class="form-signin-heading">${pageHeader}</h2>

                <form:hidden path="id"/>

                <div class="form-group">
                    <label for="title"><fmt:message key="conference.title"/></label>
                    <form:input type="text" id="title" class="form-control" placeholder="Conference Room Title"
                                name="title" path="title" required="required" autofocus="autofocus"/>
                    <form:errors path="title" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label for="capacity"><fmt:message key="conference.capacity"/></label>
                    <form:input type="text" id="capacity" class="form-control" name="Capacity" placeholder="Capacity"
                                path="capacity" required="required"/>
                    <form:errors path="capacity" cssClass="error"/>
                </div>

                <div class="form-group">
                    <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
                </div>
            </form:form>

        </div>
    </div>
</div>
<!-- /container -->
</body>
