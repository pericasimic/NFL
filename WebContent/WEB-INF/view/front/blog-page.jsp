<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet"
	href="vendor/font-awesome/css/font-awesome.min.css">
<!-- Custom icon font-->
<link rel="stylesheet" href="css/fontastic.css">
<!-- Google fonts - Open Sans-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<!-- Fancybox-->
<link rel="stylesheet"
	href="vendor/@fancyapps/fancybox/jquery.fancybox.min.css">
<!-- theme stylesheet-->
<link rel="stylesheet" href="css/style.default.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="css/custom.css">
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
							<form:form action="blog-search" modelAttribute="search">
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
					<a href="index-page" class="navbar-brand">NFL Blog</a>
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
						<li class="nav-item"><a href="index-page" class="nav-link">Home</a>
						</li>
						<li class="nav-item"><a href="" class="nav-link active">Blog</a>
						</li>
						<li class="nav-item"><a href="contact-page" class="nav-link">Contact</a>
						</li>
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
			<main class="posts-listing col-lg-8">
				<div class="container">
					<div class="row">
						<!-- post -->
						<c:forEach var="blog" items="${lastTwelveBlogs}">
							<div class="post col-xl-6">
								<div class="post-thumbnail">
									<a href="post/${blog.titleURL}"><img
										src="${blog.image}" alt="..." class="img-fluid"></a>
								</div>
								<div class="post-details">
									<div class="post-meta d-flex justify-content-between">
										<div class="date meta-last">${blog.getDateFormatTwelve()}</div>
										<div class="category">
											<a
												href="category/${blog.category.getURLNameWithoutSpace(blog.category.name)}">${blog.category.name}</a>
										</div>
									</div>
									<a href="post/${blog.titleURL}">
										<h3 class="h4">${blog.title}</h3>
									</a>
									<p class="text-muted">${blog.description}</p>
									<div class="post-footer d-flex align-items-center">
										<a href="blog/${blog.author.getNameURL()}"
											class="author d-flex align-items-center flex-wrap">
											<div class="avatar">
												<img src="${blog.author.image}" alt="..." class="img-fluid">
											</div>
											<div class="title">
												<span>${blog.author.name}&nbsp;${blog.author.lastname}</span>
											</div>
										</a>
										<div class="date">
											<i class="icon-clock"></i>${blog.getDateFormat()}
										</div>
										<div class="comments meta-last">
											<i class="icon-comment"></i>${blog.comments.size()}
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- Pagination -->
					<c:set var="count" value="${activeNumber}" scope="page" />
					<nav aria-label="Page navigation example">
						<ul
							class="pagination pagination-template d-flex justify-content-center">
							<c:if test="${count == 1}">
								<li class="page-item"><a
									href="blog-page-item?id=${activeNumber}" class="page-link">
										<i class="fa fa-angle-left"></i>
								</a></li>
							</c:if>
							<c:if test="${count > 1}">
								<li class="page-item"><a
									href="blog-page-item?id=${count-1}" class="page-link"> <i
										class="fa fa-angle-left"></i></a></li>
							</c:if>
							<c:forEach begin="1" end="${pageNumber}" varStatus="loop">
								<c:if test="${activeNumber == loop.index}">
									<li class="page-item"><a
										href="blog-page-item?id=${loop.index}"
										class="page-link active">${loop.index}</a></li>
								</c:if>
								<c:if test="${activeNumber != loop.index}">
									<li class="page-item"><a
										href="blog-page-item?id=${loop.index}" class="page-link">${loop.index}</a></li>
								</c:if>
							</c:forEach>
							<c:if test="${count != pageNumber}">
								<li class="page-item"><a
									href="blog-page-item?id=${count+1}" class="page-link"> <i
										class="fa fa-angle-right"></i></a></li>
							</c:if>
							<c:if test="${count == pageNumber}">
								<li class="page-item"><a
									href="blog-page-item?id=${activeNumber}" class="page-link">
										<i class="fa fa-angle-right"></i>
								</a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</main>
			<aside class="col-lg-4">
				<!-- Widget [Search Bar Widget]-->
				<div class="widget search">
					<header>
						<h3 class="h6">Search the blog</h3>
					</header>
					<form:form action="blog-search" modelAttribute="search"
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
							<a href="post/${blog.titleURL}">
								<div class="item d-flex align-items-center">
									<div class="image">
										<img src="${blog.image}" alt="..." class="img-fluid">
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
							<a href="category/${category.getURLNameWithoutSpace(category.name)}">${category.name}</a><span>${category.blogs.size()}</span>
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
							<li class="list-inline-item"><a href="team/${team.getURLNameWithoutSpace(team.name)}"
								class="tag">#${team.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</aside>
		</div>
	</div>
	<!-- Page Footer-->
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
							<li><a href="index-page">Home</a></li>
							<li><a href="">Blog</a></li>
							<li><a href="contact-page">Contact</a></li>
							<li><a href="admin/blog-list">Login</a></li>
						</ul>
						<ul class="list-unstyled">
							<c:forEach var="category" items="${categories}">
								<li><a
									href="category/${category.getURLNameWithoutSpace(category.name)}">${category.name}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="latest-posts">
						<c:forEach var="blog" items="${lastThreeBlogs}">
							<a href="post/${blog.titleURL}">
								<div class="post d-flex align-items-center">
									<div class="image">
										<img src="${blog.image}" alt="..." class="img-fluid">
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
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/popper.js/umd/popper.min.js">
		
	</script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="vendor/jquery.cookie/jquery.cookie.js">
		
	</script>
	<script src="vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
	<script src="js/front.js"></script>
</body>
</html>