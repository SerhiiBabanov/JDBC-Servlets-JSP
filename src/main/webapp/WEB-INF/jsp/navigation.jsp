<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Project Management System</a>
    </div>
    <ul class="nav navbar-nav">

      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Company <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${contextPath}/customer/create">Create</a></li>
          <li><a href="${contextPath}/customer/read">Read</a></li>
          <li><a href="${contextPath}/customer/update">Update</a></li>
          <li><a href="${contextPath}/customer/delete">Delete</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Customer <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${contextPath}/customer/create">Create</a></li>
          <li><a href="${contextPath}/customer/read">Read</a></li>
          <li><a href="${contextPath}/customer/update">Update</a></li>
          <li><a href="${contextPath}/customer/delete">Delete</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Developer <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${contextPath}/customer/create">Create</a></li>
          <li><a href="${contextPath}/customer/read">Read</a></li>
          <li><a href="${contextPath}/customer/update">Update</a></li>
          <li><a href="${contextPath}/customer/delete">Delete</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Skill <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${contextPath}/customer/create">Create</a></li>
          <li><a href="${contextPath}/customer/read">Read</a></li>
          <li><a href="${contextPath}/customer/update">Update</a></li>
          <li><a href="${contextPath}/customer/delete">Delete</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Project <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${contextPath}/customer/create">Create</a></li>
          <li><a href="${contextPath}/customer/read">Read</a></li>
          <li><a href="${contextPath}/customer/update">Update</a></li>
          <li><a href="${contextPath}/customer/delete">Delete</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>
