package com.taoes.compile;

public class ClassSource {

    String className;

    String source;

    public ClassSource(String className, String source) {
        this.className = className;
        this.source = source;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public StringJavaObject toStringJavaObject() {
        return new StringJavaObject(this.className, this.source);
    }
}
