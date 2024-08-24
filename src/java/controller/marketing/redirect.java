
package controller.marketing;

import dal.CPUDAO;
import dal.CardDAO;
import dal.CategoryDAO;
import dal.ProductWithDetailsDAO;
import dal.RAMDAO;
import dal.ProductTypeDAO;
import entity.CPU;
import entity.Card;
import entity.Category;
import entity.Product;
import entity.RAM;
import entity.ProductType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "redirect", urlPatterns = {"/redirect",
    "/marketing/blankpage", "/marketing/datatable", "/marketing/form"})
public class redirect extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        try (PrintWriter out = response.getWriter()) {
            switch (action) {
                // template    
                case "/marketing/blankpage":
                    request.getRequestDispatcher("/view/marketing/BlankPage.jsp").forward(request, response);
                    break;
                case "/marketing/datatable":
                    request.getRequestDispatcher("/view/marketing/data-table.jsp").forward(request, response);
                    break;
                case "/marketing/form":
                    request.getRequestDispatcher("/view/marketing/form-page.jsp").forward(request, response);
                    break;

                // tam test   
                case "/marketing/dashboard":
                    
                    request.getRequestDispatcher("/view/marketing/dashboard.jsp").forward(request, response);
                    break;
                case "/marketing/productlist":
                    request.getRequestDispatcher("/view/marketing/ProductList.jsp").forward(request, response);
                    break;
                case "/marketing/productdetails":
                    request.getRequestDispatcher("/view/marketing/ProductDetails.jsp").forward(request, response);
                    break;
                case "/marketing/addproduct":
                    request.getRequestDispatcher("/view/marketing/AddProduct.jsp").forward(request, response);
                    break;

                default:
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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

