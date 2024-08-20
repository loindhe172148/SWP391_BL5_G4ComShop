package controller.customer;

import dal.BrandDAL;
import dal.CPUDAO;
import dal.CardDAO;
import dal.CartDetailDAO;
import dal.ProductWithDetailsDAO;
import dal.RAMDAO;
import entity.Account;
import entity.Brand;
import entity.CPU;
import entity.Card;
import entity.ProductWithDetails;
import entity.RAM;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddToCartController extends HttpServlet {

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
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));
        int totalquantity = Integer.parseInt(request.getParameter("totalquantity"));
        float totalPrice = price * quantity;

        try {
            if (quantity <= 0 || quantity > totalquantity) {
                request.setAttribute("errorMessage", "Invalid quantity must > 0 and <= total quantity in stock.");
            } else {
                if (account != null) {
                    int customerId = account.getId();
                    boolean success = cartDAO.addToCart(productDetailId, quantity, price, totalPrice, customerId);

                    if (success) {
                        request.setAttribute("successMessage", "Product added to cart successfully!");
                    } else {
                        request.setAttribute("errorMessage", "Product already in cart.");
                    }
                } else {
                    boolean success = cartDAO.addToCart(productDetailId, quantity, price, totalPrice, 1);

                    if (success) {
                        request.setAttribute("successMessage", "Product added to cart successfully!");
                    } else {
                        request.setAttribute("errorMessage", "Product already in cart.");
                    }
                }
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

            request.getRequestDispatcher("/view/customer/ProductDetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product detail ID format");
        }
    }

    private void forwardToProductDetails(HttpServletRequest request, HttpServletResponse response, int productDetailId)
            throws ServletException, IOException {
        ProductDetailsController productDetailsController = new ProductDetailsController();
        request.setAttribute("id", productDetailId);
        productDetailsController.processRequest(request, response);
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
}
