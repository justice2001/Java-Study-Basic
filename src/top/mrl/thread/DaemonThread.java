package top.mrl.thread;

/**
 * 守护线程
 * jvm无需等待守护线程执行完毕
 */

public class DaemonThread {
    public static void main(String[] args) {
        Thread you = new Thread(new Person());
        Thread god = new Thread(new God());

        // 将God设置为守护线程
        god.setDaemon(true);
        you.start();
        god.start();
    }
}

class Person implements Runnable {

    @Override
    public void run() {
        for (int i = 0;i < 365 * 100;i++)
            System.out.println("Alive");
        System.out.println("DEAD");
    }
}

class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("Bless You");
        }
    }
}