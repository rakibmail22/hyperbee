<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>HyperBee::Login</title>

    <spring:url value="/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/css/bootstrap-theme.min.css" var="bootstrapThemeCss"/>
    <spring:url value="/css/signin.css" var="signIn"/>
    <spring:url value="/css/dashboard.css" var="dashboard"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${bootstrapThemeCss}" rel="stylesheet"/>
    <link href="${signIn}" rel="stylesheet"/>
    <link href="${dashboard}" rel="stylesheet"/>
</head>
<body>