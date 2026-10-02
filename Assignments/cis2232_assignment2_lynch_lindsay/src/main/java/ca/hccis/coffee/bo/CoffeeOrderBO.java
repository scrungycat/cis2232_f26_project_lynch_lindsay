package ca.hccis.coffee.bo;

import ca.hccis.coffee.entity.CoffeeOrder;

/**
 * Main entity business object calculations
 * Determines cost of each coffee order
 * CIS 2232 | Assignment 2
 *
 * @author LRML
 * @since 09252026
 */

public class CoffeeOrderBO {

    private static final double COST_COFFEE_SMALL = 2.00;
    private static final double COST_COFFEE_MEDIUM = 3.00;
    private static final double COST_COFFEE_LARGE = 4.00;
    private static final double COST_MILK_SURCHARGE = 0.75;
    private static final double COST_EXTRA_SHOT = 1.00;
    private static final double TAX_RATE = 0.15;

    // +calculate(Entity):double
    // --> create such a method in BO class, this will do the calculation

    /**
     * Calculation Needed:
     * orderSubtotal = (unitPrice + milkSurcharge + extraShotCharge) x quantity
     * **Notes**
     * No real validation added yet for typos
     * @param orderItem each item in the order
     * @return orderSubtotal
     */
    public static double calculateSubtotal(CoffeeOrder orderItem) {

        String orderDrinkSize = orderItem.getDrinkSize();
        String orderMilkType = orderItem.getMilkType();

        if (orderDrinkSize.equalsIgnoreCase("Small")) {
            orderItem.setUnitPrice(COST_COFFEE_SMALL);
        } else if (orderDrinkSize.equalsIgnoreCase("Medium")) {
            orderItem.setUnitPrice(COST_COFFEE_MEDIUM);
        } else if (orderDrinkSize.equalsIgnoreCase("Large")) {
            orderItem.setUnitPrice(COST_COFFEE_LARGE);
        }

        if (orderMilkType.equalsIgnoreCase("oat")
                || orderMilkType.equalsIgnoreCase("almond")) {
            orderItem.setMilkSurcharge(COST_MILK_SURCHARGE);
        }else {
            orderItem.setMilkSurcharge(0.00);
        }

        double extraShotCharge = orderItem.getExtraShots() * COST_EXTRA_SHOT;

        double subtotal =
                (orderItem.getUnitPrice() + orderItem.getMilkSurcharge() + extraShotCharge)
                * orderItem.getQuantity();

        orderItem.setSubtotal(subtotal);
        return subtotal;
    }

    /**
     * Calculation Needed:
     * orderTax = orderSubtotal x TAX_RATE
     *
     * @param orderItem each item in the order
     * @return double tax
     */
    public static double calculateTax(CoffeeOrder orderItem) {

        double tax = orderItem.getSubtotal() * TAX_RATE;
        orderItem.setTax(tax);
        return tax;
    }

    /**
     * Calculation Needed:
     * orderGrandTotal = orderSubtotal + orderTax
     *
     * @param orderItem each item in the order
     * @return grandTotal
     */
    public static double calculateGrandTotal(CoffeeOrder orderItem) {

        double grandTotal = orderItem.getSubtotal() + orderItem.getTax();
        orderItem.setGrandTotal(grandTotal);
        return grandTotal;
    }
}
