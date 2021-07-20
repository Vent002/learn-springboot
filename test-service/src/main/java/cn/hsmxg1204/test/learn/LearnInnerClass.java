package cn.hsmxg1204.test.learn;

import lombok.Data;

/**
 * TODO
 *
 * @author gxming
 * @description 内部类的学习代码
 * @date 2021-06-17 10:44
 * 1. 成员内部类
 * 2. 局部内部类
 * 3. 匿名内部类
 * 4. 静态内部类
 */
public class LearnInnerClass {
    // 1. 成员内部类
    @Data
    class People{
        private final Born INSTANCE = new Born();
        private String name="test";
        private int age = 24;
        public int count = 1;

        public void People(String name){
            this.name = name;
            getBornInstance().info();
        }

        private Born getBornInstance(){
            return INSTANCE;
        }

        class Born{ // 成员内部类
            public void info(){
                System.out.println("new born baby");
                System.out.println("name : "+name+"\n age :"+age +"count : "+count);
            }
        }
    }

    public static void main(String[] args) {
        LearnInnerClass learnInnerClass = new LearnInnerClass();
        // 创建成员内部对象第一种方式
        People people = learnInnerClass.new People();
        People.Born born = people.new Born();
        born.info();
        // 第二种方式
        People.Born bornInstance = people.getBornInstance();
        bornInstance.info();
    }
}
// 2. 局部内部类
class People{
    public People(){

    }
}
class Man{
    public Man(){

    }
    public People getWomen(){
        // 局部内部类
        class Woman extends People{
            int age = 24;
        }
        return new Woman();
    }
}

// 3. 匿名内部类

// 4. 静态内部类
class Outter{
    int a = 0;
    static int b = 5;
    public Outter(){}
    static class Inner{
        public Inner(){
            System.out.println(b);
        }
    }
}

class TestInnerClass{
    class Bean1 {
        public int I = 0;
    }

    static class Bean2{
        public int J = 0;
    }
    public static void main(String[] args){
        // 初始化Bean1
        //(1)
        TestInnerClass test = new TestInnerClass();
        TestInnerClass.Bean1 bean1 = test.new Bean1();

        bean1.I++;
        // 初始化Bean2
        //(2)
        TestInnerClass.Bean2 bean2 = new TestInnerClass.Bean2();
        bean2.J++;
        //初始化Bean3
        //(3)
        Bean bean = new Bean();
        Bean.Bean3 bean3 = bean.new Bean3();
        bean3.k++;
        System.out.println(bean1.I+" "+bean2.J+" "+ bean3.k);
    }

}

class Bean{
    class Bean3{
        public int k = 0;
    }
}

 class TestIInnerC {
    public static void main(String[] args)  {
        TestOutter outter = new TestOutter();
        outter.new Inner().print();
    }
}


class TestOutter {
    private int a = 1;
    class Inner {
        private int a = 2;
        public void print() {
            int a = 3;
            System.out.println("局部变量：" + a);
            System.out.println("内部类变量：" + this.a);
            System.out.println("外部类变量：" + TestOutter.this.a);
        }
    }
}