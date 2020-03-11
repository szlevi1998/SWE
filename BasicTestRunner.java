import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BasicTestRunner extends TestRummer {
    public BasicTestRunner(Class<?> klass) {
        super(klass);
    }

    @Override
    public void runTestMethods() {

        try {
            int numberOfTests = 0, numberOfFailures = 0, numberOfErrors = 0;
            for (Method method : getAnnotatedMethods(Test.class)) {
                System.out.println(method);
                Object instance = klass.getDeclaredConstructor().newInstance();
                try {


                    method.invoke(instance);
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    cause.printStackTrace();
                    if (cause instanceof AssertionError) {
                        numberOfFailures++;
                    } else {
                        numberOfErrors++;
                    }
                }
                numberOfTests++;
            }
            System.out.printf("Tesztek száma: %d\n",numberOfTests);
            System.out.printf("Tesztek száma: %d\n",numberOfFailures);
            System.out.printf("Tesztek száma: %d\n",numberOfErrors);
        } catch (ReflectiveOperationException e) {
            System.out.println("A reflexió nem sikerült");
        }
    }
    }