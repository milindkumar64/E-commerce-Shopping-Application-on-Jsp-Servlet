package com.bridgelabz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.connection.DbCon;
import com.bridgelabz.dao.OrderDao;
import com.bridgelabz.model.Cart;
import com.bridgelabz.model.Order;
import com.bridgelabz.model.User;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User auth = (User) request.getSession().getAttribute("auth");

            if (auth != null) {
            	out.println("auth1");
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                	productQuantity = 1;
                }
                out.println("auth2");
                Order orderModel = new Order();
                out.println("auth4");
                //orderModel.setId(Integer.parseInt(productId));
                out.println("auth4");
                orderModel.setUid(auth.getId());
                out.println("auth5");
                orderModel.setQunatity(productQuantity);
                orderModel.setDate(formatter.format(date));
                out.println("auth6");
                OrderDao orderDao = new OrderDao(DbCon.getConnection());
                boolean result = orderDao.insertOrder(orderModel);
                out.println(result);
                if (result) {
					/*
					 * ArrayList<Cart> cart_list = (ArrayList<Cart>)
					 * request.getSession().getAttribute("cart-list"); if (cart_list != null) { for
					 * (Cart c : cart_list) { if (c.getId() == Integer.parseInt(productId)) {
					 * cart_list.remove(cart_list.indexOf(c)); break; } } }
					 */
                	out.print("auth4");
                    response.sendRedirect("orders.jsp");
                } else {
                    out.println("order failed");
                }
            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { try(PrintWriter out =
	 * response.getWriter()){ //out.print("this buy now servlet !");
	 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); Date date =
	 * new Date(); User auth = (User)request.getSession().getAttribute("auth");
	 * out.print(auth); if(auth != null) { String productId =
	 * request.getParameter("id"); int productQuantity =
	 * Integer.parseInt(request.getParameter("quantity")); if(productQuantity<=0) {
	 * productQuantity=1; }
	 * 
	 * Order orderModel = new Order();
	 * orderModel.setId(Integer.parseInt(productId));
	 * orderModel.setUid(auth.getId()); orderModel.setQunatity(productQuantity);
	 * orderModel.setDate(formatter.format(date)); out.println("orderServlet");
	 * OrderDao orderDao = new OrderDao(DbCon.getConnection()); boolean result =
	 * orderDao.insertOrder(orderModel); if(result) { out.println(result);
	 * response.sendRedirect("orders.jsp"); }else { out.print("order failed"); }
	 * }else { response.sendRedirect("login.jsp"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
