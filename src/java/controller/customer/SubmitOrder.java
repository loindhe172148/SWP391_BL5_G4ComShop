package controller.customer;

import controller.user.BaseRequiredAuthenticationController;
import dal.CartDao;
import dal.UserDBContext;
import entity.Account;
import entity.Product;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import util.EmailUtils;

@WebServlet(name = "SubmitOrder", urlPatterns = {"/customer/SubmitOrder"})
public class SubmitOrder extends BaseRequiredAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Handles the submission of orders and sending confirmation emails.";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        // Retrieve the logged-in account
        Account account1 = (Account) req.getSession().getAttribute("account");

        if (account1 == null) {
            resp.sendRedirect("login");
            return;
        }

        // Retrieve the user details using the account ID
        UserDBContext userDao = new UserDBContext();
        User loggedInUser = userDao.getUserByAccountId(account1.getId());

        if (loggedInUser == null) {
            resp.sendRedirect("login");
            return;
        }

        // Get receiver information from the form
        String fullName = req.getParameter("fullname");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        String notes = req.getParameter("notes");

        // Retrieve cart items
        CartDao cartDao = new CartDao();
        Map<Integer, Map<Product, Double>> cartItems = cartDao.getCartItemsByUserId(account1.getId());

        // Compose the order details into an email content
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(fullName).append(",\n\n");
        emailContent.append("Thank you for your order. Here are the details:\n\n");

        double totalPrice = 0.0;

        if (cartItems != null && !cartItems.isEmpty()) {
            emailContent.append("Products:\n");
            for (Map.Entry<Integer, Map<Product, Double>> entry : cartItems.entrySet()) {
                Map<Product, Double> productWithPrice = entry.getValue();
                for (Map.Entry<Product, Double> productEntry : productWithPrice.entrySet()) {
                    Product product = productEntry.getKey();
                    double price = productEntry.getValue();
                    int quantityInCart = product.getQuantity();
                    double productTotal = price * quantityInCart;
                    totalPrice += productTotal;

                    // Append product details to the email content
                    emailContent.append("- ").append(product.getName())
                            .append(" (Original Price: $").append(String.format("%.2f", price))
                            .append(", Quantity: ").append(quantityInCart)
                            .append(", Total: $").append(String.format("%.2f", productTotal))
                            .append(")\n");
                }
            }
        }

        emailContent.append("\nTotal Price: $").append(String.format("%.2f", totalPrice)).append("\n");
        emailContent.append("\nDelivery Address: ").append(address).append("\n");
        emailContent.append("Mobile: ").append(mobile).append("\n");
        emailContent.append("Notes: ").append(notes).append("\n\n");
        emailContent.append("We will process your order and keep you updated.");

        // Send the email
        String subject = "Your Order Confirmation";
        boolean emailSent = EmailUtils.sendMail(email, subject, emailContent.toString());
        if (emailSent) {
            req.getSession().removeAttribute("cartItems");
            cartDao.deleteFromCart1(account1.getId());
            req.setAttribute("orderSuccess", true);
            req.getRequestDispatcher("/productHome?id="+account1.getId()).forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Failed to send order confirmation email. Please try again.");
            req.getRequestDispatcher("/view/customer/cartContact.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
