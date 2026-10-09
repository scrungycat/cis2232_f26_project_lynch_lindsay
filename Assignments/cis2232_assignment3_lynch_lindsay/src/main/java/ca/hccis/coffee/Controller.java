package ca.hccis.coffee;

import ca.hccis.coffee.bo.CoffeeOrderBO;
import ca.hccis.coffee.entity.CoffeeOrder;
import ca.hccis.coffee.util.CisUtility;
import ca.hccis.multithreading.threads.Runner1;
import ca.hccis.multithreading.threads.Runner2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Main entrypoint controller for Trident Roastery application
 * CIS 2232 | Assignment 1, 2, 3
 *
 * @author LRML
 * @since 09152026
 * @updated 09262026 for A2 calculations
 * @updated 10082026 for A3 multithreading
 */

public class Controller {

    public static final String MSG_ERROR = "Error.";
    public static final String MSG_EXIT = "Goodbye!";
    public static final String MSG_SUCCESS = "Success!";
    public static final String EXIT = "X";

    public static final String MENU =
                    "A) Add New Order\n" +
                    "V) View Order History\n" +
                    "X) Exit \n";

    private static HashMap<Integer, CoffeeOrder> orderMap = new HashMap<>();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final String PATH_NAME = "c:\\cis2232\\data_lynch_lindsay.json";

    /**
     * TODO Done A1 Rq: Main method for first actions on application boot
     * Main method for entrypoint to program
     * Controls flow of entry points for user input
     * Starts up both threads for console & GUI input in tandem
     *
     * @author LRML
     * @since 09152026
     * @updated 10082026 separated menu options to other methods to run in tandem between console and GUI for A3
     */
    public static void main(String[] args) {

        initialize();

        Runner1 runner1 = new Runner1();
        runner1.start();

        Thread runner2 = new Thread(new Runner2());
        runner2.start();

        try {
            runner1.join();
            runner2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted!");
        }
        menuConsole();
        menuGUI();
    }

    /**
     * TODO Done A1 Rq: Main menu for application
     * Switch menu for order options in console
     * Add new order
     * View historic orders (from HashMap/json)
     *
     * @author LRML
     * @since 09152026
     */
    public static void menuConsole(){
        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU);

            switch (menuOption.toUpperCase()) {
                case EXIT:
                    System.out.println(MSG_EXIT);
                    break;
                case "A":
                    add();
                    break;
                case "V":
                    viewAll();
                    break;
                default:
                    System.out.println(MSG_ERROR);
                    break;
            }
        } while (!menuOption.equalsIgnoreCase(EXIT));
    }

    /**
     * TODO Done A1 Rq: Add new order flow
     * Processes first menu option (A - Add order) in console
     * Creates new order object from CoffeeOrder class
     * Calculates order cost from CoffeeOrderBO class
     * Adds to HashMap and json file
     *
     * @author LRML
     * @since 09152026
     * @updated 09252026 to include call to CoffeeOrderBO for A2
     * @updated 10092026 added synchronized wrapper to avoid small chance of simultaneous order mapping causing loss of data
     */
    public static void add() {
        CoffeeOrder newCoffeeOrder = new CoffeeOrder();
        newCoffeeOrder.getInformation();

        //New call to CoffeeOrderBO to run calculations separate from entity
        CoffeeOrderBO.calculateSubtotal(newCoffeeOrder);
        CoffeeOrderBO.calculateTax(newCoffeeOrder);
        CoffeeOrderBO.calculateGrandTotal(newCoffeeOrder);

        // Auto-assign next integer ID if ID is 0 or unassigned
        // Added synchronization to avoid overlap with multithreading
        synchronized (orderMap) {
            if (newCoffeeOrder.getId() == 0) {
                int maxId = orderMap.keySet().stream().mapToInt(v -> v).max().orElse(0);
                newCoffeeOrder.setId(maxId + 1);
            }
            orderMap.put(newCoffeeOrder.getId(), newCoffeeOrder);
            writeAll();
        }
        System.out.println(MSG_SUCCESS);
    }

    /**
     * TODO Done A3 Rq: Adjusted menu flow for GUI
     * Processes menu in GUI with Multithreading for A3
     *
     * @author LRML
     * @since 10082026
     */
    public static void menuGUI(){
        String menuOption;

        do {
            menuOption = JOptionPane.showInputDialog(MENU);

            switch (menuOption.toUpperCase()) {
                case EXIT:
                    System.out.println(MSG_EXIT);
                    break;
                case "A":
                    addGUI();
                    break;
                case "V":
                    viewAllGUI();
                    break;
                default:
                    System.out.println(MSG_ERROR);
                    break;
            }
        } while (!menuOption.equalsIgnoreCase(EXIT));
    }

    /**
     * TODO Done A3 Rq: Adjusted add order flow for GUI
     * Processes same as first menu option (A - Add order) but in GUI
     *
     * @author LRML
     * @since 10092026
     */
    public static void addGUI() {
        CoffeeOrder newCoffeeOrder2 = new CoffeeOrder();
        newCoffeeOrder2.getInformationGUI();

        //New call to CoffeeOrderBO to run calculations separate from entity
        CoffeeOrderBO.calculateSubtotal(newCoffeeOrder2);
        CoffeeOrderBO.calculateTax(newCoffeeOrder2);
        CoffeeOrderBO.calculateGrandTotal(newCoffeeOrder2);

        // Auto-assign next integer ID if ID is 0 or unassigned
        // Added synchronized here to avoid overwriting in json output
        synchronized (orderMap) {
            if (newCoffeeOrder2.getId() == 0) {
                int maxId = orderMap.keySet().stream().mapToInt(v -> v).max().orElse(0);
                newCoffeeOrder2.setId(maxId + 1);
            }
            orderMap.put(newCoffeeOrder2.getId(), newCoffeeOrder2);
            writeAll();
        }

        //Changed confirmation message to GUI
        JOptionPane.showMessageDialog(null, MSG_SUCCESS);
    }

    /**
     * TODO Done A1 Rq: View all saved orders
     * Processes second menu option (V - View all orders) in Console
     *
     * @author LRML
     * @since 09152026
     */
    public static void viewAll() {

        System.out.println("\n-- View All Orders --");
        if (orderMap.isEmpty()) {
            System.out.println("No orders found.");

        } else {
            for (Map.Entry<Integer, CoffeeOrder> entry : orderMap.entrySet()) {
                System.out.println("Order ID " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    /**
     * TODO Done A3 Rq: View all saved orders in GUI
     * Processes second menu option (V - View all orders) in GUI for A3
     * Adjusted for loop to build string output & fit in one message box
     *
     * @author LRML
     * @since 10092026
     */
    public static void viewAllGUI() {

        JOptionPane.showMessageDialog(null, "Gathering All Orders");
        if (orderMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No orders found.");

        } else {

            StringBuilder orderHistory = new StringBuilder();
            for (Map.Entry<Integer, CoffeeOrder> entry : orderMap.entrySet()) {

               orderHistory.append("Order ID ")
                       .append(entry.getKey())
                       .append(": ")
                       .append(entry.getValue())
                       .append("\n");
            }
            JOptionPane.showMessageDialog(
                    null, "Order History: \n" + orderHistory);
        }
    }

    /**
     * TODO Done A1 Rq: Write orders to json
     * Writes orderMap to json file
     *
     * @author LRML
     * @since 09152026
     */
    public static void writeAll() {

        try (FileWriter writer = new FileWriter(PATH_NAME)) {
            gson.toJson(orderMap, writer);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * TODO Done A1 Rq: Reads orders from json
     * Reads json file into orderMap
     *
     * @author LRML
     * @since 09152026
     */
    public static void readAll() {

        File file = new File(PATH_NAME);
        if (!file.exists() || file.length() == 0) {
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<HashMap<Integer, CoffeeOrder>>() {
            }.getType();
            HashMap<Integer, CoffeeOrder> loaded = gson.fromJson(reader, type);
            if (loaded != null) {
                orderMap = loaded;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * TODO Done A1 Rq: Creates/checks for file, folder, dummy objects
     * Initializer helps check if both directory and json file exist
     * Makes decision based on existing structure in C:\
     * Creates folder and file in directory if needed
     * Preloads dummy file info so order data can be viewed
     * Updates existing file in directory otherwise
     *
     * @note I did not adjust initializer messages to show in GUI for A3
     * as I didn't feel this was necessary for multithreading, may cause duplication errors
     * @author LRML
     * @since 09152026
     */
    public static void initialize() {

        Path path = Paths.get(PATH_NAME);

        Path parentDir = path.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                System.err.println("Could not create directory: " + e.getMessage());
            }
        }

        if (Files.exists(path)) {
            readAll();
        } else {
            // Add initial default order and save file if starting fresh
            CoffeeOrder defaultCoffeeOrder = new CoffeeOrder();

            defaultCoffeeOrder.setId(1);
            defaultCoffeeOrder.setCustomerName("Default Customer");
            defaultCoffeeOrder.setDrinkType("Brewed Coffee");
            defaultCoffeeOrder.setDrinkSize("Medium");
            defaultCoffeeOrder.setMilkType("None");
            defaultCoffeeOrder.setQuantity(1);
            defaultCoffeeOrder.setUnitPrice(0.00);
            defaultCoffeeOrder.setExtraShots(0);
            defaultCoffeeOrder.setMilkSurcharge(0.0);
            CoffeeOrderBO.calculateSubtotal(defaultCoffeeOrder);
            CoffeeOrderBO.calculateTax(defaultCoffeeOrder);
            CoffeeOrderBO.calculateGrandTotal(defaultCoffeeOrder);
            defaultCoffeeOrder.setOrderStatus("Pending");

            orderMap.put(defaultCoffeeOrder.getId(), defaultCoffeeOrder);
            writeAll();
            System.out.println("Created new initial file at: " + PATH_NAME);
        }
    }
}
