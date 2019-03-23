<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="app.title"/></title>
    <%--<c:set var="url">${pageContext.request.requestURL}</c:set>--%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <%--<link rel="stylesheet" href="templates/style/main.css" />--%>
    <%--<script src="templates/js/main.js"></script>--%>
    <link rel="stylesheet" href="resources/css/style.css">
    <%--<script>var base = document.getElementsByTagName("base")[0].href;</script>--%>
    <%--<script>var base = document.getElementsByTagName("base")[0].href;</script>--%>
    <%--<base href="${pageContext.request.contextPath}/"/>--%>
</head>