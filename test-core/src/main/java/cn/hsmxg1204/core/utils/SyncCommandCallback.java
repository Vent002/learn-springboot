package cn.hsmxg1204.core.utils;

import io.lettuce.core.api.sync.RedisCommands;

/**
 * TODO
 *
 * @author gxming
 * @description redis 缓存
 * @date 2021-07-12 18:59
 */
@FunctionalInterface
public interface SyncCommandCallback<T> {
    T doInConnection(RedisCommands<String,String> commands);
}
