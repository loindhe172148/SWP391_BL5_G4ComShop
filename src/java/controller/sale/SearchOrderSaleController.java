/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.sale;

import controller.user.BaseRequiredAuthenticationController;
import dal.OrderDAO;
import dal.UserDBContext;
import entity.Account;
import entity.Order;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hbtth
 */
public class SearchOrderSaleController extends BaseRequiredAuthenticationController {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchOrderSaleController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchOrderSaleController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String key = req.getParameter("searchkey");
        OrderDAO db = new OrderDAO();
        List<Order> list = db.findOrderByCustomerNameOrStatusOrOrderdate(key);
        JSONObject json = new JSONObject();
        
        JSONArray j = new JSONArray();
        
        
        for(Order i : list){
            Map<String,String> map = new HashMap<>();
            map.put("ID", i.getId()+"");
            map.put("customername", i.getUser().getName());
            map.put("orderdate", i.getOrderdate().toString());
            map.put("total", i.getTotalamount()+"");
            map.put("address", i.getShippingaddress());
            map.put("status", i.getStatusid());
            j.put(map);
        }
        json.putOnce("orders", j);
        
        resp.getWriter().print(json);
    }

}
