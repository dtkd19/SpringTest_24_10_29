<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>User Modify Page...</h1>
	<hr>
	
	<sec:authentication property="principal.uvo" var="uvo"/>   
	<form action="/user/modify" method="post">
		<input type="hidden" name="email" value="${uvo.email }">
		<div class="card mb-3" style="max-width: 540px;">
			<div class="row g-0">
				<div class="col-md-4">
					<img src="/resources/image/사람2.png" class="img-fluid rounded-start"
						alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">
							<input type="text" id="n" name="nickName" value="${uvo.nickName }">
							<input type="text" id="p" name="pwd" placeholder="password...">
						</h5>
						<c:forEach items="${uvo.authList }" var="avo">
							<span class="badge text-bg-info">${avo.auth }</span>
						</c:forEach>
						<p class="card-text">가입일 : ${uvo.regDate }</p>
						<p class="card-text">
							<small class="text-body-secondary">마지막 로그인 :
								${uvo.lastLogin }</small>
						</p>
						<button type="submit" class = "btn btn-primary btn-sm">modify</button>
						<a href="/user/delete"><button type="button" class = "btn btn-danger btn-sm">delete</button></a>
					</div>
				</div>
			</div>
		</div>
	</form>
	
</div>
<jsp:include page="../layout/footer.jsp" />
