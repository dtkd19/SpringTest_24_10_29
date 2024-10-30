<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>Board List Page...</h1>
	<hr>
	<!-- serach -->
	<div class ="container-fluid">
	    <form action="/board/list" class="d-flex" role="search" >
	      <select class="form-select" name="type" id="inputGroupSelect01" style="width: 70%; margin-right: 20px ">
		    <option ${ph.pgvo.type == null ? 'selected' : ''  }>Choose...</option>
		    <option value="t" ${ph.pgvo.type eq 't' ? 'selected' : ''  }>title</option>
		    <option value="w" ${ph.pgvo.type eq 'w' ? 'selected' : ''  }>writer</option>
		    <option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : ''  }>content</option>
		    <option value="tw"${ph.pgvo.type eq 'tw' ? 'selected' : ''  } >title + writer</option>
		    <option value="wc"${ph.pgvo.type eq 'wc' ? 'selected' : ''  }>writer + content</option>
		    <option value="tc"${ph.pgvo.type eq 'tc' ? 'selected' : ''  }>title + content</option>
		    <option value="twc"${ph.pgvo.type eq 'twc' ? 'selected' : ''  }>all</option>
		  </select>
	    
        <input class="form-control me-2" name="keyword" type="search" placeholder="Search" aria-label="Search" value="${ph.pgvo.keyword }">
        <input type="hidden" name="pageNo" value="1">
        <input type="hidden" name="qty" value="${ph.pgvo.qty }">
       <!--  <button class="btn btn-outline-success" type="submit">Search</button> -->
       <button type="submit" class="btn btn-success position-relative">
  Search
  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    ${ph.totalCount }
    <span class="visually-hidden">unread messages</span>
  </span>
</button>
        
      </form>
     </div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">no.</th>
				<th scope="col">title</th>
				<th scope="col">writer</th>
				<th scope="col">regDate</th>
				<th scope="col">readCount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/board/detail?bno=${bvo.bno } ">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.regDate }</td>
					<td>${bvo.readCount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- pagination line -->
	<!-- << , >> : 값이 false면   -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item ${ph.prev eq false ? 'disabled' : '' }"><a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var = "i">
			<li class="page-item ${ph.pgvo.pageNo eq i ? 'active' : '' } "><a class="page-link" href="/board/list?pageNo=${i } &qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
			</c:forEach>
			<li class="page-item ${ph.next eq false ? 'disabled' : '' }"><a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
	<a href="/"><button type="button" class="btn btn-success">index</button></a>
</div>






<jsp:include page="../layout/footer.jsp" />
