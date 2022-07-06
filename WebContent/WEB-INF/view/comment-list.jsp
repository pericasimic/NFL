<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>NFL Blog</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/nfl.ico" type="image/x-icon"/>
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
			</ul>


			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">
				<!-- Messages Dropdown Menu -->
				<li class="nav-item dropdown"><a class="nav-link"
					data-toggle="dropdown" href="#"> <i class="far fa-user"></i>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<a href="user-edit-profile" class="dropdown-item"> <!-- Message Start -->
							<div class="media align-items-center">
								<img
									src="${pageContext.request.contextPath}/${loginAuthor.image}"
									alt="User Avatar" class="img-brand-50 mr-3 img-circle" style="width: 128px"; height="128px">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										<sec:authentication property="principal.username" />
									</h3>
								</div>
							</div> <!-- Message End -->
						</a>
						<div class="dropdown-divider"></div>
						<a href="author-change-password" class="dropdown-item"> <i class="fas fa-key"></i>
							Change password
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item"> <form:form
								action="${pageContext.request.contextPath}/logout">
								<i class="fas fa-sign-out-alt"></i>
								<input type="submit" value="Sign out">
							</form:form>
						</a>
					</div></li>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- User Logo -->
			<a href="author-list" class="brand-link"> <img
				src="${pageContext.request.contextPath}/dist/img/nfl-img.png"
				alt="NFL Logo" class="brand-image img-circle elevation-3"
				style="opacity: .8"> <span
				class="brand-text font-weight-light">NFL Blog</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
               <sec:authorize access="hasRole('admin')">
						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>
									Authors <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="author-list" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Authors list</p>
								</a></li>
								<li class="nav-item"><a href="author-form" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Add Author</p>
								</a></li>
							</ul></li>
							</sec:authorize>
						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>
									Blogs <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="blog-list" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Blogs list</p>
								</a></li>
								<li class="nav-item"><a href="blog-form" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Add Blog</p>
								</a></li>
							</ul></li>
						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>
									Categories <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="category-list"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Categories list</p>
								</a></li>
								<li class="nav-item"><a href="category-form"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Add Category</p>
								</a></li>
							</ul></li>
						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>
									Divisions <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="division-list"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Divisions list</p>
								</a></li>
								<li class="nav-item"><a href="division-form"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Add Division</p>
								</a></li>
							</ul></li>
						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>
									Teams <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="team-list" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Teams list</p>
								</a></li>
								<li class="nav-item"><a href="team-form" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Add Team</p>
								</a></li>
							</ul></li>
						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>
									Contact <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="contact-list"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Contact list</p>
								</a></li>
							</ul></li>
						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>
									Comments <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="comment-list"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Comments list</p>
								</a></li>
							</ul></li>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>Comments</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<sec:authorize access="hasRole('admin')">
									<li class="breadcrumb-item"><a href="author-list">Authors</a></li>
								</sec:authorize>
								<li class="breadcrumb-item"><a href="blog-list">Blogs</a></li>
								<li class="breadcrumb-item"><a href="category-list">Categories</a></li>
								<li class="breadcrumb-item"><a href="division-list">Divisions</a></li>
								<li class="breadcrumb-item"><a href="team-list">Teams</a></li>
								<li class="breadcrumb-item"><a href="contact-list">Contacts</a></li>
								<li class="breadcrumb-item"><a href="comment-list">Comments</a></li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Search Comments</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<form:form method="post" id="entities-filter-form"
										action="comment-search">
										<div class="row">
											<div class="col-md-3 form-group">
												<label>ID Blog</label>
												<div class="input-group input-group-sm">
													<input type="number" name="id" value="" required="required" class="form-control"
														placeholder="Search by blog ID">
													<div class="input-group-append">
														<button class="btn btn-navbar" type="submit">
															<i class="fas fa-search"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- /.card-body -->
								<div class="card-footer clearfix"></div>
							</div>
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">All Comments</h3>
									<div class="card-tools"></div>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th style="width: 10px">#</th>
												<th style="width: 20px">Status</th>
												<th class="text-center">Alias</th>
												<th>Date</th>
												<th>Comment</th>
												<th>ID Blog</th>
												<th class="text-center">Actions</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="comment" items="${commentList}"
												varStatus="loop">
												<tr>
													<td>${loop.index + 1}</td>
													<c:if test="${comment.enabled == true}">
														<td class="text-center"><span class="text-success">enabled</span>
														</td>
													</c:if>
													<c:if test="${comment.enabled == false}">
														<td class="text-center"><span class="text-danger">disabled</span>
														</td>
													</c:if>
													<td class="text-center">${comment.alias}</td>
													<td>${comment.date}</td>
													<td><strong>${comment.text}</strong></td>
													<td><a href="comment-blogId?id=${comment.blog.id}">${comment.blog.id}</a></td>
													<td class="text-center">
														<div class="btn-group">
															<a href="comment-enable?id=${comment.id}"
																class="btn btn-info"> <c:if
																	test="${comment.enabled}">
																	<i class="fas fa-eye"></i>
																</c:if> <c:if test="${!comment.enabled}">
																	<i class="fas fa-eye-slash"></i>
																</c:if>
															</a>
														</div>
													</td>
												</tr>

											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
								<div class="card-footer clearfix"></div>
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->



		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="float-right d-none d-sm-inline">Spring</div>
			<!-- Default to the left -->
			<strong>Copyright &copy; 2021 <a href="#">NFL Blog</a>.
			</strong> All rights reserved.
		</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
</body>
</html>

