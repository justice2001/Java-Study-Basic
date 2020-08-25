package top.mrl.thread;

/**
 * 生产者消费者模式: 红绿灯法
 */

public class CoTest {
    public static void main(String[] args) {
        TvShower ts = new TvShower();
        new Thread(new Shower(ts)).start();
        new Thread(new Watcher(ts)).start();
    }
}

// 表演者 生产者
class Shower implements Runnable {
    TvShower tvShower;

    public Shower(TvShower tvShower) {
        this.tvShower = tvShower;
    }

    @Override
    public void run() {
        for (int i = 0;i < 20;i++) {
            if (i % 2 == 0) {
                this.tvShower.show("大家好才是真的好！");
            }else {
                this.tvShower.show("奥里给!");
            }
        }
    }
}
// 观看者 消费者
class Watcher implements Runnable {
    TvShower tvShower;

    public Watcher(TvShower tvShower) {
        this.tvShower = tvShower;
    }

    @Override
    public void run() {
        for (int i = 0;i < 20;i++) {
            this.tvShower.watch();
        }
    }
}
// 电视节目 同一个资源
class TvShower {
    String voice;
    // 红绿灯
    // true 表演
    // false 观看
    boolean flag = true;

    public synchronized void show(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表演了:" + voice);
        this.voice = voice;
        this.flag = !this.flag;
        this.notifyAll();
    }

    public synchronized void watch() {
        if (this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看到了：" + voice);
        this.flag = !this.flag;
        this.notifyAll();

    }
}
