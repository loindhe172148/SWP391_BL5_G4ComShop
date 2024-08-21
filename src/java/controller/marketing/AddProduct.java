package controller.marketing;

import dal.CPUDAO;
import dal.CardDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import dal.RAMDAO;
import entity.CPU;
import entity.Card;
import entity.Category;
import entity.Product;
import entity.RAM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AddProductController", urlPatterns = {"/view/marketing/addnew"})
@MultipartConfig
public class AddProduct extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "assets/images/products";
    private static final String FOLDER_DIRECTORY = "web/view/marketing/assets/images/products";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String addAction = request.getParameter("addaction");

        if (addAction == null) {
            // Load danh sách category và brand để đổ vào form
            loadFormData(request, response);

            // Chuyển tiếp đến trang thêm sản phẩm
            request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
        } else {
            // Xử lý khi addaction không null, tức là người dùng gửi form để thêm sản phẩm
            Map<String, String> errors = new HashMap<>();

            // Lấy dữ liệu từ form
            String name = request.getParameter("product-name").trim();
            String description = request.getParameter("product-description").trim();
            String statusStr = request.getParameter("status");
            String originPriceStr = request.getParameter("product-originprice").trim();
            String salePriceStr = request.getParameter("product-saleprice").trim();
            String capacityStr = request.getParameter("product-capacity").trim();
            String sizeStr = request.getParameter("product-screensize").trim();
            String color = request.getParameter("product-color").trim();
            String quantityStr = request.getParameter("product-quantity").trim();

            String categoryIdStr = request.getParameter("product-category");
            String cpuIdStr = request.getParameter("product-cpu");
            String cardIdStr = request.getParameter("product-card");
            String ramIdStr = request.getParameter("product-ram");
            String typeIdStr = request.getParameter("product-type");

            // Validate dữ liệu
            validateFormData(name, description, originPriceStr, salePriceStr, capacityStr, sizeStr, color, quantityStr, errors);

            if (!errors.isEmpty()) {
                // Nếu có lỗi, gửi lỗi và dữ liệu đã nhập lại về trang thêm sản phẩm
                loadFormData(request, response);
                request.setAttribute("error", errors);
                setFormAttributes(request, name, description, statusStr, originPriceStr, salePriceStr, capacityStr, sizeStr, color, quantityStr, categoryIdStr, cpuIdStr, cardIdStr, ramIdStr, typeIdStr);

                request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
            } else {
                // Nếu không có lỗi, xử lý thêm sản phẩm vào cơ sở dữ liệu
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
                String fileName = handleImageUpload(filePart);

                // Create Product object
                Product product = new Product();
                product.setName(name);
                product.setDescription(description);
                product.setImage(fileName != null ? UPLOAD_DIRECTORY + "/" + fileName : null);
                product.setCategoryId(categoryId);
                product.setQuantity(quantity);

                // Insert into database
                ProductDAO productDAO = new ProductDAO();
                productDAO.addProduct(product);

                // Load lại dữ liệu để gửi về form
                loadFormData(request, response);
                setFormAttributes(request, name, description, statusStr, originPriceStr, salePriceStr, capacityStr, sizeStr, color, quantityStr, categoryIdStr, cpuIdStr, cardIdStr, ramIdStr, typeIdStr);

                // Success message
                request.setAttribute("message", "Product added successfully!");
                request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
            }
        }
    }

    private void loadFormData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        CPUDAO cpuDAO = new CPUDAO();
        List<CPU> cpuList = cpuDAO.getAllCPU();
        request.setAttribute("cpuList", cpuList);

        CardDAO cardDAO = new CardDAO();
        List<Card> cardList = cardDAO.getAllCard();
        request.setAttribute("cardList", cardList);

        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryList = categoryDAO.getAllCategory();
        request.setAttribute("categoryList", categoryList);

        RAMDAO ramDAO = new RAMDAO();
        List<RAM> ramList = ramDAO.getAllRAM();
        request.setAttribute("ramList", ramList);
    }

    private void validateFormData(String name, String description, String originPriceStr, String salePriceStr, String capacityStr, String sizeStr, String color, String quantityStr, Map<String, String> errors) {
        if (name.isEmpty()) {
            errors.put("productName", "Product name is required.");
        } else if (name.length() < 3 || name.length() > 50) {
            errors.put("productName", "Product name must be between 3 and 50 characters.");
        }

        if (description.isEmpty()) {
            errors.put("productDescription", "Product description is required.");
        } else if (description.length() < 3) {
            errors.put("productDescription", "Product description must be more than 3 characters.");
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

        if (sizeStr.isEmpty() || sizeStr.equals("0")) {
            errors.put("screenSize", "Screen size is required and must be greater than 0.");
        }

        if (color.isEmpty() || color.equals("non")) {
            errors.put("color", "Color is required.");
        }

        if (quantityStr.isEmpty()) {
            quantityStr = "0";
        }
    }

    private void setFormAttributes(HttpServletRequest request, String name, String description, String statusStr, String originPriceStr, String salePriceStr, String capacityStr, String sizeStr, String color, String quantityStr, String categoryIdStr, String cpuIdStr, String cardIdStr, String ramIdStr, String typeIdStr) {
        request.setAttribute("productName", name);
        request.setAttribute("productDescription", description);
        request.setAttribute("status", statusStr);
        request.setAttribute("originPrice", originPriceStr);
        request.setAttribute("salePrice", salePriceStr);
        request.setAttribute("capacity", capacityStr);
        request.setAttribute("screenSize", sizeStr);
        request.setAttribute("color", color);
        request.setAttribute("quantity", quantityStr);

        request.setAttribute("categoryId", categoryIdStr);
        request.setAttribute("cpuId", cpuIdStr);
        request.setAttribute("cardId", cardIdStr);
        request.setAttribute("ramId", ramIdStr);
        request.setAttribute("typeId", typeIdStr);
    }

    private String handleImageUpload(Part filePart) throws IOException {
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

        return fileName;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
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
