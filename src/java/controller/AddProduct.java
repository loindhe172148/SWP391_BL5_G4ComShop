/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CPUDAO;
import dal.CardDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import dal.RAMDAO;
import dal.TypeDAO;
import entity.CPU;
import entity.Card;
import entity.Category;
import entity.Product;
import entity.RAM;
import entity.Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xuant
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/admin/addnew"})
public class AddProduct extends HttpServlet {

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
            // Initialize DAOs
            ProductDAO productDAO = new ProductDAO();
            CPUDAO cpuDAO = new CPUDAO();
            CardDAO cardDAO = new CardDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            TypeDAO typeDAO = new TypeDAO();
            RAMDAO ramDAO = new RAMDAO();

            // Fetch lists for dropdowns
            List<CPU> cpuList = cpuDAO.getAllCPU();
            List<Card> cardList = cardDAO.getAllCard();
            List<Category> categoryList = categoryDAO.getAllCategory();
            List<Type> typeList = typeDAO.getAllType();
            List<RAM> ramList = ramDAO.getAllRAM();

            // Set dropdown data in request attributes
            request.setAttribute("cpuList", cpuList);
            request.setAttribute("cardList", cardList);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("typeList", typeList);
            request.setAttribute("ramList", ramList);

            // Collect form data
            String name = request.getParameter("product-name");
            String description = request.getParameter("product-description");
            String status = request.getParameter("status");
            String originPriceStr = request.getParameter("product-originprice");
            String salePriceStr = request.getParameter("product-saleprice");
            String categoryIdStr = request.getParameter("product-category");
            String capacityStr = request.getParameter("product-capacity");
            String sizeStr = request.getParameter("product-screensize");
            String color = request.getParameter("product-color");
            String cpuIdStr = request.getParameter("product-cpu");
            String cardIdStr = request.getParameter("product-card");
            String ramIdStr = request.getParameter("product-ram");
            String typeIdStr = request.getParameter("product-type");
            String quantityStr = request.getParameter("product-quantity");

            // Initialize error map
            Map<String, String> errorMap = new HashMap<>();

            // Validation
            if (name == null || name.trim().isEmpty()) {
                errorMap.put("productName", "Product name is required.");
            }
            if (description == null || description.trim().isEmpty()) {
                errorMap.put("productDescription", "Product description is required.");
            }
            if (originPriceStr == null || originPriceStr.trim().isEmpty()) {
                errorMap.put("originPrice", "Origin price is required.");
            }
            if (salePriceStr == null || salePriceStr.trim().isEmpty()) {
                errorMap.put("salePrice", "Sale price is required.");
            }
            if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
                errorMap.put("category", "Category is required.");
            }
            if (capacityStr == null || capacityStr.trim().isEmpty()) {
                errorMap.put("capacity", "Capacity is required.");
            }
            if (sizeStr == null || sizeStr.trim().isEmpty()) {
                errorMap.put("screenSize", "Screen size is required.");
            }
            if (color == null || color.trim().isEmpty()) {
                errorMap.put("color", "Color is required.");
            }
            if (cpuIdStr == null || cpuIdStr.trim().isEmpty()) {
                errorMap.put("cpu", "CPU is required.");
            }
            if (cardIdStr == null || cardIdStr.trim().isEmpty()) {
                errorMap.put("card", "Card is required.");
            }
            if (ramIdStr == null || ramIdStr.trim().isEmpty()) {
                errorMap.put("ram", "RAM is required.");
            }
            if (typeIdStr == null || typeIdStr.trim().isEmpty()) {
                errorMap.put("type", "Type is required.");
            }
            if (quantityStr == null || quantityStr.trim().isEmpty()) {
                errorMap.put("quantity", "Quantity is required.");
            }

            // Check if there are errors
            if (!errorMap.isEmpty()) {
                request.setAttribute("error", errorMap);
                request.getRequestDispatcher("add-product.jsp").forward(request, response);
                return;
            }

            // Parse numeric fields
            double originPrice = Double.parseDouble(originPriceStr);
            double salePrice = Double.parseDouble(salePriceStr);
            int categoryId = Integer.parseInt(categoryIdStr);
            double capacity = Double.parseDouble(capacityStr);
            double size = Double.parseDouble(sizeStr);
            int cpuId = Integer.parseInt(cpuIdStr);
            int cardId = Integer.parseInt(cardIdStr);
            int ramId = Integer.parseInt(ramIdStr);
            int typeId = Integer.parseInt(typeIdStr);
            int quantity = Integer.parseInt(quantityStr);
            int pharseStatus = status.equals("show") ? 1 : 0;

            // Create Product object and set fields
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setStatus(pharseStatus);
            product.setOriginPrice(originPrice);
            product.setSalePrice(salePrice);
            product.setCategoryId(categoryId);
            product.setCapacity(capacity);
            product.setSize(size);
            product.setColor(color);
            product.setCpuId(cpuId);
            product.setCardId(cardId);
            product.setRamId(ramId);
            product.setTypeId(typeId);
            product.setQuantity(quantity);

            // Add the product to the database
            productDAO.addProduct(product);

            // Success message
            request.setAttribute("message", "Product added successfully!");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
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
