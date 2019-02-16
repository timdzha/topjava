<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>${param.action=='create' ? 'Create meal' : 'Edit meal'}</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }
        dt {
            display: inline-block;
            width: 170px;
        }
        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
    <section>
        <h2><a href="index.html">Home</a> </h2>
        <h3>${param.action=='create' ? 'Create meal' : 'Edit meal'}</h3>
        <hr>

        <form method="POST" action="meals">
            <input type="hidden" name="id" value="${meal.id}">
            <dl>
                <dt>Date Time :</dt>
                <dd>
                    <input type="datetime-local" name="dateTime" value="${mealEdit.dateTime}"/>
                </dd>
            </dl>
            <dl>
                <dt>Description :</dt>
                <dd><input type="text" name="description" value="${meal.description}"/></dd>
            </dl>
            <dl>
                <dt>Calories :</dt>
                <dd><input type="number" name="calories" value="${meal.calories}" /></dd>
            </dl>

            <button type="submit">Save</button>
            <button onclick="window.history.back()">Cancel</button>
        </form>
    </section>
</body>
</html>
