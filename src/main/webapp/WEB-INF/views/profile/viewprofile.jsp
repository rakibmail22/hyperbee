<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="create.profile.title"/></title>
</head>
<body>
<div class="col-lg-10 container"
     style="background-image: url(http://localhost:8080/profile/cover/${profile.coverImage})">
    <div class="row">
        <div class="col-lg-2 container" style="padding-top: 150px">
            <c:choose>
                <c:when test="${empty profile.imagePath}">
                    <img src="/images/dummyprofilepic.png" class="img-thumbnail" alt="Cinque Terre"
                         width="200" height="200"/>
                </c:when>
                <c:otherwise>
                    <img src="/profile/image/${profile.imagePath}" class="img-thumbnail" alt="Cinque Terre"
                         width="200" height="200"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div class="col-lg-10 container" style="padding-top: 10px">
    <table>
        <tr>
            <td>
                <h1 style="color: #269abc; font-family: 'Glyphicons Halflings'">
                    <b>${user.firstName} ${user.lastName}</b>
                </h1>
            </td>
            <td style="padding-left: 650"><a href="/profile/edit" class=" btn navbar-btn"/>
                <fmt:message key="profile.edit.button"/>
            </td>
        </tr>
    </table>
</div>
<div class="row">
    <div class="col-lg-5">
        <div class="panel panel-default" style="padding: 20px">
            <div class="panel-heading">
                <h3><b><fmt:message key="profile.show.general"/></b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="user.first.name"/></th>
                        <td class="form-control-static">${user.firstName}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="user.last.name"/></th>
                        <td class="form-control-static">${user.lastName}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.designation"/></th>
                        <td class="form-control-static">${profile.designation}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="user.email"/></th>
                        <td class="form-control-static">${user.email}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.address"/></th>
                        <td class="form-control-static">${profile.address}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.contact"/></th>
                        <td class="form-control-static">${profile.contactNo}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.joining.date"/></th>
                        <td class="form-control-static"><fmt:formatDate type="date" value="${profile.joiningDate.time}"
                                                                        pattern="dd-MM-yy"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="col-lg-5 panel panel-default" style="padding: 10px">
        <div class="panel-heading">
            <h3><b><fmt:message key="profile.show.education"/></b></h3>
        </div>
        <div class="panel-body">
            <table>
                <tr>
                    <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.school"/></th>
                    <td class="form-control-static">${profile.school}</td>
                </tr>
                <tr>
                    <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.college"/></th>
                    <td class="form-control-static">${profile.college}</td>
                </tr>
                <tr>
                    <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.university"/></th>
                    <td class="form-control-static">${profile.university}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-5">
        <div class="panel panel-default" style="padding: 10px">
            <div class="panel-heading">
                <h3><b><fmt:message key="profile.show.basic.info"/></b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.gender"/></th>
                        <td class="form-control-static">${profile.gender}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.dateofbirth"/></th>
                        <td class="form-control-static"><fmt:formatDate type="date" value="${profile.dateOfBirth.time}"
                                                                        pattern="dd-MM-yy"/></td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.skills"/></th>
                        <td class="form-control-static">${profile.skills}</td>
                    </tr>

                </table>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="panel panel-default" style="padding: 10px">
            <div class="panel-heading">
                <h3><b><fmt:message key="profile.show.workexperience"/></b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message
                                key="profile.yearsofexperience"/></th>
                        <td class="form-control-static">${profile.jobExperienceYears}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.workhistory"/></th>
                        <td class="form-control-static">${profile.workHistory}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
