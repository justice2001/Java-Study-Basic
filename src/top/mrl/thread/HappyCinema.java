package top.mrl.thread;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema {
    public static void main(String[] args) {
        List<Integer> cinema = new ArrayList<Integer>();
        for (int i = 0;i < 20;i++)
            cinema.add(i);
        Cinema cinema1 = new Cinema(cinema);

        List<Integer> c1t = new ArrayList<>();
        c1t.add(1);
        c1t.add(6);

        List<Integer> c2t = new ArrayList<>();
        c2t.add(2);

        List<Integer> c3t = new ArrayList<>();
        c3t.add(1);

        new Thread(new Customer(cinema1, c1t)).start();
        new Thread(new Customer(cinema1, c2t)).start();
        new Thread(new Customer(cinema1, c3t)).start();
    }
}

class Cinema {
    private List<Integer> available;

    public Cinema(List<Integer> available) {
        this.available = available;
    }

    public boolean bookTicket(List<Integer> seats) {
        if (available.size() <= seats.size())
            return false;
        System.out.println("当前剩余位置为:" + available);
        List<Integer> copy = new ArrayList<Integer>(available);

        copy.removeAll(seats);
        if (available.size() - copy.size() != seats.size())
            return false;
        available = copy;
        return true;
    }

    public List<Integer> getAvailable() {
        return available;
    }
}

class Customer implements Runnable {
    private final Cinema cinema;
    private final List<Integer> seats;

    public Customer(Cinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            boolean flag = cinema.bookTicket(seats);
            if (flag)
                System.out.println("出票成功,位置为:" + seats + ",剩余位置:" + cinema.getAvailable());
            else System.out.println("出票失败!");
        }
    }
}
