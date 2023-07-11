<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.bridgelabz.model.Product"%>
<%@page import="com.bridgelabz.connection.DbCon"%>
<%@page import="com.bridgelabz.dao.ProductDao"%>
<%@page import="com.bridgelabz.model.User"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProduct();
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Welcome To Shopping cart!</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<%
	out.print(DbCon.getConnection());
	%>
	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-image/<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">7
							Price:$
							<%=p.getPrice()%></h6>
						<h6 class="category">
							Category:
							<%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark">Add to Cart</a> 
						    <a href="order-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary ">Buy Now </a>
						</div>
					</div>
				</div>
			</div>
			<%
			//out.println(p.getCategory());
			}
			}
			%>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>