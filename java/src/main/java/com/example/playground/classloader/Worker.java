package com.example.playground.classloader;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    CountDownLatch latch = new CountDownLatch(1);
    String name;

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String current = Thread.currentThread().getName();
        System.out.printf("[%s] Start running and counting down...\n", current);
        setOwner();
        latch.countDown();
        System.out.printf("[%s] End counting\n", current);
    }

    private void setOwner() {
        String current = Thread.currentThread().getName();
        System.out.printf("[%s] setOwner called\n", current);
        Owner owner = new Owner(name);
        System.out.printf("[%s] %s will live in this house!\n", current, owner.getName());
    }

    public void waitUntilZero() {
        try {
            latch.await();
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void doWork() {
        String current = Thread.currentThread().getName();
        System.out.printf("[%s] Start building a house...\n", current);
    }

    @Override
    public String toString() {
        String current = Thread.currentThread().getName();
        return String.format("[%s] A new worker, loaded by %s", current, this.getClass().getClassLoader());
    }
}
