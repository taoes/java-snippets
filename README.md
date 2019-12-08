## 一些Java高级特性的实现代码示例


#### 动态编译 


+ 核心实现 ` JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();`
+ 类加载器的实现

```java

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
        // 获取系统编译器
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        // 获取文件管理器
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);

        Iterable<? extends SimpleJavaFileObject> javaObjects = classSourcesList.stream().map(ClassSource::toStringJavaObject).collect(Collectors.toList());

        // 构建编译输出目录
        String outDir = null;
        try {
            File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
            outDir = file.getAbsolutePath() + File.separator;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // 构建编译选项
        Iterable<String> optional = Arrays.asList("-d", outDir);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, optional, null, javaObjects);

        // 执行编译
        if (task.call()) {
            try {
                // 遍历加载类到方法区
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

```


+ 编译出的class文件

![编译出的class文件](./doces/img/dynamic_compile.png)




#### 自定义类加载器

+ 类加载器的实现代码 `/Users/zhoutao/code/java-snippets/jvm/class_loader/src/com/taoes/classLoader/CustomizeClassLoader.java`

+ 核心实现代码，继承 java.lang.ClassLoader 重写 findClass() 方法，

```java
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

```

+ JAVA 官方推荐重写findClass()方法，而不是重写loadClass() 方法，是为了保证Java代码基础代码的稳定性
在loadClass() 方法的代码中比较详细的阐述了双亲委派机制的实现。其实现代码如下：

- [x] 使用synchronized 防止多线程一起加载
- [x] 使用findLoadedClass 判断该类是否已被加载
- [x] 如果没有被加载那么使用父类尝试使用父类加载器加载
- [x] 若父类加载器为null,则使用启动类加载器加载
- [x] 均加载失败，则使用自定的类加载加载，也就是我们Override的findClass(String name);

```java
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```