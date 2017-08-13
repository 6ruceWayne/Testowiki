<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

	<br>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#results"><spring:message
					code="results.my" /></a></li>
		<li><a data-toggle="tab" href="#tests"><spring:message
					code="test.my.proposals" /></a></li>
	</ul>
	<c:choose>
		<c:when test="${!empty moderatorList}">
			<p>Here we are!</p>
		</c:when>
	</c:choose>
	<div class="tab-content">
		<div id="results" class="tab-pane fade in active">
			<c:choose>
				<c:when test="${!empty listResults}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th width="80"><spring:message code="result.nameTest" /></th>
								<th width="25"><spring:message code="result.mark" /></th>
								<th width="25"><spring:message code="result.complete" /></th>
								<th width="25"><spring:message code="result.date" /></th>
								<th width="25"><spring:message code="result.view" /></th>
							</tr>
						</thead>
						<c:forEach items="${listResults}" var="result">
							<tr>
								<td>${result.rTest.name}</td>
								<td>${result.mark}</td>
								<c:choose>
									<c:when test="${result.passed == true}">
										<td><span class="glyphicon glyphicon-ok"></span></td>
									</c:when>
									<c:otherwise>
										<td><span class="glyphicon glyphicon-remove"></span></td>
									</c:otherwise>
								</c:choose>
								<td>${result.createdOn}</td>
								<td><a href="<c:url value='/resultView/${result.id}' />"
									class="btn btn-primary"><span
										class="glyphicon glyphicon-pencil"></span> <spring:message
											code="result.view" /></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p>
						<spring:message code="results.list.empty" />
					</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="tests" class="tab-pane fade">
			<c:choose>
				<c:when test="${!empty listTests}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th width="25"><spring:message code="test.id" /></th>
								<th width="80"><spring:message code="test.name" /></th>
								<th width="120"><spring:message code="test.description" /></th>
								<th width="60"><spring:message code="test.free" /></th>
								<th width="120"><spring:message
										code="test.comment.to.admin" /></th>
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
								<td>${test.description}</td>
								<td>${test.free}</td>
								<td>${test.commentToAdmin}</td>
								<td>${test.sectionEntity.name}</td>
								<td>${test.status}</td>
								<td><a href="<c:url value='/changeTest/${test.id}' />"
									class="btn btn-primary"><span
										class="glyphicon glyphicon-pencil"></span> <spring:message
											code="test.review" /></a></td>
								<td><a href="<c:url value='/deleteTest/${test.id}' />"
									class="btn btn-danger"><span
										class="glyphicon glyphicon-trash"></span> <spring:message
											code="test.delete" /></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p>
						<spring:message code="test.my.proposals.empty" />
					</p>
				</c:otherwise>
			</c:choose>
			<c:url var="create" value="/createTest" />
			<a href="${create}" class="btn btn-success" role="button"><spring:message
					code="test.new" /></a>
		</div>
	</div>
</body>
</html>