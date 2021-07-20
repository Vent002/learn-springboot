package cn.hsmxg1204.test.learn.leetcode.medianSortArrays;

/**
 * TODO  已知两个有序数组，找到两个数组合并后的中位数
 *
 * @author gxming
 * @description
 * @date 2021-06-25 8:55
 */
public class SortArrays {
    /**
     * 合并数组找中位数 v1.0
     *
     * 时间复杂度 o(m + n)
     * 空间复杂度 o(m + n)
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return double
     */
    public double findMedianSortedArrays(int[] nums1,int[] nums2){
        int [] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        // 判断数组1
        if(m == 0){
            // 数组长度为偶数 ， 中位数为(n/2 - 1 + n/2) / 2.0
            if(n % 2 == 0){
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            }else{
                return nums2[n / 2];
            }
        }
        // 判断数组2
        if (n == 0){
            if(m % 2 == 0){
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            }else{
                return nums1[m / 2];
            }
        }
        // 合并数组
        int count = 0;
        int i = 0, j = 0;
        while(count != (m + n)){
            if(i == m){
                while(j != n){
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if(j == n){
                while (i != m){
                    nums[count++] = nums1[i++];
                }
                break;
            }
            if(nums1[i] < nums2[j]){
                nums[count++] = nums1[i++];
            }else{
                nums[count++] = nums2[j++];
            }
        }
        // 返回合并数组后的中位数
        if(count % 2 == 0){
            return (nums[count / 2 -1] + nums[count / 2]) / 2.0;
        }else{
            return nums[count / 2];
        }
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysV20(int[] nums1,int[] nums2){
        int m = nums1.length;
        int n = nums2.length;

        int len = m + n;
        int left = -1,right = -1;
        int aStart = 0,bStart = 0;
        for (int i = 0; i < len / 2; i++) {
            left = right;

            if(aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])){
                right = nums1[aStart++];
            }else{
                right = nums2[bStart++];
            }
        }
        if((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

}
