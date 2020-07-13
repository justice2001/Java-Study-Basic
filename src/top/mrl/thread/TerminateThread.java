package top.mrl.thread;

/**
 * 终止线程
 */

public class TerminateThread implements Runnable {
    // 创建终止线程标记
    private boolean flag = true;
    private final String str;

    public TerminateThread(String str) {
        this.str = str;
    }

    public static void main(String[] args) throws InterruptedException {
        TerminateThread tt = new TerminateThread("CC");
        new Thread(tt).start ();

        Thread.sleep(2);

        for (int i = 0;i < 100;i++) {
            if (i == 90) {
                tt.terminate();
                System.out.println("CC stop");
            }
            System.out.println("main --->" + i);
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (this.flag) {
            System.out.println(this.str + "--->" + i);
            i++;
        }
    }

    public void terminate() {
        this.flag = false;
    }
}
