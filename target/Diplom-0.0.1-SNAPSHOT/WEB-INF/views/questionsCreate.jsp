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

	<form:form modelAttribute="testForm" method="POST">
		<table>
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
				<td><spring:message code="test.comment.to.admin" /></td>
				<td><form:input readonly="true" path="commentToAdmin"
						disabled="true" class="form-control" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.section" /></td>
				<td><form:input readonly="true" path="sectionEntity.name"
						disabled="true" class="form-control" /></td>
			</tr>
		</table>
	</form:form>


	<c:url var="createQuestion" value="/addQuestions" />

	<form:form modelAttribute="questionForm" action="${createQuestion}"
		method="POST">
		<table>
			<tr>
				<td><spring:message code="question.text" /></td>
				<td><form:input path="text" class="form-control" width="150" /></td>
				<td><form:hidden path="qTest.id" value="${testId}" /></td>
				<td colspan="2"><input class="btn btn-success" type="submit"
					value="<spring:message code="question.add"/>" />
			</tr>
		</table>
	</form:form>
	<c:choose>
		<c:when test="${!empty questionList}">
			<c:forEach items="${questionList}" var="question">
				<table class="table table-striped">
					<thead>
						<tr>
							<th width="30%"><spring:message code="question.text" /></th>
							<th width="50%">${question.text}</th>
							<td><a
								href="<c:url value='/deleteQuestion/${testForm.id}/${question.id}' />"
								class="btn btn-danger"><span
									class="glyphicon glyphicon-trash"></span> <spring:message
										code="question.delete" /></a></td>
						</tr>
					</thead>
					<tr>
						<c:url var="createAnswer" value="/addAnswers" />
						<form:form modelAttribute="answerForm" action="${createAnswer}"
							method="POST">
							<tr align="left">
								<td><spring:message code="answer.text" /></td>
								<td width="80"><form:input path="text" class="form-control" /></td>
								<td><form:radiobutton path="correct" value="true" /> <spring:message
										code="answer.correct.roundYes" /></td>
								<td><form:radiobutton path="correct" value="false" /> <spring:message
										code="answer.correct.roundNo" /></td>
								<td><form:hidden path="aQuestion.id" value="${question.id}" /></td>
								<td colspan="2"><input class="btn btn-success"
									type="submit" value="<spring:message code="answer.add"/>" />
							</tr>
						</form:form>
					</tr>
					<c:forEach items="${question.answers}" var="answer">
						<tr>
							<td>${answer.text}</td>
							<td>${answer.correct}</td>
							<td><a
								href="<c:url value='/deleteAnswer/${testForm.id}/${answer.id}' />"
								class="btn btn-danger"><span
									class="glyphicon glyphicon-trash"></span> <spring:message
										code="answer.delete" /></a></td>
						</tr>
					</c:forEach>

				</table>
			</c:forEach>
		</c:when>
	</c:choose>
	<%-- <form:form modelAttribute="testForm" action="/tests/review/saveChanges"
		method="POST">
		<table>
			<tr>
				<td><spring:message code="test.name" /></td>
				<td><form:input path="name" class="form-control" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.free" /></td>
				<td><form:input path="free" class="form-control" /></td>
			</tr>
			<c:forEach items="${testForm.questions}" var="question"
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
		</table>
		<br />
		<input type="submit" class="btn btn-primary" value="Save" />
	</form:form> --%>
</body>
</html>