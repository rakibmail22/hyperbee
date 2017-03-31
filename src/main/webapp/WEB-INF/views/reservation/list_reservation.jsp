<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
 * @author rumman
 * @since 11/29/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title><fmt:message key="reservation.html.title"/></title>
</head>
<body>

<div class="container-fluid">
    <c:if test="${authUser.isAdmin()}">
        <a href="${pageContext.request.contextPath}${actionUrl}" class="btn btn-success pull-right"><fmt:message
                key="reservation.list.btn.text"/></a>
    </c:if>
</div>
<br/>

<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th><fmt:message key="reservation.conferenceRoom"/></th>
            <th><fmt:message key="reservation.byUsername"/></th>
            <th><fmt:message key="reservation.from"/></th>
            <th><fmt:message key="reservation.to"/></th>
            <th><fmt:message key="reservation.displayStatus"/></th>

            <c:if test="${authUser.isAdmin()}">
                <th><fmt:message key="reservation.edit"/></th>
                <th><fmt:message key="reservation.delete"/></th>
            </c:if>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="reservation" items="${reservationList}">
            <tr>
                <td>${reservation.conferenceRoom.title}</td>
                <td>${reservation.user.username}</td>
                <td>${reservation.getFormattedFromDate()}</td>
                <td>${reservation.getFormattedToDate()}</td>
                <td>${reservation.reservationStatus}</td>

                <c:if test="${authUser.isAdmin()}">
                    <td><a href="${pageContext.request.contextPath}/reservation/${reservation.id}/"><span
                            class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a href="#" data-id="${reservation.id}"
                           data-toggle="modal" data-target="#confirm-delete"
                           class="delete-user-item"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </c:if>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<!--Modal Start -->
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><fmt:message key="reservation.list.modal.confirm"/></h4>
            </div>

            <div class="modal-body">
                <p><fmt:message key="reservation.list.modal.p1"/></p>

                <p><fmt:message key="reservation.list.modal.p2"/></p>

                <p class="debug-url"></p>
            </div>

            <div class="modal-footer">
                <form action="${deleteUrl}" method="post" id="form">
                    <input type="hidden" id="delete_id" name="id" value="#"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <input type="submit" class="btn btn-danger btn-ok" value="Delete"/>
                </form>
            </div>
        </div>
    </div>
</div>
<!--Modal END -->

<script>
    $('.delete-user-item').on('click', function (e) {
        var criteria_id = $(this).attr("data-id");
        $('#delete_id').val(criteria_id);
    });
</script>
</body>