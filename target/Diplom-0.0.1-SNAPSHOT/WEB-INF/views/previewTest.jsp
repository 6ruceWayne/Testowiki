<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<%-- <link href="${contextPath}/resources/css/common.css" rel="stylesheet"> --%>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

	<form:form modelAttribute="ourTest"
		action="${contextPath}/passing/${ourTest.id}" method="POST">
		<table>
			<tr>
				<td><spring:message code="test.author" /></td>
				<td><form:input readonly="true" path="tAuthor" disabled="true"
						class="form-control" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.name" /></td>
				<td><form:input readonly="true" path="name" disabled="true"
						class="form-control" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.free" /></td>
				<td><form:input readonly="true" path="free" disabled="true"
						class="form-control" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.description" /></td>
				<td><form:input readonly="true" path="description"
						disabled="true" class="form-control" /></td>
			</tr>

			<tr>
				<td><spring:message code="test.section" /></td>
				<td><form:input readonly="true" path="sectionEntity.name"
						disabled="true" class="form-control" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.count" /></td>
				<td><form:input readonly="true" path="count" disabled="true"
						class="form-control" /></td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn btn-success" type="submit"
					value="<spring:message code="start"/>" />
			</tr>
		</table>

	</form:form>

	<c:if test="${not empty ourArticle}">

	${ourArticle.name} 
	${ourArticle.content} 
	</c:if>
</body>
</html>