package com.taoes.classLoader;


public class CustomizeExampleClass {


    private String name;

    private int age;


    public CustomizeExampleClass() {
        this.name = "none";
        System.out.println("CustomizeExampleClass.CustomizeExampleClass");
    }

    public CustomizeExampleClass(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("CustomizeExampleClass.CustomizeExampleClass");
        System.out.println("name = " + name + ", age = " + age);
    }

    public String toUp() {
        return name.toUpperCase();
    }


    @Override
    public String toString() {
        return "CustomizeExampleClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
