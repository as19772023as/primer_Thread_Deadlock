package ru.strebkov;

public class Main {
    public static void main(String[] args) {
        Account object1 = new Account(151, 2000);
        Account object2 = new Account(152, 3000);

        new Thread(() -> {
            manySend(object1, object2);
        }, "Thread1").start();

        new Thread(() -> {
            manySend(object2, object1);
        }, "Thread2").start();
    }

    public static void manySend(Account from, Account to) {

        Account object1 = from.id < to.id ? from : to;
        Account object2 = from.id < to.id ? to : from;

        synchronized (object1) {
            System.out.println(Thread.currentThread().getName() + " -> объект 1");
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + "  -> объект 2");
            }
        }
        System.out.println(Thread.currentThread().getName() + "  ->  finish");
    }

    static class Account {
        int id;
        int balans;

        public Account(int id, int balans) {
            this.id = id;
            this.balans = balans;
        }
    }
}