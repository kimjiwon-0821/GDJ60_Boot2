<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<c:import url="../temp/style.jsp"></c:import>
</head>
<body  class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
	<!-- Navigation start-->
    <c:import url="../temp/header.jsp"></c:import>
	<!-- Navigation end-->
	<!-- Page content start -->
            <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">Login Page</h1>
                            <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <form:form id="contactForm" data-sb-form-api-token="API_TOKEN" modelAttribute="memberVO" action="./join" method="post" enctype="multipart/form-data">
                                    <!-- UserName input-->
                                    <div class="form-floating mb-3">
                                        <form:input path="userName" id="userName" cssClass="form-control"/>
                                        <form:label path="userName">UserName</form:label>
										<form:errors path="userName"></form:errors>
                                    </div>
                                   <!-- Password input-->
                                    <div class="form-floating mb-3">
                                    	<form:password path="password" id="password" cssClass="form-control"/>
                                        <label for="password">Password</label>
										<form:errors path="password"></form:errors>
									</div>
                                    <!-- Password Check input-->
                                    <div class="form-floating mb-3">
                                        <form:password cssClass="form-control" id="passwordCheck" path="passwordCheck"/>
                                        <label for="passwordCheck">Password Check</label>
                                        <form:errors path="passwordCheck"></form:errors>
                                    </div>
                                    <!-- Name input-->
                                    <div class="form-floating mb-3">
                                    	<form:input path="name" id="name" cssClass="form-control"/>
                                        <label for="name">Name</label>
										<form:errors path="name"></form:errors>                                    
									</div>
                                    <!-- Email input-->
                                    <div class="form-floating mb-3">
	                                    <form:input path="email" id="email" cssClass="form-control"/>
	                                    <label for="email">Email</label>
                                    	<form:errors path="email"></form:errors>      
                                    </div>
                                    <!-- Birth input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="birth" name="birth" type="date" placeholder="Enter Title..." data-sb-validations="required" />
                                        <label for="birth">Birth</label>
										<form:errors path="birth"></form:errors>
                                    </div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-success btn-lg" id="submitButton" type="submit">Join</button></div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <!-- Contact cards-->
                    <div class="row gx-5 row-cols-2 row-cols-lg-4 py-5">
                        <div class="col">
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-chat-dots"></i></div>
                            <div class="h5 mb-2">Chat with us</div>
                            <p class="text-muted mb-0">Chat live with one of our support specialists.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-people"></i></div>
                            <div class="h5">Ask the community</div>
                            <p class="text-muted mb-0">Explore our community forums and communicate with other users.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-question-circle"></i></div>
                            <div class="h5">Support center</div>
                            <p class="text-muted mb-0">Browse FAQ's and support articles to find solutions.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-telephone"></i></div>
                            <div class="h5">Call us</div>
                            <p class="text-muted mb-0">Call us during normal business hours at (555) 892-9403.</p>
                        </div>
                    </div>
                </div>
            </section>
			<!-- Page content end-->

	</main>
	<!-- Footer start --> 
    <c:import url="../temp/footer.jsp"></c:import>
    <!-- Footer end -->
    <script type="text/javascript" src="../js/joinFormCheck.js"></script>
</body>
</html>