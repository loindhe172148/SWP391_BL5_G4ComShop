/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.sale;

import controller.user.BaseRequiredAuthenticationController;
import dal.OrderDAO;
import entity.Account;
import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author hbtth
 */
public class SaleOrderController extends BaseRequiredAuthenticationController {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaleOrderController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleOrderController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while((line = reader.readLine()) != null){
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
        int orderId = json.getInt("id");
        String status = json.getString("status");
        OrderDAO db = new OrderDAO();
        json.clear();
        boolean check = db.changeStatusOrder(orderId, status);
        if(check){
            json.put("code", "00");
            json.put("message", status);
        }else{
            json.put("code", "01");
            json.put("message", "Can't approved this order because have a product which is't enough quantity in stock");
        }
        resp.getWriter().print(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        OrderDAO db = new OrderDAO();
        List<Order> list = db.getListOrder();
        req.setAttribute("listOrder", list);
        req.getRequestDispatcher("/view/sale/SaleOrderList.jsp").forward(req, resp);
    }

}
