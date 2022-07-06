<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>NFL</title>
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



<!-- owl carousel 2 stylesheet-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/owl-carousel2/assets/owl.carousel.min.css"
	id="theme-stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/owl-carousel2/assets/owl.theme.default.min.css"
	id="theme-stylesheet">
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
					<a href="" class="navbar-brand">NFL Blog</a>
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
						<li class="nav-item"><a href="" class="nav-link active">Home</a>
						</li>
						<li class="nav-item"><a href="blog-page" class="nav-link">Blog</a>
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

	<!-- Hero Section-->
	<div id="index-slider" class="owl-carousel">
		<c:forEach var="division" items="${divisionList}">
			<section
				style="background: url(${division.image}); background-size: cover; background-position: center center"
				class="hero">
				<div class="container">
					<div class="row">
						<div class="col-lg-7">
							<h1 style="color: black;">${division.title}</h1>
							<a href="${division.urlButton}" class="hero-link">${division.titleButton}</a>
						</div>
					</div>
				</div>
			</section>
		</c:forEach>
	</div>

	<!-- Intro Section-->
	<section class="intro">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<h2 class="h3">About NFL</h2>
					<p class="text-big">National Football League (NFL), major U.S.
						professional gridiron football organization, founded in 1920 in
						Canton, Ohio, as the American Professional Football Association.
						Its first president was Jim Thorpe, an outstanding American
						athlete who was also a player in the league. The present name was
						adopted in 1922.</p>
				</div>
			</div>
		</div>
	</section>
	<section class="featured-posts no-padding-top">
		<div class="container">
			<!-- Post-->
			<c:forEach var="blog" items="${importantBlogList}" varStatus="loop">
				<c:if test="${loop.index % 2 == 0}">
					<div class="row d-flex align-items-stretch">
						<div class="text col-lg-7">
							<div class="text-inner d-flex align-items-center">
								<div class="content">
									<header class="post-header">
										<div class="category">
											<a
												href="category/${blog.category.getURLNameWithoutSpace(blog.category.name)}">${blog.category.name}</a>
										</div>
										<a href="post/${blog.titleURL}">
											<h2 class="h4">${blog.title}</h2>
										</a>
									</header>
									<p>${blog.getBlogDescriptionShort()}</p>
									<footer class="post-footer d-flex align-items-center">
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
											<i class="icon-clock"></i> ${blog.getDateFormat()}
										</div>
										<div class="comments">
											<i class="icon-comment"></i>${blog.comments.size()}
										</div>
									</footer>
								</div>
							</div>
						</div>
						<div class="image col-lg-5">
							<img src="${blog.image}" alt="...">
						</div>
					</div>
				</c:if>
				<c:if test="${loop.index % 2 != 0}">
					<div class="row d-flex align-items-stretch">
						<div class="image col-lg-5">
							<img src="${blog.image}" alt="...">
						</div>
						<div class="text col-lg-7">
							<div class="text-inner d-flex align-items-center">
								<div class="content">
									<header class="post-header">
										<div class="category">
											<a
												href="category/${blog.category.getURLNameWithoutSpace(blog.category.name)}">${blog.category.name}</a>
										</div>
										<a href="post/${blog.titleURL}">
											<h2 class="h4">${blog.title}</h2>
										</a>
									</header>
									<p>${blog.getBlogDescriptionShort()}</p>
									<footer class="post-footer d-flex align-items-center">
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
											<i class="icon-clock"></i> ${blog.getDateFormat()}
										</div>
										<div class="comments">
											<i class="icon-comment"></i>${blog.comments.size()}
										</div>
									</footer>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<!-- Post        -->
		</div>
	</section>
	<!-- Divider Section-->
	<section
		style="background: url(img/featured-pic-1.jpeg); background-size: cover; background-position: center bottom"
		class="divider">
		<div class="container">
			<div class="row">
				<div class="col-md-7">
					<h2>A blog that is intended to bring together all the news
						related to the NFL. If you have any new information, please
						contact us.</h2>
					<a href="contact-page" class="hero-link">Contact Us</a>
				</div>
			</div>
		</div>
	</section>
	<!-- Latest Posts -->
	<section class="latest-posts">
		<div class="container">
			<header>
				<h2>Latest from the blog</h2>
				<p class="text-big">A list of the latest blogs related to the
					NFL</p>
			</header>

			<div class="owl-carousel" id="latest-posts-slider">
				<c:set var="count" value="0" scope="page" />
				<c:forEach begin="0" end="${loopNumber}" varStatus="loop">
					<div class="row">
						<c:forEach begin="0" end="${2}">
							<c:if test="${not empty twelveBlogList[count].image}">
								<div class="post col-md-4">
									<div class="post-thumbnail">
										<a href="post/${twelveBlogList[count].titleURL}"><img src="${twelveBlogList[count].image}" alt="..."
											class="img-fluid"></a>
									</div>
									<div class="post-details">
										<div class="post-meta d-flex justify-content-between">
											<div class="date">${twelveBlogList[count].getDateFormatTwelve()}</div>
											<div class="category">
												<a
													href="category/${twelveBlogList[count].category.getURLNameWithoutSpace(twelveBlogList[count].category.name)}">${twelveBlogList[count].category.name}</a>
											</div>
										</div>
											<a href="post/${twelveBlogList[count].titleURL}">
												<h3 class="h4">${twelveBlogList[count].title}</h3>
											</a>
										<p class="text-muted">${twelveBlogList[count].getBlogDescriptionShort()}</p>
									</div>
								</div>
							</c:if>
							<c:set var="count" value="${count + 1}" scope="page" />
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
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
							<li><a href="">Home</a></li>
							<li><a href="blog-page">Blog</a></li>
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
	<script src="${pageContext.request.contextPath}/js/submit.js"></script>


	<script
		src="${pageContext.request.contextPath}/plugins/owl-carousel2/owl.carousel.min.js"></script>
	<script>
		$("#index-slider").owlCarousel({
			"items" : 1,
			"loop" : true,
			"autoplay" : true,
			"autoplayHoverPause" : true
		});

		$("#latest-posts-slider").owlCarousel({
			"items" : 1,
			"loop" : true,
			"autoplay" : true,
			"autoplayHoverPause" : true
		});
	</script>

</body>
</html>