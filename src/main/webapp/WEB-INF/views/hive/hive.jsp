<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="hive.title"/></title>
</head>
<body>
<div class="col-lg-4">
    <div class="panel panel-info">
        <div class="panel-heading"><h3><fmt:message key="hive.list"/></h3>
        </div>
        <div class="panel-body">
            <table>
                <tr>
                    <td><c:forEach items="${hiveList}" var="item">
                        <a href="/user/hive/show/${item.id}"> <c:out value="${item.name}"/></a><br>
                        <br>
                    </c:forEach>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="col-lg-8">
    <div class="panel panel-success">
        <div class="panel-heading"><h3><fmt:message key="hive.form"/></h3></div>
        <div class="panel-body">
            <form:form action="/user/hive/create" method="post" commandName="hive" enctype="multipart/form-data">
                <div class="form-group">
                    <form:input path="name" type="text" name="hiveName" class="form-control" placeholder="Hive Name"/>
                    <form:errors path="name" cssClass="alert-danger"/>
                </div>
                <div class="form-group">
                    <form:textarea class="form-control" path="description" type="text" placeholder="Hive Description"/>
                    <form:errors path="description" cssClass="alert-danger"/>
                </div>
                <div class="form-group">
                    <input path="imagePath" type="file" name="file" size="50"/>

                    <p style="color: #ff0000">${fileError}</p>
                </div>
                <div class="btn-group pull-right">
                    <form:button type="submit" class="btn btn-primary" value="Upload"><fmt:message
                            key="hive.form.button"/></form:button>
                </div>
            </form:form>
        </div>
        <p>${message}</p>
    </div>
</div>
</body>
</html>
