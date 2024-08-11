package controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.EmailService;

import java.io.IOException;

@WebServlet(name = "VerifyAccount", urlPatterns = {"/verify"})
public class VerifyAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        String str = "0123456789";
        String pass = EmailService.generatePassword(str, 6);
        EmailService.sendEmail(email, "Your passcode confirm is: " + pass);
        request.getSession().setAttribute("passcode", pass);
        request.getRequestDispatcher("./view/verify.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String passcode = (String) request.getSession().getAttribute("passcode");
            String passconfirm = request.getParameter("passconfirm");
            if (!passconfirm.equals(passcode)) {
                request.setAttribute("err", "passcode doesn't match!");
                request.getRequestDispatcher("./view/verify.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("status", "confirm");
                request.getRequestDispatcher("register").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occured. Please try again later!");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
