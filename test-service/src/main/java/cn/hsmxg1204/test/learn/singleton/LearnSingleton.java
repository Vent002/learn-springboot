package cn.hsmxg1204.test.learn.singleton;

/**
 * TODO
 *
 * @author gxming
 * @description 学习单例模式设计
 *  饿汉模式
 *  懒汉模式
 *  双重校验锁
 *  静态内部类
 *  枚举
 * @date 2021-06-17 11:42
 */
public class LearnSingleton {
    /**
     * 1. 饿汉模式
     * 类的构造函数定义为private的，保证其他类不能实例化此类，然后提供了一个静态实例并返回给调用者。
     *
     *  <p>优点</p>
     *  实现简单，类加载的时候就对实例进行创建，实例在整个程序周期都存在。
     *  只在类加载的时候创建一次实例。
     *  避免多个线程创建多个实例。
     *
     *  <p>缺点</p>
     *  该单例没有用到也会被创建，造成内存浪费。
     */
    public static LearnSingleton INSTANCE = new LearnSingleton();
    // 构造函数
    private LearnSingleton(){}

    public static LearnSingleton getINSTANCE(){
        return INSTANCE;
    }

    /**
     * 2. 懒汉模式
     * 懒汉模式中单例是在需要的时候才去创建的，如果单例已经创建，
     * 再次调用获取接口将不会重新创建新的对象，而是直接返回之前创建的对象。
     *
     * <p>优点</p>
     * 线程并发问题
     * 延迟加载
     *
     * <p>缺点</p>
     * synchronized修饰的同步方法比一般方法要慢很多，
     * 如果多次调用getInstance()，累积的性能损耗就比较大了。
     *
     */

    public static LearnSingleton learnSingleton = null;
    // 构造函数

    // 加线程锁，避免多线程同步的问题
    public static synchronized LearnSingleton getLearnSingleton() {
        if(null == learnSingleton){
            learnSingleton = new LearnSingleton();
        }
        return learnSingleton;
    }

    /**
     *  3. 双重校验锁
     *  由于单例对象只需要创建一次，如果后面再次调用 getDoubleCheckInstance()只需要直接返回单例对象。
     *  大部分情况下，调用 getDoubleCheckInstance()都不会执行到同步代码块，从而提高了程序性能。
     *  不过还需要考虑一种情况，假如两个线程A、B，A执行了if (doubleCheckInstance == null)语句，
     *  它会认为单例对象没有创建，此时线程切到B也执行了同样的语句，B也认为单例对象没有创建，
     *  然后两个线程依次执行同步代码块，并分别创建了一个单例对象。为了解决这个问题，
     *  还需要在同步代码块中增加if (doubleCheckInstance == null)语句，第二次判断doubleCheckInstance == null。
     *
     *  指令重排优化
     *  所谓指令重排优化是指在不改变原语义的情况下，通过调整指令的执行顺序让程序运行的更快。
     *  JVM中并没有规定编译器优化相关的内容，也就是说JVM可以自由的进行指令重排序的优化。
     *
     *  由于指令重排优化的存在，导致初始化Singleton和将对象地址赋给instance字段的顺序是不确定的。
     *  在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象的字段设
     *  为默认值。此时就可以将分配的内存地址赋值给instance字段了，然而该对象可能还没有初始化。
     *  若紧接着另外一个线程来调用getInstance，取到的就是状态不正确的对象，程序就会出错。
     *
     *  volatile
     *  volatile的一个语义是禁止指令重排序优化，也就保证了instance变量被赋值的时候对象已经是初始化过的，
     *  从而避免了上面说到的问题。
     */
    public static volatile LearnSingleton doubleCheckInstance = null;
    // 构造函数
    public static LearnSingleton getDoubleCheckInstance(){
        if(doubleCheckInstance == null){
            synchronized (LearnSingleton.class){
                if(doubleCheckInstance == null){
                    doubleCheckInstance = new LearnSingleton();
                }
            }
        }
        return doubleCheckInstance;
    }

    /**
     * 4. 静态内部类
     *
     * 利用类加载机制，不存在多线程并发问题。
     * 并且使用内部类创建实例，因此只要不使用内部类，JVM就不会加载该单例类，也就
     * 不会创建该单例的实例对象，从而实现了懒汉的延迟加载。
     * 保证延迟加载与线程安全。
     */
    private static class LearnSingletonInner{
        public static LearnSingleton INSTANCE = new LearnSingleton();
    }
    // 构造函数

    public static LearnSingleton newInstance(){
        return LearnSingletonInner.INSTANCE;
    }

    /**
     * 5. 使用枚举实现
     * 看 enum Singleton
     */
    enum World{
        INSTANCE;
        private String name = "world";

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
