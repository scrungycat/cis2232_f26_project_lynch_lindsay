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

    private String customerName;
    private String drinkType;
    private String drinkSize;
    private String milkType;
    private Integer quantity;
    private Double unitPrice;
    private Integer extraShots;
    private Double milkSurcharge;
    private String orderStatus;

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
}