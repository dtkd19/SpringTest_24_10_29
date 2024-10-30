<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>Board Detail Page...</h1>
	<hr>

	<div class="mb-3">
		<label for="n" class="form-label">no.</label> <input type="text"
			class="form-control" id="t" name="bno" value="${bvo.bno }"
			readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="t" class="form-label">title</label> <input type="text"
			class="form-control" id="t" name="title" value="${bvo.title }"
			readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">writer</label> <input type="text"
			class="form-control" id="t" name="writer" value="${bvo.writer }"
			readonly="readonly"> <span class="badge text-bg-info">${bvo.regDate }
		</span> <span class="badge text-bg-info">${bvo.readCount } </span>

	</div>
	<div class="mb-3">
		<label for="c" class="form-label">content</label>
		<textarea class="form-control" id="c" rows="3" name="content"
			readonly="readonly">${bvo.content }</textarea>
	</div>
	<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-primary">modify</button></a>
	<a href="/board/delete?bno=${bvo.bno }"><button type="button" class="btn btn-danger">delete</button></a>
</div>


<jsp:include page="../layout/footer.jsp" />