package controller.user;

import dal.CartDao;
import dal.UserDBContext;
import entity.Account;
import entity.Product;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.EmailUtils;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "SubmitOrder", urlPatterns = {"/SubmitOrder"})
public class SubmitOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the logged-in account
        Account account = (Account) request.getSession().getAttribute("account");

        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        // Retrieve the user details using the account ID
        UserDBContext userDao = new UserDBContext();
        User loggedInUser = userDao.getUserByAccountId(account.getId());

        if (loggedInUser == null) {
            response.sendRedirect("login");
            return;
        }

        // Get receiver information from the form
        String fullName = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");

        // Retrieve cart items
        CartDao cartDao = new CartDao();
        Map<Integer, Map<Product, Double>> cartItems = cartDao.getCartItemsByUserId(loggedInUser.getId());

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

                    emailContent.append("- ").append(product.getName())
                            .append(" (Quantity: ").append(quantityInCart)
                            .append(", Price: $").append(String.format("%.2f", productTotal))
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
            // Clear the cart in the database and session
           
            request.getSession().removeAttribute("cartItems");

            // Redirect to an order confirmation page
            response.sendRedirect("order-confirmation.jsp");
        } else {
            // Handle email sending failure (show error message, retry, etc.)
            request.setAttribute("errorMessage", "Failed to send order confirmation email. Please try again.");
            request.getRequestDispatcher("./view/user/cartContact.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles the submission of orders and sending confirmation emails.";
    }
}
