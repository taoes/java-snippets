package com.taoes.classLoader;

import java.io.FileInputStream;

public class CustomizeClassLoader extends ClassLoader {

    private String classPath;

    public CustomizeClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = loadBytes(name);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException("Not found exception");
        }
    }


    private byte[] loadBytes(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        byte[] dataBy;
        try (FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class")) {
            int len = fileInputStream.available();
            dataBy = new byte[len];
            fileInputStream.read(dataBy);
        }
        return dataBy;
    }
}
