<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<jsp:include page="../layout/header.jsp" />

	<form action="/board/insert" method="post">
		<div class="container-md">
			<h1>Board Register Page...</h1>
			<div class="mb-3">
				<label for="t" class="form-label">title</label> <input type="text"
					class="form-control" id="t" name="title" placeholder="title...">
			</div>
			<div class="mb-3">
				<label for="w" class="form-label">writerL</label> <input type="text"
					class="form-control" id="w" name="writer" placeholder="writer...">
			</div>
			<div class="mb-3">
				<label for="c" class="form-label">content</label>
				<textarea class="form-control" id="c" rows="3" name="content"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">register</button>
		</div>
	</form>

	<jsp:include page="../layout/footer.jsp" />

