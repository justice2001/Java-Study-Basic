package top.mrl.thread;

public class CreatLambda {

    public static void main(String[] args) {
        new Add1().add(1, 2);

        // 匿名内部类
        One one = new One() {
            @Override
            public int add(int a, int b) {
                System.out.println("[Thread-2]:" + (a + b));
                return a + b;
            }
        };
        one.add(1, 2);

        // lambda 表达式
        One one1 = (int a, int b)-> {
            System.out.println("[Thread-3]:" + (a + b));
            return a + b;
        };
        one1.add(10, 20);
    }
}

interface One {
    int add(int a, int b);
}

// 外部类
class Add1 implements One {

    @Override
    public int add(int a, int b) {
        System.out.println("[Thread-1]:" + (a + b));
        return a + b;
    }
}
