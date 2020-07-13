package top.mrl.thread;

/**
 * 插队
 */

public class JoinThread {
    public static void main(String[] args) {
        new Thread(new Father()).start();
    }
}

class Father implements Runnable {

    @Override
    public void run() {
        System.out.println("爸爸没烟了");
        System.out.println("让儿子去买中华");
        Thread t = new Thread(new Son());
        t.start();
        try {
            t.join(); //插队线程，Father线程被阻塞
            System.out.println("买到烟了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Son implements Runnable {

    @Override
    public void run() {
        System.out.println("儿子拿着钱出门了");
        System.out.println("路上有个游戏厅，儿子进去玩了10秒");
        for (int i = 0;i < 10;i++) {
            System.out.println((i + 1) + "秒过去了");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("儿子买到了烟");
    }
}
