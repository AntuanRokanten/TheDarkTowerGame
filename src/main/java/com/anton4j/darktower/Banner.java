//package com.anton4j.darktower;
//
//import com.anton4j.darktower.console.ConsoleLine;
//
//import java.util.List;
//
//import static java.util.concurrent.TimeUnit.MILLISECONDS;
//
///**
// * Game's banner.
// *
// * @author anton
// */
//public class Banner extends ConsoleLines {
//
//    public Banner(List<ConsoleLine> lines) {
//        super(lines);
//    }
//
//    public void printSequentially() {
//        for (ConsoleLine line : lines) {
//            line.println();
//            try {
//                MILLISECONDS.sleep(300);
//            } catch (InterruptedException ignored) {
//            }
//        }
//    }
//
//}
