package top.mrl.thread;

/**
 * 礼让线程
 */

public class YieldThread {
    public static void main(String[] args) {
        new Thread(new MyYield()).start();

        for (int i = 0;i < 1000;i++ ){
            if (i >= 60) {
                Thread.yield();
            }
            System.out.println("main ---> " + i);
        }
    }
}

class MyYield implements Runnable {

    @Override
    public void run() {
        for (int i = 0;i < 1000;i++) {
            System.out.println("yield ---> " + i);
        }
    }
}