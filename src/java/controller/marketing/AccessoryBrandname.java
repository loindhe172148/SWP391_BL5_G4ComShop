package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
import dal.BrandDao;
import entity.Account;
import entity.Brandname;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccessoryBrandname extends BaseRequiredAuthenticationController {
    @Override
    public String getServletInfo() {
        return "Handles accessory brand name requests.";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
         BrandDao brandDao = new BrandDao();
            List<Brandname> listB = brandDao.getBrandnameAccessory();
            req.setAttribute("listB", listB);
            req.getRequestDispatcher("/view/marketing/Bandnameview.jsp").forward(req, resp);
    }
}

