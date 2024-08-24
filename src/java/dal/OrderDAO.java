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


    public boolean updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE [Order] SET statusid = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Order> getOrders(int customerId, String sortBy, String order, int pageNumber, int pageSize) {
        List<Order> orders = new ArrayList<>();
        // Xử lý các giá trị mặc định nếu sortBy hoặc order bị thiếu
        String sortColumn = (sortBy != null && !sortBy.trim().isEmpty()) ? sortBy : "id";
        String sortOrder = (order != null && (order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc"))) ? order : "asc";

        // Xây dựng câu lệnh SQL
        String sql = "SELECT id, customerid, orderdate, totalamount, statusid, shippingaddress "
                + "FROM [Order] WHERE customerid = ? "
                + "ORDER BY " + sortColumn + " " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, (pageNumber - 1) * pageSize); // OFFSET
            stmt.setInt(3, pageSize); // FETCH NEXT
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order orderObj = new Order();
                orderObj.setId(rs.getInt("id"));
                orderObj.setCustomerid(rs.getInt("customerid"));
                orderObj.setOrderdate(rs.getDate("orderdate"));
                orderObj.setTotalamount(rs.getFloat("totalamount"));
                orderObj.setStatusid(rs.getString("statusid"));
                orderObj.setShippingaddress(rs.getString("shippingaddress"));
                orders.add(orderObj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

    public int getTotalOrders(int customerId) {
        String sql = "SELECT COUNT(*) FROM [Order] WHERE customerid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
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

    public boolean reorder(int orderId, int customerId) {
        try {
            // Bước 1: Lấy thông tin đơn hàng gốc
            String getOrderSql = "SELECT orderdate, totalamount, statusid, shippingaddress "
                    + "FROM [Order] WHERE id = ? AND customerid = ?";
            PreparedStatement getOrderStmt = connection.prepareStatement(getOrderSql);
            getOrderStmt.setInt(1, orderId);
            getOrderStmt.setInt(2, customerId);
            ResultSet orderRs = getOrderStmt.executeQuery();

            if (orderRs.next()) {
                // Bước 2: Tạo đơn hàng mới
                String insertOrderSql = "INSERT INTO [Order] (customerid, orderdate, totalamount, statusid, shippingaddress) "
                        + "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertOrderStmt = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
                insertOrderStmt.setInt(1, customerId);
                insertOrderStmt.setDate(2, new java.sql.Date(System.currentTimeMillis())); // ngày hiện tại
                insertOrderStmt.setFloat(3, orderRs.getFloat("totalamount"));
                insertOrderStmt.setString(4, "new"); // Trạng thái mới cho đơn hàng
                insertOrderStmt.setString(5, orderRs.getString("shippingaddress"));
                insertOrderStmt.executeUpdate();

                // Lấy ID của đơn hàng mới tạo
                ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys();
                int newOrderId = -1;
                if (generatedKeys.next()) {
                    newOrderId = generatedKeys.getInt(1);
                }

                // Bước 3: Sao chép chi tiết đơn hàng
                if (newOrderId > 0) {
                    String getOrderDetailsSql = "SELECT productid, quantity, price "
                            + "FROM OrderDetail WHERE orderid = ?";
                    PreparedStatement getOrderDetailsStmt = connection.prepareStatement(getOrderDetailsSql);
                    getOrderDetailsStmt.setInt(1, orderId);
                    ResultSet orderDetailsRs = getOrderDetailsStmt.executeQuery();

                    String insertOrderDetailsSql = "INSERT INTO OrderDetail (orderid, productid, quantity, price) "
                            + "VALUES (?, ?, ?, ?)";
                    PreparedStatement insertOrderDetailsStmt = connection.prepareStatement(insertOrderDetailsSql);

                    while (orderDetailsRs.next()) {
                        insertOrderDetailsStmt.setInt(1, newOrderId);
                        insertOrderDetailsStmt.setInt(2, orderDetailsRs.getInt("productid"));
                        insertOrderDetailsStmt.setInt(3, orderDetailsRs.getInt("quantity"));
                        insertOrderDetailsStmt.setFloat(4, orderDetailsRs.getFloat("price"));
                        insertOrderDetailsStmt.addBatch(); // Thêm vào batch
                    }

                    // Thực hiện batch để thêm tất cả chi tiết đơn hàng vào đơn hàng mới
                    insertOrderDetailsStmt.executeBatch();

                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT id, customerid, orderdate, totalamount, statusid, shippingaddress "
                + "FROM [Order] WHERE customerid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomerid(rs.getInt("customerid"));
                order.setOrderdate(rs.getDate("orderdate"));
                order.setTotalamount(rs.getFloat("totalamount"));
                order.setStatusid(rs.getString("statusid"));
                order.setShippingaddress(rs.getString("shippingaddress"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
    public void placeOrder(int customer_id, double total, String address) {
        int order_id = createOrder(customer_id, total, address);
        createOrderDetail(customer_id, order_id);
    }

    private int createOrder(int customer_id, double total, String address) {
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

    private int getIDOrder() {
        String sql = "select max(id) id from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void createOrderDetail(int customer_id, int order_id) {
        List<CartDetail> list = getListCart(customer_id);
        for (CartDetail i : list) {
            createOrderDetail(i, order_id);
        }
    }

    private void createOrderDetail(CartDetail cart, int order_id) {
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

    private List<CartDetail> getListCart(int customer_id) {
        List<CartDetail> list = new ArrayList();
        String sql = "select * from CartDetail where customerid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    public boolean changeStatusOrder(int order_id, String status) {
        String sql = "update [Order] set statusid = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(2, order_id);
            ps.setString(1, status);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (status.equals("delivering")) {
            return checkAvailableInStock(order_id);
        }
        return true;
    }

    public List<Order> getListOrder() {
        UserDBContext db = new UserDBContext();
        List<Order> list = new ArrayList();
        String sql = "select * from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<OrderProduct> getListOrderProduct(int order_id) {
        ProductWithDetailsDAO db = new ProductWithDetailsDAO();
        List<OrderProduct> list = new ArrayList<>();
        String sql = "select * from [OrderProduct] where orderid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    private boolean checkAvailableInStock(int order_id) {
        ProductWithDetailsDAO db = new ProductWithDetailsDAO();
        List<OrderProduct> list = getListOrderProduct(order_id);
        for (OrderProduct i : list) {
            if (checkAvailable(i.getProductid(), i.getQuantity()) == false) {
                return false;
            }
        }
        for (OrderProduct i : list) {
            int available = db.getQuantity(i.getProductid());
            int value = available - i.getQuantity();
            db.changeQuantity(i.getProductid(), value);
        }
        return true;
    }

    private boolean checkAvailable(int productid, int quantity) {
        ProductWithDetailsDAO db = new ProductWithDetailsDAO();
        int available = db.getQuantity(productid);
        int value = available - quantity;
        return value >= 0;
    }
    public Order getOrderByID(int order_id){
        UserDBContext db = new UserDBContext();
        String sql = "select * from [Order] where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Order> findOrderByCustomerNameOrStatusOrOrderdate(String keySearch){
        UserDBContext db = new UserDBContext();
        List<Order> list = new ArrayList();
        StringBuilder sb = 
                new StringBuilder("select * from [Order] join [User] on [Order].customerid = [User].id where fullname ");
        sb.append("like '%").append(keySearch).append("%' ").append("or orderdate ").append("like '%").append(keySearch).append("%' ");
        sb.append("or statusid ").append("like '%").append(keySearch).append("%' ");
        try {
            PreparedStatement ps = connection.prepareStatement(sb.toString());
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
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
