<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>All Notes</title>

    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker({
                minDate: moment()
            });
        });
    </script>


    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>


</head>

<body>

<form:form action="/note/save" method="post" commandName="noteCommand">
    <div class="panel panel-success">
        <div class="panel-heading clearfix">
            <h4 class="panel-title pull-left" style="padding-top: 7.5px;"><fmt:message key="notes.view.label.add"/></h4>

            <div class='pull-right col-sm-2' style="width: 18.20%">

                <div class='input-group date' id='datetimepicker1'>
                    <input name="dateRemindString" type="text" class="form-control"
                           placeholder="<fmt:message key="notes.view.label.rememberMe"/>"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>

            </div>

        </div>
        <div class="panel-body">
            <form:input id="title" type="text" placeholder="Title" class="form-control"
                        path="title"/>
            <form:input id="description" type="text" placeholder="Description" class="form-control"
                        path="description"/>
        </div>
        <div class="panel-footer clearfix">
            <div class="btn-group pull-right">
                <button class="btn btn-warning btn-sm" type="submit"><fmt:message key="notes.view.label.save"/></button>
            </div>
            <form:errors path="description" cssClass="error"/>
            <form:errors path="title" cssClass="error"/>
        </div>
    </div>
</form:form>
<div class="pre-scrollable set-activity-height">
    <c:forEach items="${noteList}" var="item">
        <form:form action="/note/delete/${item.getNoteTypeAsString()}/${item.id}" method="post">
            <div class="panel panel-warning" id="${item.id}">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title pull-left" style="padding-top: 7.5px;"><strong>${item.title}</strong></h4>

                    <div class="btn-group pull-right">
                        <button class="btn btn-default btn-sm" type="submit"><fmt:message
                                key="notes.view.label.delete"/></button>
                    </div>
                </div>
                <div class="panel-body">${item.description}</div>
                <div class="panel-footer clearfix">
                    <div class="pull-right">
                        <small><i><cite>${item.noteType} ${item.getRemindDateFormatted()}</cite></i></small>
                    </div>
                </div>
            </div>
        </form:form>
    </c:forEach>
</div>

</body>
</html>
