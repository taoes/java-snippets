package com.taoes.compile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaCodeEval implements Compile {

    @Override
    public List<Class> process(List<ClassSource> classSourcesList) throws IOException {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);

        Iterable<? extends SimpleJavaFileObject> javaObjects = classSourcesList.stream().map(ClassSource::toStringJavaObject).collect(Collectors.toList());

        String outDir = null;
        try {
            File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
            outDir = file.getAbsolutePath() + File.separator;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Iterable<String> optional = Arrays.asList("-d", outDir);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, optional, null, javaObjects);

        if (task.call()) {
            try {
                List<Class> classList = new ArrayList<>(classSourcesList.size());
                for (ClassSource classSource : classSourcesList) {
                    String className = classSource.getClassName();
                    Class<?> aClass = Class.forName(className);
                    classList.add(aClass);
                }
                return classList;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
