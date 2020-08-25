package top.mrl.thread;

/**
 * 生产者消费者模式：管程法
 */

public class CoTest1 {
    public static void main(String[] args) {
        Buffer1 buffer = new Buffer1();
        new Thread(new Productor1(buffer)).start();
        new Thread(new Consumer1(buffer)).start();
    }
}

// 生产者
class Productor1 implements Runnable {
    Buffer1 buffer1;

    public Productor1(Buffer1 buffer1) {
        this.buffer1 = buffer1;
    }

    @Override
    public void run() {
        for (int i = 0;i < 100;i++) {
            // 生产
            this.buffer1.push(new Item1(i));
            System.out.println("生产第" + i + "个");
        }
    }
}
// 消费者
class Consumer1 implements Runnable {
    Buffer1 buffer1;

    public Consumer1(Buffer1 buffer1) {
        this.buffer1 = buffer1;
    }

    @Override
    public void run() {
        for (int i = 0;i < 100;i ++) {
            // 消费
            this.buffer1.pop();
            System.out.println("消费第" + i + "个");
        }
    }
}
// 缓冲区
class Buffer1 {
    Item1[] item1s = new Item1[10];
    int count = 0;
    // 存储
    public synchronized void push(Item1 item) {
        // 不足是等待
        if (count == item1s.length - 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //存储后通知读取
        this.notifyAll();
        item1s[count] = item;
        count ++;
    }
    // 读取
    public synchronized Item1 pop() {
        // 不足是等待
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 读取后通知
        this.notifyAll();
        return item1s[count --];
    }

}
// 物品
class Item1 {
    int id;

    public Item1(int id) {
        this.id = id;
    }
}
