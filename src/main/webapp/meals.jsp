<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <title>Meals</title>
    </head>
    <body>
        <section>
            <h2><a href="index.html">Home</a></h2>
            <h3>Meals</h3>
            <hr>

            <a href="meals?action=create">add meal</a>

            <table border=1>
                <thead>
                    <tr>
                        <th>Date Time</th>
                        <th>Description</th>
                        <th>Calories</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="meal" items="${meals}">
                    <tr style="color:${meal.excess ? 'red' : 'green'}">
                        <td hidden>${meal.id}</td>
                        <%--<td>${fn:replace(meal.dateTime, 'T', ' ')}</td>--%>
                        <%--<td><%=TimeUtil.toString(meal.dateTime)%></td>--%>
                        <td>
                            <fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>
                            <fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm"/>
                        </td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                        <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </body>
</html>
