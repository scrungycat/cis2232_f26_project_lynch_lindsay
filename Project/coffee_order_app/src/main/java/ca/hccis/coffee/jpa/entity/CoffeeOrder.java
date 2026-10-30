package ca.hccis.coffee.jpa.entity;

import jakarta.persistence.*;

/**
 * This maps the coffee order entity to MySQL
 *
 * @author LRML
 * @since 09262026
 */

@Entity
@Table(name = "coffee_order")
public class CoffeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "drink_type")
    private String drinkType;

    @Column(name = "drink_size")
    private String drinkSize;

    @Column(name = "milk_type")
    private String milkType;

    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "extra_shots")
    private Integer extraShots;

    @Column(name = "milk_surcharge")
    private Double milkSurcharge;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_date")
    private String orderDate;

    private double subtotal;
    private double tax;
    private double grandTotal;

    // Required Default Constructor for JPA
    public CoffeeOrder() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getDrinkType() { return drinkType; }
    public void setDrinkType(String drinkType) { this.drinkType = drinkType; }

    public String getDrinkSize() { return drinkSize; }
    public void setDrinkSize(String drinkSize) { this.drinkSize = drinkSize; }

    public String getMilkType() { return milkType; }
    public void setMilkType(String milkType) { this.milkType = milkType; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    public Integer getExtraShots() { return extraShots; }
    public void setExtraShots(Integer extraShots) { this.extraShots = extraShots; }

    public Double getMilkSurcharge() { return milkSurcharge; }
    public void setMilkSurcharge(Double milkSurcharge) { this.milkSurcharge = milkSurcharge; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
}