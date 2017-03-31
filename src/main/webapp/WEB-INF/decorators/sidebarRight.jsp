<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-2 sidenav top-padding text-left">

    <c:if test="${cachedReservationList.size() > 0}">
        <h2><fmt:message key="reservation.sidebar.header"/></h2>
    </c:if>

    <c:forEach var="reservation" items="${cachedReservationList}">
        <div class="panel-group">

            <div class="panel panel-info">
                <div class="panel-heading">${reservation.conferenceRoom.title}</div>
                <div class="panel-body">
                    <div><fmt:message key="reservation.sidebar.reservedBy"/> ${reservation.user.username}</div>
                    <div><fmt:message key="reservation.sidebar.From"/> ${reservation.getFormattedFromDate()}</div>
                    <div><fmt:message key="reservation.sidebar.To"/> ${reservation.getFormattedToDate()}</div>
                </div>
            </div>

        </div>
    </c:forEach>

    <c:if test="${cachedNoticeList.size() > 0}">
        <h2><fmt:message key="notice.sidebar.header"/></h2>
    </c:if>

    <c:forEach var="notice" items="${cachedNoticeList}">
        <div class="panel-group">

            <div class="panel panel-info">
                <div class="panel-heading">${notice.title}</div>
                <div class="panel-body">${notice.description}</div>
                <div class="panel-footer text-right"><fmt:message key="reservation.sidebar.expiresOn"/> ${notice.getRemindDateFormatted()}</div>
            </div>

        </div>
    </c:forEach>

</div>
