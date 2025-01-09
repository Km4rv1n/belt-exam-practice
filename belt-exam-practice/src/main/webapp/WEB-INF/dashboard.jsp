<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login/Register</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<h1>Teams</h1>
<a href="/logout">Logout</a>
<a href="/teams/new">Add new team</a>
<p>Hello, <c:out value="${user.username}"></c:out></p>
<table border = 1>
<tr>
    <th>Name</th>
    <th>Skill Level</th>
    <th>Game Day</th>
    <th>Nr. Players</th>
</tr>
    <c:forEach var = "team" items="${teams}">
        <tr>
            <td><a href="/team/${team.id}"><c:out value="${team.name}" /></a> </td>
            <td><c:out value="${team.skillLevel}" /> </td>
            <td><c:out value="${team.gameDay}" /> </td>
            <td><c:out value="${team.players.size()}" />/9 </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>