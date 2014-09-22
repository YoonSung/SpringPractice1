<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 회원가입</title>
<%@ include file="../commons/_header.jspf"%>

</head>
<body>
	<%@ include file="../commons/_top.jspf"%>
	<c:choose>
		<c:when test="${not empty sessionScope.userId}">
			<c:set var="title" value="개인정보 수정" />
			<c:set var="method" value="put" />	
		</c:when>
		
		<c:otherwise>
			<c:set var="title" value="회원가입" />
			<c:set var="method" value="post" />
		</c:otherwise>
	</c:choose>	
	
	
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1><c:out value="${title}"/></h1>
				</div>


				<springForm:form modelAttribute="user" cssClass="form-horizontal"
					action="/user" method="${method}">
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
							<c:choose>
								<c:when test="${not empty sessionScope.userId}">
									<div>${sessionScope.userId}</div>
									<springForm:hidden path="userId" />
								</c:when>
								<c:otherwise>
									<springForm:input path="userId" />
									<springForm:errors path="userId" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<springForm:password id="password" path="password" />
							<springForm:errors path="password" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<springForm:input id="name" path="name" />
							<springForm:errors path="name" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<springForm:input id="email" path="email" />
							<springForm:errors path="email" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary" >${title}</button>
						</div>
					</div>
				</springForm:form>
			</div>
		</div>
	</div>
</body>
</html>