package com.taoes.compile;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DynamicCompile {


    public static void main(String[] args) throws IOException {
        String className = "com.test.Eval";

        String code = "public static void main(String[]args){ Long a = 100L; System.out.println(a.intValue()); System.out.println(Constant.NAME.toLowerCase());  }";
        StringBuilder sb = new StringBuilder();
        sb.append("package com.test;");
        sb.append(" public class Eval{ ");
        sb.append(code);
        sb.append(" }");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package com.test; public class Constant { public static final String NAME = \"HELLO，WORLD\";}");


        JavaCodeEval codeEval = new JavaCodeEval();
        ClassSource classSource = new ClassSource(className, sb.toString());
        ClassSource classSource2 = new ClassSource("com.test.Constant", stringBuilder.toString());
        List<Class> classList = codeEval.process(Arrays.asList(classSource, classSource2));
        Object invokeResult = null;
        try {
            for (Class clazz : classList) {
                Object obj = clazz.newInstance();
                Method m = clazz.getMethod("main", String[].class);
                if (m == null) {
                    continue;
                }
                invokeResult = m.invoke(obj, new Object[]{new String[]{}});
                break;
            }
        } catch (Exception e) {
        }
        if (invokeResult != null) {
            System.out.println(invokeResult);
        }
    }
}
