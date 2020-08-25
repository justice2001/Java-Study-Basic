package top.mrl.server.basic;

/**
 * 反射
 * 获取Class
 * @author a2507
 *
 */

public class ReflectTest {
    public static void main(String[] args) throws ReflectiveOperationException {
        // 1.使用先有对象获取
        Iphone iphone = new Iphone();
        Class cls = iphone.getClass();
        // 2.类.class
        cls = Iphone.class;
        // 3.Class.forName(报名.类名)
        cls = Class.forName("top.mrl.server.basic.Iphone");

        // 创建对象
        Iphone iphone2 = (Iphone)cls.getConstructor().newInstance();
    }
}


class Iphone {
    public Iphone() {

    }
}
