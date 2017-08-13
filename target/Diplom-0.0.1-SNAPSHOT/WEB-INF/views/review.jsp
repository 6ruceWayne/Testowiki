<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${contextPath}/resources/css/bootstrap.min.css">
<script src="${contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<h1 align="left">
		<c:url var="formTestUrl" value="/tests/list" />
		<a href="${formTestUrl}"><spring:message code="back.to.proposals" /></a>
	</h1>

	<span style="float: right"><a href="?lang=ru">ru</a> | <a
		href="?lang=ua">ua</a> | <a href="?lang=en">en</a> </span>
	<br>

	<c:url var="saveChanges" value="/tests/review/savechanges" />

	<form:form modelAttribute="candidateTest" action="${saveChanges}"
		method="POST">
		<table>
			<tr>
				<td><form:input type="hidden" path="id" class="form-control"
						value="${candidateTest.id}" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.author" /></td>
				<td><form:input path="tAuthor" class="form-control"
						disabled="true" /></td>
			</tr>
			<%-- <tr>
				<td><spring:message code="test.name" /></td>
				<td><form:input path="name" class="form-control" /></td>
			</tr> --%>
			<tr>
				<td><spring:message code="test.description" /></td>
				<td><form:input type="text" class="form-control"
						path="description" /></td>
			</tr>
			<%-- <tr>
				<td><spring:message code="test.section" /></td>
				<td><form:select path="section">
						<c:forEach items="${listSections}" var="section"
							varStatus="status">
							<form:option value="${section}">${section}</form:option>
						</c:forEach>
					</form:select></td>
			</tr> --%>
			<%-- <tr>
				<td><spring:message code="test.section" /></td>
				<td><form:input type="text" class="form-control" path="section" /></td>
			</tr> --%>
			<tr>
				<td><spring:message code="test.section" /></td>
				<td><form:select path="sectionEntity">
						<c:forEach items="${listSections}" var="section"
							varStatus="status">
							<form:option value="${section}"
								onclick="/tests/review/${candidateTest.id}/${section}">${section}</form:option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><spring:message code="test.free" /></td>
				<td><form:input path="free" class="form-control" /></td>
			</tr>
			<c:forEach items="${candidateTest.questions}" var="question"
				varStatus="status">
				<tr>
					<td align="center">${status.count}</td>
					<td><input name="questions[${status.index}].text"
						value="${question.text}" class="form-control" /></td>
				</tr>

				<c:forEach items="${question.answers}" var="answer"
					varStatus="interator">
					<td align="center">${interator.count}</td>
					<td><input name="answers[${interator.index}].answer"
						value="${answer.text}" class="form-control" /></td>

				</c:forEach>
				<td colspan="2"><input class="btn btn-success" type="submit"
					value="<spring:message code="answer.add"/>" />
			</c:forEach>
			<tr>
				<td colspan="2"><input class="btn btn-success" type="submit"
					value="<spring:message code="question.add"/>" />
			</tr>
			<tr>
				<td><spring:message code="test.comment.to.admin" /></td>
				<td><form:input type="text" class="form-control"
						path="commentToAdmin" disabled="true" /></td>
			</tr>
		</table>
		<%-- <select name="section" size="8" tabindex="1">
			<c:forEach items="${listSections}" var="sectionType">
				<option>${sectionType}</option>
			</c:forEach>
		</select> --%>
		<br />
		<input type="submit" class="btn btn-primary" value="Save" />
	</form:form>


	<div class="container">
		<div class="btn-group">
			<button type="button" class="btn btn-primary">
				<spring:message code="choise" />
			</button>
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a
					href="<c:url value='/tests/choise/${candidateTest.id}/app' />"><spring:message
							code="aprove" /></a></li>
				<li><a
					href="<c:url value='/tests/choise/${candidateTest.id}/pro' />"><spring:message
							code="return" /></a></li>
				<li><a
					href="<c:url value='/tests/choise/${candidateTest.id}/dis' />"><spring:message
							code="refuse" /></a></li>
			</ul>
		</div>
	</div>
</body>
</html>