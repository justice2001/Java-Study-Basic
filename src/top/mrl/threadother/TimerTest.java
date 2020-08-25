package top.mrl.threadother;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 闹钟实现
 */

public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // 2020-7-17  18:08:00 闹钟
        Calendar cal = new GregorianCalendar(2020, Calendar.JULY, 17, 18, 8);

        timer.schedule(new TimerTask1(), cal.getTime());
    }
}

// Task
class TimerTask1 extends TimerTask {
    @Override
    public void run() {
        System.out.println("时间到");
    }
}
