
package dal;

import entity.CartDetail;
import entity.Order;
import entity.OrderProduct;
import entity.Product;
import entity.ProductWithDetails;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends DBContext<Order> {

    public int getOrderCount(String status, LocalDate startDate, LocalDate endDate) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM [Order] WHERE orderdate BETWEEN ? AND ?");
        
        if (status != null && !status.isEmpty()) {
            sql.append(" AND statusid = ?");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            
            if (status != null && !status.isEmpty()) {
                stmt.setString(3, status);
            }
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public void placeOrder(int customer_id, double total, String address){
        int order_id = createOrder(customer_id, total, address);
        createOrderDetail(customer_id, order_id);
    }
    private int createOrder(int customer_id, double total, String address){
        String sql = "INSERT INTO [dbo].[Order]([customerid],[orderdate],[totalamount],[statusid],[shippingaddress]) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ps.setDate(2, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            ps.setDouble(3, total);
            ps.setString(4, "processing");
            ps.setString(5, address);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getIDOrder();
    }
    private int getIDOrder(){
        String sql = "select max(id) id from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    private void createOrderDetail(int customer_id, int order_id){
        List<CartDetail> list = getListCart(customer_id);
        for (CartDetail i : list) {
            createOrderDetail(i,order_id);
        }
    }
    private void createOrderDetail(CartDetail cart, int order_id){
        String sql = "INSERT INTO [dbo].[OrderProduct]([orderid],[productid],[price],[quantity])VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            ps.setInt(2, cart.getProductDetailId());
            ps.setDouble(3, cart.getPrice());
            ps.setInt(4, cart.getQuantity());
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private List<CartDetail> getListCart(int customer_id){
        List<CartDetail> list = new ArrayList();
        String sql = "select * from CartDetail where customerid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int product_id = rs.getInt(1);
                int quantity = rs.getInt(2);
                double price = rs.getDouble(3);
                CartDetail c = new CartDetail(product_id, quantity, (float) price, customer_id);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void changeStatusOrder(int order_id, String status){
        String sql = "update [Order] set statusid = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(2, order_id);
            ps.setString(1, status);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Order> getListOrder(){
        UserDBContext db = new UserDBContext();
        List<Order> list = new ArrayList();
        String sql = "select * from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int customerid = rs.getInt(2);
                java.util.Date date = rs.getDate(3);
                float total = rs.getFloat(4);
                String statusid = rs.getString(5);
                String address = rs.getString(6);
                Order order = new Order(id, customerid, date, total, statusid, address);
                User user = db.getUserByID(customerid);
                List<OrderProduct> listOrder = getListOrderProduct(id);
                order.setUser(user);
                order.setListOrderProduct(listOrder);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<OrderProduct> getListOrderProduct(int order_id){
        ProductWithDetailsDAO db = new ProductWithDetailsDAO();
        List<OrderProduct> list = new ArrayList<>();
        String sql = "select * from [OrderProduct] where orderid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int productid = rs.getInt(2);
                float price = rs.getFloat(3);
                int quantity = rs.getInt(4);
                ProductWithDetails product = db.getProductDetailById(productid);
                OrderProduct o = new OrderProduct(order_id, productid, price, quantity);
                o.setProductdetails(product);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

