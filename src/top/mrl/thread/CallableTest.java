package top.mrl.thread;

import java.util.concurrent.*;

/**
 * 后期学习
 * 只做了解
 * 可返回值可抛异常
 * @author Justice Liu
 */

public class CallableTest implements Callable<Boolean> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> res1 = ser.submit(new CallableTest());
        Future<Boolean> res2 = ser.submit(new CallableTest());
        Future<Boolean> res3 = ser.submit(new CallableTest());
        // 获取结果
        System.out.println(res1.get());
        System.out.println(res2.get());
        System.out.println(res3.get());
        // 关闭服务
        ser.shutdownNow();
    }

    @Override
    public Boolean call() throws Exception {
        for (int i = 0;i < 10;i++){
            System.out.println("HELLO" + i);
        }
        return true;
    }
}
