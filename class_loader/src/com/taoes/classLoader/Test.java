package com.taoes.classLoader;


import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws Exception {
        // 修改此处class文件路径
        CustomizeClassLoader loader = new CustomizeClassLoader("/Users/zhoutao/code/tmp/jvm/class");
        Class<?> aClass = loader.findClass("com.taoes.classLoader.CustomizeExampleClass");
        Object newInstance = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("toUp", null);
        Object invokeResult = method.invoke(newInstance, null);
        System.out.println(invokeResult);
        System.out.println(aClass.getClassLoader());
    }
}
