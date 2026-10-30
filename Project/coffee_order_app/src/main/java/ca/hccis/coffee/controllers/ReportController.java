package ca.hccis.coffee.controllers;

import ca.hccis.coffee.bo.CoffeeOrderBO;
import ca.hccis.coffee.bo.CoffeeOrderReportBO;
import ca.hccis.coffee.dao.CoffeeOrderDAO;
import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
* CIS-2232 | Coffee Order Application Project | Controller for Reporting
* Sprint 2 Requirement
* @author LRML
* @since 10/16/2026
*
* This controller allows mapping and processing for report outputs in browser
* Pulls stats from database and pushes info to report charts
* Uses functionality for the model
* logic is located in separate BO class (this grabs all the order receipts)
 */

@Controller
public class ReportController {

    private final CoffeeOrderDAO dao = new CoffeeOrderDAO();

    @GetMapping("/report/menu")
    public String showReportMenu() {
        return "report/menu";
    }

    // --- RECEIPT REPORT ---
    @GetMapping("/report/receipt")
    public String receiptInput(Model model) {
        model.addAttribute("reportInput", new CoffeeOrderReportBO());
        return "report/reportReceipt";
    }

    @PostMapping("/report/receipt")
    public String receiptSubmit(@ModelAttribute("reportInput") CoffeeOrderReportBO reportInput, Model model) {
        CoffeeOrder order = dao.getOrderByID(reportInput.getId());

        if (order == null) {
            model.addAttribute("message", "No order found with ID: " + reportInput.getId());
        } else {
            // Runs the calculation logic from CoffeeOrderBO
            CoffeeOrderBO.calculateSubtotal(order);
            CoffeeOrderBO.calculateTax(order);
            CoffeeOrderBO.calculateGrandTotal(order);

            reportInput.setSingleOrder(order);

            StringBuilder sb = new StringBuilder();
            sb.append("=== TRIDENT ROASTERY - RECEIPT ===\n");
            sb.append("Order ID: ").append(order.getId()).append("\n");
            sb.append("Date: ").append(order.getOrderDate()).append("\n");
            sb.append("Customer: ").append(order.getCustomerName()).append("\n");
            sb.append("Drink: ").append(order.getDrinkType()).append("\n");
            sb.append("Quantity: ").append(order.getQuantity()).append("\n");
            sb.append("Unit Price: $").append(order.getUnitPrice()).append("\n");
            sb.append("Status: ").append(order.getOrderStatus()).append("\n");
            sb.append("===================================\n");

            String savedFile = saveReportToFile("TicketOrdersForCustomer" + order.getId(), sb.toString());
            model.addAttribute("fileSavedMessage", "Report successfully saved to: " + savedFile);
        }
        return "report/reportReceipt";
    }

    // --- SALES REVIEW REPORT ---
    @GetMapping("/report/sales")
    public String salesInput(Model model) {
        model.addAttribute("reportInput", new CoffeeOrderReportBO());
        return "report/reportSalesReview";
    }

    @PostMapping("/report/sales/submit")
    public String salesSubmit(@ModelAttribute("reportInput") CoffeeOrderReportBO reportInput, Model model) {
        ArrayList<CoffeeOrder> orders = dao.getOrdersByDateRange(reportInput.getStartDate(), reportInput.getEndDate());

        if (orders.isEmpty()) {
            model.addAttribute("message", "No sales found between " + reportInput.getStartDate() + " and " + reportInput.getEndDate());
        } else {
            reportInput.setCoffeeOrders(orders);

            StringBuilder sb = new StringBuilder();
            sb.append("=== TRIDENT ROASTERY - SALES REVIEW REPORT ===\n");
            sb.append(String.format("%-10s %-12s %-20s %-15s %-8s\n", "ID", "Date", "Customer", "Drink", "Qty"));
            sb.append("-------------------------------------------------------------\n");

            double grandTotal = 0;
            for (CoffeeOrder o : orders) {
                sb.append(String.format("%-10d %-12s %-20s %-15s %-8d\n",
                        o.getId(),
                        o.getOrderDate() != null ? o.getOrderDate().toString() : "N/A",
                        o.getCustomerName(),
                        o.getDrinkType(),
                        o.getQuantity()
                ));
                CoffeeOrderBO.calculateSubtotal(o);
                CoffeeOrderBO.calculateTax(o);
                CoffeeOrderBO.calculateGrandTotal(o);
                grandTotal += o.getGrandTotal();
            }

            sb.append("-------------------------------------------------------------\n");
            sb.append(String.format("Grand Total Revenue: $%.2f\n", grandTotal));
            sb.append("=============================================================\n");

            String savedFile = saveReportToFile("SalesReview", sb.toString());
            model.addAttribute("fileSavedMessage", "Report successfully saved to: "  + savedFile);
        }
        return "report/reportSalesReview";
    }

    // --- ABOUT PAGE ---
    @GetMapping("/about")
    public String showAbout() {
        return "other/about";
    }

    // Helper method for File I/O
    private String saveReportToFile(String reportType, String content) {
        try {
            File dir = new File("c:\\cis2232\\");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            String filename = "c:\\cis2232\\" + reportType + timestamp + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(content);
            }
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
