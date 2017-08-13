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
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<%-- <link href="${contextPath}/resources/css/common.css" rel="stylesheet"> --%>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<script src="http://js.nicedit.com/nicEdit-latest.js"
	type="text/javascript"></script>
<script type="text/javascript">
	bkLib.onDomLoaded(nicEditors.allTextAreas);
</script>
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
					<li><a href="${contextPath}/articleList"><spring:message
								code="articles" /></a></li>
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

	<form action="${contextPath}/articleCreate" method="POST">

		<div class="form-group">
			<label for="usr"><spring:message code="article.name" /></label> <input
				type="text" class="form-control" id="usr" name="articleName">
		</div>
		<textarea id="MyArea" rows="10" cols="100" name="articleContent"></textarea>
		<INPUT type=submit name="submit" value="Send"
			onclick="nicEditors.findEditor('MyArea').saveContent();">
	</form>

</body>
</html>