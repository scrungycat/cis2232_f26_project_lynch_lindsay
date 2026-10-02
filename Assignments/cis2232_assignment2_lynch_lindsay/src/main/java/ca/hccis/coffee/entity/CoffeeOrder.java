package ca.hccis.coffee.entity;

import ca.hccis.coffee.util.CisUtility;

/**
 * Order entity class manages each order object
 * Formats program flow in getInformation
 * Formats gathered data for .json file read/write
 * CIS 2232 | Assignment 1 & 2
 *
 * @updated 09252026:
 *          - moved calculations to CoffeeOrderBO
 *          - adjusted output formatting
 *          - update overloaded method with correct param order
 *          - added getters/setters for calculations
 *
 * @author LRML
 * @since 09222026
 */

public class CoffeeOrder {

    private int id;
    private String customerName;
    private String drinkType;
    private String drinkSize;
    private String milkType;
    private int quantity;
    private double unitPrice;
    private int extraShots;
    private double milkSurcharge;
    private double subtotal;
    private double tax;
    private double grandTotal;
    private String orderStatus;

    // Default constructor
    public CoffeeOrder() {
    }

    // Base constructor
    public CoffeeOrder(int id, String customerName, String drinkType, String drinkSize,
                       String milkType, int quantity, double unitPrice, int extraShots,
                       double milkSurcharge, double subtotal, double tax, double grandTotal,
                       String orderStatus) {
        this.id = id;
        this.customerName = customerName;
        this.drinkType = drinkType;
        this.drinkSize = drinkSize;
        this.milkType = milkType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.extraShots = extraShots;
        this.milkSurcharge = milkSurcharge;
        this.subtotal = subtotal;
        this.tax = tax;
        this.grandTotal = grandTotal;
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
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
                "Enter drink type (Coffee, Latte, Cappuccino, Americano): ");
        this.drinkSize = CisUtility.getInputString(
                "Enter drink size (Small, Medium, or Large): ");
        this.milkType = CisUtility.getInputString(
                "Enter milk type (None, Whole, Oat, Almond): ");
        this.extraShots = CisUtility.getInputInt(
                "Enter the number of extra espresso shots desired, or 0 for none: ");
        this.quantity = CisUtility.getInputInt(
                "Enter quantity of this drink you'd like: ");
        this.orderStatus = CisUtility.getInputString(
                "Enter order status (Pending, preparing, completed, cancelled): ");
    }

    /**
     * Formats gathered order data for toString
     * Outputs in both console and .json
     *
     * @author LRML
     * @since 09222026
     */
    @Override
    public String toString() {
        return String.format(
                "Order ID: %d | Customer: %s | Drink Type: %s (%s) | Qty: %d | Milk: %s " +
                        "| Extra Shots: %d | Unit Price: $%.2f | Milk Surcharge: $%.2f " +
                        "| Subtotal: $%.2f | Tax: $%.2f | Grand Total: $%.2f | Status: %s",
                id, customerName, drinkType, drinkSize, quantity, milkType, extraShots,
                unitPrice, milkSurcharge, subtotal, tax, grandTotal, orderStatus
        );
    }
}