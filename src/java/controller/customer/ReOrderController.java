/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import controller.user.BaseRequiredAuthenticationController;
import dal.CartDao;
import dal.OrderDAO;
import dal.UserDBContext;
import entity.Account;
import entity.Order;
import entity.OrderProduct;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import util.EmailUtils;

/**
 *
 * @author hbtth
 */
public class ReOrderController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReOrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        Account account1 = (Account) req.getSession().getAttribute("account");
        OrderDAO db = new OrderDAO();
        int id = Integer.parseInt(req.getParameter("id"));
        // Get receiver information from the form
        String fullName = req.getParameter("fullname");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        String notes = req.getParameter("notes");
        // Compose the order details into an email content
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(fullName).append(",\n\n");
        emailContent.append("Thank you for your order. Here are the details:\n\n");
        emailContent.append("Products:\n");
        Order o = db.getOrderByID(id);
        float totalamount =0;
        for (OrderProduct i : o.getListOrderProduct()) {
            String productname = i.getProductdetails().getProduct().getName();
            int quantity = i.getQuantity();
            float price = i.getPrice();
            float total = quantity * price;
            totalamount+=total;
            emailContent.append("- ").append(productname)
                    .append(" (Original Price: $").append(String.format("%.2f", price))
                    .append(", Quantity: ").append(quantity)
                    .append(", Total: $").append(String.format("%.2f", total))
                    .append(")\n");
        }
        emailContent.append("\nTotal Price: $").append(String.format("%.2f", totalamount)).append("\n");
        emailContent.append("\nDelivery Address: ").append(address).append("\n");
        emailContent.append("Mobile: ").append(mobile).append("\n");
        emailContent.append("Notes: ").append(notes).append("\n\n");
        emailContent.append("We will process your order and keep you updated.");
        String subject = "Your Order Confirmation";
        boolean emailSent = EmailUtils.sendMail(email, subject, emailContent.toString());
        if (emailSent) {
            o.setTotalamount(totalamount);
            db.reOrder(o, account1.getId());
            req.setAttribute("orderSuccess", true);
            req.getRequestDispatcher("/productHome?id="+account1.getId()).forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Failed to send order confirmation email. Please try again.");
            req.getRequestDispatcher("/view/customer/cartContact.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        OrderDAO db = new OrderDAO();
        Account account1 = (Account) req.getSession().getAttribute("account");
        UserDBContext userDao = new UserDBContext();
        User loggedInUser = userDao.getUserByAccountId(account1.getId());
        req.setAttribute("order", db.getOrderByID(id));
        req.setAttribute("user", loggedInUser);
        req.getRequestDispatcher("/view/customer/ReOrder.jsp").forward(req, resp);
    }

}
