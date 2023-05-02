<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-people"></i></div>
                            <h1 class="fw-bolder">Login Page</h1>
                            <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                            <c:if test="${not empty param.errorMessage}">
                            	<h1>${param.errorMessage}</h1>
                            </c:if>
                                <form id="contactForm" data-sb-form-api-token="API_TOKEN" action="./login" method="post" enctype="multipart/form-data">
                                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <!-- UserName input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="username" name="username" value="${cookie.remember.value}" type="text" placeholder="Enter Title..." data-sb-validations="required" />
                                        <label for="username">UserName</label>
                                        <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                                    </div>
                                   <!-- Password input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" name="password" type="password" placeholder="Enter Title..." data-sb-validations="required" />
                                        <label for="password">Password</label>
                                        <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                                    </div>
                                    <!-- Password input-->
                                    <div class="form-floating mb-3">
                                        <input  id="remember" name="remember" value="remember" type="checkbox" placeholder="Enter Title..." data-sb-validations="required" />
                                        <label for="remember">ID 기억하기</label>
                                    </div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-success btn-lg" id="submitButton" type="submit">Login</button></div>
                                </form>
                                <a href="./findPassword" class="btn btn-success my-2">비밀번호 찾기</a>
                                <a href="/oauth2/authorization/kakao" class="btn btn-success my-2">Kakao Login</a>
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
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <div class="h5">Ask the community</div>
                            <p class="text-muted mb-0">Explore our community forums and communicate with other users.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-telephone"></i></div>
                            <div class="h5">Support center</div>
                            <p class="text-muted mb-0">Browse FAQ's and support articles to find solutions.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-success bg-gradient text-white rounded-3 mb-3"><i class="bi bi-question-circle"></i></div>
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
</body>
<!-- script는 UserLoginFailed의 URL의 errorMessage를 숨기는 코드 -->
<script> history.replaceState({}, null, location.pathname)</script>
</html>