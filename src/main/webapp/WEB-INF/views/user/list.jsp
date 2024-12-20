<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>UserList Page...</h1>
	<hr>

	<div class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach items="${userList }" var="uvo">
			<div class="col">
				<div class="card">
					<img src="/resources/image/사람2.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h4 class="card-title">${uvo.email }</h4>
						<c:forEach items="${uvo.authList }" var="avo">
							<span class="badge text-bg-info">${avo.auth }</span>
						</c:forEach>
					 <br><br> <p class="card-text">가입일 : ${uvo.regDate }</p>
						<p>마지막 로그인 : ${uvo.lastLogin }</p>
						
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<br> <a href="/" class="btn btn-primary">돌아가기</a>



</div>
<jsp:include page="../layout/footer.jsp" />
