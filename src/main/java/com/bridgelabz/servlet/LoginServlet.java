package com.bridgelabz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.connection.DbCon;
import com.bridgelabz.dao.UserDao;
import com.bridgelabz.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("This is Login Servlet");
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");
			// out.println(email+"..."+password);

			try {
				UserDao uDao = new UserDao(DbCon.getConnection());
			    User user=uDao.userLogin(email, password);
				if(user != null) {
					out.println("User login sucessfully! ");
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
				}else {
				   out.println("user Login failed !");	
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}