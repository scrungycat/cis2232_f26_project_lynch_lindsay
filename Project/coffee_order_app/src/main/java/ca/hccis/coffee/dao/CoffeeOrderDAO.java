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
 * @since: 10/16/2026
 * @updated several times this month when adjusting variables and database script to hook everything up
 */

public class CoffeeOrderDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/cis2232_coffee_order_app";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Grabs Customer Receipt using ID requested by user
    public CoffeeOrder getOrderByID(int orderId) {
        CoffeeOrder order = null;
        String sql = "SELECT * FROM coffeeOrder WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new CoffeeOrder();
                order.setId(rs.getInt("id"));
                order.setCustomerName(rs.getString("customerName"));
                order.setDrinkType(rs.getString("drinkType"));
                order.setDrinkSize(rs.getString("drinkSize"));
                order.setMilkType(rs.getString("milkType"));
                order.setQuantity(rs.getInt("quantity"));
                order.setUnitPrice(rs.getDouble("unitPrice"));
                order.setExtraShots(rs.getInt("extraShots"));
                order.setMilkSurcharge(rs.getDouble("milkSurcharge"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setOrderDate(rs.getString("orderDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // Gets Sales Review using provided Date Range from user
    public ArrayList<CoffeeOrder> getOrdersByDateRange(String startDate, String endDate) {
        ArrayList<CoffeeOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM coffeeOrder WHERE orderDate BETWEEN ? AND ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CoffeeOrder order = new CoffeeOrder();
                order.setId(rs.getInt("id"));
                order.setCustomerName(rs.getString("customerName"));
                order.setDrinkType(rs.getString("drinkType"));
                order.setDrinkSize(rs.getString("drinkSize"));
                order.setMilkType(rs.getString("milkType"));
                order.setQuantity(rs.getInt("quantity"));
                order.setUnitPrice(rs.getDouble("unitPrice"));
                order.setExtraShots(rs.getInt("extraShots"));
                order.setMilkSurcharge(rs.getDouble("milkSurcharge"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setOrderDate(rs.getString("orderDate"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
