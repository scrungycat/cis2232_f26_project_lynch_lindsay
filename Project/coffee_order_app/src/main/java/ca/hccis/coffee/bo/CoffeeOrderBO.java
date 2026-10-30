package ca.hccis.coffee.bo;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;

/**
 * TODO Done A2 Rq: BO creation and setup
 * Main entity business object calculations for subtotals, tax, grand totals
 * Determines cost of each coffee order
 * CIS 2232 | Assignment 2
 *
 * @author LRML
 * @since 09252026
 * @updated 10/16/2026 to include some safeguards against empty inputs from web form
 */

public class CoffeeOrderBO {

    private static final double COST_COFFEE_SMALL = 2.00;
    private static final double COST_COFFEE_MEDIUM = 3.00;
    private static final double COST_COFFEE_LARGE = 4.00;
    private static final double COST_MILK_SURCHARGE = 0.75;
    private static final double COST_EXTRA_SHOT = 1.00;
    private static final double TAX_RATE = 0.15;

    /**
     * TODO Done A2 Rq: Order calculation
     * Calculation Needed:
     * orderSubtotal = (unitPrice + milkSurcharge + extraShotCharge) x quantity
     * Currently determines price based on drink size and add-ons (shots, milk)
     *
     * @author: LRML
     * @since: 10/16/2026
     *
     *  @Notes:
     *          - No real validation added yet for typos
     *          - Will add at later time
     * @param orderItem each item in the order
     * @return orderSubtotal
     */
    public static double calculateSubtotal(CoffeeOrder orderItem) {

        if (orderItem == null) {
            return 0.0;
        }

        String orderDrinkSize = orderItem.getDrinkSize() != null ? orderItem.getDrinkSize() : "Small";
        String orderMilkType = orderItem.getMilkType() != null ? orderItem.getMilkType() : "None";

        int quantity = orderItem.getQuantity() != null ? orderItem.getQuantity() : 1;
        int extraShots = orderItem.getExtraShots() != null ? orderItem.getExtraShots() : 0;

        double unitPrice = COST_COFFEE_SMALL;
        if (orderDrinkSize.equalsIgnoreCase("Medium")) {
            unitPrice = COST_COFFEE_MEDIUM;
        } else if (orderDrinkSize.equalsIgnoreCase("Large")) {
            unitPrice = COST_COFFEE_LARGE;
        }
        orderItem.setUnitPrice(unitPrice);

        double milkSurcharge = 0.00;
        if (orderMilkType.equalsIgnoreCase("oat") || orderMilkType.equalsIgnoreCase("almond")) {
            milkSurcharge = COST_MILK_SURCHARGE;
        }
        orderItem.setMilkSurcharge(milkSurcharge);

        double extraShotCharge = extraShots * COST_EXTRA_SHOT;

        double subtotal = (unitPrice + milkSurcharge + extraShotCharge) * quantity;

        orderItem.setSubtotal(subtotal);
        return subtotal;
    }

    /**
     * TODO Done A2 Rq: Tax calculation after order subtotal determined
     * Calculation Needed:
     * orderTax = orderSubtotal x TAX_RATE
     *
     * @param orderItem each item in the order
     * @return double tax
     */
    public static double calculateTax(CoffeeOrder orderItem) {
        if (orderItem == null) {
            return 0.0;
        }

        double tax = orderItem.getSubtotal() * TAX_RATE;
        orderItem.setTax(tax);
        return tax;
    }

    /**
     * TODO Done A2 Rq: Grand total calculation after subtotal and tax determined
     * Calculation Needed:
     * orderGrandTotal = orderSubtotal + orderTax
     *
     * @param orderItem each item in the order
     * @return grandTotal
     */
    public static double calculateGrandTotal(CoffeeOrder orderItem) {
        if (orderItem == null) {
            return 0.0;
        }

        double grandTotal = orderItem.getSubtotal() + orderItem.getTax();
        orderItem.setGrandTotal(grandTotal);
        return grandTotal;
    }
}
