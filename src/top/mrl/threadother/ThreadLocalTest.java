package top.mrl.threadother;

/**
 * ThreadLocal
 * 每个线程拥有独立的空间
 */

public class ThreadLocalTest {
    // 初始化一个ThreadLocal
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->100);

    public static void main(String[] args) {
        new Thread(new ThreadT()).start();
        new Thread(new ThreadT()).start();
    }

    private static class ThreadT implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "-->" + threadLocal.get());
            threadLocal.set(threadLocal.get() - 1);
            System.out.println(Thread.currentThread() + "-->" + threadLocal.get());
        }
    }
}
