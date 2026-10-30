package ca.hccis.coffee.bo;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import java.util.ArrayList;

/**
 * CIS-2232 | Coffee Order Application Project | Business Object for Reporting
 * Sprint 2 Requirement
 * @author LRML
 * @since 10/16/2026 - 10/30/2026
 *
 * This BO holds the logic for mapping and processing report forms
 * logic here grabs all the order receipts - holds inputs/search fields,
 * and results for matching orders/receipts
 * also helper fields for error/success messaging
 *
 * ReportController contains base functionality for the model, calling this to do work
 */

public class CoffeeOrderReportBO {

    private int id;
    private String startDate;
    private String endDate;

    // Receipt and sale reports
    private CoffeeOrder singleOrder;
    private ArrayList<CoffeeOrder> coffeeOrders;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public CoffeeOrder getSingleOrder() { return singleOrder; }
    public void setSingleOrder(CoffeeOrder singleOrder) { this.singleOrder = singleOrder; }

    public ArrayList<CoffeeOrder> getCoffeeOrders() { return coffeeOrders; }
    public void setCoffeeOrders(ArrayList<CoffeeOrder> coffeeOrders) { this.coffeeOrders = coffeeOrders; }

}
