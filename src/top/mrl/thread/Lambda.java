package top.mrl.thread;

/**
 * lambda 表达式 实现匿名内部类
 */

public class Lambda {
    // 内部类
    static class T2 implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread-2");
        }
    }
    public static void main(String[] args) {
        new Thread(new T1()).start();

        new Thread(new T2()).start();

        // 局部内部类
        class T3 implements Runnable {

            @Override
            public void run() {
                System.out.println("Thread-3");
            }
        }

        new Thread(new T3()).start();

        // 匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread-4");
            }
        }).start();

        // lambda 表达式
        new Thread(()-> System.out.println("Thread-5")).start();

    }
}

class T1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread-1");
    }
}
