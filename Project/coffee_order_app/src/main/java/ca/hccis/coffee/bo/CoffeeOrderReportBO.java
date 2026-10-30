package ca.hccis.coffee.bo;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import java.util.ArrayList;

/**
 * CIS-2232 | Coffee Order Application Project | Business Object for Reporting
 * Sprint 2 Requirement
 * @author LRML
 * @since
 *
 * This BO holds the logic for mapping and processing report forms
 * logic here grabs all the order receipts - holds inputs/search fields,
 * and results for matching orders/receipts
 * also helper fields for error/success messaging
 *
 * ReportController contains base functionality for the model, calling this to do work
 */

public class CoffeeOrderReportBO {

    // Search Criteria Inputs
    private int id; // For Customer Receipt by ID
    private String startDate; // For Sales Review
    private String endDate;   // For Sales Review

    // Result Containers
    private CoffeeOrder singleOrder; // For receipt
    private ArrayList<CoffeeOrder> coffeeOrders; // For sales review list

    // Getters and Setters
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
