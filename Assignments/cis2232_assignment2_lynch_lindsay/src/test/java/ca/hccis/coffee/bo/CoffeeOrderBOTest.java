package ca.hccis.coffee.bo;

import ca.hccis.coffee.entity.CoffeeOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Main entity business object calculation TESTING
 * Unit tests for business calculations
 * Uses TDD approach
 * CIS 2232 | Assignment 2
 *
 * @author LRML & Gemini
 * @since 09252026
 */

public class CoffeeOrderBOTest {

    private CoffeeOrder order;

    @BeforeEach
    public void setUp() {
        order = new CoffeeOrder();
    }

    /**
     * Test calculateSubtotal method with standard inputs.
     * Developed following a Test-Driven Development (TDD) approach.
     *
     * Drink: Small ($2.00) + Oat Milk ($0.75) + 1 Extra Shot ($1.00) = $3.75 per drink.
     * Quantity: 2 -> Expected Subtotal: $7.50
     *
     * @author LRML
     * @since 10012026
     */
    @Test
    public void testCalculateSubtotal_StandardOrder_ReturnsCorrectSubtotal() {
        order.setDrinkSize("Small");
        order.setMilkType("Oat");
        order.setExtraShots(1);
        order.setQuantity(2);

        double subtotal = CoffeeOrderBO.calculateSubtotal(order);

        assertEquals(7.50, subtotal, 0.001, "Subtotal should equal 7.50 for 2 Small Oat milk coffees with 1 extra shot each.");
        assertEquals(2.00, order.getUnitPrice(), 0.001, "Unit price should be set to 2.00.");
        assertEquals(0.75, order.getMilkSurcharge(), 0.001, "Milk surcharge should be set to 0.75.");
    }

    /**
     * Test calculateTax method using 15% tax rate.
     * Developed following a Test-Driven Development (TDD) approach.
     *
     * Subtotal: $10.00 -> Expected Tax (15%): $1.50
     *
     * @author LRML
     * @since 10012026
     */
    @Test
    public void testCalculateTax_ValidSubtotal_ReturnsCorrectTax() {
        order.setSubtotal(10.00);

        double tax = CoffeeOrderBO.calculateTax(order);

        assertEquals(1.50, tax, 0.001, "Tax should equal 1.50 for a subtotal of 10.00 at 15% rate.");
        assertTrue(order.getTax() == 1.50, "Order entity tax state should be updated to 1.50.");
    }

    /**
     * Test calculateGrandTotal method.
     * Developed following a Test-Driven Development (TDD) approach.
     *
     * Subtotal: $10.00, Tax: $1.50 -> Expected Grand Total: $11.50
     *
     * @author LRML
     * @since 10012026
     */
    @Test
    public void testCalculateGrandTotal_ValidSubtotalAndTax_ReturnsCorrectGrandTotal() {
        order.setSubtotal(10.00);
        order.setTax(1.50);

        double grandTotal = CoffeeOrderBO.calculateGrandTotal(order);

        assertEquals(11.50, grandTotal, 0.001, "Grand total should equal 11.50.");
        assertTrue(grandTotal > order.getSubtotal(), "Grand total must be greater than subtotal when tax is applied.");
    }

    /**
     * Test subtotal calculation for Large drink with Almond milk and no extra shots.
     * Large ($4.00) + Almond ($0.75) = $4.75 x Qty 1 = $4.75
     */
    @Test
    public void testCalculateSubtotal_LargeAlmondNoShots_ReturnsCorrectSubtotal() {
        order.setDrinkSize("Large");
        order.setMilkType("Almond");
        order.setExtraShots(0);
        order.setQuantity(1);

        double subtotal = CoffeeOrderBO.calculateSubtotal(order);

        assertEquals(4.75, subtotal, 0.001);
        assertEquals(4.00, order.getUnitPrice(), 0.001);
        assertEquals(0.75, order.getMilkSurcharge(), 0.001);
    }

    /**
     * Test subtotal calculation with regular Whole milk (0.00 surcharge) and multiple extra shots.
     * Medium ($3.00) + Whole ($0.00) + 3 Extra Shots ($3.00) = $6.00 x Qty 3 = $18.00
     */
    @Test
    public void testCalculateSubtotal_MediumWholeMultipleShots_ReturnsCorrectSubtotal() {
        order.setDrinkSize("Medium");
        order.setMilkType("Whole");
        order.setExtraShots(3);
        order.setQuantity(3);

        double subtotal = CoffeeOrderBO.calculateSubtotal(order);

        assertEquals(18.00, subtotal, 0.001);
        assertEquals(0.00, order.getMilkSurcharge(), 0.001);
    }

    /**
     * Test subtotal calculation when milk type is "None".
     * Small ($2.00) + None ($0.00) = $2.00 x Qty 1 = $2.00
     */
    @Test
    public void testCalculateSubtotal_NoMilk_ZeroSurcharge() {
        order.setDrinkSize("Small");
        order.setMilkType("None");
        order.setExtraShots(0);
        order.setQuantity(1);

        double subtotal = CoffeeOrderBO.calculateSubtotal(order);

        assertEquals(2.00, subtotal, 0.001);
        assertEquals(0.00, order.getMilkSurcharge(), 0.001);
    }

}
