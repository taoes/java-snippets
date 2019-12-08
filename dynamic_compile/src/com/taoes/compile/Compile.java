package com.taoes.compile;

import java.io.IOException;
import java.util.List;

public interface Compile {

    List<Class> process(List<ClassSource> classSourcesList) throws IOException;

}
