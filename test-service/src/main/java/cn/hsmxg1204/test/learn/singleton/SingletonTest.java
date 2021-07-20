package cn.hsmxg1204.test.learn.singleton;

import java.io.Serializable;

/**
 * TODO
 *
 * @author gxming
 * @description 静态内部类 实现单例模式
 * @date 2021-06-17 13:58
 *
 * 重写clone()
 * 重写序列化
 */
@SuppressWarnings("unused")
public class SingletonTest {

    private static class Singleton implements Serializable,Cloneable{
        private static volatile boolean isCreate = false;

        private Singleton(){
            if(isCreate){
                throw new RuntimeException("already instantiated");
            }
            isCreate = true;
        }
        // 内部类创建对象实例
        private static volatile Singleton INSTANCE;
        // 静态方法，返回实例对象
        public static Singleton getInstance(){
            if (INSTANCE == null){
                synchronized(Singleton.class){
                    if(INSTANCE == null){
                        INSTANCE = new Singleton();
                    }
                }
            }
            return INSTANCE;
        }

        /**
         * 防止克隆破坏
         *  重写clone(),直接返回INSTANCE
         * @return Singleton
         * @throws CloneNotSupportedException
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return INSTANCE;
        }

        /**
         * 防止序列化被破坏
         * @return Singleton
         */
        @SuppressWarnings("unused")
        private Object readResolve(){
            return INSTANCE;
        }
    }
}
