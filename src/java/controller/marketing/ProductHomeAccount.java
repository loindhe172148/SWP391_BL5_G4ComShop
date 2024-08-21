package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
import dal.BrandDao;
import dal.CategoryDAO;
import dal.ProductDAO;
import entity.Account;
import entity.Brandname;
import entity.Category;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductHomeAccount extends BaseRequiredAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Short description";
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
       
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.getAllProduct();
            List<Product> productListnew = productDAO.getNewestProducts(5);
            List<Product> topDiscountedProducts = productDAO.getTopDiscountedProducts(5);
            List<Product> topDiscountedProductsa = productDAO.getTopDiscountedProducts(3);

            req.setAttribute("productList", productList);
            req.setAttribute("productListnew", productListnew);
            req.setAttribute("topDiscountedProducts", topDiscountedProducts);
            req.setAttribute("topDiscountedProductsa", topDiscountedProductsa);

            BrandDao brandDao = new BrandDao();
            List<Brandname> listB = brandDao.getBrandnameAccessory();
            req.setAttribute("listB", listB);

            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> listC = categoryDAO.getAllCategory();
            req.setAttribute("listC", listC);

            req.getRequestDispatcher("/ProductHomeAccount.jsp").forward(req, resp);
       
    }
}
