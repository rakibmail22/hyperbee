<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
 * @author rumman
 * @since 11/22/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title><fmt:message key="notice.html.title"/></title>
</head>
<body>

<div class="container-fluid">
    <c:if test="${isAdmin}">
        <a href="${pageContext.request.contextPath}${noticeAddUrl}" class="btn btn-success pull-right"><fmt:message key="notice.list.btn.text"/></a>
    </c:if>
</div>
<br/>

<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th><fmt:message key="notice.form.title"/></th>
            <th><fmt:message key="notice.form.description"/></th>
            <th><fmt:message key="notice.list.creator"/></th>
            <th><fmt:message key="notice.form.Hives"/></th>

            <c:if test="${isAdmin}">
                <th><fmt:message key="notice.list.edit"/></th>
                <th><fmt:message key="notice.list.delete"/></th>
            </c:if>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="notice" items="${noticeList}">
            <tr>
                <td>${notice.title}</td>
                <td>${notice.description}</td>
                <td>${notice.user.username}</td>
                <td>
                    <c:forEach var="hive" items="${notice.hiveList}">
                        ${hive.name}
                    </c:forEach>
                </td>
                <c:if test="${isAdmin}">
                    <td><a href="${pageContext.request.contextPath}/notice/${notice.id}/"><span
                            class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a href="#" data-id="${notice.id}"
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
                <h4 class="modal-title" id="myModalLabel"><fmt:message key="notice.list.modal.confirm"/></h4>
            </div>

            <div class="modal-body">
                <p><fmt:message key="notice.list.modal.p1"/></p>
                <p><fmt:message key="notice.list.modal.p2"/></p>

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