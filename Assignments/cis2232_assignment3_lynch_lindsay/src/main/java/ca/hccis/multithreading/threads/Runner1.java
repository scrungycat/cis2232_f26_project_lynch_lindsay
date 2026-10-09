package ca.hccis.multithreading.threads;

import ca.hccis.coffee.Controller;

/**
 * First runner for multithreading
 * CONSOLE
 * Assignment 3
 *
 * @author LRML
 * @since 10082026
 */

public class Runner1 extends Thread {

    @Override
    public void run() {

        //runs the add order functionality like before
        Controller.menuConsole();

        // example code for accumulating
//        long total = 0;
//        int end = Controller.MAX / 2;
//        for (int i = Controller.MIN; i < end; i++) {
//            total = total + i;
//        }
//        IO.println("Runner1 total: " + total);
    }
}
