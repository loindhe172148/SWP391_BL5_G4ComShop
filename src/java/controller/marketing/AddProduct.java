/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xuant
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/view/marketing/addnew"})
@MultipartConfig
public class AddProduct extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "assets/images/products";
    private static final String FOLDER_DIRECTORY = "web/view/marketing/assets/images/products";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            CPUDAO cpuDAO = new CPUDAO();
            List<CPU> cpuList;
            cpuList = cpuDAO.getAllCPU();
            request.setAttribute("cpuList", cpuList);

            CardDAO cardDAO = new CardDAO();
            List<Card> cardList;
            cardList = cardDAO.getAllCard();
            request.setAttribute("cardList", cardList);

            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categoryList;
            categoryList = categoryDAO.getAllCategory();
            request.setAttribute("categoryList", categoryList);

            TypeDAO typeDAO = new TypeDAO();
            List<Type> typeList;
            typeList = typeDAO.getAllType();
            request.setAttribute("typeList", typeList);

            RAMDAO ramDAO = new RAMDAO();
            List<RAM> ramList;
            ramList = ramDAO.getAllRAM();
            request.setAttribute("ramList", ramList);

            Map<String, String> errors = new HashMap<>();

            // Validate required fields
            String name = request.getParameter("product-name").trim();
            String description = request.getParameter("product-description").trim();
            String statusStr = request.getParameter("status");
            String originPriceStr = request.getParameter("product-originprice").trim();
            String salePriceStr = request.getParameter("product-saleprice").trim();
            String capacityStr = request.getParameter("product-capacity").trim();
            String sizeStr = request.getParameter("product-screensize").trim();
            String color = request.getParameter("product-color").trim();
            String quantityStr = request.getParameter("product-quantity").trim();
            
            // Handle optional fields
            String categoryIdStr = request.getParameter("product-category");
            String cpuIdStr = request.getParameter("product-cpu");
            String cardIdStr = request.getParameter("product-card");
            String ramIdStr = request.getParameter("product-ram");
            String typeIdStr = request.getParameter("product-type");

            if (name.isEmpty()) {
                errors.put("productName", "Product name is required. and from 3-50 character");
            }
            if (name.length() < 3 || name.length() > 50) {
                errors.put("productName", "Product name is from 3-50 characters");
            }
            if (description.isEmpty()) {
                errors.put("productDescription", "Product description is required.");
            }
            if (description.length() < 3) {
                errors.put("productName", "Product description must >3 characters");
            }
            if (originPriceStr.isEmpty()) {
                errors.put("originPrice", "Origin price is required.");
            }
            if (salePriceStr.isEmpty()) {
                errors.put("salePrice", "Sale price is required.");
            }
            if (capacityStr.isEmpty()) {
                errors.put("capacity", "Capacity is required.");
            }
            if (sizeStr.isEmpty()) {
                errors.put("screenSize", "Screen size is required.");
            }
            if (color.isEmpty()) {
                errors.put("color", "Color is required.");
            }
            if (quantityStr.isEmpty()) {
                quantityStr = "0";
            }

            if (!errors.isEmpty()) {
                request.setAttribute("error", errors);

                // Set lại dữ liệu người dùng đã nhập vào request
                request.setAttribute("productName", name);
                request.setAttribute("productDescription", description);
                request.setAttribute("status", statusStr);
                request.setAttribute("originPrice", originPriceStr);
                request.setAttribute("salePrice", salePriceStr);
                request.setAttribute("capacity", capacityStr);
                request.setAttribute("screenSize", sizeStr);
                request.setAttribute("color", color);
                request.setAttribute("quantity", quantityStr);

                // Các trường tùy chọn
                request.setAttribute("categoryId", categoryIdStr);
                request.setAttribute("cpuId", cpuIdStr);
                request.setAttribute("cardId", cardIdStr);
                request.setAttribute("ramId", ramIdStr);
                request.setAttribute("typeId", typeIdStr);
                request.getRequestDispatcher("add-product.jsp").forward(request, response);
                return;
            }

            // Parse numeric values
            int status = Integer.parseInt(statusStr);
            double originPrice = Double.parseDouble(originPriceStr);
            double salePrice = Double.parseDouble(salePriceStr);
            double capacity = Double.parseDouble(capacityStr);
            double size = Double.parseDouble(sizeStr);
            int quantity = Integer.parseInt(quantityStr);

         

            Integer categoryId = (categoryIdStr != null && !categoryIdStr.isEmpty()) ? Integer.parseInt(categoryIdStr) : null;
            Integer cpuId = (cpuIdStr != null && !cpuIdStr.isEmpty()) ? Integer.parseInt(cpuIdStr) : null;
            Integer cardId = (cardIdStr != null && !cardIdStr.isEmpty()) ? Integer.parseInt(cardIdStr) : null;
            Integer ramId = (ramIdStr != null && !ramIdStr.isEmpty()) ? Integer.parseInt(ramIdStr) : null;
            Integer typeId = (typeIdStr != null && !typeIdStr.isEmpty()) ? Integer.parseInt(typeIdStr) : null;

            // Handle image upload
            Part filePart = request.getPart("product-image");
            String fileName = null;

            if (filePart != null && filePart.getSize() > 0) {
                String applicationPath = getServletContext().getRealPath("");
                String cleanedPath = applicationPath.replace(File.separator + "build" + File.separator + "web" + File.separator, "");
                String uploadPath = cleanedPath + File.separator + FOLDER_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                fileName = filePart.getSubmittedFileName();
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
            }

            // Create Product object
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setStatus(status);
            product.setOriginPrice(originPrice);
            product.setSalePrice(salePrice);
            product.setImage(fileName != null ? UPLOAD_DIRECTORY + "/" + fileName : null);
            product.setCategoryId(categoryId);
            product.setCapacity(capacity);
            product.setSize(size);
            product.setColor(color);
            product.setCpuId(cpuId);
            product.setCardId(cardId);
            product.setRamId(ramId);
            product.setTypeId(typeId);
            product.setQuantity(quantity);

            // Insert into database
            ProductDAO productDAO = new ProductDAO();
            productDAO.addProduct(product);

            // Success message and redirection
            request.setAttribute("message", "Product added successfully!");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);

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
