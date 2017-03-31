<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="create.profile.title"/></title>
</head>
<body>
<form:form action="/profile" method="post" commandName="profile" cssClass=" panel-primary"
           enctype="multipart/form-data">
    <table>
        <tr>
            <th class="panel-heading">
                <h1 style="color: #269abc; font-family: 'Glyphicons Halflings'">
                    <b>${user.firstName} ${user.lastName}</b>
                </h1>
            </th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.designation"/></th>
            <td><form:input path="designation" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.address"/></th>
            <td><form:input path="address" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.contact"/></th>
            <td><form:input path="contactNo" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.joining.date"/></th>
            <td><form:input path="joiningDate" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc"><fmt:message key="profile.show.education"/></h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.school"/></th>
            <td><form:input path="school" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.college"/></th>
            <td><form:input path="college" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.university"/></th>
            <td><form:input path="university" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc"><fmt:message key="profile.show.basic.info"/></h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.gender"/></th>
            <td><form:input path="gender" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.dateofbirth"/></th>
            <td><form:input path="dateOfBirth" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.skills"/></th>
            <td><form:input path="skills" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc"><fmt:message key="profile.show.workexperience"/></h2>
            </th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.yearsofexperience"/></th>
            <td><form:input path="jobExperienceYears" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.workhistory"/></th>
            <td><form:input path="workHistory" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.profileimage"/></th>
            <td><input type="file" name="file" id="fileName"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40"><fmt:message key="profile.coverimage"/></th>
            <td><input type="file" name="coverFile"/></td>
        </tr>
        <tr>
            <td colspan="2" align="left" class="pull-right"><input type="submit" class="btn btn-warning btn-sm" value="Edit"></td>
        </tr>
        <tr>
            <td style="color: green">${message}</td>
        </tr>
        <tr>
            <td style="color: green">${message2}</td>
        </tr>
        <tr>
            <td style="color: green">${message3}</td>
        </tr>
    </table>
</form:form>

</body>
</html>
