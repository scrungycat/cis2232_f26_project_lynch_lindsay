package ca.hccis.files.entity;

import ca.hccis.files.util.CisUtility;

/**
 * Order entity class manages each order object
 * Formats program flow in getInformation
 * Formats gathered data for .json file read/write
 * CIS 2232 | Assignment 1
 *
 * @author LRML
 * @since 09222026
 */

public class Order {

    private int id;
    private String customerName;
    private String drinkType;
    private String drinkSize;
    private String milkType;
    private int quantity;
    private double unitPrice;
    private int extraShots;
    private double milkSurcharge;
    private String orderStatus;

    // Default constructor
    public Order() {
    }

    // Base constructor
    public Order(int id, String customerName, String drinkType, String drinkSize,
                 String milkType, int quantity, double unitPrice, int extraShots,
                 double milkSurcharge, String orderStatus) {
        this.id = id;
        this.customerName = customerName;
        this.drinkType = drinkType;
        this.drinkSize = drinkSize;
        this.milkType = milkType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.extraShots = extraShots;
        this.milkSurcharge = milkSurcharge;
        this.orderStatus = orderStatus;
    }

    // --- Getters & Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getExtraShots() {
        return extraShots;
    }

    public void setExtraShots(int extraShots) {
        this.extraShots = extraShots;
    }

    public double getMilkSurcharge() {
        return milkSurcharge;
    }

    public void setMilkSurcharge(double milkSurcharge) {
        this.milkSurcharge = milkSurcharge;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Formats gathered data for toString
     * Outputs in both console and .json
     *
     * @author LRML
     * @since 09222026
     */
    @Override
    public String toString() {
        return String.format(
                "Order ID: %d | Customer: %s | Drink: %s (%s) | Qty: %d | Milk: %s " +
                        "| Extra Shots: %d | Unit Price: $%.2f | Milk Surcharge: $%.2f " +
                        "| Subtotal: $%.2f | Status: %s",
                id, customerName, drinkType, drinkSize, quantity, milkType, extraShots,
                unitPrice, milkSurcharge, calculateSubtotal(), orderStatus
        );
    }

    /**
     * Customer order info gathering
     *
     * @author LRML
     * @since 09222026
     */
    public void getInformation() {

        this.customerName = CisUtility.getInputString(
                "Enter customer name: ");
        this.drinkType = CisUtility.getInputString(
                "Enter drink type (Brewed coffee, latte, cappuccino, americano): ");
        this.drinkSize = CisUtility.getInputString(
                "Enter drink size (Small, medium, large): ");
        this.milkType = CisUtility.getInputString(
                "Enter milk type (None, whole, oat, almond): ");
        this.quantity = CisUtility.getInputInt(
                "Enter quantity: ");
        this.extraShots = CisUtility.getInputInt(
                "Enter extra shots count: ");
        this.orderStatus = CisUtility.getInputString(
                "Enter order status (Pending, preparing, completed, cancelled): ");

        if(this.drinkSize.equalsIgnoreCase("Small")) {
            this.unitPrice = 2.00;
        }else if (this.drinkSize.equalsIgnoreCase("Medium")) {
            this.unitPrice = 3.00;
        }else if(this.drinkSize.equalsIgnoreCase("Large")) {
            this.unitPrice = 4.00;
        }

        if (this.milkType.equalsIgnoreCase("oat") || this.milkType.equalsIgnoreCase("almond")) {
            this.milkSurcharge = 0.75;
        } else {
            this.milkSurcharge = 0.00;
        }
    }

    /**
     * Calculation - started for A2
     *
     * @author LRML
     * @since 09222026
     */
    public double calculateSubtotal() {
        double extraShotCharge = this.extraShots * 1.00;
        return (this.unitPrice + extraShotCharge + this.milkSurcharge) * this.quantity;
    }
}