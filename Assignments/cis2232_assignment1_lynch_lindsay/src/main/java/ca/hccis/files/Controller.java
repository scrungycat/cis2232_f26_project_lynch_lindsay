package ca.hccis.files;

import ca.hccis.files.entity.Order;
import ca.hccis.files.util.CisUtility;
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
 * CIS 2232 | Assignment 1
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

    private static HashMap<Integer, Order> orderMap = new HashMap();
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
     * @author LRML
     * @since 09152026
     */
    public static void add() {
        System.out.println("--Add Order--");

        Order newOrder = new Order();
        newOrder.getInformation();

        // Auto-assign next integer ID if ID is 0 or unassigned
        if (newOrder.getId() == 0) {
            int maxId = orderMap.keySet().stream().mapToInt(v -> v).max().orElse(0);
            newOrder.setId(maxId + 1);
        }

        orderMap.put(newOrder.getId(), newOrder);
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
            for (Map.Entry<Integer, Order> entry : orderMap.entrySet()) {
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
            Type type = new TypeToken<HashMap<Integer, Order>>() {
            }.getType();
            HashMap<Integer, Order> loaded = gson.fromJson(reader, type);
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
            Order defaultOrder = new Order();
            defaultOrder.setId(1);
            defaultOrder.setCustomerName("Default Customer");
            defaultOrder.setDrinkType("Brewed Coffee");
            defaultOrder.setDrinkSize("Medium");
            defaultOrder.setMilkType("None");
            defaultOrder.setQuantity(1);
            defaultOrder.setUnitPrice(2.50);
            defaultOrder.setExtraShots(0);
            defaultOrder.setMilkSurcharge(0.0);
            defaultOrder.setOrderStatus("Pending");

            orderMap.put(defaultOrder.getId(), defaultOrder);
            writeAll();
            System.out.println("Created new initial file at: " + PATH_NAME);
        }

        /// BJ example
//        if (Files.exists(path)) {
//            System.out.println("The orders file already exists!");
//            readAll();
//        } else {
//            //add premade variables to fill file
//            Order order = new Order();
//
//            orderMap.put(1, order);
//        }
    }
}













