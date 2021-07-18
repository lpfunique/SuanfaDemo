package others.reflect;

import java.lang.reflect.*;

public class LearnReflect {

    public static void main(String[] args) {

        testReflect();
    }

    private static void testReflect() {
        try {
            // 第一种， 使用ClassLoader装在类，并对类进行初始化
            Class c1= Class.forName("others.reflect.Test");
            Object t1 = c1.newInstance();
            System.out.println(((Test)t1).getString());

            // 第二种，返回类对象运行时真正所指的对象、所属类型的Class对象
            Class c2 = new Test().getClass();
            Object t2 = c2.newInstance();
            System.out.println(((Test)t2).getString());


            // 第三种，ClassLoader装入内存，不对类进行类的初始化
            Class c3 = Test.class;
            Object t3 = c3.newInstance();
            System.out.println(((Test)t3).getString());

            // 有参数的构造
            Constructor<?> csr = c1.getDeclaredConstructor(String.class, int.class);
            csr.setAccessible(true);
            Object o1 = csr.newInstance("lpf",18);

            // 反射类中的属性
            // getField : 只能获取public的，包括从父类继承来的字段
            // getDeclaredField: 可以获取本类所有的字段，包括private以及继承来的字段
            Field field = c1.getDeclaredField("name");
            // 使用setAccessible取消Java的权限控制检查，特别是可以取消私有字段访问限制
            // public 和 private修饰的属性，默认accessible属性都为false
            field.setAccessible(true);
            field.set(t1, "lpf");
            System.out.println(((Test) t1).getName());

            // 修改属性中的修饰符
            Field field2 = c1.getDeclaredField("name");
            String priv = Modifier.toString(field2.getModifiers());
            System.out.println("打印属性修饰符" + priv);

            // 反射类中的方法
            Method m = c1.getDeclaredMethod("setName",String.class);
            m.invoke(o1, "new invoke name");
            System.out.println("打印setName后的结果"+((Test)o1).getName());

            // 反射静态方法
            Class clz = Class.forName("others.reflect.Test");
            Method m2 = clz.getDeclaredMethod("getStaticMethod");
            m2.invoke(null);

            // 反射泛型参数方法
            Class clzT = Test.TestT.class;
            // 方法中有泛型参数时，编译器会自动类型向上转型，T 向上转型是Object
            Method m3 = clzT.getDeclaredMethod("test", Object.class);
            m3.setAccessible(true);
            m3.invoke(new Test.TestT<Integer>(),1);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
