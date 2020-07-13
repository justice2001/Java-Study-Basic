package top.mrl.thread;

/**
 * 线程安全的取款
 */

public class synATM {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(200, "J");
        new Thread(new BankAtm(account ,20, "YOU")).start();
        new Thread(new BankAtm(account, 50, "WIFE")).start();
        new Thread(new BankAtm(account, 500, "STOLEN")).start();
    }
}

// 银行账户
class BankAccount {
    private int money;
    private String name;

    BankAccount(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// 取款机
class BankAtm implements Runnable {
    private final BankAccount account;
    private int m;
    private final String name;

    BankAtm(BankAccount account, int m, String name) {
        this.m = m;
        this.account = account;
        this.name = name;
    }

    @Override
    public void run() {
        this.getMoney();
    }

    private void getMoney() {
        if (account.getMoney() <= this.m) {
            System.out.println("余额不足");
            return;
        }
        synchronized (account) {
            if (account.getMoney() <= this.m) {
                System.out.println("余额不足");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.account.setMoney(this.account.getMoney() - this.m);
            System.out.println("[" + this.name + "]取出了:" + this.m + ",银行内还剩:" + this.account.getMoney());
        }
    }
}
