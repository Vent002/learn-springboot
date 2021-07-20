package cn.hsmxg1204.core.cache.interfaces;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-15 13:48
 */
public interface MyCache {
    Object get(String str);
    void put(String str1,Object obj,long var);
    boolean delete(String str);
}
