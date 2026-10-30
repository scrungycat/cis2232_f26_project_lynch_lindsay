package ca.hccis.coffee.dao;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * Coffee Order DAO (Data Access Object) | Sprint 2 Requirement
 * Abstracts/encapsulates interactions with database, allows isolating from business logic
 *
 * @author: LRML
 * @since:
 */

public class CoffeeOrderDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/cis2232_coffee_order_app";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Report 1. Get Customer Receipt by Order ID
    public CoffeeOrder getOrderByID(int orderId) {
        CoffeeOrder order = null;
        String sql = "SELECT * FROM coffee_order WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new CoffeeOrder();
                order.setId(rs.getInt("id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setDrinkType(rs.getString("drink_type"));
                order.setDrinkSize(rs.getString("drink_size"));
                order.setMilkType(rs.getString("milk_type"));
                order.setQuantity(rs.getInt("quantity"));
                order.setUnitPrice(rs.getDouble("unit_price"));
                order.setExtraShots(rs.getInt("extra_shots"));
                order.setMilkSurcharge(rs.getDouble("milk_surcharge"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getString("order_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // Report 2. Get Sales Review by Date Range
    public ArrayList<CoffeeOrder> getOrdersByDateRange(String startDate, String endDate) {
        ArrayList<CoffeeOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM coffee_order WHERE order_date BETWEEN ? AND ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CoffeeOrder order = new CoffeeOrder();
                order.setId(rs.getInt("id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setDrinkType(rs.getString("drink_type"));
                order.setDrinkSize(rs.getString("drink_size"));
                order.setMilkType(rs.getString("milk_type"));
                order.setQuantity(rs.getInt("quantity"));
                order.setUnitPrice(rs.getDouble("unit_price"));
                order.setExtraShots(rs.getInt("extra_shots"));
                order.setMilkSurcharge(rs.getDouble("milk_surcharge"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getString("order_date"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
