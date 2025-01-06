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
<div class="container mt-5">
  <h1 class="text-primary text-center">Lyrics Lab</h1>

  <div class="row mt-4">
    <div class="col-md-6">
      <h2>Register</h2>
      <form:form method="post" action="/register" modelAttribute="newUser">
        <div class="form-group">
          <form:label path="username" class="form-label">Username:</form:label>
          <form:input path="username" class="form-control" />
          <form:errors path="username" class="text-danger" />
        </div>
        <div class="form-group">
          <form:label path="email" class="form-label">Email:</form:label>
          <form:input path="email" class="form-control" />
          <form:errors path="email" class="text-danger" />
        </div>
        <div class="form-group">
          <form:label path="password" class="form-label">Password:</form:label>
          <form:input type="password" path="password" class="form-control" />
          <form:errors path="password" class="text-danger" />
        </div>
        <div class="form-group">
          <form:label path="confirm" class="form-label">Confirm Password:</form:label>
          <form:input type="password" path="confirm" class="form-control" />
          <form:errors path="confirm" class="text-danger" />
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
      </form:form>
    </div>

    <!-- Login Form (Second Column) -->
    <div class="col-md-6">
      <h2>Log in</h2>
      <form:form method="post" action="/login" modelAttribute="newLogin">
        <div class="form-group">
          <form:label path="email" class="form-label">Email:</form:label>
          <form:input path="email" class="form-control" />
          <form:errors path="email" class="text-danger" />
        </div>
        <div class="form-group">
          <form:label path="password" class="form-label">Password:</form:label>
          <form:input type="password" path="password" class="form-control" />
          <form:errors path="password" class="text-danger" />
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
      </form:form>
    </div>
  </div>
</div>

</body>
</html>