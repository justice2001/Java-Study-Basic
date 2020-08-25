package top.mrl.gf;

/**
 * 七夕特别程序！
 * 对象？ new一个不就完了！！
 * @author ZhengyiLiu
 */

public class GfMain {
    public static void main(String[] args) throws Exception {
        final GirlFriend gf = new GirlFriend(1000000, true, true);
        System.out.println("========COOK=========");
        Food f = gf.cook();
        System.out.println(f.taste);
        System.out.println("========LOVE YOU?=======");
        System.out.println(gf.loveMe());
        System.out.println("========TENDER=======");
        gf.tender();
        System.out.println("========WORK=======");
        gf.work();
    }
}
