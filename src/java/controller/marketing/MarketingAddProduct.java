package controller.marketing;

import dal.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.*;

@MultipartConfig
public class MarketingAddProduct extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "assets/images/products";
    private static final String FOLDER_DIRECTORY = "web/view/marketing/assets/images/products";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryList = categoryDAO.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        BrandDAL brandDAO = new BrandDAL();
        List<Brand> brandList = brandDAO.getAllBrands();
        request.setAttribute("brandList", brandList);

        RAMDAO ramDAO = new RAMDAO();
        List<RAM> ramList = ramDAO.getAllRAM();
        request.setAttribute("ramList", ramList);

        CPUDAO cpuDAO = new CPUDAO();
        List<CPU> cpuList = cpuDAO.getAllCPU();
        request.setAttribute("cpuList", cpuList);

        CardDAO cardDAO = new CardDAO();
        List<Card> cardList = cardDAO.getAllCard();
        request.setAttribute("cardList", cardList);

        String servletPath = request.getServletPath();

        switch (servletPath) {
            case "/marketing/formadd":
                request.setAttribute("screen_size", 0);
                request.getRequestDispatcher("/view/marketing/AddProduct.jsp").forward(request, response);
                break;

            case "/marketing/addproduct":
                Map<String, String> errors = new HashMap<>();
                String productName = request.getParameter("product_name").trim();
                String title = request.getParameter("title").trim();
                String description = request.getParameter("description").trim();
                String category = request.getParameter("product_category");
                String brand = request.getParameter("product_brand");
                String screenSizeStr = request.getParameter("screen_size").trim();
                String ramStr = request.getParameter("ram");
                String cpuStr = request.getParameter("cpu");
                String cardStr = request.getParameter("card");
                String color = request.getParameter("color").trim();
                String originPriceStr = request.getParameter("origin_price").trim();
                String salePriceStr = request.getParameter("sale_price").trim();
                String status = request.getParameter("product_status");
                Part imagePart = request.getPart("product_image");
                String imageName = imagePart != null && imagePart.getSize() > 0 ? imagePart.getSubmittedFileName() : "default_laptop.jpg";

                // Validate Product Name
                if (productName.isEmpty()) {
                    errors.put("productNameError", "Product name cannot be empty");
                } else if (productName.length() < 3 || productName.length() > 100 || !productName.matches("^[\\w\\s]+$")) {
                    errors.put("productNameError", "Product name must be between 3 and 100 characters and cannot contain special characters");
                }

                // Validate Title
                if (title.isEmpty()) {
                    errors.put("titleError", "Title cannot be empty");
                } else if (title.length() < 3 || title.length() > 100) {
                    errors.put("titleError", "Title must be between 3 and 100 characters");
                }

                // Validate Description
                if (description.isEmpty()) {
                    errors.put("descriptionError", "Description cannot be empty");
                }

                // Validate Category
                if (category == null || category.isEmpty()) {
                    errors.put("categoryError", "Category must be selected");
                }

                // Validate Brand
                if (brand == null || brand.isEmpty()) {
                    errors.put("brandError", "Brand must be selected");
                }

                // Validate Screen Size
                double screenSize;
                try {
                    screenSize = Double.parseDouble(screenSizeStr);
                    if (screenSize <= 0) {
                        errors.put("screenSizeError", "Screen size must be greater than 0");
                    }
                } catch (NumberFormatException e) {
                    errors.put("screenSizeError", "Screen size must be a selected");
                }

                // Validate RAM
                int ramId;
                try {
                    ramId = Integer.parseInt(ramStr);
                    if (ramId <= 0) {
                        errors.put("ramError", "Invalid RAM selection");
                    }
                } catch (NumberFormatException e) {
                    errors.put("ramError", "RAM must be selected");
                }

                // Validate CPU
                int cpuId;
                try {
                    cpuId = Integer.parseInt(cpuStr);
                    if (cpuId <= 0) {
                        errors.put("cpuError", "Invalid CPU selection");
                    }
                } catch (NumberFormatException e) {
                    errors.put("cpuError", "CPU must be selected");
                }

                // Validate Graphics Card
                int cardId;
                try {
                    cardId = Integer.parseInt(cardStr);
                    if (cardId <= 0) {
                        errors.put("cardError", "Invalid Graphics Card selection");
                    }
                } catch (NumberFormatException e) {
                    errors.put("cardError", "Graphics Card must be selected");
                }

                // Validate Color
                if (color.isEmpty()) {
                    errors.put("colorError", "Color cannot be empty");
                }

                // Validate Origin Price
                double originPrice = 0;
                try {
                    originPrice = Double.parseDouble(originPriceStr);
                } catch (NumberFormatException e) {
                    errors.put("originPriceError", "Origin price must be a number");
                }

                // Validate Sale Price
                double salePrice = 0;
                try {
                    salePrice = Double.parseDouble(salePriceStr);
                    if (originPrice < salePrice) {
                        errors.put("salePriceError", "Sale price must be < or = to the origin price.");
                    }
                } catch (NumberFormatException e) {
                    errors.put("salePriceError", "Sale price must be a number");
                }


                // Default Status if not provided
                if (status == null || status.isEmpty()) {
                    status = "show";
                }

                // If there are errors, set attributes and forward to form
                if (!errors.isEmpty()) {
                    request.setAttribute("errors", errors);
                    request.setAttribute("product_name", productName);
                    request.setAttribute("title", title);
                    request.setAttribute("description", description);
                    request.setAttribute("product_category", category);
                    request.setAttribute("product_brand", brand);
                    request.setAttribute("screen_size", screenSizeStr);
                    request.setAttribute("ram", ramStr);
                    request.setAttribute("cpu", cpuStr);
                    request.setAttribute("card", cardStr);
                    request.setAttribute("color", color);
                    request.setAttribute("origin_price", originPriceStr);
                    request.setAttribute("sale_price", salePriceStr);
                    request.setAttribute("product_status", status);

                    request.getRequestDispatcher("/view/marketing/AddProduct.jsp").forward(request, response);
                    break;
                }

                // Handle Image Upload
                if (!imageName.equals("default_laptop.jpg")) {
                    String uploadPath = getServletContext().getRealPath("/assets/electro/img/") + File.separator + imageName;
                    imagePart.write(uploadPath);
                }

                // TODO: Save product to database
                // Example: ProductDAO.save(new Product(...));
                // Set success message and forward to form
                request.setAttribute("message", "Product added successfully!");
                request.getRequestDispatcher("/view/marketing/AddProduct.jsp").forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested page is not available.");
                break;
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
