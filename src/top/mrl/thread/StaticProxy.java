package top.mrl.thread;

/**
 * 静态代理
 */

public class StaticProxy {
    public static void main(String[] args) {
        new MarryCompany(new You()).happyMarry();
    }
}

interface Marry {
    void happyMarry();
}

// 真实角色
class You implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("You are Married");
    }
}

// 代理角色
class MarryCompany implements Marry {
    private final Marry target;

    public MarryCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }

    private void ready() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }
}
