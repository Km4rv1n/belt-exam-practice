<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add new team</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<h1>New Team</h1>

<a href="">dashboard</a>
<form:form method="post" action="/teams/add" modelAttribute="team">
    <form:label path="name" class="name">Name:</form:label>
    <form:input path="name" class="name" />
    <form:errors path="name" class="text-danger" />
    <form:label path="skillLevel" class="skilLevel">SkillLevel:</form:label>
    <form:input path="skillLevel" class="name" />
    <form:errors path="skillLevel" class="text-danger" />
    <form:label path="gameDay" class="name">gameDay:</form:label>
    <form:input path="gameDay" class="name" />
    <form:errors path="gameDay" class="text-danger" />
    <button>Submit</button>
</form:form>
</body>
</html>