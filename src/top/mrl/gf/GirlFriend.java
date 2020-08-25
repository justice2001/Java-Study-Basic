package top.mrl.gf;

/**
 * 七夕特别程序！
 * 为你准备的对象模版！！！
 * @author ZhengyiLiu
 */

public class GirlFriend extends  Girl {
    GirlFriend(int money, boolean haveHouse, boolean haveCar) throws Exception {
        if (money <= 100000 || !haveHouse || !haveCar) {
            throw new Exception("对象嫌弃了你！");
        }
        this.isBeautiful = true;
    }

    public Food cook() {
        return new Food("GOOD!");
    }

    public void tender() {
        System.out.println("我会撒娇,你会吗！");
    }

    public void work() {
        System.out.println("GO TO WORK!!!");
    }

    public boolean loveMe() {
        return true;
    }
}

class Girl {
    boolean isBeautiful;
    Girl() {

    }
}

class Food {
    Food(String taste) {
        this.taste = taste;
    }

    String taste;
}
