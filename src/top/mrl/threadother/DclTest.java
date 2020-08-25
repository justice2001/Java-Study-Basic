package top.mrl.threadother;

/**
 * DCL单例模式结合DoubleChecking和volatile
 */

public class DclTest {
    // 2.提供静态属性
    private static volatile DclTest instance;
    // 1.构造器私有化
    private DclTest() {

    }
    // 3.提供公共的静态方法获取属性
    public static DclTest getInstance() {
        // Double-Checking
        if (null != instance) {
            return instance;
        }
        // 同步
        synchronized (DclTest.class) {
            if (null == instance) {
                instance = new DclTest();
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(DclTest.getInstance());
        }).start();
        new Thread(()->{
            System.out.println(DclTest.getInstance());
        }).start();
    }
}
