/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

;

/**
 *
 * @author hbtth
 */
public class DashBoardSaleDAL extends DBContext<Account> {

    public int getProductCount() {
        String sql = "select count(*) from [Product]";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getOrderCount() {
        String sql = "select count(*) from [Order]";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getTopSuccessProduct() {
        String sql = "with aaa as\n"
                + "(\n"
                + "	select productid, statusid, count(productid) quantity from [Order] join [OrderProduct] on [Order].id = [OrderProduct].orderid\n"
                + "	where statusid = 'success'\n"
                + "	group by productid, statusid\n"
                + "	having count(productid) = \n"
                + "		(\n"
                + "			select max(quantity) [max] from \n"
                + "				(select count(productid) quantity from [Order] join [OrderProduct] on [Order].id = [OrderProduct].orderid\n"
                + "					where statusid = 'success'\n"
                + "					group by productid, statusid\n"
                + "				) a\n"
                + "		)\n"
                + ")\n"
                + "select name from [Product] join aaa on Product.id = aaa.productid";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String getTopCancelProduct() {
        String sql = "with aaa as\n"
                + "(\n"
                + "	select productid, statusid, count(productid) quantity from [Order] join [OrderProduct] on [Order].id = [OrderProduct].orderid\n"
                + "	where statusid = 'cancel'\n"
                + "	group by productid, statusid\n"
                + "	having count(productid) = \n"
                + "		(\n"
                + "			select max(quantity) [max] from \n"
                + "				(select count(productid) quantity from [Order] join [OrderProduct] on [Order].id = [OrderProduct].orderid\n"
                + "					where statusid = 'cancel'\n"
                + "					group by productid, statusid\n"
                + "				) a\n"
                + "		)\n"
                + ")\n"
                + "select name from [Product] join aaa on Product.id = aaa.productid";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public int getTotalCustomer() {
        String sql = "select count(distinct(customerid)) from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public String getTopUser() {
        String sql = "with bbb as\n"
                + "(\n"
                + "select customerid from [Order]\n"
                + "group by customerid\n"
                + "having count(distinct(customerid)) =\n"
                + "(\n"
                + "select max(id) from \n"
                + "(select count(distinct(customerid))id from [Order])b))\n"
                + "select fullname from [User] join bbb on [User].id = bbb.customerid";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public int getTotalOrder(){
        String sql = "select count(*) from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getTotalOrderCancel(){
        String sql = "select count(*) from [Order] where statusid = 'cancel'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getTotalOrderDeclined(){
        String sql = "select count(*) from [Order] where statusid = 'declined'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getTotalOrderProcessing(){
        String sql = "select count(*) from [Order] where statusid = 'processing'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getTotalOrderDelivering (){
        String sql = "select count(*) from [Order] where statusid = 'delivering'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getTotalOrderSuccess (){
        String sql = "select count(*) from [Order] where statusid = 'success'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardSaleDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
