package cn.hsmxg1204.test.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-06-07 17:12
 */
public class LeetCode {
    /**
     * 1. 两数之和
     *  难度： 简单
     * 给定⼀个数组和⼀个⽬标和，从数组中找两个数字相加等于⽬标和，输出这两个数字的下标。
     *  nums = [2,7,11,15],target = 9,
     *  nums[0] + nums[1] = 2 + 7 = 9
     *  return [0,1]
     */
    public static int[] twoSum(int[] nums,int target) throws IllegalAccessException {
        /**
         * v1.0 双重循环暴力破解
         * 时间复杂度：o(n^2)
         * 空间复杂服：o(1)
         */
//        int []ans = new int[2];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = (i+1); j < nums.length; j++) {
//                if(nums[i] + nums[j] == target){
//                    ans[0] = i;
//                    ans[1] = j;
//                    return ans;
//                }
//            }
//        }
//        return ans;
        /**
         * v2.0 使用hashmap
         * 时间复杂度：o(n)
         * 空间复杂服：o(n)
         */
//        Map<Integer,Integer> map  = new HashMap<>(4);
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i],i);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int sub = target - nums[i];
//            if(map.containsKey(sub) && map.get(sub) != i){
//                return new int[]{i,map.get(sub)};
//            }
//        }
//        throw new IllegalAccessException("No two sum solution!");

        Map<Integer,Integer> map  = new HashMap<>(4);
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if(map.containsKey(sub) && map.get(sub) != i){
                return new int[]{i,map.get(sub)};
            }
            map.put(nums[i],i);
        }
        throw new IllegalAccessException("No two sum solution!");
    }

    public static void main(String[] args) throws IllegalAccessException {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] ints = twoSum(nums, target);
//        Arrays.stream(ints).forEach(System.out::println);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}

class addTwoNumbers{
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTewNums(ListNode pre,ListNode next){
        ListNode dummyHead = new ListNode(0);
        return null;
    }

}
