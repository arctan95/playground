package com.example.playground.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

//    static {
//        // control classloader parallelCapable
//        ClassLoader.registerAsParallelCapable();
//    }

    private ClassLoader javaseClassLoader;

    public MyClassLoader() {
        ClassLoader j = String.class.getClassLoader();
        if (j == null) {
            j = getSystemClassLoader();
            while (j.getParent() != null) {
                j = j.getParent();
            }
        }
        this.javaseClassLoader = j;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String current = Thread.currentThread().getName();
        synchronized (getClassLoadingLock(name)) {
            Class<?> clazz = null;
            try {
//                System.out.printf("[%s] JavaseClassLoader try loading class: [%s]...\n", current, name);
                clazz = javaseClassLoader.loadClass(name);
                if (clazz != null) {
//                    System.out.printf("[%s] JavaseClassLoader load class success: [%s]\n", current, name);
                    return clazz;
                }
            } catch (ClassNotFoundException e) {
                System.out.printf("[%s] JavaseClassLoader can't load class: [%s]\n", current, name);
            }

            try {
                System.out.printf("[%s] MyClassLoader try to load class: [%s]...\n", current, name);
                clazz = findClass(name);
                if (clazz != null) {
                    System.out.printf("[%s] MyClassLoader load class success: [%s]\n", current, name);
                    return clazz;
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        throw new ClassNotFoundException(name);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        byte[] b = loadClassFromFile(name);
        clazz = defineClass(name, b, 0, b.length);

        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    private byte[] loadClassFromFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
