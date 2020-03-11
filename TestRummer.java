import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TestRummer {

    protected Class<?> klass;

    public TestRummer(Class<?> klass){
        this.klass = klass;

    }

    protected List<Method> getAnnotatedMethods(final Class<? extends Annotation> annotatedClass){
        return Arrays.stream(klass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(annotatedClass))
        .collect(Collectors.toList());
    }

    public abstract void runTestMethods();

}



