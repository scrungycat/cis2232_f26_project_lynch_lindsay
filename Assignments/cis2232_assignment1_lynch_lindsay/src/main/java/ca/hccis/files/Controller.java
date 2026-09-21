package ca.hccis.files;

import ca.hccis.files.entity.Order;
import ca.hccis.files.util.CisUtility;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Main entrypoint controller for application
 *
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
    //    private static Gson gson = new Gson();
    public static final String PATH_NAME = "c:\\cis2232\\orders.json";

    public static void main(String[] args) {

        initialize();

        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU);

            switch (menuOption) {
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
        } while (menuOption != EXIT);
    }

    /**
     * Processes first menu option (A - Add order)
     *
     * @author LRML
     * @since 09152026
     */
    public static void add() {
        Order newOrder = new Order();
        IO.println("--Add Order--");

        //need to make this in the order class
//        newOrder.getInformation();

//        orderMap.put(newOrder.getOrderId(), newOrder);

        writeAll();
    }

    /**
     * Processes second menu option (V - View all orders)
     *
     * @author
     * @since
     */
    public static void viewAll() {
        readAll();

    }

    /**
     * Writes to json file
     *
     * @author
     * @since
     */
    public static void writeAll(){

    }

    /**
     * Reads json file
     *
     * @author
     * @since
     */
    public static void readAll() {

    }


    public static void initialize() {

        Path path = Paths.get(PATH_NAME);

        if (Files.exists(path)) {
            System.out.println("The orders file already exists!");
            readAll();
        } else {
            //add premade variables to fill file
            Order order = new Order();

            orderMap.put(1, order);
        }
    }
}
