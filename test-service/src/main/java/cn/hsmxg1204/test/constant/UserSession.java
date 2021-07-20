package cn.hsmxg1204.test.constant;

import cn.hsmxg1204.test.entity.CurrentUser;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-16 14:48
 */
public class UserSession {
    private static ThreadLocal<CurrentUser> currentUser = new ThreadLocal<>();
    public static CurrentUser getCurrentUser(){
        return currentUser.get();
    }
    public static void setCurrentUser(CurrentUser user){
        currentUser.set(user);
    }
}
