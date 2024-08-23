package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
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
import java.util.List;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class MarketingProductDetails extends BaseRequiredAuthenticationController {

    private static final String FOLDER_DIRECTORY = "web/assets/electro/img/";

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
        String idParam = request.getParameter("id");
        String message = request.getParameter("message");
        String err = request.getParameter("err");
        switch (servletPath) {
            case "/marketing/productdetails":   
                try {
                if (idParam != null && !idParam.isEmpty()) {
                    int detailId = Integer.parseInt(idParam);
                    ProductWithDetailsDAO productDAO = new ProductWithDetailsDAO();
                    ProductWithDetails productWithDetails = productDAO.getProductDetailById(detailId);

                    if (productWithDetails != null) {
                        request.setAttribute("p_id", productWithDetails.getProduct().getId());
                        request.setAttribute("createDate", productWithDetails.getProduct().getCreateDate());
                        request.setAttribute("updateDate", productWithDetails.getProduct().getUpdateDate());
                        request.setAttribute("product_quantity", productWithDetails.getProductDetails().getQuantity());
                        request.setAttribute("product_name", productWithDetails.getProduct().getName());
                        request.setAttribute("product_title", productWithDetails.getProduct().getTitle());
                        request.setAttribute("product_description", productWithDetails.getProduct().getDescription());
                        request.setAttribute("product_cate", productWithDetails.getProduct().getCategoryId());
                        request.setAttribute("product_brandid", productWithDetails.getProduct().getBrandId());
                        request.setAttribute("screen_size", productWithDetails.getProduct().getScreenSize());
                        request.setAttribute("pd_ramid", productWithDetails.getProductDetails().getRamId());
                        request.setAttribute("pd_cpuid", productWithDetails.getProductDetails().getCpuId());
                        request.setAttribute("pd_cardid", productWithDetails.getProductDetails().getCardId());
                        request.setAttribute("color", productWithDetails.getProductDetails().getColor());
                        request.setAttribute("origin_price", productWithDetails.getProductDetails().getOriginPrice());
                        request.setAttribute("sale_price", productWithDetails.getProductDetails().getSalePrice());
                        request.setAttribute("product_quantity", productWithDetails.getProductDetails().getQuantity());
                        request.setAttribute("product_image", productWithDetails.getProduct().getImage());
                        request.setAttribute("product_status", productWithDetails.getProduct().getStatus());

                        request.setAttribute("message", message);
                        request.setAttribute("err", err);
                        request.getRequestDispatcher("/view/marketing/ProductDetails.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product details not found.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format.");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
            }
            break;
            case "/marketing/updateproduct":
                Map<String, String> errors = new HashMap<>();
                String productIdParam = request.getParameter("id");
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

                int productId = Integer.parseInt(productIdParam);

                // Validate Product Name
                if (productName.isEmpty()) {
                    errors.put("productNameError", "Product name cannot be empty");
                } else if (productName.length() < 3 || productName.length() > 100 || !productName.matches("^[\\w\\s.,;:!|?()-]+$")) {
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
                    if (Integer.parseInt(category) == 1) {
                        if (screenSize <= 0) {
                            errors.put("screenSizeError", "Screen size must be greater than 0");
                        }
                    }
                    if (screenSize < 0) {
                        errors.put("screenSizeError", "You do not need to add screen size for accessories");
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
                    if (originPrice <= 0) {
                        errors.put("originPriceError", "Original Price > 0");
                    }
                } catch (NumberFormatException e) {
                    errors.put("originPriceError", "Origin price must be a number");
                }

                // Validate Sale Price
                double salePrice = 0;
                try {
                    salePrice = Double.parseDouble(salePriceStr);
                    if (salePrice < 0) {
                        errors.put("salePriceError", "0 <= Sale price <= Original Price");
                    }
                    if (originPrice < salePrice) {
                        errors.put("salePriceError", "Sale price must be < or = to the origin price.");
                    }
                } catch (NumberFormatException e) {
                    errors.put("salePriceError", "Sale price must be a number");
                }

                // Default Status if not provided
                if (status == null || status.isEmpty()) {
                    status = "Showing";
                }
                int detailId = Integer.parseInt(idParam);
                ProductWithDetailsDAO productDAO = new ProductWithDetailsDAO();
                ProductWithDetails productWithDetails = productDAO.getProductDetailById(detailId);
                request.setAttribute("p_id", productId);
                request.setAttribute("product_name", productName);
                request.setAttribute("product_title", title);
                request.setAttribute("product_description", description);
                request.setAttribute("product_cate", category);
                request.setAttribute("product_brandid", brand);
                request.setAttribute("screen_size", screenSizeStr);
                request.setAttribute("pd_ramid", ramStr);
                request.setAttribute("pd_cpuid", cpuStr);
                request.setAttribute("pd_cardid", cardStr);
                request.setAttribute("color", color);
                request.setAttribute("origin_price", originPriceStr);
                request.setAttribute("sale_price", salePriceStr);
                request.setAttribute("product_image", productWithDetails.getProduct().getImage());
                request.setAttribute("product_status", status);
                request.setAttribute("product_quantity", productWithDetails.getProductDetails().getQuantity());
                request.setAttribute("createDate", productWithDetails.getProduct().getCreateDate());
                request.setAttribute("updateDate", productWithDetails.getProduct().getUpdateDate());
                // If there are errors, set attributes and forward to form
                if (!errors.isEmpty()) {
                    request.setAttribute("errors", errors);
                    request.setAttribute("err", "Validation Product fails. Please check input");
                    request.getRequestDispatcher("/view/marketing/ProductDetails.jsp").forward(request, response);
                    break;
                }
                String imageName = imagePart != null && imagePart.getSize() > 0 ? imagePart.getSubmittedFileName() : productWithDetails.getProduct().getImage();

                // Handle Image Upload
                if (!imageName.equals("default-laptop.jpg")) {
                    String uploadPath = getServletContext().getRealPath("/") + FOLDER_DIRECTORY + imageName;

                    String buildWebPathUnix = "build/web/";
                    String buildWebPathWindows = "build\\web\\";

                    if (uploadPath.contains(buildWebPathUnix)) {
                        uploadPath = uploadPath.replace(buildWebPathUnix, "");
                    } else if (uploadPath.contains(buildWebPathWindows)) {
                        uploadPath = uploadPath.replace(buildWebPathWindows, "");
                    }

                    imagePart.write(uploadPath);
                }

                // Add Product to database
                ProductWithDetailsDAO pdetailDAO = new ProductWithDetailsDAO();
                Product product = new Product();
                product.setName(productName);
                product.setTitle(title);
                product.setDescription(description);
                product.setImage(imageName);
                product.setCategoryId(Integer.parseInt(category));
                product.setBrandId(Integer.parseInt(brand));
                product.setScreenSize(Float.parseFloat(screenSizeStr));
                product.setCreateDate(new java.util.Date()); // Current Date
                product.setUpdateDate(new java.util.Date()); // Current Date
                product.setId(productId);
                product.setStatus(status);

                // Prepare ProductDetail object
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProductId(productId);
                productDetail.setRamId(Integer.parseInt(ramStr));
                productDetail.setCpuId(Integer.parseInt(cpuStr));
                productDetail.setCardId(Integer.parseInt(cardStr));
                productDetail.setColor(color);
                productDetail.setOriginPrice(Double.parseDouble(originPriceStr));
                productDetail.setSalePrice(Double.parseDouble(salePriceStr));
                productDetail.setQuantity(0); // Assuming default quantity is 0
                System.out.println(product.toString());
                System.out.println(productDetail.toString());
                ProductWithDetails pwithdetail = new ProductWithDetails(product, productDetail);

                boolean isUpdated = pdetailDAO.updateProductWithDetails(pwithdetail);
                // Set success message and forward to form
                if (isUpdated) {
                    response.sendRedirect("productdetails?id=" + productId + "&message=Product UPDATE successfully!");
                } else {
                    response.sendRedirect("productdetails?id=" + productId + "&err=SQL error");
                }

                break;
            case "/marketing/changestatus":

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested page is not available. From mkt product details svlet");
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
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
