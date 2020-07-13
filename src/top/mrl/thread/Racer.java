package top.mrl.thread;

public class Racer implements Runnable {
    private String winner;
    public static void main(String[] args) {
        Racer racer = new Racer();

        new Thread(racer, "turtle").start();
        new Thread(racer, "rabbit").start();
    }

    @Override
    public void run() {
        for (int i = 0;i <= 100;i++) {
            if (Thread.currentThread().getName().equals("rabbit") && i % 10 == 0) {
                System.out.println("Sleep");
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[" + Thread.currentThread().getName() + "]:" + i);
            if (this.gameOver(i)){
                break;
            }
        }
    }

    private boolean gameOver(int steps) {
        if (this.winner != null) {
            return true;
        }
        if (steps == 100) {
            this.winner = Thread.currentThread().getName();
            System.out.println(this.winner);
            return true;
        }
        return false;
    }
}
