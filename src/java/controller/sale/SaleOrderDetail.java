/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.sale;

import controller.user.BaseRequiredAuthenticationController;
import dal.OrderDAO;
import entity.Account;
import entity.Order;
import entity.OrderProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hbtth
 */
public class SaleOrderDetail extends BaseRequiredAuthenticationController {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaleOrderDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleOrderDetail at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        OrderDAO db = new OrderDAO();
        Order o = db.getOrderByID(id);
        for(OrderProduct i : o.getListOrderProduct()){
            System.out.println(i.getProductdetails().getProduct().getImage());
        }
        req.setAttribute("order", o);
        req.getRequestDispatcher("/view/sale/SaleOrderDetailList.jsp").forward(req, resp);
    }

}

