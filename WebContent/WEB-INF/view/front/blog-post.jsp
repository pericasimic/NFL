<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>NFL Blog</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">
<!-- Bootstrap CSS-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css">
<!-- Custom icon font-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/fontastic.css">
<!-- Google fonts - Open Sans-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<!-- Fancybox-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendor/@fancyapps/fancybox/jquery.fancybox.min.css">
<!-- theme stylesheet-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.default.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/custom.css">
<!-- Favicon-->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/nfl.ico" type="image/x-icon"/>
<!-- Tweaks for older IEs-->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<body>
	<header class="header">
		<!-- Main Navbar-->
		<nav class="navbar navbar-expand-lg">
			<div class="search-area">
				<div
					class="search-area-inner d-flex align-items-center justify-content-center">
					<div class="close-btn">
						<i class="icon-close"></i>
					</div>
					<div class="row d-flex justify-content-center">
						<div class="col-md-8">
							<form:form action="../blog-search" modelAttribute="search">
								<div class="form-group">
									<form:input path="term" placeholder="What are you looking for?" />
									<button type="submit" class="submit">
										<i class="icon-search-1"></i>
									</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<!-- Navbar Brand -->
				<div
					class="navbar-header d-flex align-items-center justify-content-between">
					<!-- Navbar Brand -->
					<a href="../index-page" class="navbar-brand">NFL Blog</a>
					<!-- Toggle Button-->
					<button type="button" data-toggle="collapse"
						data-target="#navbarcollapse" aria-controls="navbarcollapse"
						aria-expanded="false" aria-label="Toggle navigation"
						class="navbar-toggler">
						<span></span><span></span><span></span>
					</button>
				</div>
				<!-- Navbar Menu -->
				<div id="navbarcollapse" class="collapse navbar-collapse">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a href="../index-page" class="nav-link">Home</a>
						</li>
						<li class="nav-item"><a href="../blog-page" class="nav-link">Blog</a>
						</li>
						<li class="nav-item"><a href="../contact-page"
							class="nav-link">Contact</a></li>
					</ul>
					<div class="navbar-text">
						<a href="#" class="search-btn"><i class="icon-search-1"></i></a>
					</div>
				</div>
			</div>
		</nav>
	</header>
	<div class="container">
		<div class="row">
			<!-- Latest Posts -->
			<main class="post blog-post col-lg-8">
				<div class="container">
					<div class="post-single">
						<div class="post-thumbnail">
							<img src="../${blogs[1].image.image}" alt="..." class="img-fluid">
						</div>
						<div class="post-details">
							<div class="post-meta d-flex justify-content-between">
								<div class="category">
									<a
										href="../category/${blogs[1].category.getURLNameWithoutSpace(blogs[1].category.name)}">${blogs[1].category.name}</a>
								</div>
							</div>
							<h1>
								${blogs[1].title}<a href=""><i class="fa fa-bookmark-o"></i></a>
							</h1>
							<div
								class="post-footer d-flex align-items-center flex-column flex-sm-row">
								<a href="../blog/${blogs[1].author.getNameURL()}"
									class="author d-flex align-items-center flex-wrap">
									<div class="avatar">
										<img src="../${blogs[1].author.image}" alt="..."
											class="img-fluid">
									</div>
									<div class="title">
										<span>${blogs[1].author.name}&nbsp;${blogs[1].author.lastname}</span>
									</div>
								</a>
								<div class="d-flex align-items-center flex-wrap">
									<div class="date">
										<i class="icon-clock"></i> ${blogs[1].getDateFormat()}
									</div>
									<div class="views">
										<i class="icon-eye"></i> ${blogs[1].numberViews}
									</div>
									<div class="comments meta-last">
										<a href="#post-comments"><i class="icon-comment"></i>${blogs[1].comments.size()}</a>
									</div>
								</div>
							</div>
							<div class="post-body">
								<p class="lead">${blogs[1].description}</p>
								<c:forEach var="image" items="${blogs[1].images}">
									<p>
										<img src="../${image.image}" alt="..." class="img-fluid">
									</p>
								</c:forEach>
								<h3>${blogs[1].subheading}</h3>
								<c:if test="${empty blogs[1].getBlogTextShort()}">
									<p>${blogs[1].text}</p>
								</c:if>
								<c:if test="${not empty blogs[1].getBlogTextShort()}">
									<p>${blogs[1].getBlogTextBefore()}</p>
									<blockquote class="blockquote">
										<p>${blogs[1].getBlogTextShort()}</p>
									</blockquote>
									<p>${blogs[1].getBlogTextAfter()}</p>
								</c:if>
							</div>
							<div class="post-tags">
								<c:forEach var="team" items="${blogs[1].teams}">
									<li class="list-inline-item"><a
										href="../team/${team.getURLNameWithoutSpace(team.name)}"
										class="tag">#${team.name}</a></li>
								</c:forEach>
							</div>
							<div
								class="posts-nav d-flex justify-content-between align-items-stretch flex-column flex-md-row">
								<a href="${blogs[0].titleURL}"
									class="prev-post text-left d-flex align-items-center">
									<div class="icon prev">
										<i class="fa fa-angle-left"></i>
									</div>
									<div class="text">
										<strong class="text-primary">Previous Post </strong>
										<h6>${blogs[0].title}</h6>
									</div>
								</a><a href="${blogs[2].titleURL}"
									class="next-post text-right d-flex align-items-center justify-content-end">
									<div class="text">
										<strong class="text-primary">Next Post </strong>
										<h6>${blogs[2].title}</h6>
									</div>
									<div class="icon next">
										<i class="fa fa-angle-right"> </i>
									</div>
								</a>
							</div>
							<div class="post-comments" id="post-comments">
								<header>
									<h3 class="h6">
										Post Comments<span class="no-of-comments">(${blogs[1].comments.size()})</span>
									</h3>
								</header>
								<c:forEach var="comment" items="${blogs[1].comments}">
									<div class="comment">
										<div class="comment-header d-flex justify-content-between">
											<div class="user d-flex align-items-center">
												<div class="image">
													<img src="../img/user.svg" alt="..."
														class="img-fluid rounded-circle">
												</div>
												<div class="title">
													<strong>${comment.alias}</strong><span class="date">${comment.getDateFormat()}</span>
												</div>
											</div>
										</div>
										<div class="comment-body">
											<p>${comment.text}</p>
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="add-comment">
								<header>
									<h3 class="h6">Leave a reply</h3>
								</header>
								<form:form id="form" class="commenting-form"
									action="comment-save" modelAttribute="comment">
									<form:hidden path="blog.id" />
									<div class="row">
										<div class="form-group col-md-6">
											<form:input id="alias" path="alias" type="text"
												class="form-control" placeholder="Name" />
											<font id="aliasError" style="color: red;"></font>
										</div>
										<div class="form-group col-md-6">
											<form:input id="email" type="email" path="email"
												placeholder="Email Address (will not be published)"
												class="form-control" />
											<font id="emailError" style="color: red;"></font>
										</div>
										<div class="form-group col-md-12">
											<form:textarea id="text" path="text" type="text"
												class="form-control" placeholder="Type your comment" />
											<font id="textError" style="color: red;"></font>
										</div>
										<div class="form-group col-md-12">
											<button type="submit" class="btn btn-secondary"
												onclick="return validate()">Submit Comment</button>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</main>
			<aside class="col-lg-4">
				<!-- Widget [Search Bar Widget]-->
				<div class="widget search">
					<header>
						<h3 class="h6">Search the blog</h3>
					</header>
					<form:form action="../blog-search" modelAttribute="search"
						class="search-form">
						<div class="form-group">
							<form:input path="term" type="text" class="form-control"
								placeholder="What are you looking for?" />
							<button type="submit" class="submit">
								<i class="icon-search"></i>
							</button>
						</div>
					</form:form>
				</div>
				<!-- Widget [Latest Posts Widget]        -->
				<div class="widget latest-posts">
					<header>
						<h3 class="h6">Latest Posts</h3>
					</header>
					<div class="blog-posts">
						<c:forEach var="blog" items="${threeViewsBlogs}">
							<a href="${blog.titleURL}">
								<div class="item d-flex align-items-center">
									<div class="image">
										<img src="../${blog.image}" alt="..." class="img-fluid">
									</div>
									<div class="title">
										<strong>${blog.title}</strong>
										<div class="d-flex align-items-center">
											<div class="views">
												<i class="icon-eye"></i> ${blog.numberViews}
											</div>
											<div class="comments">
												<i class="icon-comment"></i>${blog.comments.size()}
											</div>
										</div>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
				</div>
				<!-- Widget [Categories Widget]-->
				<div class="widget categories">
					<header>
						<h3 class="h6">Categories</h3>
					</header>
					<c:forEach var="category" items="${listCategories}">
						<div class="item d-flex justify-content-between">
							<a
								href="../category/${category.getURLNameWithoutSpace(category.name)}">${category.name}</a><span>${category.blogs.size()}</span>
						</div>
					</c:forEach>
				</div>
				<!-- Widget [Tags Cloud Widget]-->
				<div class="widget tags">
					<header>
						<h3 class="h6">Teams</h3>
					</header>
					<ul class="list-inline">
						<c:forEach var="team" items="${listTeams}">
							<li class="list-inline-item"><a
								href="../team/${team.getURLNameWithoutSpace(team.name)}"
								class="tag">#${team.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</aside>
		</div>
	</div>
	<!--footer-->
	<footer class="main-footer">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="logo">
						<h6 class="text-white">NFL Blog</h6>
					</div>
					<div class="contact-details">
						<p>Luke Stevica 43, Krupanj, SRB 15314</p>
						<p>Phone: (015) 581 738</p>
						<p>
							Email: <a href="mailto:perce@perce.rs">perce@perce.rs</a>
						</p>
						<ul class="social-menu">
							<li class="list-inline-item"><i class="fa fa-facebook"></i></li>
							<li class="list-inline-item"><i class="fa fa-twitter"></i></li>
							<li class="list-inline-item"><i class="fa fa-instagram"></i></li>
							<li class="list-inline-item"><i class="fa fa-behance"></i></li>
							<li class="list-inline-item"><i class="fa fa-pinterest"></i></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="menus d-flex">
						<ul class="list-unstyled">
							<li><a href="../index-page">Home</a></li>
							<li><a href="../blog-page">Blog</a></li>
							<li><a href="../contact-page">Contact</a></li>
							<li><a href="../admin/blog-list">Login</a></li>
						</ul>
						<ul class="list-unstyled">
							<c:forEach var="category" items="${categories}">
								<li><a
									href="../category/${category.getURLNameWithoutSpace(category.name)}">${category.name}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="latest-posts">
						<c:forEach var="blog" items="${lastThreeBlogs}">
							<a href="${blog.titleURL}">
								<div class="post d-flex align-items-center">
									<div class="image">
										<img src="../${blog.image}" alt="..." class="img-fluid">
									</div>
									<div class="title">
										<strong>${blog.title}</strong><span class="date last-meta">${blog.getDateFormatFooter()}</span>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="copyrights">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<p>Copyright &copy; 2021 NFL Blog. All rights reserved.</p>
					</div>
					<div class="col-md-6 text-right">
						<p>
							Template By <a
								href="https://bootstrapious.com/p/bootstrap-carousel"
								class="text-white">Bootstrapious</a>
							<!-- Please do not remove the backlink to Bootstrap Temple unless you purchase an attribution-free license @ Bootstrap Temple or support us at http://bootstrapious.com/donate. It is part of the license conditions. Thanks for understanding :)                         -->
						</p>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- JavaScript files-->
	<script
		src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/popper.js/umd/popper.min.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/jquery.cookie/jquery.cookie.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath}/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/front.js"></script>
	<script src="${pageContext.request.contextPath}/js/validate.js"></script>
</body>
</html>