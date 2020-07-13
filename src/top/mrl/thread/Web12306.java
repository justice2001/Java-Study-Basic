package top.mrl.thread;

import javax.sound.midi.Track;

/**
 * 方便共享资源
 */

public class Web12306 implements Runnable{
    public static void main(String[] args) {
        Web12306 w = new Web12306();

        new Thread(w, "thread-worker-1").start();
        new Thread(w, "thread-worker-2").start();
        new Thread(w, "thread-worker-3").start();
    }

    @Override
    public void run() {
        int t = 100;
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (t >= 0) {
            System.out.println("[" + Thread.currentThread().getName() + "]:" + t--);
        }
    }
}
