package controller.customer;

import controller.user.BaseRequiredAuthenticationController;
import dal.*;
import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddToCartController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CartDetailDAO cartDAO = new CartDetailDAO();
        HttpSession session = request.getSession();
        Account account = new Account();

        try {
            account = (Account) session.getAttribute("account");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
        int quantity = 0;
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid quantity must > 0 and <= total quantity in stock.");
        }
        float price = Float.parseFloat(request.getParameter("price"));
        int totalquantity = Integer.parseInt(request.getParameter("totalquantity"));

        try {
            if (account != null) {
                if (quantity <= 0 || quantity > totalquantity) {
                    request.setAttribute("errorMessage", "Invalid quantity must > 0 and <= total quantity in stock.");
                } else {
                    int customerId = account.getId();
                    boolean success = cartDAO.addToCart(productDetailId, quantity, price, customerId);
                    if (success) {
                        request.setAttribute("successMessage", "Product added to cart successfully!");
                    } else {
                        request.setAttribute("errorMessage", "Product already in cart.");
                    }
                }
            } else {
                request.setAttribute("errorMessage", "You have to login first.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input format");
        }
        ProductWithDetailsDAO productDetailsDAO = new ProductWithDetailsDAO();
        BrandDAL branddao = new BrandDAL();
        RAMDAO ramdao = new RAMDAO();
        CPUDAO cpudao = new CPUDAO();
        CardDAO carddao = new CardDAO();

        try {
            int detailId = productDetailId;

            ProductWithDetails productWithDetails = productDetailsDAO.getProductDetailById(detailId);
            if (productWithDetails == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
            Brand brandname = branddao.getBrandById(productWithDetails.getProduct().getBrandId());
            RAM ramname = ramdao.getRAMById(productWithDetails.getProductDetails().getRamId());
            CPU cpuname = cpudao.getCPUById(productWithDetails.getProductDetails().getCpuId());
            Card cardname = carddao.getCardById(productWithDetails.getProductDetails().getCardId());

            int brandid = productWithDetails.getProduct().getBrandId();
            List<ProductWithDetails> relatedProducts = productDetailsDAO.getProductsByBrand(brandid, 4);

            request.setAttribute("productWithDetails", productWithDetails);
            request.setAttribute("brandname", brandname);
            request.setAttribute("ramname", ramname);
            request.setAttribute("cpuname", cpuname);
            request.setAttribute("cardname", cardname);
            request.setAttribute("relatedProducts", relatedProducts);

            request.getRequestDispatcher("ProductDetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product detail ID format");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles adding products to cart";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
