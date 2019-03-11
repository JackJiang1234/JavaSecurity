package common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 *
 * */
public class MethodHelper {

    public static void runStatic(Class c, String ... names) {
        out.println("------run " + c.getName() + " static methods begin....");

        Method[] methods = c.getDeclaredMethods();
        List<String> excludeNames = Arrays.asList(names);
        for (Method m :
                methods) {
            if (Modifier.isStatic(m.getModifiers())){

                String methodName = m.getName();
                if (methodName.equals("main") || excludeNames.contains(methodName)){
                    continue;
                }

                try {
                    out.println("********run "  + methodName + " ...");
                    long before = System.nanoTime();

                    m.setAccessible(true);
                    m.invoke(null);

                    long dur = System.nanoTime() - before;
                    out.println("********run "  + methodName + " end." + " take time:" + dur + " nanosecond");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        out.println("------run " + c.getName() + " static method end.");
    }
}
