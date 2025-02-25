import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassTest {

    @Test
    public void test_01() throws ClassNotFoundException {
        //1.直接通过class名
        Class<?> clazz1 = String.class;
        //2.通过对象实例
        String str = "Hello World";
        Class<?> clazz2 = str.getClass();
        //3.通过权限定类名
        Class<?> clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz3);

    }
    @Test
    public void test_02() throws ClassNotFoundException{
        Class<?> clazz = Class.forName("java.util.ArrayList");

        System.out.println("类名"+clazz.getName());
        System.out.println("Simple name:"+ clazz.getSimpleName());
        System.out.println("package name:" + clazz.getPackageName());
        System.out.println("Parent Class:" + clazz.getSuperclass().getName());

        Class<?>[] interfaces = clazz.getInterfaces();

        for (Class<?> i : interfaces){
            System.out.println("implementations :" + i.getName());

        }

    }

    @Test
    public void test_03() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("java.lang.String");

        String str = "hello";
        String strstr = new String("hello");

        System.out.println(str == strstr);
        System.out.println(str == strstr.intern());


        Constructor<?>[] constructors = clazz.getConstructors();

        for (Constructor<?> c : constructors){
            System.out.println("Constructor name:" + c);
        }

        Constructor<?> cons = clazz.getConstructor(String.class);

        String result = (String) cons.newInstance("hello_world");

        System.out.println("Created Object:" + result);
    }

    @Test
    public void test_04(){
        student s1 = new student("Tom", 19);
        s1.setSecret("I'm gay");
        s1.printInfo("Tom");
    }

    @Test
    public void test_05() throws NoSuchFieldException, IllegalAccessException {
        student t1 = new student("Tom", 23);
        Class<?> clazz = t1.getClass();
        Field[] ds = clazz.getDeclaredFields();
        for (Field f : ds){
            System.out.println("字段名" + f.getName());
        }
        Field field = clazz.getDeclaredField("secret");
        field.setAccessible(true);
        field.set(t1,"I 'm a fool");
        System.out.println("The value after change : " + field.get(t1));


    }
    @Test
    public void test_06() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        student s1 = new student("steve", 111);

        Class<?> c = s1.getClass();

        Method[] methods = c.getMethods();
        for (Method m : methods){
            System.out.println("Class" + c + "'s method : " + m.getName());
        }

        Method m1 = c.getMethod("printInfo", String.class);
        m1.invoke(s1,"233333");
    }


}


class student {

    private String name;

    private String email;

    private int age;

    private String secret;

    public student(){};

    public student(String name , int age){
        this.age = age;
        this.name = name;
    }

    public void printInfo(String name){
        System.out.print("My name is : " + name + " and I'm " + this.getAge() + " years old ");
        System.out.println("My secret : " + this.getSecret());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String getSecret(){
        return secret;
    }

    public  void setSecret(String secret){
        this.secret = secret;
    }

}



//import java.lang.reflect.Method;
//
//class Person {
//    private String name;
//
//    public Person(String name) {
//        this.name = name;
//    }
//
//    public void sayHello(String greeting) {
//        System.out.println(greeting + ", my name is " + name);
//    }
//}
//
//public class ReflectionExample {
//    public static void main(String[] args) throws Exception {
//        Person person = new Person("Alice");
//
//        // 获取 Class 对象
//        Class<?> clazz = person.getClass();
//
//        // 获取所有方法
//        Method[] methods = clazz.getMethods();
//        for (Method method : methods) {
//            System.out.println("方法名: " + method.getName());
//        }
//
//        // 获取指定方法并调用
//        Method sayHelloMethod = clazz.getMethod("sayHello", String.class);
//        sayHelloMethod.invoke(person, "Hello");
//    }
//}
//
//import java.lang.reflect.Method;
//
//class MathUtil {
//    public static int add(int a, int b) {
//        return a + b;
//    }
//}
//
//public class ReflectionExample {
//    public static void main(String[] args) throws Exception {
//        // 获取 Class 对象
//        Class<?> clazz = MathUtil.class;
//
//        // 获取静态方法并调用
//        Method addMethod = clazz.getMethod("add", int.class, int.class);
//        int result = (int) addMethod.invoke(null, 5, 10); // 静态方法无需实例
//        System.out.println("调用静态方法结果: " + result);
//    }
//}
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
//interface Hello {
//    void sayHello();
//}
//
//class HelloImpl implements Hello {
//    public void sayHello() {
//        System.out.println("Hello from HelloImpl!");
//    }
//}
//
//public class ReflectionExample {
//    public static void main(String[] args) {
//        Hello hello = new HelloImpl();
//
//        // 创建动态代理
//        Hello proxy = (Hello) Proxy.newProxyInstance(
//                hello.getClass().getClassLoader(),
//                hello.getClass().getInterfaces(),
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("Before method call");
//                        Object result = method.invoke(hello, args);
//                        System.out.println("After method call");
//                        return result;
//                    }
//                }
//        );
//
//        // 调用代理方法
//        proxy.sayHello();
//    }
//}
//
//
//import java.lang.reflect.Modifier;
//
//public class ReflectionExample {
//    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> clazz = Class.forName("java.lang.String");
//
//        // 判断是否是 public 类
//        int modifiers = clazz.getModifiers();
//        System.out.println("是否是 public 类: " + Modifier.isPublic(modifiers));
//
//        // 判断是否是 final 类
//        System.out.println("是否是 final 类: " + Modifier.isFinal(modifiers));
//    }
//}
//
//
//import java.lang.annotation.*;
//        import java.lang.reflect.Method;
//
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//@interface MyAnnotation {
//    String value();
//}
//
//class Person {
//    @MyAnnotation(value = "Hello Annotation")
//    public void sayHello() {
//        System.out.println("Hello!");
//    }
//}
//
//public class ReflectionExample {
//    public static void main(String[] args) throws Exception {
//        Method method = Person.class.getMethod("sayHello");
//
//        // 获取方法上的注解
//        if (method.isAnnotationPresent(MyAnnotation.class)) {
//            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
//            System.out.println("注解值: " + annotation.value());
//        }
//    }
//}
