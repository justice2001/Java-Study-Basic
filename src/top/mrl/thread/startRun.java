package top.mrl.thread;

/**
 * 重写runnable实现启动线程
 * 优先使用，避免单继承的局限性
 */

public class startRun implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new startRun()).start();

        for (int i = 0;i < 10;i++) {
            System.out.println("一边Coding");
            Thread.sleep(20);
        }
    }

    @Override
    public void run() {
        for (int i = 0;i < 10;i++) {
            System.out.println("一边听歌");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
