package com.example.playground.classloader;

import java.lang.reflect.Method;

public class Main {

    static final String current = Thread.currentThread().getName();

    public static void main(String[] args) {
        // testInitialization(false);
        // testInstantiation();
        testLockedClassLoader(true);
    }

    public static void testLockedClassLoader(boolean locked) {
        MyClassLoader mc = new MyClassLoader();
        System.out.printf("[%s] This is [%s], loaded by: %s\n", current, mc.getClass().getName(), mc.getClass().getClassLoader());
        try {
            // Use MyClassLoader to load HouseBuilder
            Class<?> clazz = Class.forName("com.example.playground.classloader.HouseBuilder", false, mc);
            Object o = clazz.getDeclaredConstructor().newInstance();
            System.out.println(o);
            Method m = clazz.getDeclaredMethod("build", Boolean.class);
            m.invoke(o, locked);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testInitialization(boolean initialize) {
        MyClassLoader mc = new MyClassLoader();
        System.out.printf("[%s] This is [%s], loaded by: %s\n", current, mc.getClass().getName(), mc.getClass().getClassLoader());
        try {
            // Use MyClassLoader to load HouseBuilder
            Class<?> clazz = Class.forName("com.example.playground.classloader.HouseBuilder", initialize, mc);
            System.out.printf( "%s has been loaded! loaded by: %s\n", clazz.getName(), clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testInstantiation() {
        MyClassLoader mc = new MyClassLoader();
        System.out.printf("[%s] This is [%s], loaded by: %s\n", current, mc.getClass().getName(), mc.getClass().getClassLoader());
        try {
            // Use MyClassLoader to load HouseBuilder
            Class<?> clazz = Class.forName("com.example.playground.classloader.HouseBuilder", false, mc);
            Object o = clazz.newInstance();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
