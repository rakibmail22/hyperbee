<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/js/bootstrap.min.js" var="bootstrapJs"/>
<spring:url value="/js/jquery-3.1.1.js" var="jquery"/>

<script src="${jquery}" rel="script"></script>
<script src="${bootstrapJs}" rel="script"></script>
</body>
</html>
