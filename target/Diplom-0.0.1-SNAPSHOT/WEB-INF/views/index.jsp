<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
/*row.content {
	height: 450px
}*/

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>
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
						<li><a href="${contextPath}/articleCreate"><spring:message
									code="articleCreate" /></a></li>
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
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-8 text-left">
				<div id="container">
					<div id="left"></div>
					<div class="container">
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<form id="logoutForm" method="POST"
								action="${contextPath}/logout">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</c:if>

					</div>
					<br>

					<%-- <c:forEach items="${listSections}" var="section">
						<div class="panel panel-default">
							<div class="panel-heading">${section}</div>
							<c:forEach items="${mapTests}" var="tests">
							
								<c:if test="${ test.section== section}">
									<div class="panel-body">
										<c:url var="pass" value='/tests/previewTest/${test.id}' />
										<a href="${pass}">${test.name}</a>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</c:forEach> --%>
					<c:forEach items="${mapTests}" var="tests">
						<div class="panel panel-default">
							<div class="panel-heading">${tests.key.name}</div>
							<c:forEach items="${tests.value}" var="test">
								<div class="panel-body">
									<c:url var="pass" value='/previewTest/${test.id}' />
									<a href="${pass}">${test.name}</a>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-sm-3 sidenav" align="right">
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>Top popular tests</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listTopVisited}" var="test">
								<tr>
									<c:url var="pass" value='/previewTest/${test.id}' />
									<td><a href="${pass}">${test.name}</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td>C++ Middle</td>
							</tr>
							<tr>
								<td>PHP Senior</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>Top hard</th>
								<th>Middle mark</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Java Junior</td>
								<td>20%</td>
							</tr>
							<tr>
								<td>C++ Middle</td>
								<td>24%</td>
							</tr>
							<tr>
								<td>PHP Senior</td>
								<td>35%</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>