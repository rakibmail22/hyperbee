<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="../header.jsp" %>
<div class="container">
    <form:form class="form-signin" action="/signUp" method="POST" modelAttribute="signUp">
        <h2 class="form-signin-heading">Please sign up</h2>
        <form:errors path="*" element="div" cssClass="alert alert-danger fade in"/>
        <form:input type="text" class="form-control" path="firstName" placeholder="First Name"/>
        <form:input type="text" class="form-control" path="lastName" placeholder="Last Name"/>
        <form:input type="text" class="form-control" path="username" placeholder="Username"/>
        <form:input type="email" class="form-control" path="email" placeholder="Email"/>
        <form:input type="password" class="form-control" path="password1" placeholder="Enter Password"/>
        <form:input type="password" class="form-control" path="password2" placeholder="Confirm Password"/>
        <form:button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</form:button>
    </form:form>
</div>
<%@ include file="../footer.jsp" %>