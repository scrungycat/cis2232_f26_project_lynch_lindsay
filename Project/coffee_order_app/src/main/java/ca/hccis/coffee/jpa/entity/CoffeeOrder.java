package ca.hccis.coffee.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * This maps the coffee order entity to MySQL by matching variables with columns
 *
 * @author LRML
 * @since 09262026
 * @updated 10/16-29/2026 to add column hookups and naming conventions for db connection
 */
@Entity
@Table(name = "coffeeOrder")
public class CoffeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{required}")
    @Size(max = 100)
    @Column(name = "customerName")
    private String customerName;

    @NotBlank(message = "{required}")
    @Column(name = "drinkType")
    private String drinkType;

    @NotBlank(message = "{required}")
    @Size(max = 20)
    @Column(name = "drinkSize")
    private String drinkSize;

    @Column(name = "milkType")
    private String milkType;

    @NotNull(message = "{required}") @Min(1) @Max(50)
    @Column(name = "quantity")
    private Integer quantity = 1;

    @Column(name = "unitPrice")
    private Double unitPrice;

    @NotNull(message = "{required}") @Min(0) @Max(10)
    @Column(name = "extraShots")
    private Integer extraShots = 0;

    @Column(name = "milkSurcharge")
    private Double milkSurcharge;

    @NotBlank(message = "{required}")
    @Size(max = 20)
    @Column(name = "orderStatus")
    private String orderStatus;

    @NotBlank(message = "{required}")
    @Size(max = 20)
    @Column(name = "orderDate")
    private String orderDate;

    @Transient
    private double subtotal;

    @Transient
    private double tax;

    @Transient
    private double grandTotal;

    public CoffeeOrder() {}

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