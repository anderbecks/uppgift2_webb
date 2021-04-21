<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light bg-primary">
  <div class="container-fluid">
  <a class="navbar-brand text-light">Weather Search</a>
    <form class="d-flex" action="OWservlet" method="get">
      <input class="form-control me-2" type="text" name="city" placeholder="City" aria-label="Search">
      <input class="form-control me-2" type="text" name="country" placeholder="Country (Language code)" aria-label="Search">
      <button class="btn btn-light btn-outline-success" type="submit">Search</button>
    </form>
  </div>
</nav>



</body>
</html>