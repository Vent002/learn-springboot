package cn.hsmxg1204.test.learn.singleton;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-06-17 13:55
 */
public enum Singleton {
    uniqueInstance;

    public Singleton getUniqueInstance(){
        return uniqueInstance;
    }


}
