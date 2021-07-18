package others.reflect;

import java.lang.reflect.*;

/**
 * Created by lpf on 2021/6/18.
 */
public class Test {

    private String name;
    private int age;


    Test(){

    }

    private Test(String _name, int _age){
        this.name = _name;
        this.age = _age;
    }


    public String getString(){
        return "123";
    }

    public static void getStaticMethod(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static class TestT<T> {
        public void test(T t) {
            System.out.println("打印test中的值"+t.toString());
        }
    }

}
