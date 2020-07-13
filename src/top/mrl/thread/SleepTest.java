package top.mrl.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 倒计时
 * 阻塞线程并不释放资源
 */

public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        Date endDate = new Date(System.currentTimeMillis() + 1000 * 10);
        long endTime = endDate.getTime();
        while (true) {
            System.out.println(new SimpleDateFormat("hh:mm:ss").format(endDate));
            Thread.sleep(1000);
            endDate = new Date(endDate.getTime() - 1000);
            if (endDate.getTime() == endTime - 1000 * 10)
                break;
        }
    }
}
