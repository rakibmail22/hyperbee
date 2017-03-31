<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
 * @author rumman
 * @since 11/22/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<head>
    <title><fmt:message key="notice.html.title"/></title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form:form class="form-signin" method="post" action="${pageContext.request.contextPath}${action}"
                       modelAttribute="notice">
                <h2 class="form-signin-heading">${noticeHeader}</h2>

                <form:hidden path="id"/>

                <div class="form-group">
                    <label for="title"><fmt:message key="notice.form.title"/></label>
                    <form:input type="text" id="title" class="form-control" placeholder="Notice Title" name="title"
                                path="title" required="required" autofocus="autofocus"/>
                    <form:errors path="title" cssClass="alert alert-danger"/>
                </div>

                <div class="form-group">
                    <label for="description"><fmt:message key="notice.form.description"/></label>
                    <form:textarea class="form-control" rows="5" id="description" path="description" name="description"
                                   required="required"></form:textarea>
                    <form:errors path="description" cssClass="alert alert-danger"/>
                </div>

                <div class="form-group">
                    <label for="displayStatus"><fmt:message key="notice.form.displayStatus"/></label>
                    <form:select path="displayStatus" id="displayStatus" class="form-control">
                        <form:options items="${displayStatusOptions}" itemValue="status" itemLabel="status"/>
                    </form:select>
                </div>

                <div class="form-group input-group date" id='datetimepicker1'>
                    <label for="dateExpired"><fmt:message key="notice.form.ExpiryDate"/></label>
                    <form:input onkeydown="return false;" type="text" id="dateExpired" class="form-control"
                                name="dateExpired" placeholder="dd-MM-yy"
                                path="dateExpired" required="required"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    <form:errors path="dateExpired" cssClass="alert alert-danger"/>
                </div>

                <div class="form-group">
                    <label for="hiveList"><fmt:message key="notice.form.Hives"/></label>
                    <form:select multiple="true" path="hiveList" id="hiveList" class="form-control">
                        <form:options items="${hiveList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="hiveList" cssClass="alert alert-danger"/>
                </div>

                <div class="form-group">
                    <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
                </div>
            </form:form>

        </div>
    </div>
</div>
<!-- /container -->

<script>
    $(function () {
        $('#datetimepicker1').datetimepicker({
            minDate: moment(),
            format: 'DD-MM-YY'
        });
    });
</script>
</body>