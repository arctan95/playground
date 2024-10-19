package com.example.playground.classloader;

public class House {

    private Owner owner;

    public House() {
        Worker worker = new Worker("Tom");
        System.out.println(worker);
        Thread t = new Thread(worker);
        t.setName("test-worker-wait-thread");
        t.start();
        worker.waitUntilZero();
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        String current = Thread.currentThread().getName();
        return String.format("[%s] A new house, loaded by %s", current, this.getClass().getClassLoader());
    }
}
