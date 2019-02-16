<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Meals</title>
    </head>
    <body>
        <section>
            <h2><a href="index.html">Home</a></h2>
            <h3>Meals</h3>
            <hr>
            <table border=1>
                <thead>
                <tr>
                    <th>Date Time</th>
                    <th>Description</th>
                    <th>Calories</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="meal" items="${meals}">
                    <tr style="color:${meal.excess ? 'red' : 'green'}">
                        <td>${fn:replace(meal.dateTime, 'T', ' ')}</td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </body>
</html>
