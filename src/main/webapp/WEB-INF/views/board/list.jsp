<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
	<c:import url="../temp/style.jsp"></c:import>
    </head>
	<body class="d-flex flex-column h-100">
       <main class="flex-shrink-0">
       		<!-- Navigation start-->
           <c:import url="../temp/header.jsp"></c:import>
			<!-- Navigation end-->
			
			<section class="bg-light py-5">
                <div class="container px-5 my-5">
                    <div class="text-center mb-5">
                        <h1 class="fw-bolder" style="text-transform: capitalize;">${board} List</h1>
                        <p class="lead fw-normal text-muted mb-0" style="text-transform: capitalize;">${board}를 위한 게시판입니다.</p>
                    </div>
	                <form action="./list" >
	                    <div class="input-group">
							<select class="form-select" name="kind" id="kind">
							  <option value="title" ${pager.kind eq 'title' ? 'selected':''} >Title</option>
							  <option value="contents" ${pager.kind eq 'contents' ? 'selected':''}>Contents</option>
							  <option value="writer"${pager.kind eq 'writer' ? 'selected':''}>writer</option>
							</select>
							<input type="text" class="form-control" name="search" id="search" aria-label="Text input with 2 dropdown buttons">
							<button class="btn btn-outline-success" type="submit">search</button>
						</div>
					</form>
                    <div class="row gx-5 my-3">
                    	<table class="table table-hover">
                    		<thead style="background-color: black; color: white;">
	                    		<tr>
	                    			<th>Num</th>
	                    			<th>Title</th>
	                    			<th>Writer</th>
	                    			<th>Date</th>
	                    			<th>Hit</th>
	                    		</tr>
	                    	</thead>
	                    	<tbody>
	                    		<c:forEach items="${list}" var="boardVO">
	                    			<tr>
	                    				<td>${boardVO.num}</td>
	                    				<td><a href="./detail?num=${boardVO.num}" style="color: black;">${boardVO.title}</a></td>
	                    				<td>${boardVO.writer}</td>
	                    				<td>${boardVO.date}</td>
	                    				<td>${boardVO.hit}</td>
	                    			</tr>
	                    		</c:forEach>
	                    	</tbody>
                    	</table>
                    </div>
                    <a href="./add" class="btn btn-outline-success">Write</a>
                    
                    <!-- Pasing -->
		            <nav aria-label="Page navigation example" class="col-row my-2">
						<ul class="pagination">
							<c:if test="${pager.pre}">
								<li class="page-item">
						    		<a class="page-link" href="./list?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}" aria-label="Previous">
						        		<span aria-hidden="true" style="color: black;">&laquo;</span>
						      		</a>
						    	</li>
					    	</c:if>
					    	<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
					    		<li class="page-item"><a class="page-link" href="./list?page=${i}" style="color: black;">${i}</a></li>
					    	</c:forEach>
					    	
					    	<c:if test="${pager.next}">
								<li class="page-item">
						    		<a class="page-link" href="./list?page=${pager.lastNum+1}" aria-label="Next">
						        		<span aria-hidden="true" style="color: black;">&raquo;</span>
						      		</a>
						    	</li>
					    	</c:if>
					  	</ul>
					</nav>
                </div>
             </section>
             
            
             
      </main>
    <!-- Footer start --> 
    <c:import url="../temp/footer.jsp"></c:import>
    <!-- Footer end -->
</body>
</html>