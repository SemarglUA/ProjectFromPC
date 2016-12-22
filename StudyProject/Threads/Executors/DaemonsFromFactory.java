package Threads.Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

public class DaemonsFromFactory implements Runnable {
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool(
                new DaemonThreadFactory());
        for(int i = 0; i < 10; i++)
            exec.execute(new DaemonsFromFactory());
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(102); // Задержка
    }
}