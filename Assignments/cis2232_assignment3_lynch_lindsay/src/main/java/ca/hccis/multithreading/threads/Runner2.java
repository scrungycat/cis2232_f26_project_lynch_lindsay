package ca.hccis.multithreading.threads;

import ca.hccis.coffee.Controller;

/**
 * Second runner for multithreading
 * JOPTIONPANE
 * Assignment 3
 *
 * @author LRML
 * @since 10082026
 */

public class Runner2 implements Runnable {

    @Override
    public void run() {

        //add joptionpane order entry
        Controller.menuGUI();

//        long total = 0;
//        int start = Controller.MAX / 2 + 1;
//        for (int i = start; i < Controller.MAX; i++) {
//            total = total + i;
//        }
//        IO.println("Runner2 total: " + total);
    }
}
