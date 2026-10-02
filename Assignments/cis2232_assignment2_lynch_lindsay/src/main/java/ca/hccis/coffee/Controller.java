package ca.hccis.coffee;

import ca.hccis.coffee.bo.CoffeeOrderBO;
import ca.hccis.coffee.entity.CoffeeOrder;
import ca.hccis.coffee.util.CisUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
 * CIS 2232 | Assignment 1 & 2
 *
 * @author LRML
 * @since 09152026
 */

public class Controller {

    public static final String MSG_ERROR = "Error.";
    public static final String MSG_EXIT = "Goodbye!";
    public static final String MSG_SUCCESS = "Success!";
    public static final String EXIT = "X";

    public static final String MENU =
                    "A) Add\n" +
                    "V) View\n" +
                    "X) Exit \n";

    private static HashMap<Integer, CoffeeOrder> orderMap = new HashMap<>();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final String PATH_NAME = "c:\\cis2232\\data_lynch_lindsay.json";

    public static void main(String[] args) {

        initialize();

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
     * Processes first menu option (A - Add order)
     *
     * @updated 09252026 to include call to CoffeeOrderBO
     *
     * @author LRML
     * @since 09152026
     */
    public static void add() {
        System.out.println("--Add Order--");

        CoffeeOrder newCoffeeOrder = new CoffeeOrder();
        newCoffeeOrder.getInformation();

        //New call to CoffeeOrderBO to run calculations separate from entity
        CoffeeOrderBO.calculateSubtotal(newCoffeeOrder);
        CoffeeOrderBO.calculateTax(newCoffeeOrder);
        CoffeeOrderBO.calculateGrandTotal(newCoffeeOrder);


        // Auto-assign next integer ID if ID is 0 or unassigned
        if (newCoffeeOrder.getId() == 0) {
            int maxId = orderMap.keySet().stream().mapToInt(v -> v).max().orElse(0);
            newCoffeeOrder.setId(maxId + 1);
        }

        orderMap.put(newCoffeeOrder.getId(), newCoffeeOrder);
        writeAll();
        System.out.println(MSG_SUCCESS);
    }

    /**
     * Processes second menu option (V - View all orders)
     *
     * @author LRML
     * @since 09152026
     */
    public static void viewAll() {
//        readAll();

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

    public static void initialize() {

        Path path = Paths.get(PATH_NAME);

        Path parentDir = path.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            try {
                Files.createDirectories(parentDir);
                System.out.println("Created directory: " + parentDir.toString());
            } catch (IOException e) {
                System.err.println("Could not create directory: " + e.getMessage());
            }
        }

        if (Files.exists(path)) {
            System.out.println("The orders file already exists! Loading existing data...");
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
