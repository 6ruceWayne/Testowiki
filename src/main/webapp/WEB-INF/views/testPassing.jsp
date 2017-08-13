<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<%-- 
<link href="${contextPath}/resources/css/common.css" rel="stylesheet"> --%>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	// Set the date we're counting down to
	var countDownDate = new Date("${ourDate}").getTime();

	// Update the count down every 1 second
	var x = setInterval(function() {

		// Get todays date and time
		var now = new Date().getTime();

		// Find the distance between now an the count down date
		var distance = countDownDate - now;

		// Time calculations for days, hours, minutes and seconds
		var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
				/ (1000 * 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		// Output the result in an element with id="demo"
		if (hours != 0) {
			document.getElementById("demo").innerHTML = hours + " " + minutes
					+ " " + seconds + " ";
		} else {
			document.getElementById("demo").innerHTML = minutes + " " + seconds
					+ " ";
		}

		// If the count down is over, write some text 
		if (distance < 0) {
			clearInterval(x);
			document.getElementById("demo").innerHTML = "EXPIRED";
		}
	}, 1000);
</script>
<style>
p {
	text-align: center;
	font-size: 60px;
}
</style>
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
	<p id="demo"></p>


	<div id="pagination">

		<table class="table table-striped">
			<thead>
				<tr>
					<th width="80"><spring:message code="question.text" /></th>
				</tr>
				<tr>
					<th width="80">${question.text}</th>
				</tr>
			</thead>
		</table>
		<%-- <form:form modelAttribute="listAnswers" action="/setAnswer"
			method="get">
			<table class="table table-striped">
				<tr>
					<td><form:hidden path="question.id" value="${question.id}" /></td>
					<c:forEach items="${question.answers}" var="answer">
						<tr>
							<td>${answer.id}</td>
							<td>${answer.text}</td>

							<td>${listAnswers.key}</td>

							<td><input type="checkbox"
								name="listAnswers['${answer.id}']" value="${listAnswers.value}" /></td>
						</tr>
					</c:forEach>
					<td colspan="2"><input class="btn btn-success" type="submit"
						value="<spring:message code="answer.set"/>" />
				</tr>
			</table>
		</form:form> --%>

		<%-- <c:forEach items="${listAnswers}" var="entry">
    Key = ${entry.key}, value = ${entry.value}<br>
		</c:forEach> --%>


		<%-- <form:form
			action="${pageContext.request.contextPath}/setAnswer/${questionId}"
			modelAttribute="listAnswers" method="GET">
			<table>
				<c:forEach items="${listAnswers}" var="answer" varStatus="status">
					<tr>
						<td>${answer}</td>
						<td><form:select path="${answer.key}" value="${answer.value}" /></td>
					</tr>
				</c:forEach>
			</table>
			<br />

			<input type="submit" value="Save" />

		</form:form> --%>
		<c:url value="/setAnswer" var="setA">
		</c:url>
		<form:form method="post" action="${setA}"
			modelAttribute="userAnswerForm">
			<input type="hidden" id="questionid" name="questionid"
				value="${question.id}" />
			<input type="hidden" id="page" name="page" value="${qId}" />



			<c:forEach items="${userAnswerForm.map}" var="item">

				<input type="checkbox" name="map['${item.key}']" value="true" />
				<label>${item.key}</label>
				<br>
			</c:forEach>

			<input type="submit" class="btn btn-success" value="Отправить">
			<br>
			<br>
		</form:form>
		<a href="<c:url value='/finishTest/${passingTest.id}' />"
			class="btn btn-info"><spring:message code="finish.test" /></a> <br>
		<br>
		<c:url value="/passing/${passingTest.id}" var="prev">
			<c:param name="qId" value="${qId-1}" />
		</c:url>
		<c:if test="${qId > 1}">
			<a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
		</c:if>

		<c:forEach begin="1" end="${questionSize}" step="1" varStatus="i">
			<c:choose>
				<c:when test="${qId == i.index}">
					<span>${i.index}</span>
				</c:when>
				<c:otherwise>
					<c:url value="/passing/${passingTest.id}" var="url">
						<c:param name="qId" value="${i.index}" />
					</c:url>
					<a href='<c:out value="${url}" />' class="btn btn-default">${i.index}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:url value="/passing/${passingTest.id}" var="next">
			<c:param name="qId" value="${qId + 1}" />
		</c:url>
		<c:if test="${qId + 1 <= questionSize}">
			<a href='<c:out value="${next}" />' class="btn btn-default"><spring:message
					code="next" /></a>
		</c:if>
	</div>


</body>
</html>