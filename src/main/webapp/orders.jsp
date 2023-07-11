<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.bridgelabz.model.User"%>
<% User auth = (User)request.getSession().getAttribute("auth");
   if(auth != null){
	   //request.setAttribute("auth",auth);
	   //response.sendRedirect("index.jsp");
   }
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Orders Page</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
	<%@include file="includes/footer.jsp"%>
</body>
</html>