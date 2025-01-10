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
<a href="/dashboard">dashboard</a>
<c:if test="${team.createdBy.id == sessionScope.userId}">
    <a href="/team/edit/${team.id}">Edit team</a>
    <a href="/team/delete/${team.id}">Delete team</a>
</c:if>

<a href="/team/${id}"><c:out value="${team.name}" /></a>
<c:out value="${team.skillLevel}" />
<c:out value="${team.gameDay}" />

<c:forEach var = "player" items="${players}">
    <c:out value="${player.name}" />
</c:forEach>

<form:form method="post" action="/teams/add-player/${team.id}" modelAttribute="player">
    <input type="hidden" name="_method" value="put">
    <form:hidden path="id" />
    <form:label path="name" class="name">Name:</form:label>
    <form:input path="name" class="name" />
    <form:errors path="name" class="text-danger" />
    <button>Add player</button>
</form:form>
</body>
</html>
