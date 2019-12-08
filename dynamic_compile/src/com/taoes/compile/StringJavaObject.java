package com.taoes.compile;

import java.io.IOException;
import java.net.URI;
import javax.tools.SimpleJavaFileObject;

public class StringJavaObject extends SimpleJavaFileObject {

    private String content;

    protected StringJavaObject(String name, String  content) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return content;
    }
}
