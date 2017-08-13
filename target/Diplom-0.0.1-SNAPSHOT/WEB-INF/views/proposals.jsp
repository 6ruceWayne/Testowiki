<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${contextPath}/">TestMe</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/"><spring:message
								code="main" /></a></li>
					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<li><c:url var="formPerson" value="/personalOffice" /> <a
							href="${formPerson}"><spring:message code="personal.office" /></a></li>
					</c:if>
					<li><a href="${contextPath}/rules"><spring:message
								code="rules" /></a></li>
					<li><a href="${contextPath}/faq"><spring:message
								code="FAQ" /></a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${pageContext.request.userPrincipal.name != null}">
							<li><c:url var="logout" value="/logout" /> <a
								href="${logout}"><span class="glyphicon glyphicon-log-out"></span>
									<spring:message code="logout" /></a></li>
						</c:when>
						<c:otherwise>
							<li><c:url var="formLoginUrl" value="/login" /> <a
								href="${formLoginUrl}"><span
									class="glyphicon glyphicon-log-in"></span> <spring:message
										code="login" /></a></li>
							<li><c:url var="formRegistrationUrl" value="/registration" />
								<a href="${formRegistrationUrl}"> <span
									class="glyphicon glyphicon-education"></span> <spring:message
										code="registration" />
							</a></li>


						</c:otherwise>
					</c:choose>
					<li><a href="?lang=ru"
						onclick="window.location.href='/locale/{ru}';" class="btn">ru</a></li>
					<li><a href="?lang=ua"
						onclick="window.location.href='/locale/{ua}';" class="btn">ua</a></li>
					<li><a href="?lang=en"
						onclick="window.location.href='/locale/{en}';" class="btn">en</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<h3 align="center">
		<spring:message code="test.list" />
	</h3>
	<c:if test="${!empty listTests}">
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="25"><spring:message code="test.id" /></th>
					<th width="80"><spring:message code="test.name" /></th>
					<th width="80"><spring:message code="test.author" /></th>
					<th width="120"><spring:message code="test.description" /></th>
					<th width="60"><spring:message code="test.free" /></th>
					<th width="120"><spring:message code="test.comment.to.admin" /></th>
					<th width="120"><spring:message code="test.section" /></th>
					<th width="120"><spring:message code="test.status" /></th>
					<th width="60"><spring:message code="test.review" /></th>
					<th width="60"><spring:message code="test.delete" /></th>
				</tr>
			</thead>
			<c:forEach items="${listTests}" var="test">
				<tr>
					<td>${test.id}</td>
					<td>${test.name}</td>
					<td>${test.tAuthor}</td>
					<td>${test.description}</td>
					<td>${test.free}</td>
					<td>${test.commentToAdmin}</td>
					<td>${test.sectionEntity.name}</td>
					<td>${test.status}</td>
					<td><a href="<c:url value='/tests/review/${test.id}' />"
						class="btn btn-primary"><span
							class="glyphicon glyphicon-pencil"></span> <spring:message
								code="test.review" /></a></td>
					<td><a href="<c:url value='/tests/remove/${test.id}' />"><spring:message
								code="test.delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>