package top.mrl.thread;


public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread n = new Thread(new NewThread());
        // NEW
        Thread.State state = n.getState();
        System.out.println(state);

        n.start();

        // RUNNABLE
        state = n.getState();
        System.out.println(state);


        // TIMED_WAITING
        while (state != Thread.State.TERMINATED) {
            Thread.sleep(200);
            state = n.getState();
            System.out.println(state);
        }

        // TERMINATED
        state = n.getState();
        System.out.println(state);



    }
}

class NewThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0;i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----");
        }
    }
}
