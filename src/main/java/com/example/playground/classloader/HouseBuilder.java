package com.example.playground.classloader;

public class HouseBuilder {

    static {
        String current = Thread.currentThread().getName();
        System.out.printf("[%s] HouseBuilder is being initialized...\n", current);
    }

    public HouseBuilder() {
        String current = Thread.currentThread().getName();
        System.out.printf("[%s] HouseBuilder is being created...\n", current);
    }

    public void build(Boolean locked) {
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            if (cl == null) {
                return;
            }

            if (locked) {
                synchronized (cl) {
                    Class<?> clazz = Class.forName("com.example.playground.classloader.House", false, cl);
                    Object h = clazz.newInstance();
                    System.out.println(h);
                }
            } else {
                Class<?> clazz = Class.forName("com.example.playground.classloader.House", false, cl);
                Object h = clazz.newInstance();
                System.out.println(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String current = Thread.currentThread().getName();
        return String.format("[%s] This is [%s], loaded by: %s\n", current, this.getClass().getName(), this.getClass().getClassLoader());
    }

}
